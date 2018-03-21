var express      = require('express'),
    app          = express(),
    session      = require('express-session'),
    bodyParser   = require("body-parser");
    
//Configurations-------------------------------------------------------------->

app.use(bodyParser.urlencoded({extended : true}));

app.use(session({
    cookieName: 'session',
    secret: "Secret Louis cmput 301",
    resave: false,
    saveUninitialized: false,
    duration: 30 * 60 * 1000,
    activeDuration: 5 * 60 * 1000,
    secure : true
}));

app.get('/', function(request, response){
    response.end('CMPUT 301 Tenner Server Started!'); 
});

app.listen(process.env.PORT, process.env.IP, function(){
    console.log("Tenner Server Started!");
});