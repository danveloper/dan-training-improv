package g3summit

class DefaultBookService implements BookService {

  @Override
  Book getBook(String isbn) {
    return new Book(
        isbn,
        10,
        BigDecimal.valueOf(49.99),
        "Learning Ratpack",
        "Dan Woods",
        "O'Reilly")
  }
}
