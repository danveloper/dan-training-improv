import static ratpack.groovy.Groovy.ratpack
import ratpack.registry.Registry

ratpack {
  handlers {
    all {
      next(Registry.single("Hello World"))
    }

    all {
      render get(String)
    }
  }
}
