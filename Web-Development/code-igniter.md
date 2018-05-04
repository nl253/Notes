# CodeIgniter

## MVC

- Commonly used to develop web applications.
- Traditionally used for GUIs.
- Divides the application into 3 interconnected components.
- The aim is to separate the way information is processed and stored internally
  from the way it's presented to and accepted from the user.

**Advantages**:

- MVC promotes separation of concerns as it decouples those 3 elements of
  application
- As a side-effect more than one developers can work on the same application.
- This pluggable design makes it possible to reuse code. Since most (web) apps
  are based on the same design pattern it's easy to create a framework and
  continuously reuse the same components (only change what's specific to that
  business)
- low coupling -- promotes independence of components
- high cohesion -- related components that perform some specific function are
  grouped together

**Disadvantages**:

- learning curve -- frameworks tend to have a specific way of doing things and
  may take time to learn
- code navigability -- additional layers of abstraction 

### Model

- expresses the application's behaviour in terms of the problem domain
- independent of UI
- independently manages data, logic and rules of the application
- manages application data
- receives instructions from the controller

### View

- any output representation of information (e.g.: a chart, a diagram)
- it's possible to have several views on the same information
- presents information in model in a particular format (way)

### Controller

- accepts input and converts it into commands for the model or view
- responds to user input and performs interactions on the data model objects
