# DSI practical exam: Choreographed Microservices

## What you are given
In this repository you´ll find the codebase for the exam. It is actually the solution of **Assignment 4**. As you may 
remember it consists of 4 services:
* *Users*: handles all the users' information with CRUD operations. When a user is deleted it sends a message with its username
* *Notes*: handles the users' notes with CRUD operations. When it receives a message with a username it deletes all her notes
* *Reverse Proxy*: is a Zuul reverse proxy of the previous services used as an entry point to the exposed API
* *Message Broker*: this service is not part of this repository. We use RabbitMQ that must be installed and run on the machine independently. 
Recall that once started you can monitor it from http://localhost:15672/

Additionally you'll find another module called **Logger** which is almost empty and which you will need to code...

As you know, *users* and *notes* services are organized in a choreographed way in the sense that there is not a director
that tells each service what to do when a user is deleted. Instead the *users* service sends a message with the deleted username and the *notes* 
service knows what to do when receives this message

## What you need to do 
The problem with choreographed systems is that the behaviour of the system is not explicitly coded anywhere, and so when it fails
it is difficult to know why and where to look for the problem. One way of mitigating this problem is to have a service that
monitors all the system. Yes, you guessed it!! This is precisely what you have to do.

### Monitor Service
You are going to code a service called ***logger*** that is going to log (to the console to make it simple) every time any of the other
two services (*users* and *notes*) perform an operation that modifies the state of the system: POST, PUT or DELETE operation.
To do so, both *users* and *notes* services are going to throw a message whenever they perform any of the operations above 
and the *logger* is going to log to the console whenever it receives a message, telling what kind of operation have been performed.
For logging you can use any of the libraries out there. However, the provided module has already a dependency with the *Log4j* library.
It also has a dependency with `spring-cloud-starter-stream-rabbit`.

We are going to have three different queues:
* **deleteNote**: the one we already have used when all notes from one user must be deleted.
* **noteInfo**: the one to be used when a POST, PUT or DELETE operation is performed to a single note.
* **userInfo**: the one to be used when a POST, PUT or DELETE operation is performed to an user.

### Recommended steps (not mandatory though)

Following the Scrum methodology I will grade much higher the **working** features. It is much better having few features that work 
than having partially coded all of them. Thus, I recommend following these steps:
1. The *logger* service consumes the *deleteNote* queue
1. The *logger* service consumes *userInfo* and *noteInfo* queues
1. The *notes* service throws a message to the *noteInfo* queue when a POST, PUT or DELETE operation is performed on a single note
1. The *user* service throws a message to the *userInfo* queue when a POST, PUT or DELETE operation is performed on an user

## Useful links
* https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/multi-io repository with a *multichannel* example
* https://github.com/DSI-T21617/sourceSink_SpringCloudStream the class example
* http://docs.spring.io/spring-cloud-stream/docs/Brooklyn.RELEASE/reference/html/_programming_model.html official documentation. You want
to go to section 3.1.1 where it reads **"Injecting Channels Directly"**. Go to the third code block but be aware that there are errors:
    * The attribute `private MessageChannel output` should not be annotated with `@Autowired`
    * The constructor method should be a setter of the attribute `private MessageChannel output` otherwise the compiler complaints
    about the `@Qualifier` annotation

## Calling the API

### GET
* http://localhost:8080/users/api/ gets all the users
* http://localhost:8080/users/api/jrjrjr gets the user with username *jrjrjr*
* http://localhost:8080/notes/api/user/jrjrjr gets *jrjrjr*'s notes
* http://localhost:8080/notes/api/allNotes gets all notes from all users
* http://localhost:8080/notes/api/1 gets the note with id *1*

### POST
* http://localhost:8080/api/createUser creates the user sent in the body:
```json
{
  "username": "mememe",
  "name": "Maria",
  "secondName": "Espinac",
  "email": "espinac@gmail.com"
}
```
* http://localhost:8080/notes/api/createNote creates the note sent in the body:
```json
{
      "title": "jola",
      "content": "va super be",
      "userId": "jrjrjr"
}
```

### PUT
* http://localhost:8080/notes/api/3 updates the note with id *3* sent in the body:
```json
{
      "title": "nota 33",
      "content": "va super be 33"
}
```

### DELETE
* http://localhost:8080/users/api/jrjrjr deletes user *jrjrjr*
* http://localhost:8080/notes/api/3 deletes note *3*