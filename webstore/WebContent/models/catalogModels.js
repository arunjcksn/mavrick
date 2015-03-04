shelf=Backbone.Model.extend({

	 defaults: { categoryId: null,
	  displayName: null,
	  enabled: null,
	  startDate:null,
	  endDate:null,
	  categoryTitle:null
	 }
	});

shelfCollection = Backbone.Collection.extend({ 
	model: shelf,
	initialize: function (models,options) { }
	});


aisle=Backbone.Model.extend({

	 defaults: { categoryId: null,
	  displayName: null,
	  enabled: null,
	  startDate:null,
	  endDate:null,
	  categoryTitle:null,
	  childCategories: new shelfCollection()
	 },parse:function(response){
		 response.childCategories = new shelfCollection(response.childCategories);
	        return response;
	 }
	});

aisleCollection = Backbone.Collection.extend({ 
	model: aisle,
	initialize: function (models,options) { }
	});


category = Backbone.Model.extend({

	 defaults: {categoryId: null,
	  displayName: null,
	  enabled: null,
	  startDate:null,
	  endDate:null,
	  categoryTitle:null,
	  childCategories: new aisleCollection()
	},
    parse: function(response) {
    	response.childCategories = new aisleCollection(response.childCategories);
        return response;
    }
	});

	CategoryCollection = Backbone.Collection.extend({ 
	model: category,
	initialize: function (models,options) { }
	});
	
	/////////////////////////////////////////////////////////////////////////////////
	
	content= Backbone.Model.extend({
		bannerId:null,
		bannerType:null,
		content:null	
		
	});
	
	contentCollection=Backbone.Collection.extend({
		model:content,
		initialize:function(models,options){}
		
		
	});
	
	//////////////////////////////////////////////////////////////////////////////////////////
	product = Backbone.Model.extend({

		 defaults: {skuId: null,
	            productName: null,
	            startDate: null,
	            endDate: null,
	            productDescription: null,
	            productType: null,
	            itemType: null,
	            productBrand: null,
	            isActive: null,
	            isStoreOnly: null,
	            image: null,
	            thumbnail: null,
	            video:null ,
	            featuredProduct: null,
	            basePrice: null,
	            offerPrice: null,
	            specialPackaging: null,
	            packagingDescription: null,
	            keywords: null,
	            salesUom: null,
	            salesValue: null,
	            maxOrderQty: null,
	            additionalInfo: null,
	            relatedProducts: null,
	            offerType: null,
	            lastModifiedDate: null,
	            createDate: null,
	            updateDate: null
		}
		});

		ProductCollection = Backbone.Collection.extend({ 
		model: product,
		initialize: function (models,options) { }
		});
