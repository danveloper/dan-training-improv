package g3summit

import ratpack.handling.Context
import ratpack.render.RendererSupport

import static ratpack.jackson.Jackson.json

class BookRenderer extends RendererSupport<Book> {
  @Override
  void render(Context ctx, Book book) throws Exception {
    ctx.render(json(book))
  }
}
