package g3summit

import ratpack.handling.Context
import ratpack.render.RendererSupport

class BookRenderer extends RendererSupport<Book> {
  @Override
  void render(Context ctx, Book book) throws Exception {
    // ProTip: look at ratpack.jackson.Jackson.json method
    // TODO IMPLEMENT ME TO RENDER JSON
  }
}
