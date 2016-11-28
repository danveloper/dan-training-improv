import g3summit.BookService
import g3summit.DefaultBookService

import static ratpack.groovy.Groovy.ratpack
import ratpack.registry.Registry

ratpack {

  bindings {
    bind(BookService, DefaultBookService)
    // REGISTER A BOOK RENDERER
  }

  handlers {

    prefix("book/:isbn") {

      all { BookService bookService ->
        def isbn = allPathTokens.isbn
        def book = bookService.getBook(isbn)
        next(Registry.single(book))
      }

      get {
        // TODO RENDER A BOOK
      }
    }
  }
}
