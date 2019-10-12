var product = require('../models/products.model');

module.exports.index = async (req, res) => {
    var products = await product.find();
    res.render('../views/index.pug',{
        products: products
    });
};

// module.exports.search =  (req, res) => {
//     let q = req.query.q;
//     console.log(q);
//     let matchedProduct = product.find()
// };

module.exports.createProduct = (req, res) => {
    res.render('../views/products/createproduct.pug');
};

module.exports.postCreateProduct = async (req, res) => {
    try {
        var body = req.body;  
        await product.create(body); 
        res.redirect('/'); 
    } catch (error) {
        // return 
    }
};

module.exports.viewProduct = async (req, res) => { 
    try{
        let id = req.params.id;
        await product.findById(id).exec((error, data) => {
            console.log(data);
            res.render('../views/products/viewproduct.pug',{
                product : data,
            })
        })
    }catch(error){
        console.log("error");
    }
};

module.exports.getUpdateProduct = (req, res) => {
    let id = req.params.id;
    console.log(id);
    res.render('../views/products/updateproduct.pug');
};

module.exports.updateProduct = async (req, res) => {
    try{
        let body = req.body;
        console.log(body);
        await product.updateOne(req.params.product_id, {$set: body});
         res.redirect('/');
    }catch(err){
        console.log('err');
    }
};

module.exports.deleteProduct = async (req, res) => {
    try{
        let id = req.params.id
        await product.deleteOne({_id:id});
        res.redirect('/');
    }catch(error){
        console.log("error");
    }
};