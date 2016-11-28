import g3summit.Book
import g3summit.BookRenderer
import g3summit.BookService
import g3summit.DefaultBookService

import static ratpack.groovy.Groovy.ratpack
import ratpack.registry.Registry
import ratpack.handlebars.HandlebarsModule

ratpack {

  bindings {
    bind(BookService, DefaultBookService)
    bindInstance(new BookRenderer())
    module(HandlebarsModule)
  }

  handlers {

    prefix("book/:isbn") {

      all { BookService bookService ->
        def isbn = allPathTokens.isbn
        def book = bookService.getBook(isbn)
        next(Registry.single(book))
      }

      get { Book book ->
        render book
      }
    }
  }
}
