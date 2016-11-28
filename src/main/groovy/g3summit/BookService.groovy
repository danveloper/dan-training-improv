package g3summit

import ratpack.exec.Operation
import ratpack.exec.Promise

public interface BookService {
  Promise<List<Book>> list()
  Promise<Book> getBook(String isbn)
  Operation save(Book book)
  Operation remove(String isbn)
}
