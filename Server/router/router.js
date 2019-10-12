const express = require("express");
const controller = require("../controller/controller");

const router = express.Router();
var validate = require("../middlewares/validate");
router.get("/", controller.index);

// router.get("/product/search", controller.search);

router.get('/viewproduct/:id', controller.viewProduct);

router.get('/updateproduct/:id', controller.getUpdateProduct);

router.post('/updateproduct', controller.updateProduct);

router.get('/delete/:id', controller.deleteProduct);

router.get("/createproduct", controller.createProduct);

router.post("/createproduct", validate.postCreateProduct, controller.postCreateProduct);

module.exports = router;