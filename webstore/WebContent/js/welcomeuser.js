

window.HomeCategoryView = Backbone.View.extend({

	el:  $("#categorydiv")   ,
   
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
	        var catVOs=obj[0].allrootCategories;
	        
	        console.log(catVOs);
	        var myCollection = new CategoryCollection(catVOs, { view: this });
	        
	        
	       /* _.each(myCollection.models,function(category) {
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
	        var template=_.template($('#category-desktop-list-template_1').html(),{categories:myCollection.models});
	        $('#category_div_desktop_1').html(template);
	       
	    }  //,
		/*error: function (errorResponse) {
			var that=this;
			 var template=_.template($('#user-list-template').html(),{user:undefined});
			 $('#userheader').html(template);
		}*/
	    
	    });
    }

   

    
    
});


window.HomeContentView = Backbone.View.extend({

	//el:  $("#home_page_carousel_outer"),
	
	el:  $('body'),
   
   initialize:function (postCodeSuccess) {
	   
	   this.render(postCodeSuccess);
    },events: {    
           	    	'click #AddToCart': 'addToCart',
           	 	'click #flip-mn': 'toggleCart',
           	 'click #secureCheckout':'secureCheckout',
           	 'click #department':'navigateToCategory',
           	 'click #DecrementItem':'decrementItem',
           	 'click #removeItemFromCart':'removeItemFromCart'
           	
    },
    template: '<a id="open" class="btn">open modal</a>',
    openModal: function() {
        var view = new ModalView();
        var modal = new Backbone.BootstrapModal({
            content: view,
            title: 'modal header',
            animate: true
        }).open(function(){ console.log('clicked OK') });
    },
    render: function (params){
    	
    	var postCodeSuccess=params.postCodeSuccess;
    	
  
	    var template1=_.template($('#homepage_banner_1_template').html(),{});
        $('#home_page_banner_1').html(template1);
      //  var template2=_.template($('#homepage_banner_2_template').html(),{});
        //$('#home_page_banner_2').html(template2);
        
        var template3=_.template($('#below_category_banner_template').html(),{});
        $('#below_category_banner').html(template3);
        var template4=_.template($('#carousel-example-generic_1').html(),{});
        $('#carousel-example-generic').html(template4);
        var template5=_.template($('#carousel-example-generic-2').html(),{});
        $('#carousel-example-generic-two').html(template5);
	    if(postCodeSuccess){
	    	
	    	var view = new ModalView();
	        var modal = new Backbone.BootstrapModal({
	            content: view,
	            title: 'modal header',
	            animate: true
	        }).open(function(){ console.log('clicked OK') });
	    }
	    
	    //return false;
	    
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
           return false;
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
           return false;
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
           
           return false;
    },
    toggleCart: function() {
    	
	    $("#panel-mn").slideToggle("slow");
	
   
},
secureCheckout: function() {
	window.location.replace('secure_checkout.shtml');
},
navigateToCategory: function(e) {
	var id =  $(e.target).attr('catid');
	var catType=$(e.target).attr('catType');
	window.location.replace('category-page.shtml#'+catType+'/'+id);
}
    
    
});


/////////////////////////
var ModalView = Backbone.View.extend({
    tagName: 'p',
    template: 'this is modal content',
    render: function() {
        this.$el.html(_.template($('#modal_template').html()));
        console.log('modal rendered');
        return this;
    }
});




    