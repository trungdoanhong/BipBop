require("dotenv").config();

const express = require('express');
const app = express();
const mongoose = require('mongoose');
const bodyParser = require("body-parser");
const cookieParser = require("cookie-parser");

const sessionMidleware = require("./middlewares/session.middleware");
const router = require("./router/router");
const cartRouter = require("./router/cart.router");
const authRoute = require("./router/auth.route");

const multer = require('multer');
const upload = multer();

mongoose.connect(process.env.MONGO_URL);

const port = 8000;

app.use(cookieParser(process.env.SESSION_SECRET));
app.use(upload.array());
app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true
  })
);

app.use(express.static("public"));

app.set("views", "./views");
app.set("view engine", "pug");

app.use("/", router);  
app.use("/auth", authRoute);
app.use("/", cartRouter);

app.listen(port, () => {
    console.log('App listening on port 8000!');
});