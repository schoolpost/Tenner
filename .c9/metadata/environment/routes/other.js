{"filter":false,"title":"other.js","tooltip":"/routes/other.js","undoManager":{"mark":26,"position":26,"stack":[[{"start":{"row":7,"column":21},"end":{"row":7,"column":22},"action":"insert","lines":[" "],"id":2}],[{"start":{"row":18,"column":6},"end":{"row":19,"column":0},"action":"insert","lines":["",""],"id":3}],[{"start":{"row":19,"column":0},"end":{"row":20,"column":0},"action":"insert","lines":["",""],"id":4}],[{"start":{"row":20,"column":0},"end":{"row":38,"column":6},"action":"insert","lines":["// router.get('/getAllUsers', function(request, response){","//     client.search({","//       index: 'tenner',","//       type: 'users'","//       /*body: {","//         query: {","//           match: {","//             body:  ''","//           }","//         }","//       }*/","//     }).then(function (responseBody) {","//         var data = responseBody.hits.hits;","//         return response.send(data);","//     }, function (err) {","//         console.log(err.message);","//         return response.send({'Error' : 'At /getAllUsers ' + err.message});","//     });","// });"],"id":5}],[{"start":{"row":20,"column":16},"end":{"row":20,"column":27},"action":"remove","lines":["getAllUsers"],"id":6},{"start":{"row":20,"column":16},"end":{"row":20,"column":17},"action":"insert","lines":["g"]}],[{"start":{"row":20,"column":17},"end":{"row":20,"column":18},"action":"insert","lines":["e"],"id":7}],[{"start":{"row":20,"column":18},"end":{"row":20,"column":19},"action":"insert","lines":["t"],"id":8}],[{"start":{"row":20,"column":19},"end":{"row":20,"column":20},"action":"insert","lines":["A"],"id":9}],[{"start":{"row":20,"column":20},"end":{"row":20,"column":21},"action":"insert","lines":["l"],"id":10}],[{"start":{"row":20,"column":21},"end":{"row":20,"column":22},"action":"insert","lines":["l"],"id":11}],[{"start":{"row":20,"column":22},"end":{"row":20,"column":23},"action":"insert","lines":["T"],"id":12}],[{"start":{"row":20,"column":23},"end":{"row":20,"column":24},"action":"insert","lines":["a"],"id":13}],[{"start":{"row":20,"column":24},"end":{"row":20,"column":25},"action":"insert","lines":["s"],"id":14}],[{"start":{"row":20,"column":25},"end":{"row":20,"column":26},"action":"insert","lines":["k"],"id":15}],[{"start":{"row":20,"column":26},"end":{"row":20,"column":27},"action":"insert","lines":["s"],"id":16}],[{"start":{"row":23,"column":16},"end":{"row":23,"column":21},"action":"remove","lines":["users"],"id":17},{"start":{"row":23,"column":16},"end":{"row":23,"column":17},"action":"insert","lines":["t"]}],[{"start":{"row":23,"column":17},"end":{"row":23,"column":18},"action":"insert","lines":["a"],"id":18}],[{"start":{"row":23,"column":18},"end":{"row":23,"column":19},"action":"insert","lines":["s"],"id":19}],[{"start":{"row":23,"column":19},"end":{"row":23,"column":20},"action":"insert","lines":["k"],"id":20}],[{"start":{"row":23,"column":20},"end":{"row":23,"column":21},"action":"insert","lines":["s"],"id":21}],[{"start":{"row":38,"column":6},"end":{"row":39,"column":0},"action":"insert","lines":["",""],"id":23}],[{"start":{"row":39,"column":0},"end":{"row":40,"column":0},"action":"insert","lines":["",""],"id":24}],[{"start":{"row":40,"column":0},"end":{"row":73,"column":6},"action":"insert","lines":["// router.post('/signInUser', function(request, response){","//     var user = request.body.user;","    ","//     if(typeof(user) != 'undefined'){","//         var queryString = 'email:' + user;","//         console.log(queryString);","        ","//         client.search({","//           index: 'tenner',","//           type: 'users',","//           q : queryString","//         }).then(function (responseBody) {","//             var data = responseBody.hits.hits;","//             console.log(data)","//             for(var dataObj in data){","//                 if (data.hasOwnProperty(dataObj)) {","//                     if(user == data[dataObj]._source.email){","//                         console.log('User Exists!');","//                         return response.send({'Success' : 'At /signInUser Log In!'});","//                     }","//                 }","//             }","//             return response.send({'Error' : 'At /signInUser No User Found!'});","            ","//         }, function (err) {","//             if(err){","//                 console.log(err.message);","//                 return response.send({'Error' : 'At /signInUser' + err.message});","//             }","//         });","//     } else {","//         return response.send({'Error' : 'At /signInUser No User Email!'});","//     }","// });"],"id":25}],[{"start":{"row":0,"column":0},"end":{"row":18,"column":6},"action":"remove","lines":["// router.get('/getAllUsers', function(request, response){","//     client.search({","//       index: 'tenner',","//       type: 'users'","//       /*body: {","//         query: {","//           match: {","//             body:  ''","//           }","//         }","//       }*/","//     }).then(function (responseBody) {","//         var data = responseBody.hits.hits;","//         return response.send(data);","//     }, function (err) {","//         console.log(err.message);","//         return response.send({'Error' : 'At /getAllUsers ' + err.message});","//     });","// });"],"id":26}],[{"start":{"row":1,"column":0},"end":{"row":2,"column":0},"action":"remove","lines":["",""],"id":27}],[{"start":{"row":0,"column":0},"end":{"row":1,"column":0},"action":"remove","lines":["",""],"id":28}],[{"start":{"row":0,"column":0},"end":{"row":53,"column":6},"action":"remove","lines":["// router.get('/getAllTasks', function(request, response){","//     client.search({","//       index: 'tenner',","//       type: 'tasks'","//       /*body: {","//         query: {","//           match: {","//             body:  ''","//           }","//         }","//       }*/","//     }).then(function (responseBody) {","//         var data = responseBody.hits.hits;","//         return response.send(data);","//     }, function (err) {","//         console.log(err.message);","//         return response.send({'Error' : 'At /getAllUsers ' + err.message});","//     });","// });","","// router.post('/signInUser', function(request, response){","//     var user = request.body.user;","    ","//     if(typeof(user) != 'undefined'){","//         var queryString = 'email:' + user;","//         console.log(queryString);","        ","//         client.search({","//           index: 'tenner',","//           type: 'users',","//           q : queryString","//         }).then(function (responseBody) {","//             var data = responseBody.hits.hits;","//             console.log(data)","//             for(var dataObj in data){","//                 if (data.hasOwnProperty(dataObj)) {","//                     if(user == data[dataObj]._source.email){","//                         console.log('User Exists!');","//                         return response.send({'Success' : 'At /signInUser Log In!'});","//                     }","//                 }","//             }","//             return response.send({'Error' : 'At /signInUser No User Found!'});","            ","//         }, function (err) {","//             if(err){","//                 console.log(err.message);","//                 return response.send({'Error' : 'At /signInUser' + err.message});","//             }","//         });","//     } else {","//         return response.send({'Error' : 'At /signInUser No User Email!'});","//     }","// });"],"id":29},{"start":{"row":0,"column":0},"end":{"row":34,"column":1},"action":"insert","lines":["    // admin         = require(\"firebase-admin\");","    ","// var db = admin.database();","    ","function notify(){","","    // if (magnitude) {","    //     topic = \"/topics/news1\";","    // } else if (magnitude >= 2.5) {","    //     topic = \"/topics/news25\";","    // } else if (magnitude >= 4.5){","    //     topic = \"/topics/news45\";","    // } else if (magnitude >= 5.0) {","    //     topic = \"/topics/news5\";","    // }","    // var payload = {","    //     notification: {","    //         title: \"New Task!\",","    //         body: title","    //     }","    // };","    // var options = {","    //     priority: \"high\",","    //     timeToLive: 60 * 60 * 24","    // };","    // admin.messaging().sendToTopic(topic, payload, options)","    // .then(function(response) {","    //     // See the MessagingTopicResponse reference documentation for the","    //     // contents of response.","    // console.log(\"Successfully sent message:\", response);","    // })","    // .catch(function(error) {","    //      console.log(\"Error sending message:\", error);","    // });","}"]}]]},"ace":{"folds":[],"scrolltop":0,"scrollleft":0,"selection":{"start":{"row":34,"column":1},"end":{"row":34,"column":1},"isBackwards":false},"options":{"guessTabSize":true,"useWrapMode":false,"wrapToView":true},"firstLineState":{"row":245,"mode":"ace/mode/javascript"}},"timestamp":1523052372603,"hash":"6296144593db40c691b9d130b62f867264e44cf7"}