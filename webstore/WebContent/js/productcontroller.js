var viewHelpers = {
  evaluate: function(type){
    
    return type;
  }
}

window.ProductView = Backbone.View.extend({
		el : ".container.main-content-bg" ,
       
        initialize: function(){
				console.log('>>>Loaded ProductView');
        },
        events: {
           'click #addtocart' : 'addtocart'
            },
        render: function(){

        		_.extend(this.model, viewHelpers);
            
                var data=_.isUndefined(this.model.toJSON().responseVOs[0]) ? '': this.model.toJSON().responseVOs[0];
                
                if(this.model.toJSON().statusCode == 200) {
                    var template = _.template($('#product_details_template').html(),{productdetails:data});
                } else {
                    var template = _.template('<p>Oops! something went wrong');    
                }
         		
         		return this.$el.html(template);
        },
        
        addtocart: function(event){
                var Response=Backbone.Collection.extend ({
                        url:'./commerce/cart/addItem'
                });
                var responses = new Response();
                responses.fetch({
                    data:{skuId:$(event.target).attr('sku_id'),qty:$("#incr4").val()} ,   
                    type:'POST',
                    success: function(response) {
     
                    response =  response.toJSON();
                    var orderJSONObject =  response[0].responseVOs[0];
                    var price=_.isUndefined(orderJSONObject) ? '': orderJSONObject.orderPrice.amount;
                    var orderModel = new Order(orderJSONObject, { view: this });
                    var commerceItems = new CommerceItemList(orderModel.get("commerceItems"), { view: this });
                    var commerceItemCnt=commerceItems.length;

                    var template=_.template($('#cart_total_template').html(),{orderPrice:price});
                    $('#flip-mn').html(template);
                    if (!_.isUndefined($('#panel-mn'))){}
                    var template=_.template($('#cart_view_home_page_template').html(),
                        {commerceItemCnt:commerceItemCnt,cartTotal:price,commerceItems:commerceItems.models});
                    $('#cart-view-homepage').html(template);
                    var template=_.template($('#cart_view_home_page_bill_summary_template').html(),{commerceItemCnt:commerceItemCnt,cartTotal:price,commerceItems:commerceItems.models});
                    $('#cart_total_summary').html(template);


                    console.log(JSON.stringify(response));
                    }
                });

        }
        
});


window.ProductDetailsRouter = Backbone.Router.extend({

    routes: {
        "": "home",
        "product/:id": "productDetails"
    },

    initialize: function () {
        HomeHeadView = new window.HomeHeadView();
        PersSettingsView = new window.PersSettingsView();
        console.log('>>> Loaded ProductDetailsRouter');
    },

    home: function () {
        // Since the home view never changes, we instantiate it and render it only once
       return this;
    },
    productDetails: function (id) {

    	var product = new Product({id: id});
        product.fetch({
            success: function (data) {
			console.log('data to be passed to view' + JSON.stringify(data));
            new ProductView({model: data}).render();
            }
        });
    }

});

var app = new ProductDetailsRouter();
Backbone.history.start();