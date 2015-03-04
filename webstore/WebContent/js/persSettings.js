//////////////////////////////////////////////////////////////////////////////////// 

$(document).ready(function(){
	   $(window).bind('scroll', function() {
	   var navHeight = $( window ).height() - 120;
			 if ($(window).scrollTop() > navHeight) {
				 $('#fixed-nav').addClass('fixed');
			 }
			 else {
				 $('#fixed-nav').removeClass('fixed');
			 }
		});
	});

  $(document).on('click', '#run', function(e) {
    e.preventDefault();
    $('#simple-example-table').stacktable({hideOriginal:true});
    $(this).replaceWith('<span>ran</span>');
  });
  $('#responsive-example-table').stacktable({myClass:'stacktable small-only'});
  $('#agenda-example').stackcolumns({myClass:'stacktable small-only' });

$(document).ready(function() {
    $('.carousel').each(function(){
        $(this).carousel({
            pause: true,
            interval: false
        });
    });
});

    // very simple to use!
    $(document).ready(function() {
      $('.js-activated').dropdownHover().dropdown();
    });

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

$(document).ready(function(){
  $("#flip-mn").click(function(){
    $("#panel-mn").slideToggle("slow");
  });
});
////////////////////////////////////////////////////////////////////////
var Router=Backbone.Router.extend({
	routes :{
		    'address' : 'addressView',
	        'orderhistory' : 'orderHistView',
	        'persnlSettings':'persnlSettings'
	}
	}
	);

var router=new Router();
router.on ('route:addressView',function(){
	  var template=_.template($('#breadcrumb-template_address').html(),{});
      $('#bredcrumb').html(template);
      
      var template1=_.template($('#my_account_navigation_address').html(),{});
      $('#myAccountNavigation').html(template1);
});
router.on ('route:orderHistView',function(){
	
	var template=_.template($('#breadcrumb-template_order_history').html(),{});
    $('#bredcrumb').html(template);
    
    var template1=_.template($('#my_account_navigation_order_history').html(),{});
    $('#myAccountNavigation').html(template1);
	
	});

router.on ('route:persnlSettings',function(){
	
	 var template=_.template($('#breadcrumb-template_persnl_settings').html(),{});
     $('#bredcrumb').html(template);
     
     var template1=_.template($('#my_account_navigation_persnl_settings').html(),{});
     $('#myAccountNavigation').html(template1);
	
	});
    
///////////////////////////////////////////////////////////////////////////

window.PersSettingsView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    events: {    
    		'click #orderHistLink': 'loadOrderHist',
    	'click #addressLink': 'loadAddress',
    	'click #persSettings': 'loadPersSettings',
    	'click #addAddressLink': 'loadAddAddressPage',
    	'click #addAddress': 'addShippingAddress',
    	'click #loadEditAddress':'loadEditShippingAddress',
    	'click #saveEditedAddress':'editShippingAddress',
    	'click #cancelEditAddress':'loadAddress',
    	'click #deleteAddress':'deleteAddress',
    	'click #flip-mn': 'toggleCart',
    	'click #viewOrderDetail': 'viewOrderDetails',
    	'click #editPersDetails': 'editPersDetails',
    	'click #AddToCart': 'addToCart',
    	'click #secureCheckout':'secureCheckout',
    	'click #secureCheckout':'secureCheckout',
    	'mouseover #mainMenuOuter':'hoverMenu',
    	'click #department':'navigateToCategory',
    	'click #saveEditPersnlInfo':'saveEditedPersnlInfo',
    	'click #resetPassword':'resetPassword',
      	 'click #DecrementItem':'decrementItem',
       	 'click #removeItemFromCart':'removeItemFromCart'
    },
    render: function (){
   
         //console.log(temmplate7);
         /////////////////////////////////////////////////
	    //$('.alert-error').hide(); // Hide any errors on a new submit
	    //var url = 'http://localhost:8080/lemon/commerce/user/view';
	    //console.log('getting user info... ');
	  
	    
	    var Response=Backbone.Collection.extend ({
	   	 url:'http://localhost:8080/lemon/commerce/user/view'
	   	 
	    });
	    
	    
	    var responses = new Response();
	    responses.fetch({
	    
	    success: function(response) {
	    	var that=this;
	        var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var userObj=obj[0];
	        
	        var userViewModel = new User(userObj, { view: this });
	        
	        
	        
	      
	    	 var template4=_.template($('#breadcrumb-template_persnl_settings').html(),{});
	 	    var template5=_.template($('#my_account_navigation_persnl_settings').html(),{});
	 	    var template6=_.template($('#my_account_persnl_settings_main').html(),{user:userViewModel});
	 	     	    
	 	    var temmplate7=template4+template5+template6;
	 	   $('#myAccountMainDiv').html(temmplate7);
	 	   
	 	  var template8=_.template($('#my_account_pers_settings_view').html(),{user:userViewModel});
	 	 $('#persnlDetailsMain').html(template8);
	 	 
	    }  ,
		error: function (errorResponse) {
			var that=this;
			 var template=_.template($('#user-list-template').html(),{user:undefined});
			 $('#userheader').html(template);
		}
	    
	    });
	    
	    /////////////////////////////////////////////////////////
	    //var url = 'http://localhost:8080/lemon/commerce/catalog/categories';
	    //console.log('getting user info... ');
	  
	    
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
	       
	        
	    }  
	    
	    });
	    ///////////////////////////////////////////////////////////////////////
	   
        
       
        
    },
    toggleCart: function() {
    	
	    $("#panel-mn").slideToggle("slow");   
    },
    loadOrderHist: function() {
    	
    	////Gurucharan Order history Changes starts
    	var Response=Backbone.Collection.extend ({
   	   	 url:'http://localhost:8080/lemon/commerce/cart/getOrders?profileId=400'
   	   	 
   	    });	
    	
    	var responses=new Response();
    	
    	responses.fetch({
		    
		    success: function(response) {
		    	
		        var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        var userOrder=obj[0].Order;
		        
		        
		        var orderList = new OrderCollection(userOrder, { view: this });
		        
		        
		        var template4=_.template($('#breadcrumb-template_order_history').html(),{});
		  	    var template5=_.template($('#my_account_navigation_order_history').html(),{});
		  	  var template6=_.template($('#my_account_order_history_main').html(),{orders:orderList.models});
		  	    
		  	    var temmplate7=template4+template5+template6;
		  	   $('#myAccountMainDiv').html(temmplate7);
		     	
		    	
		    }  ,
			error: function (errorResponse) {
				
			}
		    
		    });
    	////Gurucharan Order history changes ends
    	 	 
    },
    editPersDetails: function(){
    	
    	 var Response=Backbone.Collection.extend ({
    	   	 url:'http://localhost:8080/lemon/commerce/user/view'
    	   	 
    	    });
    	    
    	    
    	    var responses = new Response();
    	    responses.fetch({
    	    
    	    success: function(response) {
    	    	var that=this;
    	        var data = response.toJSON();
    	        var obj=data[0].responseVOs;
    	        var userObj=obj[0];
    	        
    	        var userViewModel = new User(userObj, { view: this });
    	        
    	    	
    	    	var template8=_.template($('#my_account_pers_settings_edit').html(),{user:userViewModel});
    		 	 $('#persnlDetailsMain').html(template8);
    		 	 
    	    }  ,
    		error: function (errorResponse) {
    			var that=this;
    			 var template=_.template($('#user-list-template').html(),{user:undefined});
    			 $('#userheader').html(template);
    		}
    	    
    	    });
    	    

    },
    loadAddress: function() {
    	
	    var Response=Backbone.Collection.extend ({
		   	 url:'http://localhost:8080/lemon/commerce/user/view'
		   	 
		    });
		    
		    
		    var responses = new Response();
		    responses.fetch({
		    
		    success: function(response) {
		    	
		        var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        var userAddr=obj[0].address;
var userObj=obj[0];
    	        
    	        var userViewModel = new User(userObj, { view: this });
    	        
		        var addressss=userViewModel.get("address");
		        var addresseListCollection = new AddressList(userViewModel.get("address"), { view: this });
		        
		        
		        var template4=_.template($('#breadcrumb-template_address').html(),{});
		  	    var template5=_.template($('#my_account_navigation_address').html(),{});
		  	  var template6=_.template($('#my_account_address_main').html(),{adresses:addresseListCollection.models});
		  	    
		  	    var temmplate7=template4+template5+template6;
		  	   $('#myAccountMainDiv').html(temmplate7);
		     	
		    	
		    }  ,
			error: function (errorResponse) {
				
			}
		    
		    });
        
        
       
    },
    loadPersSettings: function() {
    	
    	  var Response=Backbone.Collection.extend ({
    		   	 url:'http://localhost:8080/lemon/commerce/user/view'
    		   	 
    		    });
    		    
    		    
    		    var responses = new Response();
    		    responses.fetch({
    		    
    		    success: function(response) {
    		    	var that=this;
    		        var data = response.toJSON();
    		        var obj=data[0].responseVOs;
    		        var userObj=obj[0];
    		        
    		        var userViewModel = new User(userObj, { view: this });
    		        
    		        var template4=_.template($('#breadcrumb-template_persnl_settings').html(),{});
    		 	    var template5=_.template($('#my_account_navigation_persnl_settings').html(),{});
    		 	    var template6=_.template($('#my_account_persnl_settings_main').html(),{user:userViewModel});
    		 	    
    		 	    var temmplate7=template4+template5+template6;
    		 	   $('#myAccountMainDiv').html(temmplate7);
    		 	  var template8=_.template($('#my_account_pers_settings_view').html(),{user:userViewModel});
    			 	 $('#persnlDetailsMain').html(template8);
    		    }  ,
    			error: function (errorResponse) {
    				var that=this;
    				 var template=_.template($('#user-list-template').html(),{user:undefined});
    				 $('#userheader').html(template);
    			}
    		    
    		    });
    },
    loadAddAddressPage: function() {
    	
	    var Response=Backbone.Collection.extend ({
		   	 url:'http://localhost:8080/lemon/commerce/user/view'
		   	 
		    });
		    
		    
		    var responses = new Response();
		    responses.fetch({
		    
		    success: function(response) {
		    	var that=this;
		        var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        var userAddr=obj[0].address;
		        
		        
		        var addresseListCollection = new AddressList(userAddr, { view: this });
		        
		        
		        var template4=_.template($('#breadcrumb_template_add_address').html(),{});
		  	    var template5=_.template($('#my_account_navigation_address').html(),{});
		  	  var template6=_.template($('#my_account_add_address_main').html(),{});
		  	    
		  	    var temmplate7=template4+template5+template6;
		  	   $('#myAccountMainDiv').html(temmplate7);
		     	
		    	
		    }  ,
			error: function (errorResponse) {
				
			}
		    
		    });
        
        
       
    },
    
    addShippingAddress:function (event) {
    	
   	 
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
	        
	        
	        var template4=_.template($('#breadcrumb-template_address').html(),{});
	  	    var template5=_.template($('#my_account_navigation_address').html(),{});
	  	  var template6=_.template($('#my_account_address_main').html(),{adresses:addresseListCollection.models});
	  	    
	  	    var temmplate7=template4+template5+template6;
	  	   $('#myAccountMainDiv').html(temmplate7);
       }  
       
       });
      
     
     
       
       
   },
   
   loadEditShippingAddress:function (event) {
   	
  	 
  	 ///////////////////////////////////////////
      event.preventDefault(); // Don't let this button submit the form
      $('.alert-error').hide(); // Hide any errors on a new submit
    
      var Response=Backbone.Collection.extend ({
     	 url:'http://localhost:8080/lemon/commerce/user/view'
     	 
      });
      
      var addressId=$(event.target).attr('addressId');
      
      var responses = new Response();
      responses.fetch({
      
      success: function(response) {
   	   
   	   var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var userAddr=obj[0].address;
	        var addressToEdit;
	        
	        var addresseListCollection = new AddressList(userAddr, { view: this });
	        
	        addresseListCollection.each(function(address) {
		           if(address.get("addressId")==addressId){
		        	   addressToEdit=address;
		           }
		        });
	        
	        var template4=_.template($('#breadcrumb_template_edit_address').html(),{});
	  	    var template5=_.template($('#my_account_navigation_address').html(),{});
	  	  var template6=_.template($('#my_account_edit_address_main').html(),{address:addressToEdit});
	  	    
	  	    var temmplate7=template4+template5+template6;
	  	   $('#myAccountMainDiv').html(temmplate7);
      }  
      
      });
     
    
    
      
      
  },
  
  editShippingAddress:function (event) {
  	
 	 
 	 ///////////////////////////////////////////
     event.preventDefault(); // Don't let this button submit the form
     $('.alert-error').hide(); // Hide any errors on a new submit
     
     var Response=Backbone.Collection.extend ({
    	 url:'http://localhost:8080/lemon/commerce/user/editAddress'
    	 
     });
     var addressId=$(event.target).attr('addressId');
     var firstName= $('#first_name_edit_address').val();
     var lastName= $('#last_name_edit_address').val();
     var address1= $('#address1_edit_address').val();
     var address2= $('#address2_edit_address').val();
     var city= $('#city_edit_address').val();
   
     var province= $('#province-territory_edit_address').val();
     var postal=$('#postal_edit_address').val();
     var phoneNum=$('#phone_edit_address').val();
     
     var responses = new Response();
     responses.fetch({data:{addressId:addressId,email:'testaddr@test.com',nickName:'dummy',address1:address1,address2:address2,area:province,city:city,postcode:postal,phoneNum:phoneNum,mobilePhoneNum:phoneNum,altPhoneNum:phoneNum},type:'POST',
     
     success: function(response) {
  	   
  	   var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var userAddr=obj[0].address;
	        
	        
	        var addresseListCollection = new AddressList(userAddr, { view: this });
	        
	        
	        var template4=_.template($('#breadcrumb-template_address').html(),{});
	  	    var template5=_.template($('#my_account_navigation_address').html(),{});
	  	  var template6=_.template($('#my_account_address_main').html(),{adresses:addresseListCollection.models});
	  	    
	  	    var temmplate7=template4+template5+template6;
	  	   $('#myAccountMainDiv').html(temmplate7);
     }  
     
     });
    
   
   
     
     
 }
,
  
deleteAddress:function (event) {
  	
 	 
 	 ///////////////////////////////////////////
     event.preventDefault(); // Don't let this button submit the form
     $('.alert-error').hide(); // Hide any errors on a new submit
     
     var Response=Backbone.Collection.extend ({
    	 url:'http://localhost:8080/lemon/commerce/user/deleteAddress'
    	 
     });
     var addressId=$(event.target).attr('addressId');
     
     var responses = new Response();
     responses.fetch({data:{addressId:addressId},type:'POST',
     
     success: function(response) {
  	   
  	   var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var userAddr=obj[0].address;
	        
	        
	        var addresseListCollection = new AddressList(userAddr, { view: this });
	        
	        
	        var template4=_.template($('#breadcrumb-template_address').html(),{});
	  	    var template5=_.template($('#my_account_navigation_address').html(),{});
	  	  var template6=_.template($('#my_account_address_main').html(),{adresses:addresseListCollection.models});
	  	    
	  	    var temmplate7=template4+template5+template6;
	  	   $('#myAccountMainDiv').html(temmplate7);
     }  
     
     });
    
   
   
     
     
 }
,

viewOrderDetails:function (event) {
  	
 	 
 	 ///////////////////////////////////////////
     event.preventDefault(); // Don't let this button submit the form
     $('.alert-error').hide(); // Hide any errors on a new submit
     
     var Response=Backbone.Collection.extend ({
    	 url:'http://localhost:8080/lemon/commerce/cart/view'
    	 
     });
     var orderId=$(event.target).attr('order_id');
     console.log(orderId);
     var responses = new Response();
     responses.fetch({     
     success: function(response) {
  	   
  	   var data = response.toJSON();
	       
	        
  	 var template4=_.template($('#breadcrumb-template_order_history_order_details').html(),{});
	    var template5=_.template($('#my_account_navigation_order_history').html(),{});
	    var template6=_.template($('#my_account_order_history_order_details').html(),{});
	  	    
	  	    var temmplate7=template4+template5+template6;
	  	   $('#myAccountMainDiv').html(temmplate7);
     }  
     
     });
    
   
   
     
     
 },
 addToCart: function(e) {
 	var item= $(e.target);
 	var id =  $(e.target).attr('sku_id');
 	console.log(id);
 	
     var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/cart/addItem'
       	 
        });
        
        var skuId= id;
        var qty=1;
        
        var responses = new Response();
        responses.fetch({data:{skuId:skuId,qty:qty},type:'POST',
        
        success: function(response) {
     	   var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        
		        //////////////////////////////////////
		        var orderResponse=obj[0];
		        var orderModelObj = new Order(orderResponse, { view: this });
		        var priceObj=orderModelObj.get('orderPrice');
		        var cartTotal=priceObj.amount;
		        /////////////////////////////////////////
		        var commerceItemCollection = new CommerceItemList(orderModelObj.get("commerceItems"), { view: this });
		        /////////////////////////////////////////
		       var commerceItemCnt=commerceItemCollection.length;
		       		       
		       var style=document.getElementById("panel-mn").style.display;
		        var template=_.template($('#cart_total_template').html(),{orderPrice:cartTotal});
		        $('#flip-mn').html(template);
		        var template=_.template($('#cart_view_home_page_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart-view-homepage').html(template);
		        var template=_.template($('#cart_view_home_page_bill_summary_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart_total_summary').html(template);
		        document.getElementById("panel-mn").style.display=style;
        }   
        
        });
 },
 decrementItem: function(e) {
 	var item= $(e.target);
 	var id =  $(e.target).attr('sku_id');
 	var commerceItemid=$(e.target).attr('commerce_item_id');
 	console.log(id);
 	
     var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/cart/updateItem'
       	 
        });
        
        var skuId= id;
        var qty=1;
        
        var responses = new Response();
        responses.fetch({data:{commerceItemId:skuId,qty:qty,quantityFlag:0},type:'POST',
        
        success: function(response) {
     	   var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        
		        //////////////////////////////////////
		        var orderResponse=obj[0];
		        var orderModelObj = new Order(orderResponse, { view: this });
		        var priceObj=orderModelObj.get('orderPrice');
		        var cartTotal=priceObj.amount;
		        /////////////////////////////////////////
		        var commerceItemCollection = new CommerceItemList(orderModelObj.get("commerceItems"), { view: this });
		        /////////////////////////////////////////
		       var commerceItemCnt=commerceItemCollection.length;
		       		       
		       // var minicartStyle= $('#flip-mn').html(template);
		       var miniCart=document.getElementById("panel-mn");
		       var style;
		       if(miniCart!=null){
		        style=miniCart.style.display;
        }
		        var template=_.template($('#cart_total_template').html(),{orderPrice:cartTotal});
		        $('#flip-mn').html(template);
		        var template=_.template($('#cart_view_home_page_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart-view-homepage').html(template);
		        var template=_.template($('#cart_view_home_page_bill_summary_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart_total_summary').html(template);
		        if(miniCart!=null){
		        document.getElementById("panel-mn").style.display=style;
		        }
        }   
        
        });
 },
 removeItemFromCart: function(e) {
 	var item= $(e.target);
 	var id =  $(e.target).attr('sku_id');
 	var commerceItemid=$(e.target).attr('commerce_item_id');
 	console.log(id);
 	
     var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/cart/removeItem'
       	 
        });
        
        var skuId= id;
        var qty=1;
        
        var responses = new Response();
        responses.fetch({data:{commerceItemId:commerceItemid},type:'POST',
        
        success: function(response) {
     	   var data = response.toJSON();
		        var obj=data[0].responseVOs;
		        
		        //////////////////////////////////////
		        var orderResponse=obj[0];
		        var orderModelObj = new Order(orderResponse, { view: this });
		        var priceObj=orderModelObj.get('orderPrice');
		        var cartTotal=priceObj.amount;
		        /////////////////////////////////////////
		        var commerceItemCollection = new CommerceItemList(orderModelObj.get("commerceItems"), { view: this });
		        /////////////////////////////////////////
		       var commerceItemCnt=commerceItemCollection.length;
		       		       
		       // var minicartStyle= $('#flip-mn').html(template);
		       var miniCart=document.getElementById("panel-mn");
		       var style;
		       if(miniCart!=null){
		        style=miniCart.style.display;
        }
		        var template=_.template($('#cart_total_template').html(),{orderPrice:cartTotal});
		        $('#flip-mn').html(template);
		        var template=_.template($('#cart_view_home_page_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart-view-homepage').html(template);
		        var template=_.template($('#cart_view_home_page_bill_summary_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#cart_total_summary').html(template);
		        if(miniCart!=null){
		        document.getElementById("panel-mn").style.display=style;
		        }
        }   
        
        });
 }
 ,
 secureCheckout: function() {
 	    	window.location.replace('secure_checkout.shtml');
 }
 ,
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
 },
 resetPassword: function(e) {
	 var password= $('#new-password').val();
     var resetpassword= $('#confirm-password').val();
     var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/user/resetpassword'
       	 
        });
     
     var responses = new Response();
     responses.fetch({data:{password:password,resetpassword:resetpassword},type:'POST',
     
     success: function(response) {
     	var that=this;
	        var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var userObj=obj[0];
	        
     }   
     
     });
	 },
 saveEditedPersnlInfo: function() {
	 var Response=Backbone.Collection.extend ({
       	 url:'http://localhost:8080/lemon/commerce/user/editPersonalInfo'
       	 
        });
        
        var firstName= $('#edit_first-name').val();
        var lastName= $('#edit_last-name').val();
        var email= $('#edit_email').val();
        var id=$('#edit_id').val();
        
        var responses = new Response();
        responses.fetch({data:{firstName:firstName,lastName:lastName,email:email,id:id},type:'POST',
        
        success: function(response) {
        	var that=this;
	        var data = response.toJSON();
	        var obj=data[0].responseVOs;
	        var userObj=obj[0];
	        
	        var userViewModel = new User(userObj, { view: this });
	        
	        
	        
	      
	    	 var template4=_.template($('#breadcrumb-template_persnl_settings').html(),{});
	 	    var template5=_.template($('#my_account_navigation_persnl_settings').html(),{});
	 	    var template6=_.template($('#my_account_persnl_settings_main').html(),{user:userViewModel});
	 	     	    
	 	    var temmplate7=template4+template5+template6;
	 	   $('#myAccountMainDiv').html(temmplate7);
	 	   
	 	  var template8=_.template($('#my_account_pers_settings_view').html(),{user:userViewModel});
	 	 $('#persnlDetailsMain').html(template8);
        }   
        
        });
       
	 }
 
 
    
    
});

