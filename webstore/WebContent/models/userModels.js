address= Backbone.Model.extend({
	defaults: {addressId:null,
		  		  nickName: null,
		  address1: null,
		  address2:null,
		  area:null,
		  city:null,
		  postcode:null,
		  phoneNum:null,
		  mobilePhoneNum:null,
		  altPhoneNum:null,
		  email:null
	    }
});

AddressList = Backbone.Collection.extend({
    model: address
});


User = Backbone.Model.extend({

	    
	  defaults: {
		  id: null,
		  firstName: null,
		  lastName: null,
		  email:null,
		  address: new AddressList()
	    },

	    parse: function(response) {
	    	response.address = new AddressList(response.address);
	        return response;
	    }
	});

	
	
	