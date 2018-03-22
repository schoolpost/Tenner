Android async client <-> REST Server(Processing data) <-> ElasticSearch

Listening on port 8080 -> process.env.PORT, process.env.IP.

Functions - ElasticSearch and query handling,
    Reverse geocoding addresses, restful routes, data processing for maps

Endpoints:

Get Routes - 

    / -> Ping server
    /ping -> Ping server and elastic search
    /getAllUsers -> Returns all users
    
    
Post/Put Routes - 

    /signUpUser -> Sign up a user and check if they're unique
    /updateUser -> Update a user's profile
    /getAssignedTasks -> If user is the provider get their tasks
    /getRequestedTasks -> If user isn't the provider get the tasks
    /getMyBids -> If user isn't the provider get the bids on their tasks
    /getOtherBids -> If user is the provider get their bids on the tasks