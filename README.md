Android async client <-> REST Server(Processing data) <-> ElasticSearch

Listening on port 8080 -> process.env.PORT, process.env.IP.

Endpoints:

Get Routes - 

    / -> Ping server
    /ping -> Ping server and elastic search
    /getAllUsers -> Returns all users
    
    
    //Other
    getAllUsers -> Gets all the users
    deleteUsers -> Delete all the users
    getAllTasks -> Gets all the tasks
    deleteTasks -> Deletes all the tasks
    
Post/Put Routes - 

    --> Users
    /signUpUser -> Sign up a user if they're unique
    /getUser -> Sign in a user or get user profile
    /editUser -> Update a user's profile
    
    --> Tasks
    /searchTasks -> User enters a list of keywords and elasticsearch return close matches
    /addTask -> Add a task for a user
    /getRequestedTasks -> Get requested tasks for the user
    /getAssignedTasks -> Get assigneds tasks for the user
    
    /getMyBids -> If user isn't the provider get the bids on their tasks
    /getOtherBids -> If user is the provider get their bids on the tasks