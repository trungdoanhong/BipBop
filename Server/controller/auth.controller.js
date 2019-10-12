var product = require("../models/products.model");

module.exports.login = (req, res, next) => {
    res.render('../views/auth/login.pug');
};