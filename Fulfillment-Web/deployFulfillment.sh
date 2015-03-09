#! /bin/bash
# This script will deploy the lemonfulfillment application on server 
#
txtred=$(tput setaf 1)
txtgrn=$(tput setaf 2)
txtrst=$(tput sgr0)

TIME_STAMP=`date +%Y_%m_%d_%H_%M_%S`
#TIME_STAMP=$(date +%s);
echo $TIME_STAMP

#
# startLogging will be used to log the message when command starts executing
#
startLogging() { 
	echo "Starting Task-$1 at :"; date
}

#
# stopLogging will be used to log the message when command completes its execution
#
#
stopLogging(){
	echo "Task-$1 is completed  at :"; date
}

# logSuccess will be used to log success messages
#

logSuccess(){
	echo "${txtgrn}[OK]${txtrst}";
}

# logFailure will be used to log failure messages
#

logFailure(){
	echo "${txtred} [Failure] ${txtrst}";
}

#
# Get the environment
#

echo "Environment type (DEV, IT, UAT, PROD)";
read "env";

cd ..
export SUDO_HOME=`pwd`;

if test "$env" = "DEV" -o "$env" = "IT" -o "$env" = "UAT" -o "$env" = "PROD"
then
	echo "Environment passed as $env";
    export LEMON_HOME=$SUDO_HOME;
	export TOMCAT_SERVER_HOME="$LEMON_HOME/apache-tomcat-7.0.52";
	export LEMON_BUILD_HOME="$LEMON_HOME/build"
	export BACKUP_LOCATION="$LEMON_HOME/backup";# tar backup location
	export PROPERTIES_HOME="$TOMCAT_SERVER_HOME";#Location for .properties file for lemon  application.
	export SOURCE_PROPERTIES_HOME="$LEMON_BUILD_HOME/WEB-INF/classes";
	export PROPERTIES_CLASSPATH_LOCATION="$TOMCAT_SERVER_HOME/webapps/ContentServices/WEB-INF/classes";
	export JAVA_OPTS="-Dlemon.env=$env";
else
	echo "Invalid Environment Type. Exiting.";
exit 1;
fi

startLogging LemonfulfillmentDeployment

if [ -d "$LEMON_HOME/backup" ] 
	then
		echo "$LEMON_HOME/backup exist.....";
	else
		mkdir "$LEMON_HOME/backup" ;
		echo "$LEMON_HOME/backup created.";
	fi

if [ "$?" -ne 0 ]; then
logFailure
exit 1;
else
logSuccess
fi	

cd $LEMON_BUILD_HOME;
FILE="lemonfulfillment.war";
if [ -e $FILE ]
then
	echo "Backup existing war";
	mv lemonfulfillment.war $BACKUP_LOCATION/lemonfulfillment.war_${TIME_STAMP}
else
    echo "$FILE file does not exist";
fi 

# Get the username to use in curl
echo "Please enter your username:";
read "USERNAME";
# Get the password to use in curl
echo "Please enter your password:";
read -s "PASSWORD";

#use the curl command to get the package
#curl from Jenkins

curl -u $USERNAME:$PASSWORD -O -C - 'http://jenkins.url.com/job/url/lastBuild/url$lemonfulfillment/artifact/url/lemonfulfillment/1.0-SNAPSHOT/lemonfulfillment-1.0-SNAPSHOT.war'

#renaming war file
mv lemonfulfillment-1.0-SNAPSHOT.war lemonfulfillment.war

jar xf lemonfulfillment.war

if [ "$?" -ne "0" ]; then
echo 'Downloaded archive is not a valid package. Exiting.';
exit 1;
fi

cd ..

#Create all required directories if it does not exist.
echo "Tomcat home==$TOMCAT_SERVER_HOME";
if [ -d "$TOMCAT_SERVER_HOME" ] 
then
	echo "Tomcat is installed.....";
else
	echo "Please install tomcat then run the script.";
	exit 1;
fi

#
# Stop tomcat if it is running
#
cd $TOMCAT_SERVER_HOME/bin;
./shutdown.sh;
echo "Script waiting for operation to complete. Please wait for 20 seconds";
sleep 20;
echo "Tomcat stopped";

# removing previously exploded  war file
cd $TOMCAT_SERVER_HOME/webapps/
FILE="ContentServices";
        if [ -e $FILE ]
then
	
        cd $TOMCAT_SERVER_HOME/webapps/
        rm -r lemonfulfillment
	rm lemonfulfillment.war
	echo "Removed existing war file";
else
    echo "no exploded war";
fi

echo "Coping ContentServices application war ....";
cd $LEMON_BUILD_HOME
fileCount=$(ls | wc -l);
FILE="lemonfulfillment.war";
if [ -e $FILE ] #"$fileCount" -gt 0
then
	cp lemonfulfillment.war $TOMCAT_SERVER_HOME/webapps;
else
	echo "lemonfulfillment.war does not exist please download the war and run the script  ..";
fi	
 
echo "copy lemonfulfillment properties."

cd $SOURCE_PROPERTIES_HOME ;
FILE="contentservices.properties_$env";
if [ -e $FILE ]  
then
	cp lemonfulfillment.properties_$env $PROPERTIES_HOME/lemonfulfillment.properties_$env;
  	cd $PROPERTIES_HOME ;
  	rm -r lemonfulfillment.properties;
  	mv lemonfulfillment.properties_$env lemonfulfillment.properties;
else
	echo "$FILE file does not exist";
    exit 1;
fi

echo "copying common properties"
cd $SOURCE_PROPERTIES_HOME ;
FILE="commons.properties_$env";
if [ -e $FILE ]
then
        cp commons.properties_$env $PROPERTIES_HOME/commons.properties_$env;
        cd $PROPERTIES_HOME ;
        rm -r commons.properties;
        mv commons.properties_$env commons.properties;
else
        echo "$FILE file does not exist";
    exit 1;
fi

echo "Starting tomcat...."
cd $TOMCAT_SERVER_HOME/bin;
./startup.sh


if [ "$?" -ne 0 ]; then
logFailure
exit 1;
else
logSuccess
echo "Tomcat started";
fi

stopLogging lemonfulfillment

#Script for lemonfulfillment completes.

