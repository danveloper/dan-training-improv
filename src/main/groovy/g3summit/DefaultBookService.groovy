package g3summit

import groovy.sql.Sql
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.inject.Inject

class DefaultBookService implements BookService {

  final Sql sql

  @Inject
  DefaultBookService(Sql sql) {
    this.sql = sql
    createTables()
  }

  void createTables() {
    Blocking.get {
      sql.executeInsert("drop table if exists books")
      sql.executeInsert("create table books (isbn varchar(13) primary key, quantity int, price numeric(15, 2), title varchar(50), author varchar(50), publisher varchar(50))")
    }.operation().then()
  }

  @Override
  Promise<List<Book>> list() {
    // TODO: implement getting all books on a blocking thread
    return null
  }

  @Override
  Promise<Book> getBook(String isbn) {
    // TODO: implement getting a single book on a blocking thread
    return null
  }

  @Override
  Operation save(Book book) {
    // TODO: implement saving a single book on a blocking thread
    return null
  }

  @Override
  Operation remove(String isbn) {
    // TODO: implement removing a book on a blocking thread
    return null
  }
}
