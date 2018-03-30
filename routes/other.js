// router.get('/getAllUsers', function(request, response){
//     client.search({
//       index: 'tenner',
//       type: 'users'
//       /*body: {
//         query: {
//           match: {
//             body: ''
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