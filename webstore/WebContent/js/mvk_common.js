window.HomeHeadView = Backbone.View.extend({

	el:  $("#flip-mn")   ,
   
   initialize:function () {
	   
	   this.render();
    },

    events: {    
           	    	//'click #flip-mn': 'toggleCart'
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
	        //////////////////////////////////////
	        var userResponse=obj[0];
	        var userModelObj = new User(userResponse, { view: this });
	        console.log(userModelObj);
	        /////////////////////////////////////////
	       
	        
	        var template=_.template($('#user-list-template').html(),{user:userModelObj});
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
	    /////////////////////////////////////////////////////////////////////////////////
	    var Response=Backbone.Collection.extend ({
		   	 url:'http://localhost:8080/lemon/commerce/cart/view'
		   	 
		    });
		    
		    
		    var responses = new Response();
		    responses.fetch({
		    
		    success: function(response) {
		    	var that=this;
		        var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        
		        //////////////////////////////////////
		    	if(_.isNull(obj)) {
		    		console.log('undefined object');
		    		var that=this;
					 var template=_.template($('#cart_total_template').html(),{orderPrice:undefined});
				 	$('#flip-mn').html(template);
				 	return;
		    	}
		        var orderResponse=obj[0];
		        var orderModelObj = new Order(orderResponse, { view: this });
		        var priceObj=orderModelObj.get('orderPrice');
		        var cartTotal=priceObj.amount;
		       // var commerceItemCollection=orderModelObj.get("commerceItems");
		        var commerceItemCollection = new CommerceItemList(orderModelObj.get("commerceItems"), { view: this });
		        /////////////////////////////////////////
		       var commerceItemCnt=orderModelObj.get("commerceItems").length;
		        
		        var template=_.template($('#cart_total_template').html(),{orderPrice:cartTotal});
		        $('#flip-mn').html(template);
		        if(commerceItemCnt>0){
		        var template=_.template($('#cart_view_home_page_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart-view-homepage').html(template);
		        var template=_.template($('#cart_view_home_page_bill_summary_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart_total_summary').html(template);
		        }
		    }  ,
			error: function (errorResponse) {
				var that=this;
				 var template=_.template($('#cart_total_template').html(),{orderPrice:undefined});
				 $('#flip-mn').html(template);
			}
		    
		    });
		    /////////////////////////////////////////////
		    
		    var Response1=Backbone.Collection.extend ({
		   	 url:'http://localhost:8080/lemon/commerce/catalog/categoriesView'
		   	 
		    });
		    
		    
		    var responses1 = new Response1();
		    responses1.fetch({
		    
		    success: function(response1) {
		    	var that=this;
		        var data = response1.toJSON();
		        var obj=data[0].responseVOs;
		        var catVOs=obj[0].allrootCategories;
		        
		      //  console.log(catVOs);
		        var myCollection = new CategoryCollection(catVOs, { view: this });
		        
		        var template2=_.template($('#category-list-template').html(),{categories:myCollection.models});
		        $('#categorydiv').html(template2);
		        
		        var template3=_.template($('#category-desktop-list-template').html(),{categories:myCollection.models});
		        $('#category_div_desktop').html(template3);
		       
		        ////////////////////////////
		      /*  _.each(myCollection.models,function(category) {
		        	var dept=category.get("displayName");
		        	var aisleCollectionForDept=new aisleCollection(category.get('childCategories'), { view: this });
		        	 
		         _.each(aisleCollectionForDept.models,function(aisle) {
		        	 var aisleName=aisle.get("displayName");
		        	 
		        	 console.log(aisleName);
		        	 var shelfCollectionForAisle=new shelfCollection(aisle.get('childCategories'), { view: this });
			         _.each(shelfCollectionForAisle.models,function(shelf) { 
		        		 var shelfname=shelf.get("displayName");
		        		 console.log(shelfname);
		        	 })
		        	})
		        	
		        });*/
		        /////////////////////////
		        
		    }  
		    
		    });
		    //////////////////////////////////////////////
    },
    toggleCart: function() {
    	
	    $("#panel-mn").slideToggle("slow");
	
   
}

   

    
    
});


window.SecureCheckoutHeadView = Backbone.View.extend({

	el:  $("#flip-mn")   ,
   
   initialize:function () {
	   
	   this.render();
    },

    events: {    
           	    	//'click #flip-mn': 'toggleCart'
    },
    render: function (){
    	
  	  	        var template=_.template($('#secure_checkout_head_template').html(),{});
	        $('#userheader').html(template);
	       
	       
	   
	    /////////////////////////////////////////////////////////////////////////////////
	   
    }
   
  
    
});

window.CheckPostcodeHeadView = Backbone.View.extend({

	el:  $("#flip-mn")   ,
   
   initialize:function () {
	   
	   this.render();
    },

    events: {    
           	    	//'click #flip-mn': 'toggleCart'
    },
    render: function (){
    	
  	  	        var template=_.template($('#check_postcode_head_template').html(),{});
	        $('#userheader').html(template);
	       
	       
	   
	    /////////////////////////////////////////////////////////////////////////////////
	   
    }
   
  
    
});