package g3summit

import ratpack.handling.Context
import ratpack.render.RendererSupport

import static ratpack.handlebars.Template.handlebarsTemplate
import static ratpack.jackson.Jackson.json

class BookRenderer extends RendererSupport<Book> {
  @Override
  void render(Context ctx, Book book) throws Exception {
    // TODO:
    // - Implement rendering book as JSON when application/json is specified
    // - Implement rendering book to handlebars template when no content-type or HTML is specified
    //
    // LifeProTips:
    //  - Look at ratpack.handlebars.Template.handlebarsTemplate
    //  - Look at Context#byContent

    def tmpl = handlebarsTemplate("book", book, "text/html")

    ctx.byContent { c -> c
        .json { ctx.render(json(book)) }
        .html { ctx.render(tmpl) }
        .noMatch("text/html")
    }
  }
}
