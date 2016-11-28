package g3summit

import groovy.sql.GroovyRowResult
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
    sql.executeInsert("drop table if exists books")
    sql.executeInsert("create table books (isbn varchar(13) primary key, quantity int, price numeric(15, 2), title varchar(50), author varchar(50), publisher varchar(50))")
  }

  @Override
  Promise<List<Book>> list() {
    Blocking.get {
      sql.rows """
        select * from books
      """
    }.map { rows ->
      rows.collect(this.&mapFromRow)
    }
  }

  private static Book mapFromRow(GroovyRowResult row) {
    new Book(
        row["ISBN"] as String,
        row["QUANTITY"] as int,
        row["PRICE"] as BigDecimal,
        row["TITLE"] as String,
        row["AUTHOR"] as String,
        row["PUBLISHER"] as String
    )
  }

  @Override
  Promise<Book> getBook(String isbn) {
    Blocking.get {
      sql.firstRow """
      select * from books where isbn = ${isbn}
      """
    }.map(this.&mapFromRow)
  }

  @Override
  Operation save(Book book) {
    Blocking.get {
      sql.executeInsert """
        insert into books values (${book.isbn}, ${book.quantity}, ${book.price}, ${book.title}, ${book.author}, ${book.publisher})
      """
    }.operation()
  }

  @Override
  Operation remove(String isbn) {
    // TODO: implement removing a book on a blocking thread
    return null
  }
}
