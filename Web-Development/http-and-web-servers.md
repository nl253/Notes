# HTTP and Web Servers

Web servers wait for a client to send a request and respond with some sort of
resource such as HTML page which they send back to it.

``` {.java}
while (true) {
    connection = waitOnPort(80 | 443);
    request = connection.get();
    if (connection.isHTTPS())
        request = decrypt(request);
    response = getResource(connection.request);
    if (connection.isHTTPS())
        response = encrypt(response);
    connection.put(response);
}
```

**Standard HTTP(S) ports** <br/> 80 for HTTP and 443 for HTTPS.

**The most popular web servers**

1.  Apache (45%)
2.  Nginx (20%)
3.  Microsoft (10%)
4.  Google (7%)

Apache is free and open source. It is available for most OSs. It was developed
by the Apache Software Foundations.

## HTTP

### HTTP Headers

HTTP headers allow the client and the server to pass additional information
with the request or the response. An HTTP header consists of its
case-insensitive name followed by a colon ':', then by its value (without line
breaks). Leading white space before the value is ignored.

#### HTTP Request

    GET             /people/staff/iau/x.txt HTTP/1.1
    Host            www.cs.kent.ac.uk
    User-Agent      Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.99 Safari/537.36
    Accept          text/html;q=0.9,text/plain;q=0.8
    Accept-Charset  ISO-8859-1,UTF-8;q=0.7,*;q=0.7
    Accept-Language de,en;q=0.7,en-us;q=0.3
    Referer         http://web-sniffer.net/

  -----------------------------------------------------------------------------
  Header               Description
  -------------------- --------------------------------------------------------
  `GET`                is the method (see [http verbs](#http-verbs))

  `Accept-Charset`     encoding

  `User-Agent`         identifies the client (OS, browser with version)

  `Accept-Language`    prefered natural language of the resource (eg en-us (USA
                       English), en (any English), en-gb (British English))

  `Referer`            address of the previous web page from which a link to
                       the currently requested page was followed.

  `Accept`             is the type of content (MIME is used -- eg. `text/html`,
                       `image/png`, `image/\*` (wildcards are accepted so
                       `\*/\*` is fine))

  `Host`               domain name of the server
  -----------------------------------------------------------------------------

  Table: HTTP request headers

##### Query Preferences

Each language-range MAY be given an associated quality value which represents
an estimate of the user's preference for the languages specified by that range.
The quality value defaults to `q=1`. For example,

    Accept-Language: da, en-gb;q=0.8, en;q=0.7

Would mean:

> "I prefer Danish, but will accept British English and other types of
> English."

##### HTTP Verbs

###### Common HTTP Verbs

-   `GET`
-   `POST`
-   `DELETE`
-   `PUT`

###### Less common HTTP verbs

-   `OPTIONS`
-   `CONNECT`

#### HTTP Response

    Status:           HTTP/1.1 200 OK
    Date:             Thu, 24 Sep 2015 10:29:00 GMT
    Server:           Apache
    Last:-Modified    Fri, 26 Sep 2014 01:57:14 GMT
    Content-Length:   6
    Content-Type:     text/plain

    Hello

Where:

  Header                          Description
  ------------------------------- -------------------------------------------------------------------------------------------
  `Status`                        description of what he server has done along with a [response code](#http-response-codes)
  `Last-Modified`                 last change of the resource
  `Content` (below the headers)   the response itself
  `Content-Type`                  type of the content
  `Date`                          timestamp

#### HTTP Response Codes

  Type of Resp     code
  ---------------- ------
  Good             200
  Redirect         300
  Server Failure   400

### Server Configuration

A set of configuration files tell the server how to behave, including:

-   Handling concurrent requests
-   Dealing with different types of content
-   What ports to listen on (e.g. 80 and 443)
-   Where the static content is kept (the "DocumentRoot"), and what the server
    will do with its contents, e.g.: allow execution of CGI scripts, index the
    contents, allow/deny access to specific areas
-   "Virtual Host" descriptions to allow the same server to serve multiple
    addresses with different content (e.g. hp.com, hp.co.uk)

The main `httpd.conf` file contains configuration directives, and sections of
directives

#### Concurrent requests

-   Requests, even for static content, will overlap
-   Servers must arrange to deal with this, either by:
    -   Running multiple server processes, each dealing with a single request
        (Apache calls this "prefork")
    -   Running multiple threads, within a process, each dealing with a single
        request (Apache: "workers")
-   Threads are quick to set up, but not well separated
-   Processes take time to set up, but interfere less with each other
    -   "Process pools" can offset this cost

#### Server control

You'll need to be able to start up, shut down and restart the server in a
controlled way. E.g. (for Apache):

  --------------------------------------------------------------------------------------
  Command                        Description
  ------------------------------ -------------------------------------------------------
  `apachectl -k start`           read config and start listening

  `apachectl -k stop`            now

  `apachectl -k restart`         stop all server processes now. New processes will read
                                 new config

  `apachectl -k graceful`        new server processes start with new config, old
                                 processes get to finish

  `apachectl -k graceful-stop`   stop listening, but wait for server processes to finish
  --------------------------------------------------------------------------------------

  Table: Apache commands reference


### Serving Static Content

<!--TODO Serving Static Content-->

### Serving Dynamic Content

<!--TODO Serving Dynamic Content-->
