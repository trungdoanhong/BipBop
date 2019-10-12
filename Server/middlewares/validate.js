module.exports.postCreateProduct = (req, res, next) => {
    var error = [];
        if(!req.body.name){
            error.push("name is required !");
        }
        if(!req.body.price){
            error.push("price id required !");
        }
        if(error.length){
            res.render('../views/products/createproduct.pug',{
                error : error,
                values : req.body
            });
            return;
        }
        res.locals.success = true;
        next();
}