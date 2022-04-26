package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.InputOutput;
import utils.Load;

@SuppressWarnings("all")
public class demo4 {
  public static void main(final String[] args) {
    final String forsyde = "forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
    final String root = "generateCode\\c\\single";
    ForSyDeSystemGraph model = Load.load(forsyde);
    Set<Vertex> s = new HashSet<Vertex>();
    Set<Vertex> _vertexSet = model.vertexSet();
    for (final Vertex v : _vertexSet) {
      Boolean _hasTrait = v.hasTrait("WCET");
      if ((_hasTrait).booleanValue()) {
        InputOutput.<String>println(v.getIdentifier());
        s.add(v);
      }
    }
  }
}
