# Curl 

## Basic Syntax

```sh
curl <URL>
```

## Useful Options

### Include Headers

```sh
curl -i <URL>
```

### GET Request

```sh
curl -G <URL>
```

### User

```sh
curl -u <user_name:password> <URL>
# or
curl -u <user_name> <URL>
# or
curl --user <user_name:password>
# or
curl --user <user_name>
```

## References

- <https://developer.github.com/v3/>
- `curl(1)`
