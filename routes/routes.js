var express       = require('express'),
    router        = express.Router(),
    geocoder      = require('geocoder'),
    elasticsearch = require('elasticsearch');
    
var client = new elasticsearch.Client({
  host: 'cmput301.softwareprocess.es:8080/',
  apiVersion : '6.2'
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

router.post('/signUpUser', function(request, response){
    var user = JSON.parse(request.body.user);
    
    if(typeof(user.email) != 'undefined'){
        var queryString = 'email:' + user.email;
        console.log(queryString);
        
        client.search({
          index: 'tenner',
          type: 'users',
          q : queryString
        }).then(function (responseBody) {
            var data = responseBody.hits.hits;
            for(var dataObj in data){
                if (data.hasOwnProperty(dataObj)) {
                    if(user.email == data[dataObj]._source.email){
                        console.log('User Exists!');
                        return response.send({'Error' : 'At /signUpUser User Exists!'});
                    }
                }
            }
            
            //Write
            client.create({
                index: 'tenner',
                type: 'users',
                id : user.email,
                body : {
                    email: user.email, 
                    firstName : user.firstName,
                    lastName : user.lastName,
                    phoneNum : user.phoneNum,
                    photo : user.photo,
                    requestedTasks : user.requestedTasks,
                    providedTasks : user.providedTasks,
                    bids : user.bids
                }
            }).then(function(error, responseWrite){
                if(error){
                    console.log(error);
                    return response.send({'Error' : 'At /signUpUser Upload Error!'});
                } else {
                    console.log(response);
                    return response.send({'Success' : 'At /signUpUser User Signed Up!'});
                }
            });
            
        }, function (err) {
            if(err){
                console.log(err.message);
                return response.send({'Error' : 'At /signUpUser' + err.message});
            }
        });
    } else {
        return response.send({'Error' : 'At /signUpUser No User Email!'});
    }
});

router.post('/getUser', function(request, response){
    var user = request.body.user;
    
    if(typeof(user) != 'undefined'){
        var queryString = 'email:' + user;
        console.log(queryString);
        
        client.search({
          index: 'tenner',
          type: 'users',
          q : queryString
        }).then(function (responseBody) {
            var data = responseBody.hits.hits;
            for(var dataObj in data){
                if (data.hasOwnProperty(dataObj)) {
                    if(user == data[dataObj]._source.email){
                        console.log('User Exists!');
                        return response.send(data[dataObj]._source);
                    }
                }
            }
            return response.send({'Error' : 'At /getUser No User Found!!'});
            
        }, function (err) {
            if(err){
                console.log(err.message);
                return response.send({'Error' : 'At /getUser' + err.message});
            }
        });
    } else {
        return response.send({'Error' : 'At /getUser No User Email!'});
    }
});

router.post('/editUser', function(request, response){
    var user = JSON.parse(request.body.user);
    
    if(typeof(user) != 'undefined'){
        var queryString = 'email:' + user.email;
        console.log(queryString);
        
        client.search({
          index: 'tenner',
          type: 'users',
          q : queryString
        }).then(function (responseBody) {
            var data = responseBody.hits.hits;
            for(var dataObj in data){
                if (data.hasOwnProperty(dataObj)) {
                    if(user.email == data[dataObj]._source.email){
                        console.log('User Exists!');
                        client.index({
                            index: 'tenner',
                            type : 'users',
                            id : user.email,
                            body : {
                                email : user.email,
                                firstName : user.firstName,
                                lastName : user.lastName,
                                phoneNum : user.phoneNum,
                                photo : user.photo,
                                requestedTasks : user.requestedTasks,
                                providedTasks : user.providedTasks,
                                bids : user.bids
                            }
                        }, function (err, response2) {
                            if(err){
                                console.log(err.message);
                                return response.send({'Error' : 'At /editUser' + err.message});
                            } else {
                                return response.send({'Success' : 'At /editUser User Updated!'});
                            }
                        });
                    }
                }
            }
            return response.send({'Error' : 'At /editUser No User Found!'});
            
        }, function (err) {
            if(err){
                return response.send({'Error' : 'At /editUser' + err.message});
            }
        });
    } else {
        return response.send({'Error' : 'At /editUser No User Email!'});
    }
});

//Tasks-------------------------------------------------------------->

router.post('/searchTasks', function(request, response){
    var query = request.body.query;
    
    if(typeof(query) != 'undefined'){
        client.search({
          index: 'tenner',
          type: 'tasks',
          q : query
        }).then(function (responseBody) {
            var data = responseBody.hits.hits;
            return response.send(data);
        }, function (err) {
            console.log(err.message);
            return response.send('Error at /searchTasks : ' + err.message);
        });
    }
});

router.post('/addTask', function(request, response){
    var task = JSON.parse(request.body.task);
    
    var date = new Date(task.requestedDate);
    
    if(typeof(task.requester) != 'undefined'){
        client.search({
          index: 'tenner',
          type: 'tasks',
          id: task.title + task.requester.email,
        }).then(function (responseBody) {
            var data = responseBody.hits.hits;
            if(data.length != 0){
                return response.send({'Error' :  'At /addTask : Cannot have same task title!'});
            } else {
                client.index({
                    index: 'tenner',
                    type : 'tasks',
                    id : task.title + task.email,
                    body : {
                        status: task['status'], 
                        title : task.title,
                        description : task['description'],
                        bidList : task.bidList,
                        location : task['location'],
                        photos : task.photos,
                        requestedDate : date,
                        hasNewBids : task.hasNewBids,
                        requester : task.requester
                    }
                }, function (err, response2) {
                    if(err){
                        console.log(err.message);
                        return response.send({'Error' : 'At /addTask' + err.message});
                    } else {
                        return response.send({'Success' : 'At /addTask Task Added!'});
                    }
                });
            }
        }, function (err) {
            console.log(err.message);
            return response.send('Error at /searchTasks : ' + err.message);
        });
    } else {
        return response.send({'Error' : 'At /addTask No Requester!'});
    }
});

router.post('/editTask', function(request, response){
    var task = JSON.parse(request.body.task);
    
    var date = new Date(task.requestedDate);
    
    if(typeof(task.requester) != 'undefined'){
        client.index({
          index: 'tenner',
          type : 'tasks',
          id : task.title + task.requester.email,
          body : {
            status: task['status'], 
            title : task.title,
            description : task['description'],
            bidList : task.bidList,
            location : task['location'],
            photos : task.photos,
            requestedDate : date,
            hasNewBids : task.hasNewBids,
            requester : task.requester
          }
        }, function (err, response2) {
            if(err){
                console.log(err.message);
                return response.send({'Error' : 'At /addTask' + err.message});
            } else {
                return response.send({'Success' : 'At /addTask Task Added!'});
            }
        });
    } else {
        return response.send({'Error' : 'At /addTask No Requester!'});
    }
});

router.post('/getRequestedTasks', function(request, response){
    var userID = request.body.user;
    
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        var requestedTaskArray = [];
        for(var dataObj in data){
            if (data.hasOwnProperty(dataObj)) {
                if(data[dataObj]._source.requester.email == userID){
                    requestedTaskArray.push(data[dataObj]._source);
                }
            }
        }
        return response.send(JSON.stringify(requestedTaskArray));
    }, function (err) {
        console.log(err.message);
        return response.send({'Error' : 'At /getRequestedTasks : ' + err.message});
    });
});

router.post('/getProvidingTasks', function(request, response){
    var userID = request.body.email;
    
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        var assignedTaskArray = [];
        
        for(var dataObj in data){
            if (data.hasOwnProperty(dataObj)) {
                if(typeof(data[dataObj]._source.provider) != 'undefined'){
                    if(data[dataObj]._source.provider.email == userID){
                        assignedTaskArray.push(data[dataObj]._source);
                    }
                }
            }
        }
        return response.send(JSON.stringify(assignedTaskArray));
    }, function (err) {
        console.log(err.message);
        return response.send({'Error' : 'At /getProvidingTasks : ' + err.message});
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

//Get All and Delete-------------------------------------------------------------->

router.get('/getAllUsers', function(request, response){
    client.search({
      index: 'tenner',
      type: 'users'
      /*body: {
        query: {
          match: {
            body:  ''
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

router.get('/deleteUsers', function(request, response){
    client.search({
      index: 'tenner',
      type : 'users',
      id : "tester@gmail.com"
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        var arr = [];
        for(var dataObj in data){
            if (data.hasOwnProperty(dataObj)) {
                client.delete({
                  index: 'tenner',
                  type : 'users',
                  id : data[dataObj]._id
                }, function (error, response2) {
                  if(error){
                      console.log(error);
                      return response.send("Error at delete users!");
                  } else {
                    
                  }
                });
            }
        }
        return response.send({'Success' : 'At /deleteUsers!'}); 
    }, function (err) {
        console.log(err.message);
        return response.send({'Error' : 'At /getAllUsers ' + err.message});
    });
});

router.get('/getAllTasks', function(request, response){
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        console.log(data);
        var arr = [];
        for(var dataObj in data){
            if (data.hasOwnProperty(dataObj)) {
                arr.push(data[dataObj]._source);
            }
        }
        response.send(JSON.stringify(arr));
    }, function (err) {
        console.log(err.message);
        return response.send({'Error' : 'At /getAllUsers ' + err.message});
    });
});

router.get('/deleteTasks', function(request, response){
    client.search({
      index: 'tenner',
      type: 'tasks'
    }).then(function (responseBody) {
        var data = responseBody.hits.hits;
        var arr = [];
        for(var dataObj in data){
            if (data.hasOwnProperty(dataObj)) {
                client.delete({
                  index: 'tenner',
                  type : 'tasks',
                  id : data[dataObj]._id
                }, function (error, response2) {
                  if(error){
                      console.log(error);
                      return response.send("lol");
                  } else {
                    
                  }
                });
            }
        }
        return response.send({'Success' : 'User Sign Up Success!'}); 
    }, function (err) {
        console.log(err.message);
        return response.send({'Error' : 'At /getAllUsers ' + err.message});
    });
});

module.exports = router;