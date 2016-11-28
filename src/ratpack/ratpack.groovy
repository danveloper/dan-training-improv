import com.zaxxer.hikari.HikariConfig
import g3summit.Book
import g3summit.BookRenderer
import g3summit.BookService
import g3summit.DefaultBookService

import static ratpack.groovy.Groovy.ratpack
import ratpack.registry.Registry
import ratpack.handlebars.HandlebarsModule
import ratpack.exec.Blocking
import ratpack.h2.H2Module
import ratpack.hikari.HikariModule
import ratpack.groovy.sql.SqlModule

import static ratpack.jackson.Jackson.json

ratpack {

  bindings {
    binder { b ->
      b.bind(BookService).to(DefaultBookService).asEagerSingleton()
    }
    bindInstance(new BookRenderer())
    module(HandlebarsModule)
    module SqlModule
    module H2Module
    module HikariModule, { HikariConfig c ->
      c.addDataSourceProperty("URL", "jdbc:h2:mem:dev;INIT=CREATE SCHEMA IF NOT EXISTS DEV")
      c.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource")
    }
  }

  handlers {

    prefix("book") {
      prefix(":isbn") {
        all { BookService bookService ->
          def isbn = allPathTokens.isbn
          bookService.getBook(isbn).then { book ->
            next(Registry.single(book))
          }
        }

        all { Book book ->
          byMethod {
            get {
              render book
            }
            delete {
              // TODO: implement delete book
            }
          }
        }

        get { Book book ->
          render book
        }
      }

      all { BookService bookService ->
        byMethod {
          get {
            bookService.list().then { books ->
              render json(books)
            }
          }
          post {
            parse(Book).flatMap { book ->
              bookService.save(book).promise()
            }.then {
              response.status(201).send()
            }
          }
        }
      }
    }
  }
}
