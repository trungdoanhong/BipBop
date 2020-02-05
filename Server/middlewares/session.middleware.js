var product = require('../models/products.model');
var shortid = require('shortid');
module.exports = (req, res, next) => {
    if(!req.signedCookies.sessionID){
        var sessionID = shortid.generate();
        res.cookie('sessionID', sessionID,{
            signed: true
        });
        console.log(req.signedCookies);
    }
    next();
}

