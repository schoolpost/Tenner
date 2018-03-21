var express       = require('express'),
    router        = express.Router(),
    geocoder      = require('geocoder'),
    elasticsearch = require('elasticsearch');
    
var client = new elasticsearch.Client({
  host: 'cmput301.softwareprocess.es:8080/',
  log: 'trace'
});

router.get('/', function(request, response){
    response.send("Hello to Tenner's Server!");
});

router.get('/ping', function(request, response){
    client.ping({
      // ping usually has a 3000ms timeout
      requestTimeout: 1000
    }, function (error) {
      if (error) {
        console.trace('elasticsearch cluster is down!');
        response.end('CMPUT 301 Tenner Server Is Down!');
      } else {
        console.log('All is well');
        response.end('CMPUT 301 Tenner Server Is Up!');
      }
    });
});
    
router.get('/getUsers', function(request, response){
    
});

module.exports = router;