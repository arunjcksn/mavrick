window.RegisterView = Backbone.View.extend({

   
   
   initialize:function () {
        console.log('Initializing Login View');
    },

    events: {    
           	    	'click #login': 'login',
           	    	'click #createAccountButton': 'register'
    },

    render:function () {
        $(this.el).html(this.template());
        return this;
    },
    el: $('body'),
    

    register:function (event) {
    	
    	 
    	 ///////////////////////////////////////////
        event.preventDefault(); // Don't let this button submit the form
        $('.alert-error').hide(); // Hide any errors on a new submit
       
        
        
        var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/user/register'
       	 
        });
        
        var firstName= $('#registerFirstName').val();
        var lastName= $('#registerLasttName').val();
        var email= $('#registerEmail').val();
        var password= $('#registerPassword').val();
        var confirmPassword=$('#registerConfirmPassword').val();
        
        var responses = new Response();
        responses.fetch({data:{firstName:firstName,lastName:lastName,email:email,password:password,confirmPassword:confirmPassword},type:'POST',
        
        success: function(response) {
            var data = response.toJSON();
            var obj=data[0];
            
            console.log(obj.responseVOs);
            window.location.replace('home.shtml');
        }   
        
        });
       
        
      
        
        
    },
    login: function() {
    	window.location.replace('sign-in.shtml');
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



    