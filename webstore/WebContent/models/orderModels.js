OrderPrice = Backbone.Model.extend({
	defaults: {
		  priceId: null,
		  amount: null,
		  adjustment: null
	    }
});

//AddressList = Backbone.Collection.extend({
  //  model: address
//});

commerceItem = Backbone.Model.extend({
	defaults: {
		 commerceItemId: null,
         displayName:  null,
         orderId:  null,
         skuId:  null,
         qty:  null,
         unitPrice:  null,
         priceId:  null,
         itemPriceInfo:new OrderPrice()
	    },
	    
	    parse: function(response) {
	    	response.itemPriceInfo = new OrderPrice(response.itemPriceInfo);
	    	return response;
	    }
});

CommerceItemList = Backbone.Collection.extend({
    model: commerceItem
});


Order = Backbone.Model.extend({

	    
	  defaults: {
		  orderId: null,
		  userId: null,
		  lastName: null,
		  state:null,
		  orderPrice: new OrderPrice(),
		  commerceItems: new CommerceItemList()
	    },

	    parse: function(response) {
	    	response.orderPrice = new OrderPrice(response.orderPrice);
	    	response.commerceItems = new CommerceItemList(response.commerceItems);
	        return response;
	    }
	});

	OrderCollection = Backbone.Collection.extend({ 
	model: Order,
	initialize: function (models,options) { }
	});
	
	