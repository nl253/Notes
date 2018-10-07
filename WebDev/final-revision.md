# Final Revision

## HTTP and Web Servers

- Explain the difference between a URL and a URI.

A URI is a unique resource identifier, a URL on the other hand is a unique
resource **location**. The difference is subtle, all URLs bare URIs.
Additionally, all URLs correspond to real locations on the network. URIs on the
other hand may be identifiers that don't correspond to a real location on the
network but are merely addresses that trigger CGI scripts on a server.

E.g.: a web framework might make use of dynamically generated content and
respond to a URI of `https://www.server.com/find/a/car/`.

The trailing backslash would normally indicate a directory. If we receive
something other than a directory listing then we can be pretty sure the content
is dynamically generated.

- Give a brief description of how an HTTP request is sent, what is the client
  and server's role in the process? How does responding happen?

> 1. The client is using a browser to send an HTTP (GET or POST) request to a
>   server
> 2. To do that it uses a URL which gets translated by a DNS server to an IP
>    address i.e. an address of the server
> 3. The server is listening for HTTP requests and receives the request sent by
>    the client
> 4. The request contains information about who the request is from:
>     - What was the URI this request was issues from (i.e. the referer).
>     - What browser was the client using (e.g. Internet Explorer or Google
>       Chrome).
>     - What language the client expects the response in (e.g. fr, en, en-gb,
>       en-us).
>     - What character encoding the client expects the response in.
>     - What type of content the client is expecting (typically html).
> 5. Assuming the request was for an HTML page, the server sends a response
>    (optionally there might be some CGI scripts running to render the response if
>    this is dynamic content) which includes: metadata and the HTML message itself.
> 6. The client receives the HTTP response: the metadata and the message
> 7. The browser makes use of the metadata to figure out how to render the message
>    to the client (e.g. checks encoding that the server chose)
>

- What is dynamic content? Support your answer with an example.

> Dynamic content differs from static content in that it is generated dynamically
> which means during the running of a web application typically by CGI scripts
> written in a programming (typically scripting) language such as PHP, Perl or Python.
> To serve dynamic content web servers need to be configured to instead of sending
> an HTML run those scripts. Dynamic content allows for interactive applications
> where the content needs to be for instance updated in real time. Dynamic content
> might be: web pages whose content is first fetched from a remote database.
> Static content, in contrast does not change, it does not need any special
> configured. Static content is for example: web pages whose content does not
> change and can be served "as-is" without any modifications. Another example
> would be CSS stylesheets and JavaScript scripts. Both are considered static
> assets because they aren't (typically) dynamically generated.
>

- How can Apache be configured? What options does it give to programmers?

> The Apache Web Server can be configured to:

> - listen to requests on a different port,
> - run CGI scripts instead of serving static content
> - it can be configured to prevent access to certain class of users
> - it can be configured to serve content from a different directory
> - it can be configured to selectively prevent access to some directories
> - it allows to configure the format of logging and where logs are saved as
>   well as how long they are kept for
> - it allows to specify where CGI scripts are kept
>

- What is a CGI script? What does CGI stand for?

> CGI stands for common gateway interface, it's a mechanism whereby dynamic
> content can be sent to clients. CGI scripts are scripts written in (typically)
> PHP or Python or Perl that generate a response dynamically by (typically)
> contacting a database. Web servers such as Apache can be configured to run
> those scripts instead of just serving static content.

- How to: start, shut down, restart an Apache Web Server?

> `apachectl` is the command line utility used for controlling the Apache Web
> Server, it ships with Apache:
>
> - `apachectl start` to start the Apache Web Server
> - `apachectl stop` to stop the Apache Web Server immediately
> - `apachectl restart` to restart the Apache Web Server immediately

- How do "graceful" command differ from "non-graceful" variants?

> Graceful commands are used when we want the web server to finish off the tasks
> before proceeding with the actions (an action could be: stop, restart)

- How do child-workers differ from worker threads? Compare and contrast.

???

- Explain the differences between HTTP status codes 200, 300, 404 and 500. What
  conventions does it follow?

> Status codes are governed by conventions. By convention codes in range 200-299
> are "OK" status codes which mean that the interaction between client and
> server was successful. Status codes between 300-399 indicate a redirection
> i.e. the resource that the client was looking for is not in the place.
> Status codes in range 400-499 are user errors which might happen when the URL
> is malformed or the requested resource does not exists.
> Finally, requests in range 500-599 are errors on the server's part. It might
> happen when the server is overloaded or fails.

- Explain each line in:
    - `GET /people/staff/iau/x.txt HTTP/1.1` -- this is an HTTP1 `GET` request
      for a text file `x.txt` in `/people/staff/iau/x.txt`
    - `Host: www.cs.kent.ac.uk` -- the request is sent to the server with the
      address: `www.cs.kent.ac.uk`
    - `User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536 ...` --
      the browser that the client is using is Mozilla/5.0
    - `Accept: text/html;q=0.9,text/plain;q=0.8` -- the client is willing to
      accept HTML content but is also willing to accept text content if HTML
      content is not available (but the preference is smaller for text files)
    - `Accept-Charset: ISO-8859-1,UTF-8;q=0.7,*;q=0.7` -- the client is willing
      to accept encoding `ISO-8859-1` but also `UTF-8` if that is not available
      (but with a smaller preference for UTF-8) and finally if none of these are
      available, then the client will accept anything
    - `Accept-Language: fr,en;q=0.7,en-us;q=0.3` -- the client will accept
      content in French but also in (any dialect of) English if French is not
      available (smaller preference), finally the client is willing to accept
      US English content if the two previous choices aren't available
    - `Referer: http://cs.kent.ac.uk/people/staff/iau/index.html` -- the
      request for this text file was fired from the location specified by
      `http://cs.kent.ac.uk/people/staff/iau/index.html`


## CodeIgniter

- Explain MVC, what it is, it's role in web development and what individual
  letters stand for and their roles in CodeIgniter.

MVC stands for `MODEL-VIEW-CONTROLLER`. It's an architectural design pattern
often used when building web applications. In CodeIgniter Models are PHP classes
that interact with databases. Views are what gets rendered and displayed to the
user. They are typically PHP files with a bunch of `echo` statements. Lastly, in
CodeIgniter controllers get instructions form the user, request data from the
models and render the views using the data.

- What's the difference between a parse error and a fatal error?

Fatal errors are critical errors and it means that the parser cannot
possibly continue to parse the rest of your code, because of this error.

- List 3 advantages and 3 disadvantages of MVC frameworks

> Advantages:
>
> - separation of concerns
> - at least 2 developers can work on the same application at the same time
>   without interference
> - encourages high cohesion -- related components are grouped together

- How would you display a view in CodeIgniter?
- Given a URL <http://mywebsite.co.uk/ci/index.php/users/read/5>, explain what
  information is being requested
- Explain, how data is inserted into views in CodeIgniter.
- Why might you use autoloading in CodeIgniter? Where can you define
  autoloading? What are helpers? What is their relationship to autoloading?
- What needs to be done initially in CodeIgniter before you request data from a
  database?
- What form does the data sent back by a database take in CodeIgniter?
- Describe the role of `index.php` in a CodeIgniter application.

## Android

- How does Android enable programmers to display their app in multiple
  languages?

> Programmers who wish to display their application in a different language have
> the option to declare translated application strings (stored in
> `res/values/strings.xml`) in the `res/values/` directory
> by adding a suffix that corresponds to the language to the name of the values
> directory. E.g.: `res/values-fr/`.

- Explain what is and what role play in an android application:
     - an activity
     - an intent
     - a content provider
     - a broadcast receiver
- Describe the role of `AndroidManifest.xml` and discuss it's contents.
- Outline the structure of an Android application. What are the main components
  and configurations files?

---------------------------------------------------------------

## Improve

**`200-299` HTTP status codes**

This class of status codes indicates the action requested by the client was
received, understood and accepted.

**MIME types**

`text/html` and `image/png` are examples of "**MIME types**". They
are used by web severs to determine what to do with a file. Servers which serve
dynamic content may specify what type of content they are returning using the
`Content-Type` header. Another mechanism for determining how to handle a file
is to infer the type of content from it's extension (`*.html` maps to `text/html`).

**apachectl**

`graceful` implies restart `graceful-stop` is waiting for Apache to finish
before stopping the server.

**Virtual Hosts**

A single Apache server may:

- listen on multiple ports (e.g. it may listen on 80 AND 8080)
- have multiple network addresses (e.g. it might be 192.1.0.0 AND 192.1.0.1)
- have multiple hostnames (e.g. it might be <https://www.google.com> and <https://www.google.co.uk>)

to configure Apache in this way:

```apache
<VirtualHost *:80> 
    ServerAdmin root@example.com 
    ServerName www.example.com
    ServerAlias example.com
    DocumentRoot /home/www/example
    ErrorLog logs/example.com 
</VirtualHost>

<VirtualHost *:8080>
  ServerAdmin root@somewhere.com 
  ServerName special.example.com 
  DocumentRoot /home/www/special
  ErrorLog logs/special 
</VirtualHost>
```

Where:

- `ServerAdmin` is the email of the server administrator
- `DocumentRoot` is where to serve static content from i.e. a Directory
- `ErrorLog` is where to output logs
- `servername` is

**Access Control in Apache**

**DNS Server** (Domain Name System)

It maps human-readable URIs to IP addresses. More than one URIs can point to the same IP address.

**Hostname**

I.e. domain name of a server. Servers have both an IP address and a domain
name. A hostname may be associate with more than one IP addresses:
<https://www.google.com> has much more than one IP address associated with it.
The purpose of that is load balancing -- instead of redirecting all clients to
the same server we can take pressure off it and redirect it to other servers.

**Load Balancing**

A way of distributing network traffic between multiple servers.

**CGI**

Is a mechanism whereby an external process is run and it's output is captured.
