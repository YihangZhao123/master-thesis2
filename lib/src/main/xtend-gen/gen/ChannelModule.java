package gen;

import forsyde.io.java.core.Vertex;
import java.util.function.Consumer;
import java.util.function.Predicate;
import template.Template;
import utils.Global;

@SuppressWarnings("all")
public class ChannelModule extends ModuleFather implements gen.Module {
  public void create() {
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return (v.hasTrait("impl::TokenizableDataBlock")).booleanValue();
      }
    };
    final Consumer<Vertex> _function_1 = new Consumer<Vertex>() {
      public void accept(final Vertex v) {
        ChannelModule.this.process(v);
      }
    };
    Global.model.vertexSet().stream().filter(_function).forEach(_function_1);
  }
  
  public void process(final Vertex vertex) {
    final Consumer<Template> _function = new Consumer<Template>() {
      public void accept(final Template template) {
        template.create();
      }
    };
    this.templates.stream().forEach(_function);
  }
}
