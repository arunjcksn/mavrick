import java.util.HashMap;
import java.util.Map;

import java.util.regex.*;

public class Test {
	
	public static void main (String[] args){
		args[0]=" \"\\d*\" ab34ef";
				
		Pattern p = Pattern.compile(args[0]);
	    Matcher m = p.matcher(args[1]);
	    boolean b = false;
	    while(b = m.find()) {
	      System.out.print(m.start() + m.group());
	    }
		
	}
}