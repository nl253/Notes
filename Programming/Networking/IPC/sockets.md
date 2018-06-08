# Sockets

-   A network socket is an internal endpoint for sending or receiving
    data in a node on a computer network.
-   Not the fastest but by far the most portable and, as an effect, most
    popular form of IPC.

**NOTE**: If you need fast IPC between two processes on one machine, you
should look into pipes or shared memory.

## Types Of Sockets

### Client vs Server

-   "client" socket -- an endpoint of a conversation
-   "server" socket -- more like a switchboard operator

**NOTE**: low number ports are usually reserved for "well known"
services (HTTP, SNMP etc). If you're playing around, use a nice high
number (4 digits).

Creating a Socket Roughly speaking, when you clicked on the link that
brought you to this page, your browser did something like the following:

``` {.python}
import socket

# create an INET, streaming client socket
cs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# now connect to the web server on port 80 - the normal http port
cs.connect(("www.python.org", 80))
```

When the connect completes, the socket s can be used to send in a
request for the text of the page. The same socket will read the reply,
and then be destroyed. That's right, destroyed. Client sockets are
normally only used for one exchange (or a small set of sequential
exchanges).

``` {.python}
# create an INET, streaming server socket
ss = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# bind the socket to a public host, and a well-known port
ss.bind(('localhost', 80))

# become a server socket
ss.listen(5)
```

Now that we have a "server" socket, listening on port 80, we can enter
the mainloop of the web server:

``` {.python}
while True:
    # accept connections from outside
    (cs, addr) = ss.accept()
    # now do something with the client socket
    print(f'connection request from {addr}')
```

Now you can open your browser and type `http://localhost:4000/` in the
address bar. Alternatively try with `curl(1)`:

``` {.sh}
$ curl 'http://localhost:4000/'
```

There's actually 3 general ways in which this loop could work:

1.  dispatching a thread to handle `cs`
2.  create a new process to handle `cs`
3.  restructure this app to use non-blocking sockets, and multiplex
    between our "server" socket and any active clientsockets using
    select.

This *is* all a "server" socket does. It doesn't send any data. It
doesn't receive any data. It just produces "client" sockets. I.e. it is
a client-socket factory.

Each clientsocket is created in response to some other "client" socket
doing a `connect()`{.python} to the host and port the server is bound to.

As soon as a server has created that clientsocket, it goes back to
listening for more connections.

Sockets are *recycled* when the conversation ends.

The web browser's "client" socket and the web server's "client" socket
are identical beasts. That is, this is a "**peer to peer**"
conversation.

You will have to decide what the rules of etiquette for a conversation.
Normally, the connecting socket starts the conversation, by sending in a
request. But that's a design decision -- it's not a rule of sockets.

You can use `send` and `recv`, or you can transform your client socket
into a file-like beast and use `read` and `write`. **You need to use
flush on sockets**. These are buffered "files", and a common mistake is
to write something, and then read for a reply. Without a `flush` in
there, you may wait forever for the reply, because the request may still
be in your output buffer.

Now we come to the major stumbling block of sockets -- send and recv
operate on the network buffers. They do not necessarily handle all the
bytes you hand them (or expect from them), because their major focus is
handling the network buffers. In general, they return when the
associated network buffers have been filled (send) or emptied (recv).
They then tell you how many bytes they handled. It is your
responsibility to call them again until your message has been completely
dealt with.

When a recv returns 0 bytes, it means the other side has closed (or is
in the process of closing) the connection. You will not receive any more
data on this connection.

This means that a client can detect the end of the reply by receiving 0
bytes.

### Blocking Vs Non-Blocking

## Glossary

IPC

:   Inter Process Communication

Stream Socket

:   i.e. a TCP socket

INET

:   IPv4

## References

-   <https://docs.python.org/3/howto/sockets.html>
