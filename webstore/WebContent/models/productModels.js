window.Product = Backbone.Model.extend({
    url : function(){
        return './commerce/catalog/displayProduct?productId=' + this.id;
        },
    defaults:{
        "entityName" : "Product"
    },
    initialize : function(){
        console.log('>>> Loaded Product Model');
    }
});


window.ProductCollection = Backbone.Collection.extend({
    model : Product,
    url : '/api/product'
})


var ProductResponse =  Backbone.Collection.extend({
    url:'http://localhost:8080/lemon/commerce/catalog/displayProduct?productId=prd1'
});

