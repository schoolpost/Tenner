var express       = require('express'),
    router        = express.Router(),
    geocoder      = require('geocoder'),
    elasticsearch = require('elasticsearch');
    
var client = new elasticsearch.Client({
  host: 'cmput301.softwareprocess.es:8080/',
  log: 'trace'
});

router.get('/', function(request, response){
    return response.send("Hello, Welcome To Tenner's Server!");
});

router.get('/ping', function(request, response){
    client.ping({
      requestTimeout: 10000
    }, function (error) {
        if (error) {
            //console.trace('Cluster is down!');
            return response.send({'Error' : 'CMPUT 301 Tenner Server Is Down!'});
        } else {
            return response.send({'Success' : 'CMPUT 301 Tenner Server Is Up!'});
      }
    });
});
    
//Users-------------------------------------------------------------->

router.get('/getAllUsers', function(request, response){
    client.search({
      index: 'tenner',
      type: 'users'
      /*body: {
        query: {
          match: {
            body: ''
          }
        }
      }*/
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        return response.send(data);
    }, function (err) {
        console.log(err.message);
        return response.send({'Error' : 'At /getAllUsers ' + err.message});
    });
});

router.post('/signUpUser', function(request, response){
    var user = request.body.user;
    console.log('lol');
    console.log(JSON.parse(user.email));
    console.log(JSON.parse(user).email);
    client.search({
      index: 'tenner',
      type: 'users'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        
        for(var dataObj in data){
            if(user.email == dataObj._source.email){
                return response.send({'Error' : 'At /signUpUser User Exists!'});
            }
        }
        //TODO : Write data
        return response.send({'Success' : 'User Signed Up!'});
        
    }, function (err) {
        console.log(err.message);
        return response.send({'Error' : 'At /signUp' + err.message});
    });
});

router.post('/updateUser', function(request, response){
    var user = request.body.user;
    
    client.search({
      index: 'tenner',
      type: 'users'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        
        for(var dataObj in data){
            if(user.email == dataObj._source.email){
                //TODO : Write data
                return response.send(JSON.stringify('Success : User Updated!'));
            }
        }
        return response.send(JSON.stringify('Error at /updateUser : User not found!!'));
        
    }, function (err) {
        console.log(err.message);
        return response.send('Error at /updateUser : ' + err.message);
    });
});

//Tasks-------------------------------------------------------------->

router.post('/getAssignedTasks', function(request, response){
    var userID = request.body.email;
    
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        
        var assignedTaskArray = [];
        for(var dataObj in data){
            if(dataObj._source.provider == userID){
                assignedTaskArray.push(dataObj);
            }
        }
        return response.send(assignedTaskArray);
    }, function (err) {
        console.log(err.message);
        return response.send('Error at /getAssignedTasks : ' + err.message);
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
        
        var assignedTaskArray = [];
        for(var dataObj in data){
            if(dataObj._source.provider != userID){
                assignedTaskArray.push(dataObj);
            }
        }
        return response.send(assignedTaskArray);
    }, function (err) {
        console.log(err.message);
        return response.send('Error at /getRequestedTasks : ' + err.message);
    });
});

//Bids-------------------------------------------------------------->

router.get('/getMyBids', function(request, response){
    var userID = request.body.email;
    
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        
        var bidArray = [];
        
        for(var dataObj in data){
            if(dataObj._source.provider != userID){
                bidArray.push(dataObj._source.bids);
            }
        }
        
        response.send(data);
    }, function (err) {
        console.log(err.message);
        response.send('Error!');
    });
});

router.get('/getOtherBids', function(request, response){
    var userID = request.body.email;
    
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        
        var bidArray = [];
        
        for(var dataObj in data){
            if(dataObj._source.provider == userID){
                bidArray.push(dataObj._source.bids);
            }
        }
        response.send(data);
    }, function (err) {
        console.log(err.message);
        response.send('Error!');
    });
});

module.exports = router;