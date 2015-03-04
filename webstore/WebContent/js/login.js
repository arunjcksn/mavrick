

//////////////////////////////////
window.LoginView = Backbone.View.extend({

   
   
   initialize:function () {
        console.log('Initializing Login View');
    },

    events: {    
           	    	'click #loginButton': 'login',
           	    	'click #createAccountButton': 'register',
           	    	'click #forgotPassword':'loadForgotPassword',
           	    	'click #resetPassword':'resetPassword',
           	    	'mouseover #mainMenuOuter':'hoverMenu',
           	    	'click #department':'navigateToCategory',
           	    	    },

    render:function () {
        $(this.el).html(this.template());
        return this;
    },
    el: $('body'),
    

    login:function (event) {
    	//client side hover functions
    	 
    	 
    	 $(window).bind('scroll', function() {
    		   var navHeight = $( window ).height() - 120;
    				 if ($(window).scrollTop() > navHeight) {
    					 $('#fixed-nav').addClass('fixed');
    				 }
    				 else {
    					 $('#fixed-nav').removeClass('fixed');
    				 }
    			});
    	 
    	 $('.carousel').each(function(){
    	        $(this).carousel({
    	            pause: true,
    	            interval: false
    	        });

    	    });
    	 
    	 $('.js-activated').dropdownHover().dropdown();
    	 
    	 
    	 $('.popover-markup > .trigger').popover({
    		    html : true,

    		    content: function() {
    		      return $(this).parent().find('.content').html();
    		    },
    		    container: 'body',
    		    placement: 'bottom',
    			delay:
    		{ show: 100, hide: 100 }
    		});
    	 
    	 ///////////////////////////////////////////
        event.preventDefault(); // Don't let this button submit the form
        $('.alert-error').hide(); // Hide any errors on a new submit
        var url = 'http://localhost:8080/lemon/commerce/user/login';
        console.log('Loggin in... ');
        var formValues = {
            email: $('#exampleInputEmail1').val(),
            password: $('#exampleInputPassword1').val()
        };
        
        var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/user/login'
       	 
        });
        
        var username= $('#exampleInputEmail1').val();
        var password1= $('#exampleInputPassword1').val();
        
        var responses = new Response();
        responses.fetch({data:{email:username,password:password1},type:'POST',
        
        success: function(response) {
            var data = response.toJSON();
            var obj=data[0];
            
            console.log(obj.responseVOs);
            window.location.replace('home.shtml');
        }   
        
        });
       
        
      
        
        
    },
    register: function() {
    	window.location.replace('create-account.shtml');
    },
   
    loadForgotPassword: function() {
    	var templateMain=_.template($('#forgot_passw_content_template').html(),{});
	       $('#main_content_container').html(templateMain);
    },
   
    resetPassword: function() {
    	  var Response=Backbone.Collection.extend ({
    	       	 url:'http://localhost:8080/lemon/commerce/user/forgotpassword'
    	       	 
    	        });
    	        
    	       
    	        var email=$('#emailForForgotPassw').val();
    	        var responses = new Response();
    	        responses.fetch({data:{email:email},type:'POST',
    	        
    	        success: function(response) {
    	        	var templateMain=_.template($('#main_content_template').html(),{});
    	 	       $('#main_content_container').html(templateMain);
    	        }   
    	        
    	        });
    	
    },
	 hoverMenu: function (){
		 jQuery('ul.nav li.dropdown').hover(function() { 
				jQuery(this).closest('.dropdown-menu').stop(true, true).show(); jQuery(this).addClass('open'); 
				}, 
				function() { 
					jQuery(this).closest('.dropdown-menu').stop(true, true).hide(); jQuery(this).removeClass('open'); 
					});
	 },
	 navigateToCategory: function(e) {
			var id =  $(e.target).attr('catid');
			var catType=$(e.target).attr('catType');
			window.location.replace('category-page.shtml#'+catType+'/'+id);
		}
});

window.HomeHeadView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    render: function (){
    	
  	  
	    $('.alert-error').hide(); // Hide any errors on a new submit
	    var url = 'http://localhost:8080/lemon/commerce/user/view';
	    console.log('getting user info... ');
	  
	    
	    var Response=Backbone.Collection.extend ({
	   	 url:'http://localhost:8080/lemon/commerce/user/view'
	   	 
	    });
	    
	    
	    var responses = new Response();
	    responses.fetch({
	    
	    success: function(response) {
	    	var that=this;
	        var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        if(obj[0]!=null){
	        	
	        	 var userName=obj[0].firstName;
	 	        
	 	        console.log(obj.responseVOs);
	 	        
	 	        var template=_.template($('#user-list-template').html(),{user:userName});
	 	        $('#userheader').html(template);
	        	
	        }else{
	        	var that=this;
				 var template=_.template($('#user-list-template').html(),{user:undefined});
				 $('#userheader').html(template);	
	        }
	       
	    }  ,
		error: function (errorResponse) {
			var that=this;
			 var template=_.template($('#user-list-template').html(),{user:undefined});
			 $('#userheader').html(template);
		}
	    
	    });
    }

   

    
    
});


window.HomeCategoryView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    render: function (){
    	
  	  
	    $('.alert-error').hide(); // Hide any errors on a new submit
	    var url = 'http://localhost:8080/lemon/commerce/catalog/categoriesView';
	    console.log('getting user info... ');
	  
	    
	    var Response=Backbone.Collection.extend ({
	   	 url:'http://localhost:8080/lemon/commerce/catalog/categoriesView'
	   	 
	    });
	    
	    
	    var responses = new Response();
	    responses.fetch({
	    
	    success: function(response) {
	    	var that=this;
	        var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var catVOs=obj[0].categoryVOs;
	        
	        console.log(catVOs);
	        var myCollection = new CategoryCollection(catVOs, { view: this });
	        
	        var template=_.template($('#category-list-template').html(),{categories:myCollection.models});
	        $('#categorydiv').html(template);
	        
	        var template1=_.template($('#category-desktop-list-template').html(),{categories:myCollection.models});
	        $('#category_div_desktop').html(template1);
	       
	        
	    }  //,
		/*error: function (errorResponse) {
			var that=this;
			 var template=_.template($('#user-list-template').html(),{user:undefined});
			 $('#userheader').html(template);
		}*/
	    
	    });
    }

   

    
    
});




window.LoginContentView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    render: function (){
    	
  	  
	    $('.alert-error').hide(); // Hide any errors on a new submit
	    var url = 'http://localhost:8080/lemon/commerce/catalog/categoriesView';
	    console.log('getting user info... ');
	  
	    
	    var Response=Backbone.Collection.extend ({
	   	 url:'http://localhost:8080/lemon/commerce/catalog/categoriesView'
	   	 
	    });
	    
	    
	    var responses = new Response();
	    responses.fetch({
	    
	    success: function(response) {
	    	var that=this;
	        var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var catVOs=obj[0].categoryVOs;
	        
	        console.log(catVOs);
	        var myCollection = new CategoryCollection(catVOs, { view: this });
	        
	       var templateMain=_.template($('#main_content_template').html(),{});
	       $('#main_content_container').html(templateMain);
	        
	    }  //,
		/*error: function (errorResponse) {
			var that=this;
			 var template=_.template($('#user-list-template').html(),{user:undefined});
			 $('#userheader').html(template);
		}*/
	    
	    });
    }

   

    
    
});

window.ForgotPasswordView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    render: function (){
    	
    	var templateMain=_.template($('#forgot_passw_content_template').html(),{});
	       $('#main_content_container').html(templateMain);
	        var template3=_.template($('#below_category_banner_template').html(),{});
	        $('#below_category_banner').html(template3);
	        var template4=_.template($('#carousel-example-generic_1').html(),{});
	        $('#carousel-example-generic').html(template4);
	        var template5=_.template($('#carousel-example-generic-2').html(),{});
	        $('#carousel-example-generic-two').html(template5);
	    
    }

   

    
    
});

function getUserInfo() {
	
   
}
     
    