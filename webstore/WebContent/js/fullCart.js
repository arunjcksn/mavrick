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

///////////////////////////////////////////////////////////////////////////

window.FullCartView = Backbone.View.extend({

	el:  $('body')   ,
   
   initialize:function () {
	   
	   this.render();
    },
    events: {    
    	'click #AddToCart': 'addToCart',
       	'click #flip-mn': 'toggleCart',
       	'click #secureCheckout':'secureCheckout',
      	 'click #DecrementItem':'decrementItem',
       	 'click #removeItemFromCart':'removeItemFromCart'
    },
    render: function (){
   
         //console.log(temmplate7);
         /////////////////////////////////////////////////
	    //$('.alert-error').hide(); // Hide any errors on a new submit
	    //var url = 'http://localhost:8080/lemon/commerce/user/view';
	    //console.log('getting user info... ');
	  
	    
	 
	    
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
	        var catVOs=obj[0].categoryVOs;
	        
	      //  console.log(catVOs);
	        var myCollection = new CategoryCollection(catVOs, { view: this });
	        
	        var template2=_.template($('#category-list-template').html(),{categories:myCollection.models});
	        $('#categorydiv').html(template2);
	        
	        var template3=_.template($('#category-desktop-list-template').html(),{categories:myCollection.models});
	        $('#category_div_desktop').html(template3);
	       
	        
	    }  
	    
	    });
	    ///////////////////////////////////////////////////////////////////////
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
		        var orderResponse=obj[0];
		        var orderModelObj = new Order(orderResponse, { view: this });
		        var priceObj=orderModelObj.get('orderPrice');
		        var cartTotal=priceObj.amount;
		       // var commerceItemCollection=orderModelObj.get("commerceItems");
		        var commerceItemCollection = new CommerceItemList(orderModelObj.get("commerceItems"), { view: this });
		        /////////////////////////////////////////
		       var commerceItemCnt=commerceItemCollection.length;
		        
		        
		        var template=_.template($('#full_cart_view_main_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#full_cart_main_view').html(template);
		        var template=_.template($('#full_cart_view_bill_summary_template_1').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#full_cart_bill_summary_1').html(template);
		        var template=_.template($('#full_cart_view_bill_summary_template_2').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#full_cart_bill_summary_2').html(template);
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
		        var template=_.template($('#full_cart_view_bill_summary_template_1').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#full_cart_bill_summary_1').html(template);
		        var template=_.template($('#full_cart_view_bill_summary_template_2').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
		        $('#full_cart_bill_summary_2').html(template);
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

    
    
});

