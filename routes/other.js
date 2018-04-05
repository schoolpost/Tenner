// router.get('/getAllUsers', function(request, response){
//     client.search({
//       index: 'tenner',
//       type: 'users'
//       /*body: {
//         query: {
//           match: {
//             body:  ''
//           }
//         }
//       }*/
//     }).then(function (responseBody) {
//         var data = responseBody.hits.hits;
//         return response.send(data);
//     }, function (err) {
//         console.log(err.message);
//         return response.send({'Error' : 'At /getAllUsers ' + err.message});
//     });
// });

// router.get('/getAllTasks', function(request, response){
//     client.search({
//       index: 'tenner',
//       type: 'tasks'
//       /*body: {
//         query: {
//           match: {
//             body:  ''
//           }
//         }
//       }*/
//     }).then(function (responseBody) {
//         var data = responseBody.hits.hits;
//         return response.send(data);
//     }, function (err) {
//         console.log(err.message);
//         return response.send({'Error' : 'At /getAllUsers ' + err.message});
//     });
// });

// router.post('/signInUser', function(request, response){
//     var user = request.body.user;
    
//     if(typeof(user) != 'undefined'){
//         var queryString = 'email:' + user;
//         console.log(queryString);
        
//         client.search({
//           index: 'tenner',
//           type: 'users',
//           q : queryString
//         }).then(function (responseBody) {
//             var data = responseBody.hits.hits;
//             console.log(data)
//             for(var dataObj in data){
//                 if (data.hasOwnProperty(dataObj)) {
//                     if(user == data[dataObj]._source.email){
//                         console.log('User Exists!');
//                         return response.send({'Success' : 'At /signInUser Log In!'});
//                     }
//                 }
//             }
//             return response.send({'Error' : 'At /signInUser No User Found!'});
            
//         }, function (err) {
//             if(err){
//                 console.log(err.message);
//                 return response.send({'Error' : 'At /signInUser' + err.message});
//             }
//         });
//     } else {
//         return response.send({'Error' : 'At /signInUser No User Email!'});
//     }
// });