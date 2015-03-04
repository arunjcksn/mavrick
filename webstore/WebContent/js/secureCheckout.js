

///////////////////////////////////////////////////////////////////////////

window.SecureCheckouttView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    events: {    
    	       	'click #forgotPassword': 'forgotPassword',
    	       	'click #addAddress': 'addShippingAddress',
    	       	'click #placeOrder': 'placeOrder',
    	       	'click #ContinueShopping':'continueShopping'
    },
    render: function (){
    	
    	/////////////////////////////////////////////////////////////////////////////////////////
    	 var Response=Backbone.Collection.extend ({
		   	 url:'http://localhost:8080/lemon/commerce/user/view'
		   	 
		    });
		    
		    
		    var responses = new Response();
		    responses.fetch({
		    
		    success: function(response) {
		    	
		        var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        var userObj=obj[0];
		        
		        var userViewModel = new User(userObj, { view: this });
		        var userAddr=obj[0].address;
		        
		        
		        var addresseListCollection = new AddressList(userAddr, { view: this });
		        
		        var template1=_.template($('#secure_checkout_landing_template_section1').html(),{user:userViewModel});
		        $('#checkout_main_section_1').html(template1);
		        
		        var template2=_.template($('#secure_checkout_landing_template_section2').html(),{adresses:addresseListCollection.models});
		        $('#address_list_and_add_address').html(template2);
		        
		        
		     	
		    	
		    }  ,
			error: function (errorResponse) {
				
			}
		    
		    });
    	
    	
    	////////////////////////////////////////////////////////////////////////////////////////
   
        
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
		        var orderResponse=obj[0];
		        var orderModelObj = new Order(orderResponse, { view: this });
		        var priceObj=orderModelObj.get('orderPrice');
		        var cartTotal=priceObj.amount;
		       // var commerceItemCollection=orderModelObj.get("commerceItems");
		        var commerceItemCollection = new CommerceItemList(orderModelObj.get("commerceItems"), { view: this });
		        /////////////////////////////////////////
		       var commerceItemCnt=commerceItemCollection.length;
		        
		        
		        
		        var template2=_.template($('#secure_checkout_landing_template_section3').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#order_shipping_information').html(template2);
		        
		        var template2=_.template($('#secure_checkout_landing_template_order_summary_main').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#secure_checkout_main_order_summary').html(template2);
		        
		        
		        var template=_.template($('#secure_checkout_bill_summary_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#secure_checkout_cart_bill_summary').html(template);
		        var template=_.template($('#secure_checkout_cart_n_delivery_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#secure_checkout_cart_n_delivery_summary').html(template);
		    }  ,
			error: function (errorResponse) {
				var that=this;
				 var template=_.template($('#cart_total_template').html(),{orderPrice:undefined});
				 $('#flip-mn').html(template);
			}
		    
		    });
        
       
        
    },
    toggleCart: function() {
    	
	    $("#panel-mn").slideToggle("slow");   
    },
    forgotPassword: function() {
    	window.location.replace('sign-in.shtml#forgotPassword');  
    },addShippingAddress:function (event) {
    	
      	 
      	 ///////////////////////////////////////////
          event.preventDefault(); // Don't let this button submit the form
          $('.alert-error').hide(); // Hide any errors on a new submit
         
          
          var Response=Backbone.Collection.extend ({
         	 url:'http://localhost:8080/lemon/commerce/user/addAddress'
         	 
          });
          
          var firstName= $('#first_name').val();
          var lastName= $('#last_name').val();
          var address1= $('#address_1').val();
          var address2= $('#address_2').val();
          var city= $('#city').val();
        
          var province= $('#province-territory').val();
          var postal=$('#postal').val();
          var phoneNum=$('#phone').val();
          
          var responses = new Response();
          responses.fetch({data:{email:'testaddr@test.com',nickName:'dummy',address1:address1,address2:address2,area:province,city:city,postcode:postal,phoneNum:phoneNum,mobilePhoneNum:phoneNum,altPhoneNum:phoneNum},type:'POST',
          
          success: function(response) {
       	   
       	   var data = response.toJSON();
   	        var obj=data[0].responseVOs;
   	        var userAddr=obj[0].address;
   	        
   	        
   	        var addresseListCollection = new AddressList(userAddr, { view: this });
   	        
   	        
   	     var template2=_.template($('#secure_checkout_landing_template_section2').html(),{adresses:addresseListCollection.models});
	        $('#address_list_and_add_address').html(template2);
	        document.getElementById("collapseOne").className ="panel-collapse collapse out";
	        document.getElementById("collapseOne").focus();
	        
          }  
          
          });
         
        
        
          
          
      },placeOrder:function (event) {
      	
       	 
       	 ///////////////////////////////////////////
           event.preventDefault(); // Don't let this button submit the form
           $('.alert-error').hide(); // Hide any errors on a new submit
          
           
           var Response=Backbone.Collection.extend ({
          	 url:'http://localhost:8080/lemon/commerce/cart/confirmOrder'
          	 
           });
           
                   
           var responses = new Response();
           responses.fetch({data:{},type:'POST',
           
           success: function(response) {
        	   
        	   var template2=_.template($('#order_confirmation_template').html(),{});
   	        $('#main_content').html(template2);
        	   //window.location.replace('home.shtml');
 	        
           }  
           
           });
          
         
         
           
           
       },
       continueShopping:function (event) {
        	
         	 
         	 ///////////////////////////////////////////
             event.preventDefault(); // Don't let this button submit the form
             $('.alert-error').hide(); // Hide any errors on a new submit
            
             
           
          	  
          	  window.location.replace('home.shtml');
   	        
         
            
           
           
             
             
         }

    
    
});

