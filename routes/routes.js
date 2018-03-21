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
    });
});

router.get('/getTasks', function(request, response){
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        response.send(data);
    }, function (err) {
        console.log(err.message);
    });
});

router.get('/getBids', function(request, response){
    client.search({
      index: 'tenner',
      type: 'bids'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        response.send(data);
    }, function (err) {
        console.log(err.message);
    });
});

router.get('/getTasks', function(request, response){
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        response.send(data);
    }, function (err) {
        console.log(err.message);
    });
});

module.exports = router;