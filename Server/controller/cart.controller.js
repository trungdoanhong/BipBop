var product = require("../models/products.model");

module.exports.addToCart = (req, res) => {
    var productID = req.params.productID;
    var sessionID = req.signedCookies.sessionID;
    if(!sessionID){
        res.redirect('/');
        return;
    }

};