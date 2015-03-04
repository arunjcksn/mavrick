
var Router=Backbone.Router.extend({
	routes :{
		  '(:postCodeSuccess)': 'home',
	        'login' : 'login',
	        'logout' : 'logout',
	        'addToCart/:skuId': 'addToCart'
	}
	}
	);
	
	var router=new Router();
	router.on ('route:home',function(postCodeSuccess){
		window.HomeHeadView = new HomeHeadView();
		window.HomeCategoryView = new HomeCategoryView();
		window.HomeContentView = new HomeContentView({postCodeSuccess:postCodeSuccess});
	});
	router.on ('route:login',function(){
		
		window.location.replace('sign-in.shtml');
		
		});
	
	router.on ('route:logout',function () {
    	
        
        $('.alert-error').hide(); // Hide any errors on a new submit
       
        
        var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/user/logout'
       	 
        });
        
         
        var responses = new Response();
        responses.fetch({type:'POST',        
        success: function(response) {
        	window.location.replace('home.shtml');
        }   
        
        });       
      
        
        
    });

	Backbone.history.start();

