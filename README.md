Create a book:

```
curl -d '{ "isbn": "12345", "quantity": 5, "price": 42.99, "title": "Learning Ratpack", "author": "Dan Woods", "publisher": "OReilly" }' -H "Content-type: application/json" -v localhost:5050/book
```

Get all books:

```
curl localhost:5050/book
```

Get a specific book:

```
curl localhost:5050/book/12345
```

Get a specific book as Handlebars HTML:

```
curl -H "Accept: text/html" localhost:5050/book/12345
```
