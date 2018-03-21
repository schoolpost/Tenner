var express       = require('express'),
    router        = express.Router(),
    geocoder      = require('geocoder'),
    elasticsearch = require('elasticsearch');
    
var client = new elasticsearch.Client({
  host: 'cmput301.softwareprocess.es:8080/',
  log: 'trace'
});

router.get('/', function(request, response){
    response.send("Hello, Welcome To Tenner's Server!");
});

router.get('/ping', function(request, response){
    client.ping({
      requestTimeout: 3000
    }, function (error) {
      if (error) {
        //console.trace('Cluster is down!');
        response.send('CMPUT 301 Tenner Server Is Down!');
      } else {
        response.send('CMPUT 301 Tenner Server Is Up!');
      }
    });
});
    
//Users-------------------------------------------------------------->

router.get('/getUsers', function(request, response){
    client.search({
      index: 'tenner',
      type: 'users',
      /*body: {
        query: {
          match: {
            body: ''
          }
        }
      }*/
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        response.send(data);
    }, function (err) {
        console.log(err.message);
        response.send('Error!');
    });
});

router.post('/signUp', function(request, response){
    var user = request.body.user;
    client.search({
      index: 'tenner',
      type: 'users',
      /*body: {
        query: {
          match: {
            body: ''
          }
        }
      }*/
    }).then(function (responseBody) {
        client.search({
            index: 'tenner',
            type: 'users'
        }).then(function (responseBody) {
            var data = responseBody.hits.hits;
            console.log(data);
            response.send(data);
        }, function (err) {
            console.log(err.message);
            response.send('Error!');
        });
        var data = responseBody.hits.hits;
        console.log(data);
        for(var dataObj in data){
            if(user.email == dataObj._source.email){
                return response.send('Error : User Exists!');
            } else {
                //TODO : Write data
                return response.send('Success : User Signed Up!');
            }
        }
    }, function (err) {
        console.log(err.message);
        response.send('Error!');
    });
});

router.post('/getAssignedTasks', function(request, response){
    var userID = request.body.email;
    console.log(userID);
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        response.send(data);
    }, function (err) {
        console.log(err.message);
        response.send('Error!');
    });
});

router.post('/getRequestedTasks', function(request, response){
    var userID = request.body.email;
    console.log(userID);
    
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        response.send(data);
    }, function (err) {
        console.log(err.message);
        response.send('Error!');
    });
});

router.get('/getBids', function(request, response){
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        
        var bidArray = [];
        
        for(var dataObj in data){
            bidArray.push(dataObj._source.bids);
        }
        
        response.send(data);
    }, function (err) {
        console.log(err.message);
        response.send('Error!');
    });
});

module.exports = router;