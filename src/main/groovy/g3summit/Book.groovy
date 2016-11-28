package g3summit

import groovy.transform.Immutable

@Immutable
public class Book {
  String isbn
  long quantity
  BigDecimal price
  String title
  String author
  String publisher
}
