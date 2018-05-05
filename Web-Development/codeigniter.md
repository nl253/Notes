# CodeIgniter

- PHP framework
- OOP approach, makes use of PHP objects

## Web Frameworks

- make common operations related to CRUD easier
- promote separation of concerns i.e. a clear division into model, view and
  controller
- provide the necessary infrastructure for a web app
- allow multiple developers to work on the same project (back- and front-end)
  without interference
- promote best practices

## Structure

```
./CodeIgniter/
├── application
│   ├── cache
│   │   └── index.html
│   ├── config
│   │   ├── autoload.php
│   │   ├── config.php
│   │   ├── constants.php
│   │   ├── database.php
│   │   ├── doctypes.php
│   │   ├── foreign_chars.php
│   │   ├── hooks.php
│   │   ├── index.html
│   │   ├── memcached.php
│   │   ├── migration.php
│   │   ├── mimes.php
│   │   ├── profiler.php
│   │   ├── routes.php
│   │   ├── smileys.php
│   │   └── user_agents.php
│   ├── controllers
│   │   ├── index.html
│   │   └── Welcome.php
│   ├── core
│   │   └── index.html
│   ├── helpers
│   │   └── index.html
│   ├── hooks
│   │   └── index.html
│   ├── index.html
│   ├── language
│   │   ├── english
│   │   │   └── index.html
│   │   └── index.html
│   ├── libraries
│   │   └── index.html
│   ├── logs
│   │   └── index.html
│   ├── models
│   │   └── index.html
│   ├── third_party
│   │   └── index.html
│   └── views
│       ├── errors
│       │   ├── cli
│       │   │   ├── error_404.php
│       │   │   ├── error_db.php
│       │   │   ├── error_exception.php
│       │   │   ├── error_general.php
│       │   │   ├── error_php.php
│       │   │   └── index.html
│       │   ├── html
│       │   │   ├── error_404.php
│       │   │   ├── error_db.php
│       │   │   ├── error_exception.php
│       │   │   ├── error_general.php
│       │   │   ├── error_php.php
│       │   │   └── index.html
│       │   └── index.html
│       ├── index.html
│       └── welcome_message.php
├── composer.json
├── contributing.md
├── index.php
├── license.txt
├── readme.rst
├── system
│   ├── core
│   │   ├── Benchmark.php
│   │   ├── CodeIgniter.php
│   │   ├── Common.php
│   │   ├── compat
│   │   │   ├── hash.php
│   │   │   ├── index.html
│   │   │   ├── mbstring.php
│   │   │   ├── password.php
│   │   │   └── standard.php
│   │   ├── Config.php
│   │   ├── Controller.php
│   │   ├── Exceptions.php
│   │   ├── Hooks.php
│   │   ├── index.html
│   │   ├── Input.php
│   │   ├── Lang.php
│   │   ├── Loader.php
│   │   ├── Log.php
│   │   ├── Model.php
│   │   ├── Output.php
│   │   ├── Router.php
│   │   ├── Security.php
│   │   ├── URI.php
│   │   └── Utf8.php
│   ├── database
│   │   ├── DB_cache.php
│   │   ├── DB_driver.php
│   │   ├── DB_forge.php
│   │   ├── DB.php
│   │   ├── DB_query_builder.php
│   │   ├── DB_result.php
│   │   ├── DB_utility.php
│   │   ├── drivers
│   │   │   ├── cubrid
│   │   │   │   ├── cubrid_driver.php
│   │   │   │   ├── cubrid_forge.php
│   │   │   │   ├── cubrid_result.php
│   │   │   │   ├── cubrid_utility.php
│   │   │   │   └── index.html
│   │   │   ├── ibase
│   │   │   │   ├── ibase_driver.php
│   │   │   │   ├── ibase_forge.php
│   │   │   │   ├── ibase_result.php
│   │   │   │   ├── ibase_utility.php
│   │   │   │   └── index.html
│   │   │   ├── index.html
│   │   │   ├── mssql
│   │   │   │   ├── index.html
│   │   │   │   ├── mssql_driver.php
│   │   │   │   ├── mssql_forge.php
│   │   │   │   ├── mssql_result.php
│   │   │   │   └── mssql_utility.php
│   │   │   ├── mysql
│   │   │   │   ├── index.html
│   │   │   │   ├── mysql_driver.php
│   │   │   │   ├── mysql_forge.php
│   │   │   │   ├── mysql_result.php
│   │   │   │   └── mysql_utility.php
│   │   │   ├── mysqli
│   │   │   │   ├── index.html
│   │   │   │   ├── mysqli_driver.php
│   │   │   │   ├── mysqli_forge.php
│   │   │   │   ├── mysqli_result.php
│   │   │   │   └── mysqli_utility.php
│   │   │   ├── oci8
│   │   │   │   ├── index.html
│   │   │   │   ├── oci8_driver.php
│   │   │   │   ├── oci8_forge.php
│   │   │   │   ├── oci8_result.php
│   │   │   │   └── oci8_utility.php
│   │   │   ├── odbc
│   │   │   │   ├── index.html
│   │   │   │   ├── odbc_driver.php
│   │   │   │   ├── odbc_forge.php
│   │   │   │   ├── odbc_result.php
│   │   │   │   └── odbc_utility.php
│   │   │   ├── pdo
│   │   │   │   ├── index.html
│   │   │   │   ├── pdo_driver.php
│   │   │   │   ├── pdo_forge.php
│   │   │   │   ├── pdo_result.php
│   │   │   │   ├── pdo_utility.php
│   │   │   │   └── subdrivers
│   │   │   │       ├── index.html
│   │   │   │       ├── pdo_4d_driver.php
│   │   │   │       ├── pdo_4d_forge.php
│   │   │   │       ├── pdo_cubrid_driver.php
│   │   │   │       ├── pdo_cubrid_forge.php
│   │   │   │       ├── pdo_dblib_driver.php
│   │   │   │       ├── pdo_dblib_forge.php
│   │   │   │       ├── pdo_firebird_driver.php
│   │   │   │       ├── pdo_firebird_forge.php
│   │   │   │       ├── pdo_ibm_driver.php
│   │   │   │       ├── pdo_ibm_forge.php
│   │   │   │       ├── pdo_informix_driver.php
│   │   │   │       ├── pdo_informix_forge.php
│   │   │   │       ├── pdo_mysql_driver.php
│   │   │   │       ├── pdo_mysql_forge.php
│   │   │   │       ├── pdo_oci_driver.php
│   │   │   │       ├── pdo_oci_forge.php
│   │   │   │       ├── pdo_odbc_driver.php
│   │   │   │       ├── pdo_odbc_forge.php
│   │   │   │       ├── pdo_pgsql_driver.php
│   │   │   │       ├── pdo_pgsql_forge.php
│   │   │   │       ├── pdo_sqlite_driver.php
│   │   │   │       ├── pdo_sqlite_forge.php
│   │   │   │       ├── pdo_sqlsrv_driver.php
│   │   │   │       └── pdo_sqlsrv_forge.php
│   │   │   ├── postgre
│   │   │   │   ├── index.html
│   │   │   │   ├── postgre_driver.php
│   │   │   │   ├── postgre_forge.php
│   │   │   │   ├── postgre_result.php
│   │   │   │   └── postgre_utility.php
│   │   │   ├── sqlite
│   │   │   │   ├── index.html
│   │   │   │   ├── sqlite_driver.php
│   │   │   │   ├── sqlite_forge.php
│   │   │   │   ├── sqlite_result.php
│   │   │   │   └── sqlite_utility.php
│   │   │   ├── sqlite3
│   │   │   │   ├── index.html
│   │   │   │   ├── sqlite3_driver.php
│   │   │   │   ├── sqlite3_forge.php
│   │   │   │   ├── sqlite3_result.php
│   │   │   │   └── sqlite3_utility.php
│   │   │   └── sqlsrv
│   │   │       ├── index.html
│   │   │       ├── sqlsrv_driver.php
│   │   │       ├── sqlsrv_forge.php
│   │   │       ├── sqlsrv_result.php
│   │   │       └── sqlsrv_utility.php
│   │   └── index.html
│   ├── fonts
│   │   ├── index.html
│   │   └── texb.ttf
│   ├── helpers
│   │   ├── array_helper.php
│   │   ├── captcha_helper.php
│   │   ├── cookie_helper.php
│   │   ├── date_helper.php
│   │   ├── directory_helper.php
│   │   ├── download_helper.php
│   │   ├── email_helper.php
│   │   ├── file_helper.php
│   │   ├── form_helper.php
│   │   ├── html_helper.php
│   │   ├── index.html
│   │   ├── inflector_helper.php
│   │   ├── language_helper.php
│   │   ├── number_helper.php
│   │   ├── path_helper.php
│   │   ├── security_helper.php
│   │   ├── smiley_helper.php
│   │   ├── string_helper.php
│   │   ├── text_helper.php
│   │   ├── typography_helper.php
│   │   ├── url_helper.php
│   │   └── xml_helper.php
│   ├── index.html
│   ├── language
│   │   ├── english
│   │   │   ├── calendar_lang.php
│   │   │   ├── date_lang.php
│   │   │   ├── db_lang.php
│   │   │   ├── email_lang.php
│   │   │   ├── form_validation_lang.php
│   │   │   ├── ftp_lang.php
│   │   │   ├── imglib_lang.php
│   │   │   ├── index.html
│   │   │   ├── migration_lang.php
│   │   │   ├── number_lang.php
│   │   │   ├── pagination_lang.php
│   │   │   ├── profiler_lang.php
│   │   │   ├── unit_test_lang.php
│   │   │   └── upload_lang.php
│   │   └── index.html
│   └── libraries
│       ├── Cache
│       │   ├── Cache.php
│       │   ├── drivers
│       │   │   ├── Cache_apc.php
│       │   │   ├── Cache_dummy.php
│       │   │   ├── Cache_file.php
│       │   │   ├── Cache_memcached.php
│       │   │   ├── Cache_redis.php
│       │   │   ├── Cache_wincache.php
│       │   │   └── index.html
│       │   └── index.html
│       ├── Calendar.php
│       ├── Cart.php
│       ├── Driver.php
│       ├── Email.php
│       ├── Encryption.php
│       ├── Encrypt.php
│       ├── Form_validation.php
│       ├── Ftp.php
│       ├── Image_lib.php
│       ├── index.html
│       ├── Javascript
│       │   ├── index.html
│       │   └── Jquery.php
│       ├── Javascript.php
│       ├── Migration.php
│       ├── Pagination.php
│       ├── Parser.php
│       ├── Profiler.php
│       ├── Session
│       │   ├── drivers
│       │   │   ├── index.html
│       │   │   ├── Session_database_driver.php
│       │   │   ├── Session_files_driver.php
│       │   │   ├── Session_memcached_driver.php
│       │   │   └── Session_redis_driver.php
│       │   ├── index.html
│       │   ├── Session_driver.php
│       │   ├── SessionHandlerInterface.php
│       │   └── Session.php
│       ├── Table.php
│       ├── Trackback.php
│       ├── Typography.php
│       ├── Unit_test.php
│       ├── Upload.php
│       ├── User_agent.php
│       ├── Xmlrpc.php
│       ├── Xmlrpcs.php
│       └── Zip.php


```

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
- live in `CodeIgniter/application/models` directory
- must extend the `CI_Model` class

### View

- any output representation of information (e.g.: a chart, a diagram)
- it's possible to have several views on the same information
- presents information in model in a particular format (way)
- to load a view from a controller: `$this->load->view("helloView")`
- live inside the `CodeIgniter/application/views/` directory
- PHP files with `echo` statements
- to pass data into a view call `load->view` with an extra parameter:

```php
$data = array();

$data['user_name'] = 'John Smith';
$data['user_age'] = 29;

$this->load->view("helloView", $data)
```

**NOTE**: CodeIgniter will transform dictionary keys into variable names so that they
can be referenced like `$user_name` in the `helloView` view.

### Controller

- accepts input and converts it into commands for the model or view
- responds to user input and performs interactions on the data model objects
- in CodeIgniter controllers must extend the `CI_Controller` class
- all methods in controllers will be served unless they are prefixed with an `_`, that makes them private (useful for utility methods)
- can be put into sub-directories but this means the URIs will change as well

### Routing

-   It's possible to make the `index.php` vanish from URIs.
-   You can have Apache insert it for you. (tweak `.htaccess` or `httpd.conf`)
    but you also need to set `$config['index_page']` to the empty string (`""`)
    in `CodeIgniter/application/config/config.php`
-   Routing in CodeIgniter is configurable.
-   Navigate to `CodeIgniter/application/config/routes.php`.

```php
//remap entire controller
$route['posts'] = 'notes'; 

//remap just 1 action
$route['posts/read'] = 'notes/view'; 

//:num = any no.
$route['notes/:num'] = 'notes/view/$1'; 

//regex
$route['products/s([a-Z].*)'] = 'shirts/view/$1'; 
```

### Get and Post

To retrieve `GET` or `POST` parameters call:

- `$this->input->post("password")`
- `$this->input->get("password")`
- `$get this->input->get_post("password")` (check first post then get -- for some
  reason it's inverted)

### Sessions

- you can use `$_SESSION`
- CodeIgniter provides a (more flexible) session library (load with
  `$this->load->library('session')` or autoload in `CodeIgniter/application/config/autoload.php`)

### Autoloading

- Can be done in `CodeIgniter/application/config/autoload.php`.
- For: helpers, libraries, models, etc.
- **NOTE**: autoloading the database library makes a connection with the
  database.

### Helpers

#### URL Helper

Provides utility functions related to URL handling:

- `site_url()` -- turns CodeIgniter slugs such as `user/account_state/923` into full URI
- `base_url()` -- similar but for static assets such as `media/css/styles.css`
- `current_url()`
- `anchor()` -- return `<a href=" ... " > ... </a>`
- ...

#### Form Helper

### Caching

- some pages are expensive to generate, you can have CodeIgniter cache a page
  and if the user asks for the same content just return the cached page
- just call `$this->output->cache(5)` (5 min)

### Profiling

- just call `$this->output->enable_profiler(TRUE)`
- will display:
    - memory usage
    - database queries
    - time taken to load page
    - HTTP headers
