
	///////////////////////////

window.CategoryView = Backbone.View.extend({

	el:  $('body')   ,
	categoryId:null,
   
   initialize:function (categoryType,id) {
	   this.categoryId=id;
	   this.render(categoryType,id);
	   
    },
    events: {    
    		
    	'click #flip-mn': 'toggleCart',
    	'click #AddToCart': 'addToCart',
    	'click #secureCheckout':'secureCheckout',
    	'click #secureCheckout':'secureCheckout',
    	'mouseover #mainMenuOuter':'hoverMenu',
    	'click #department':'navigateToCategory',
    	'click #aisleDiv':'toggleAisleSelection',
    	'click #shelf':'listItemsInShelf',
    	'click #deptBrdCmb':'showDepartment',
      	 'click #DecrementItem':'decrementItem',
       	 'click #removeItemFromCart':'removeItemFromCart'
    },
    render: function (params){
   
        
	    
	    /////////////////////////////////////////////////////////
	    //var url = 'http://localhost:8080/lemon/commerce/catalog/categoriesView';
	    //console.log('getting user info... ');
	  
	  console.log(params.catId);
	  console.log(params.catType);
	    
	    var Response1=Backbone.Collection.extend ({
	   	 url:'http://localhost:8080/lemon/commerce/catalog/categoriesView?categoryId='+params.catId
	   	 
	    });
	    
	    
	    var responses1 = new Response1();
	    responses1.fetch({
	    
	    success: function(response1) {
	    	var that=this;
	        var data = response1.toJSON();
	        var obj=data[0].responseVOs;
	        var catVOs=obj[0];
	        var department=null;
	        if(params.catType == 'department'){
	        	department=new category(catVOs,{view:this});
	    
	      //  console.log(catVOs);
	        
	        var template=_.template($('#category_bread_crumb').html(),{deptId:department.get('categoryId'),deptName:department.get('displayName')});
	 	     $('#category_page_breadcrumb').html(template);
	       
	 	    var template4=_.template($('#category_left_pane').html(),{deptModel:department});
	 	     $('#category_page_left_pane').html(template4);
	       
	        var template4=_.template($('#category_view_main').html(),{});
	 	     $('#category_main_content').html(template4);
	       
	        ////////////////////////////
	        
	        	var dept=department.get("displayName");
	        	console.log(dept);
	        	var aisleCollectionForDept=new aisleCollection(department.get('childCategories'), { view: this });
	        	 
	         _.each(aisleCollectionForDept.models,function(aisle) {
	        	 var aisleName=aisle.get("displayName");
	        	 
	        	 console.log(aisleName);
	        	 var shelfCollectionForAisle=new shelfCollection(aisle.get('childCategories'), { view: this });
		         _.each(shelfCollectionForAisle.models,function(shelf) { 
	        		 var shelfname=shelf.get("displayName");
	        		 console.log(shelfname);
	        	 })
	        	
	        	
	        });
	        /////////////////////////
	        }//cattype department check
	        else if(params.catType == 'aisle'){
	        	department=new category(catVOs,{view:this});
	    	    
	  	      //  console.log(catVOs);
	  	        
	  	        var template=_.template($('#category_bread_crumb').html(),{deptId:department.get('categoryId'),deptName:department.get('displayName')});
	  	 	     $('#category_page_breadcrumb').html(template);
	  	       
	  	 	    var template4=_.template($('#category_left_pane').html(),{deptModel:department});
	  	 	     $('#category_page_left_pane').html(template4);
	  	       
	  	        var template4=_.template($('#category_view_main').html(),{});
	  	 	     $('#category_main_content').html(template4);
	        } else if(params.catType == 'shelf'){
	        	department=new category(catVOs,{view:this});
	    	    
		  	      //  console.log(catVOs);
		  	        
		  	        var template=_.template($('#category_bread_crumb').html(),{deptId:department.get('categoryId'),deptName:department.get('displayName')});
		  	 	     $('#category_page_breadcrumb').html(template);
		  	       
		  	 	    var template4=_.template($('#category_left_pane').html(),{deptModel:department});
		  	 	     $('#category_page_left_pane').html(template4);
		  	       
		  	        var template4=_.template($('#category_view_main').html(),{});
		  	 	     $('#category_main_content').html(template4);
		        }
	    }  
	    
	    });
	    ///////////////////////////////////////////////////////////////////////
	   
        
       
        
    },
    toggleCart: function() {
    	
	    $("#panel-mn").slideToggle("slow");   
    }
     ,
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
    			       		  if(document.getElementById("panel-mn")){     
    			       var style=document.getElementById("panel-mn").style.display;
    			       		  }
    			        var template=_.template($('#cart_total_template').html(),{orderPrice:cartTotal});
    			        $('#flip-mn').html(template);
    			        var template=_.template($('#cart_view_home_page_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
    			        $('#cart-view-homepage').html(template);
    			        var template=_.template($('#cart_view_home_page_bill_summary_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:cartTotal,commerceItems:commerceItemCollection.models});
    			        $('#cart_total_summary').html(template);
    			        if(document.getElementById("panel-mn")){     
    			        document.getElementById("panel-mn").style.display=style;
    			        }
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
    	 },
    	 hoverMenu: function (){
    		 jQuery('ul.nav li.dropdown').hover(function() { 
    				jQuery(this).closest('.dropdown-menu').stop(true, true).show(); jQuery(this).addClass('open'); 
    				}, 
    				function() { 
    					jQuery(this).closest('.dropdown-menu').stop(true, true).hide(); jQuery(this).removeClass('open'); 
    					});
    	 },
    	 
    	 navigateToCategory: function() {
    	 	window.location.replace('category-page.shtml');
    	 }
    	 ,
    	 toggleAisleSelection: function(e) {
    		 var trioDivId= $(e.target).attr('trioDivId');
    		 var shelfDivId= $(e.target).attr('shelfDivId');
    		 var aisleCnt=$(e.target).attr('aisleCnt');
    		 var trioClass=document.getElementById(trioDivId).class;
    		 if (trioClass=='tri'+aisleCnt){
    			 document.getElementById(trioDivId).class='tri'+aisleCnt+' toggle';
    			 document.getElementById(trioDivId).className='tri'+aisleCnt+' toggle';
    			 document.getElementById(shelfDivId).style.display='block';
    		 }else{
    			 document.getElementById(trioDivId).class='tri'+aisleCnt;
    			 document.getElementById(trioDivId).className='tri'+aisleCnt;
    			 document.getElementById(shelfDivId).style.display='none'; 
    		 }
    	 }
    	 ,
    	 listItemsInShelf: function(e) {
    		 
    		 var shelfId= $(e.target).attr('shelf_id');
    		 var shelfDisplayName= $(e.target).attr('display_name');
    		 var aisleId=$(e.target).attr('aisleId');
    		 var aisleName=$(e.target).attr('aisleName');
    		 var deptId=$(e.target).attr('deptId');
    		 var deptName=$(e.target).attr('deptName');
    		 
    		 var template=_.template($('#shelf_bread_crumb').html(),{deptId:deptId,deptName:deptName,shelfId:shelfId,shelfName:shelfDisplayName,aisleId:aisleId,aisleName:aisleName});
	 	     $('#category_page_breadcrumb').html(template);
	       
	 	    var template1=_.template($('#shelf_left_pane').html(),{});
	 	     $('#category_page_left_pane').html(template1);
	 	     
    		//  var template4=_.template($('#product_listing_shelf').html(),{});
    		  
 	 	    
 	 	   var Response1=Backbone.Collection.extend ({
  		   	 url:'http://localhost:8080/lemon/commerce/catalog/categoryProducts?categoryId='+30002
  		   	 
  		    });
  		    
  		    
  		    var responses1 = new Response1();
  		    responses1.fetch({
  		    
  		    success: function(response1) {
  		    	var data = response1.toJSON();
		        var obj=data[0].responsePayload;
		        var productListCollection = new ProductCollection(obj, { view: this });
		        
  		    console.log(productListCollection.models);
  		  //console.log(obj);
  		var template4=_.template($('#product_listing_shelf').html(),{products:productListCollection.models});
  		 $('#category_main_content').html(template4);
  		    }});
 	 	   window.location.hash='shelf/'+shelfId;
 	 	   return false;
 	       
    	 },
    	 showDepartment: function(e) {
    		 deptId=$(e.target).attr('deptId');
    		 
    		 
    			//window.location.replace('category-page.shtml#department/'+deptId);
    			
    		 var Response1=Backbone.Collection.extend ({
    		   	 url:'http://localhost:8080/lemon/commerce/catalog/categoriesView?categoryId='+deptId
    		   	 
    		    });
    		    
    		    
    		    var responses1 = new Response1();
    		    responses1.fetch({
    		    
    		    success: function(response1) {
    		    	var that=this;
    		        var data = response1.toJSON();
    		        var obj=data[0].responseVOs;
    		        var catVOs=obj[0];
    		        var department=null;
    		        
    		        	department=new category(catVOs,{view:this});
    		    
    		      //  console.log(catVOs);
    		        
    		        var template=_.template($('#category_bread_crumb').html(),{deptId:department.get('categoryId'),deptName:department.get('displayName')});
    		 	     $('#category_page_breadcrumb').html(template);
    		       
    		 	    var template4=_.template($('#category_left_pane').html(),{deptModel:department});
    		 	     $('#category_page_left_pane').html(template4);
    		       
    		        var template4=_.template($('#category_view_main').html(),{});
    		 	     $('#category_main_content').html(template4);
    		       
    		 	    
    		        
    		        
    		    }  
    		    
    		    });
    		    window.location.hash='department/'+deptId;
    		    return false;
    		    
    	 }

  
});
/////////////////////////////////////////////
var Router1=Backbone.Router.extend({
	routes :{
		 '(:categoryType/:id)': 'viewCategory'
		 
	}
	}
	);
window.HomeHeadView = new HomeHeadView();
	var router=new Router1();
	
	router.on ('route:viewCategory',function(categoryType,id){
		
		window.CategoryView = new CategoryView({catType:categoryType,catId: id});

	});
	Backbone.history.start();
