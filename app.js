var express      = require('express'),
    app          = express(),
    session      = require('express-session'),
    bodyParser   = require("body-parser"),
    admin        = require("firebase-admin");
    
//Configurations-------------------------------------------------------------->

app.use(bodyParser({limit: '1mb'}));
app.use(bodyParser.urlencoded({extended : true}));

app.use(session({
    cookieName: 'session',
    secret: "Secret Louis CMPUT 301",
    resave: false,
    saveUninitialized: false,
    duration: 30 * 60 * 1000,
    activeDuration: 5 * 60 * 1000,
    secure : true
}));

var serviceAccount = require("./private/cmput301-firebase-adminsdk-eadch-7722678525");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://cmput301.firebaseio.com"
});

var routes = require('./routes/routes');

app.use(routes);

var notify       = require("./routes/routes");

// (function(){
//     notify(); 
//     setTimeout(arguments.callee, 10000);
// })();

// var http = require("http");
// setInterval(function() {
//     http.get("http://cmputer301tenner.herokuapp.com/");
//     console.log("pinged");
// }, 30000);

app.listen(process.env.PORT, process.env.IP, function(){
    console.log("Tenner Server Started!");
});