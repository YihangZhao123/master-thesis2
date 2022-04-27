package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.eclipse.xtext.xbase.lib.InputOutput;
import utils.Global;
import utils.Load;
import utils.Name;

@SuppressWarnings("all")
public class demo4 {
  public static void main(final String[] args) {
    final String forsyde = "forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
    final String root = "generateCode\\c\\single";
    ForSyDeSystemGraph model = Load.load(forsyde);
    Set<Vertex> s = new HashSet<Vertex>();
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return (v.hasTrait("impl::TokenizableDataBlock")).booleanValue();
      }
    };
    final Consumer<Vertex> _function_1 = new Consumer<Vertex>() {
      public void accept(final Vertex v) {
        InputOutput.<String>println(Name.name(v));
      }
    };
    Global.model.vertexSet().stream().filter(_function).forEach(_function_1);
  }
}
