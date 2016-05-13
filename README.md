# Management for Emergency Response Volunteers

##Django Apps: 
Tasks, Recommender, API, merv

**Tasks:** handles task creation, storage, updating, and deleting

  * **Models** 
   * Task
     * id : Task ID
     * collaborative : team collaboration skills necessary
     * language : foreign language experience needed
     * outdoor : task environment - indoor vs outdoor
     * transportation : transportation resources required
     * task_name : name of the task
     * task_description : description of task
     * timestamp : datetime of task creation
     * updated : datetime of last task update
  * **Views**:
    * TaskCreate: view for creating a task
    * TaskUpdate: view for updating a task
    * TaskList: generate task recommendations for a User
    * TaskDetail: API endpoint for task CRUD
    * Generate: generate and populate the Task Model with randomized test values 
  * **URLS**: /tasks/
    * create, tasklist, taskdetail/<pk>, /<pk>, rate/<pk>
    * <pk> : the primary key of each task
  

