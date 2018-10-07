# JQuery

## Introduction

JQuery is a JavaScript (JS) library. Purpose of JQuery:

-   DOM (Document Object Model) navigation
-   AJAX
-   ensuring compatibility (chrome, safari, opera, ... )

In JS functions are first class objects which can be passes around in
parameters and stored in variables. We can also declare them wihtout a name ie
decalre an anonymous function.

``` {.javascript}
var a = function() {
    alert(‘expired!’);
};
setTimeOut(a, 1000);

setTimeOut(function() {
    alert(‘expired!’);
}, 1000);
```

JQuery leverages this ability heavily so code can look different to code you've
seen elsewhere.

## Including the library:

-   Google CDN
-   download a copy and put it on server

Don't use both together. One's enough.

The Google one uses a nice trick:

-   Starting the URL with `//` means that the protocol of whatever page is
    currently open will be used (i.e. http or https)
-   This will cause problems however if you run the file locally

## JQuery Selectors

They use a special "dollar" function `$()`

-   It takes a string
-   Selects one or more HTML elements
-   Same syntax for this string as CSS selectors

Eg

-   `$("p")`
-   `$("#error")`
-   `$("p.important")`

### Special selectors

As well as CSS selectors, jQuery has a small number of special selectors that
aren't strings, and don't need quotes:

-   `$(document)` selects the whole page
-   `$(this)` used inside an event handler, this will select the element that
    triggered the event

### CSS3 Selectors

You can use CSS3 Selectors to make some relatively complex selections:

-   ul:first-of-type \> li:nth-child(2)
-   div p:only-child

It can sometimes be helpful to select elements from the set returned by a
previous selector.

-   `$("div p.items").filter(".important")`;
-   `$("div p.items").not(".important")`;

There's also:

-   `.first()`
-   `.last()`
-   `.eq(index)` for one in between

## Events

The most important event in jQuery is the document ready event.

This event fires when the page has finished loading, and the DOM isn't changing
any more. This means that iframes/images might not have loaded yet.

``` {.javascript}
$(document).ready(function() {
    // code ...
});
```

Others:

-   `dblclick()`
-   `mouseenter()` and `mouseleave()` (or `hover()`)
-   `keypress()`, `keydown()`, and `keyup()`
-   `focus()` and `blur()`
-   `submit()`

## API

-   `hide()`-- equivalent to `display: none;` in CSS.
-   `parent()`
-   `parents()`
-   `siblings()`
-   `children()`
-   `find("p, div > ul")`-- takes an additional selector and filters results

### Chaining

jQuery is designed so that you can "chain" function calls onto the end of
one-another.

Can reduce long lists of actions to single statements. Can also reduce the need
to re-run selectors ... but can lead to seriously ugly code in extreme cases.

Eg. `$("p").not(".important").siblings().children().hide();`

### Effects

jQuery has some built-in effects:

-   `.hide()`
-   `.show()`
-   `.fadeOut()`
-   `.fadeIn()`
-   `.slideDown()`
-   `.slideUp()`

## Asynchronous JavaScript And XML (AJAX)

Is a technique for Web development which encompasses a number of other
inter-related techniques and standards:

-   CSS -- to style information
-   Javascript and DOM -- to handle information
-   XML -- to structure data exchanged by client and server
-   XMLHttpRequest objects -- for client-server communication

Enables updates to be made to the webpage content at the client end without
reloading the whole page.

### AJAX Steps

1.  Triggered off some local action eg. clicking a button, filling in a form
    etc ...
2.  Sends off an XMLHttpRequest to the server
    a)  Asks for new data from server
    b)  Request is normally Asynchronous.
3.  JavaScript code does not wait for the reply -- but just deals with this
    when it arrives.
4.  When the reply arrives the client typically updates some part of its view
    of the page.

Typically it will be a request to a server side script, such as a php file etc.
Script can examine the incoming request to determine what the client wants.
Returns a reply, typically encoded as XML (or JSON). Might include information
retrieved from a server side data base.

### Benefits of AJAX

-   AJAX allows us to make the process more interactive.
-   Make small updates to the page as the user is interacting with it.
-   Flagging problems faster.
-   Avoids needing reloading lots of data for a large web page.

### Format of Results

Text or HTML:

-   Useful if we just have a single item to display and don't want to do any
    processing on the data at the client end.

XML:

-   Can return multiple data items
-   Structured as a XML document
-   Useful if want to add data to different parts of a page.

JSON:

-   JavaScript Object Notation.
-   More compact that XML and faster to parse.
-   Can be converted to a JavaScript object using the built in `eval()`
    function.

### AJAX Without JQuery

Normally use XMLHttpRequest object.

With Internet Explorer 5 and 6, where we use ActiveX
`xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");`

We then use open to specify the server and send to make the request, e.g.:

``` {.javascript}
xmlhttp = new XMLHttpRequest();
xmlhttp.open("GET", "server.php?param1=fred", true);
xmlhttp.send();
```

Wait for the reply as 'statechange' event on the XMLHttpRequest object.

``` {.javascript}
 xmlhttp.onreadystatechange = function () {
   if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

   }
 }
```

When request is finished and status is 200 ie OK the response is available in
`xmlhttp.ResponseText` for text and in `xmlhttp.ResponseXML` for XML.

### AJAX with JQuery

**Load a file's content from a server with load()** <br/>

`node.load(URL, calldata, callback);` -- for simple updates

**GET with JQuery** <br/>

`$.get(URL, calldata, callback, datatype);`

**POST with JQuery** <br/>

`$.post(URL, calldata, callback, datatype);`

Callback signtature is `function(data, status) { ... }`.

**NOTE** <br/> GET may return cached data, POST never does

Datatype could be:

-   `"xml"`
-   `"json"`
-   `"text"`
-   `"html"`
