// https://en.wikipedia.org/wiki/Evaluation_strategy#Call_by_sharing

methods: {                                 //
    addToCart: function() {                  //
        this.cart.push( this.product.id );     //
    }                                        //
}                                          //