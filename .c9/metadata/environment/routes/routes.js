{"filter":false,"title":"routes.js","tooltip":"/routes/routes.js","undoManager":{"mark":100,"position":100,"stack":[[{"start":{"row":205,"column":65},"end":{"row":205,"column":66},"action":"insert","lines":["i"],"id":11259}],[{"start":{"row":205,"column":66},"end":{"row":205,"column":67},"action":"insert","lines":["s"],"id":11260}],[{"start":{"row":205,"column":67},"end":{"row":205,"column":68},"action":"insert","lines":["t"],"id":11261}],[{"start":{"row":205,"column":68},"end":{"row":205,"column":69},"action":"insert","lines":["s"],"id":11262}],[{"start":{"row":205,"column":69},"end":{"row":205,"column":70},"action":"insert","lines":["'"],"id":11263}],[{"start":{"row":205,"column":70},"end":{"row":205,"column":71},"action":"insert","lines":["!"],"id":11264}],[{"start":{"row":205,"column":71},"end":{"row":205,"column":72},"action":"insert","lines":[")"],"id":11265}],[{"start":{"row":205,"column":69},"end":{"row":205,"column":70},"action":"remove","lines":["'"],"id":11266}],[{"start":{"row":205,"column":70},"end":{"row":205,"column":71},"action":"insert","lines":["'"],"id":11267}],[{"start":{"row":206,"column":13},"end":{"row":206,"column":14},"action":"insert","lines":[" "],"id":11268}],[{"start":{"row":206,"column":14},"end":{"row":206,"column":15},"action":"insert","lines":["e"],"id":11269}],[{"start":{"row":206,"column":15},"end":{"row":206,"column":16},"action":"insert","lines":["s"],"id":11270}],[{"start":{"row":206,"column":16},"end":{"row":206,"column":17},"action":"insert","lines":["l"],"id":11271}],[{"start":{"row":206,"column":16},"end":{"row":206,"column":17},"action":"remove","lines":["l"],"id":11272}],[{"start":{"row":206,"column":15},"end":{"row":206,"column":16},"action":"remove","lines":["s"],"id":11273}],[{"start":{"row":206,"column":15},"end":{"row":206,"column":16},"action":"insert","lines":["l"],"id":11274}],[{"start":{"row":206,"column":16},"end":{"row":206,"column":17},"action":"insert","lines":["s"],"id":11275}],[{"start":{"row":206,"column":17},"end":{"row":206,"column":18},"action":"insert","lines":["e"],"id":11276}],[{"start":{"row":206,"column":18},"end":{"row":206,"column":19},"action":"insert","lines":[" "],"id":11277}],[{"start":{"row":206,"column":19},"end":{"row":206,"column":20},"action":"insert","lines":["{"],"id":11278}],[{"start":{"row":206,"column":20},"end":{"row":208,"column":13},"action":"insert","lines":["","                ","            }"],"id":11279}],[{"start":{"row":214,"column":8},"end":{"row":236,"column":11},"action":"remove","lines":["client.index({","          index: 'tenner',","          type : 'tasks',","          id : task.title + task.email,","          body : {","            status: task['status'], ","            title : task.title,","            description : task['description'],","            bidList : task.bidList,","            location : task['location'],","            photos : task.photos,","            requestedDate : date,","            hasNewBids : task.hasNewBids,","            requester : task.requester","          }","        }, function (err, response2) {","            if(err){","                console.log(err.message);","                return response.send({'Error' : 'At /addTask' + err.message});","            } else {","                return response.send({'Success' : 'At /addTask Task Added!'});","            }","        });"],"id":11280}],[{"start":{"row":214,"column":4},"end":{"row":214,"column":8},"action":"remove","lines":["    "],"id":11281}],[{"start":{"row":214,"column":0},"end":{"row":214,"column":4},"action":"remove","lines":["    "],"id":11282}],[{"start":{"row":213,"column":11},"end":{"row":214,"column":0},"action":"remove","lines":["",""],"id":11283}],[{"start":{"row":207,"column":16},"end":{"row":229,"column":11},"action":"insert","lines":["client.index({","          index: 'tenner',","          type : 'tasks',","          id : task.title + task.email,","          body : {","            status: task['status'], ","            title : task.title,","            description : task['description'],","            bidList : task.bidList,","            location : task['location'],","            photos : task.photos,","            requestedDate : date,","            hasNewBids : task.hasNewBids,","            requester : task.requester","          }","        }, function (err, response2) {","            if(err){","                console.log(err.message);","                return response.send({'Error' : 'At /addTask' + err.message});","            } else {","                return response.send({'Success' : 'At /addTask Task Added!'});","            }","        });"],"id":11284}],[{"start":{"row":208,"column":0},"end":{"row":208,"column":4},"action":"insert","lines":["    "],"id":11285},{"start":{"row":209,"column":0},"end":{"row":209,"column":4},"action":"insert","lines":["    "]},{"start":{"row":210,"column":0},"end":{"row":210,"column":4},"action":"insert","lines":["    "]},{"start":{"row":211,"column":0},"end":{"row":211,"column":4},"action":"insert","lines":["    "]},{"start":{"row":212,"column":0},"end":{"row":212,"column":4},"action":"insert","lines":["    "]},{"start":{"row":213,"column":0},"end":{"row":213,"column":4},"action":"insert","lines":["    "]},{"start":{"row":214,"column":0},"end":{"row":214,"column":4},"action":"insert","lines":["    "]},{"start":{"row":215,"column":0},"end":{"row":215,"column":4},"action":"insert","lines":["    "]},{"start":{"row":216,"column":0},"end":{"row":216,"column":4},"action":"insert","lines":["    "]},{"start":{"row":217,"column":0},"end":{"row":217,"column":4},"action":"insert","lines":["    "]},{"start":{"row":218,"column":0},"end":{"row":218,"column":4},"action":"insert","lines":["    "]},{"start":{"row":219,"column":0},"end":{"row":219,"column":4},"action":"insert","lines":["    "]},{"start":{"row":220,"column":0},"end":{"row":220,"column":4},"action":"insert","lines":["    "]},{"start":{"row":221,"column":0},"end":{"row":221,"column":4},"action":"insert","lines":["    "]},{"start":{"row":222,"column":0},"end":{"row":222,"column":4},"action":"insert","lines":["    "]},{"start":{"row":223,"column":0},"end":{"row":223,"column":4},"action":"insert","lines":["    "]},{"start":{"row":224,"column":0},"end":{"row":224,"column":4},"action":"insert","lines":["    "]},{"start":{"row":225,"column":0},"end":{"row":225,"column":4},"action":"insert","lines":["    "]},{"start":{"row":226,"column":0},"end":{"row":226,"column":4},"action":"insert","lines":["    "]},{"start":{"row":227,"column":0},"end":{"row":227,"column":4},"action":"insert","lines":["    "]},{"start":{"row":228,"column":0},"end":{"row":228,"column":4},"action":"insert","lines":["    "]},{"start":{"row":229,"column":0},"end":{"row":229,"column":4},"action":"insert","lines":["    "]}],[{"start":{"row":208,"column":0},"end":{"row":208,"column":4},"action":"insert","lines":["    "],"id":11286},{"start":{"row":209,"column":0},"end":{"row":209,"column":4},"action":"insert","lines":["    "]},{"start":{"row":210,"column":0},"end":{"row":210,"column":4},"action":"insert","lines":["    "]},{"start":{"row":211,"column":0},"end":{"row":211,"column":4},"action":"insert","lines":["    "]},{"start":{"row":212,"column":0},"end":{"row":212,"column":4},"action":"insert","lines":["    "]},{"start":{"row":213,"column":0},"end":{"row":213,"column":4},"action":"insert","lines":["    "]},{"start":{"row":214,"column":0},"end":{"row":214,"column":4},"action":"insert","lines":["    "]},{"start":{"row":215,"column":0},"end":{"row":215,"column":4},"action":"insert","lines":["    "]},{"start":{"row":216,"column":0},"end":{"row":216,"column":4},"action":"insert","lines":["    "]},{"start":{"row":217,"column":0},"end":{"row":217,"column":4},"action":"insert","lines":["    "]},{"start":{"row":218,"column":0},"end":{"row":218,"column":4},"action":"insert","lines":["    "]},{"start":{"row":219,"column":0},"end":{"row":219,"column":4},"action":"insert","lines":["    "]},{"start":{"row":220,"column":0},"end":{"row":220,"column":4},"action":"insert","lines":["    "]},{"start":{"row":221,"column":0},"end":{"row":221,"column":4},"action":"insert","lines":["    "]},{"start":{"row":222,"column":0},"end":{"row":222,"column":4},"action":"insert","lines":["    "]},{"start":{"row":223,"column":0},"end":{"row":223,"column":4},"action":"insert","lines":["    "]},{"start":{"row":224,"column":0},"end":{"row":224,"column":4},"action":"insert","lines":["    "]},{"start":{"row":225,"column":0},"end":{"row":225,"column":4},"action":"insert","lines":["    "]},{"start":{"row":226,"column":0},"end":{"row":226,"column":4},"action":"insert","lines":["    "]},{"start":{"row":227,"column":0},"end":{"row":227,"column":4},"action":"insert","lines":["    "]},{"start":{"row":228,"column":0},"end":{"row":228,"column":4},"action":"insert","lines":["    "]},{"start":{"row":229,"column":0},"end":{"row":229,"column":4},"action":"insert","lines":["    "]}],[{"start":{"row":250,"column":33},"end":{"row":250,"column":38},"action":"remove","lines":["email"],"id":11287},{"start":{"row":250,"column":33},"end":{"row":250,"column":34},"action":"insert","lines":["u"]}],[{"start":{"row":250,"column":34},"end":{"row":250,"column":35},"action":"insert","lines":["s"],"id":11288}],[{"start":{"row":250,"column":35},"end":{"row":250,"column":36},"action":"insert","lines":["e"],"id":11289}],[{"start":{"row":250,"column":36},"end":{"row":250,"column":37},"action":"insert","lines":["r"],"id":11290}],[{"start":{"row":250,"column":37},"end":{"row":250,"column":38},"action":"insert","lines":["."],"id":11291}],[{"start":{"row":250,"column":38},"end":{"row":250,"column":39},"action":"insert","lines":["e"],"id":11292}],[{"start":{"row":250,"column":39},"end":{"row":250,"column":40},"action":"insert","lines":["m"],"id":11293}],[{"start":{"row":250,"column":40},"end":{"row":250,"column":41},"action":"insert","lines":["a"],"id":11294}],[{"start":{"row":250,"column":41},"end":{"row":250,"column":42},"action":"insert","lines":["i"],"id":11295}],[{"start":{"row":250,"column":42},"end":{"row":250,"column":43},"action":"insert","lines":["l"],"id":11296}],[{"start":{"row":250,"column":42},"end":{"row":250,"column":43},"action":"remove","lines":["l"],"id":11297}],[{"start":{"row":250,"column":41},"end":{"row":250,"column":42},"action":"remove","lines":["i"],"id":11298}],[{"start":{"row":250,"column":40},"end":{"row":250,"column":41},"action":"remove","lines":["a"],"id":11299}],[{"start":{"row":250,"column":39},"end":{"row":250,"column":40},"action":"remove","lines":["m"],"id":11300}],[{"start":{"row":250,"column":38},"end":{"row":250,"column":39},"action":"remove","lines":["e"],"id":11301}],[{"start":{"row":250,"column":37},"end":{"row":250,"column":38},"action":"remove","lines":["."],"id":11302}],[{"start":{"row":250,"column":36},"end":{"row":250,"column":37},"action":"remove","lines":["r"],"id":11303}],[{"start":{"row":250,"column":35},"end":{"row":250,"column":36},"action":"remove","lines":["e"],"id":11304}],[{"start":{"row":250,"column":34},"end":{"row":250,"column":35},"action":"remove","lines":["s"],"id":11305}],[{"start":{"row":250,"column":33},"end":{"row":250,"column":34},"action":"remove","lines":["u"],"id":11306}],[{"start":{"row":250,"column":33},"end":{"row":250,"column":34},"action":"insert","lines":["r"],"id":11307}],[{"start":{"row":250,"column":34},"end":{"row":250,"column":35},"action":"insert","lines":["e"],"id":11308}],[{"start":{"row":250,"column":35},"end":{"row":250,"column":36},"action":"insert","lines":["q"],"id":11309}],[{"start":{"row":250,"column":36},"end":{"row":250,"column":37},"action":"insert","lines":["u"],"id":11310}],[{"start":{"row":250,"column":37},"end":{"row":250,"column":38},"action":"insert","lines":["e"],"id":11311}],[{"start":{"row":250,"column":38},"end":{"row":250,"column":39},"action":"insert","lines":["s"],"id":11312}],[{"start":{"row":250,"column":39},"end":{"row":250,"column":40},"action":"insert","lines":["t"],"id":11313}],[{"start":{"row":250,"column":40},"end":{"row":250,"column":41},"action":"insert","lines":["e"],"id":11314}],[{"start":{"row":250,"column":41},"end":{"row":250,"column":42},"action":"insert","lines":["r"],"id":11315}],[{"start":{"row":250,"column":42},"end":{"row":250,"column":43},"action":"insert","lines":["."],"id":11316}],[{"start":{"row":250,"column":43},"end":{"row":250,"column":44},"action":"insert","lines":["e"],"id":11317}],[{"start":{"row":250,"column":44},"end":{"row":250,"column":45},"action":"insert","lines":["m"],"id":11318}],[{"start":{"row":250,"column":45},"end":{"row":250,"column":46},"action":"insert","lines":["a"],"id":11319}],[{"start":{"row":250,"column":46},"end":{"row":250,"column":47},"action":"insert","lines":["i"],"id":11320}],[{"start":{"row":250,"column":47},"end":{"row":250,"column":48},"action":"insert","lines":["l"],"id":11321}],[{"start":{"row":201,"column":32},"end":{"row":201,"column":37},"action":"remove","lines":["email"],"id":11322},{"start":{"row":201,"column":32},"end":{"row":201,"column":41},"action":"insert","lines":["requester"]}],[{"start":{"row":201,"column":41},"end":{"row":201,"column":42},"action":"insert","lines":["."],"id":11323}],[{"start":{"row":201,"column":42},"end":{"row":201,"column":43},"action":"insert","lines":["e"],"id":11324}],[{"start":{"row":201,"column":43},"end":{"row":201,"column":44},"action":"insert","lines":["m"],"id":11325}],[{"start":{"row":201,"column":42},"end":{"row":201,"column":44},"action":"remove","lines":["em"],"id":11326},{"start":{"row":201,"column":42},"end":{"row":201,"column":47},"action":"insert","lines":["email"]}],[{"start":{"row":205,"column":63},"end":{"row":205,"column":69},"action":"remove","lines":["Exists"],"id":11327}],[{"start":{"row":205,"column":62},"end":{"row":205,"column":63},"action":"remove","lines":[" "],"id":11328}],[{"start":{"row":205,"column":61},"end":{"row":205,"column":62},"action":"remove","lines":["k"],"id":11329}],[{"start":{"row":205,"column":60},"end":{"row":205,"column":61},"action":"remove","lines":["s"],"id":11330}],[{"start":{"row":205,"column":59},"end":{"row":205,"column":60},"action":"remove","lines":["a"],"id":11331}],[{"start":{"row":205,"column":58},"end":{"row":205,"column":59},"action":"remove","lines":["T"],"id":11332}],[{"start":{"row":205,"column":58},"end":{"row":205,"column":59},"action":"insert","lines":["C"],"id":11333}],[{"start":{"row":205,"column":59},"end":{"row":205,"column":60},"action":"insert","lines":["a"],"id":11334}],[{"start":{"row":205,"column":60},"end":{"row":205,"column":61},"action":"insert","lines":["n"],"id":11335}],[{"start":{"row":205,"column":61},"end":{"row":205,"column":62},"action":"insert","lines":["n"],"id":11336}],[{"start":{"row":205,"column":62},"end":{"row":205,"column":63},"action":"insert","lines":["o"],"id":11337}],[{"start":{"row":205,"column":63},"end":{"row":205,"column":64},"action":"insert","lines":["t"],"id":11338}],[{"start":{"row":205,"column":64},"end":{"row":205,"column":65},"action":"insert","lines":[" "],"id":11339}],[{"start":{"row":205,"column":65},"end":{"row":205,"column":66},"action":"insert","lines":["h"],"id":11340}],[{"start":{"row":205,"column":66},"end":{"row":205,"column":67},"action":"insert","lines":["a"],"id":11341}],[{"start":{"row":205,"column":67},"end":{"row":205,"column":68},"action":"insert","lines":["v"],"id":11342}],[{"start":{"row":205,"column":68},"end":{"row":205,"column":69},"action":"insert","lines":["e"],"id":11343}],[{"start":{"row":205,"column":69},"end":{"row":205,"column":70},"action":"insert","lines":[" "],"id":11344}],[{"start":{"row":205,"column":70},"end":{"row":205,"column":71},"action":"insert","lines":["s"],"id":11345}],[{"start":{"row":205,"column":71},"end":{"row":205,"column":72},"action":"insert","lines":["a"],"id":11346}],[{"start":{"row":205,"column":72},"end":{"row":205,"column":73},"action":"insert","lines":["m"],"id":11347}],[{"start":{"row":205,"column":73},"end":{"row":205,"column":74},"action":"insert","lines":["e"],"id":11348}],[{"start":{"row":205,"column":74},"end":{"row":205,"column":75},"action":"insert","lines":[" "],"id":11349}],[{"start":{"row":205,"column":75},"end":{"row":205,"column":76},"action":"insert","lines":["t"],"id":11350}],[{"start":{"row":205,"column":76},"end":{"row":205,"column":77},"action":"insert","lines":["a"],"id":11351}],[{"start":{"row":205,"column":77},"end":{"row":205,"column":78},"action":"insert","lines":["s"],"id":11352}],[{"start":{"row":205,"column":78},"end":{"row":205,"column":79},"action":"insert","lines":["k"],"id":11353}],[{"start":{"row":205,"column":79},"end":{"row":205,"column":80},"action":"insert","lines":[" "],"id":11354}],[{"start":{"row":205,"column":80},"end":{"row":205,"column":81},"action":"insert","lines":["t"],"id":11355}],[{"start":{"row":205,"column":81},"end":{"row":205,"column":82},"action":"insert","lines":["i"],"id":11356}],[{"start":{"row":205,"column":82},"end":{"row":205,"column":83},"action":"insert","lines":["t"],"id":11357}],[{"start":{"row":205,"column":83},"end":{"row":205,"column":84},"action":"insert","lines":["l"],"id":11358}],[{"start":{"row":205,"column":84},"end":{"row":205,"column":85},"action":"insert","lines":["e"],"id":11359}]]},"ace":{"folds":[],"scrolltop":3115,"scrollleft":0,"selection":{"start":{"row":205,"column":85},"end":{"row":205,"column":85},"isBackwards":false},"options":{"guessTabSize":true,"useWrapMode":false,"wrapToView":true},"firstLineState":{"row":193,"state":"start","mode":"ace/mode/javascript"}},"timestamp":1523058720859,"hash":"a51c6eb002e9593800a71b9b42eba3acaac31f57"}