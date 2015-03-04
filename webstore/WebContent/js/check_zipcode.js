

///////////////////////////////////////////////////////////////////////////

window.CheckPostCodeView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    events: {    
    	    'click #checkZip':'checkZipCode'  ,
    	    'click #checkZipCode':'render',
    	    'click #notifyMe':'notifyMe'
    },
    render: function (){
    	
    	var template1=_.template($('#checkZipCodeTemplate').html(),{});
 	     $('#main_content').html(template1);
    	
        },
      checkZipCode: function(e){
    	  var zipCode= $('#zipcode').val();
    	  if(zipCode==1000){
    		  window.location.replace('home.shtml#postCodeSuccess');
    	  }else{
    		  var template1=_.template($('#zipCodeNotAvailTemplate').html(),{zipCode:zipCode});
    		  $('#main_content').html(template1);
    	  }
      },
      notifyMe: function (){
      	
      	var template1=_.template($('#emailThankyouTemplate').html(),{});
   	     $('#main_content').html(template1);
      	
          }

    
    
});

