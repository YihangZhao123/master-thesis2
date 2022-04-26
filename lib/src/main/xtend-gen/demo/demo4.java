package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import schedule.Schedule;
import utils.Load;

@SuppressWarnings("all")
public class demo4 {
  public static void main(final String[] args) {
    final String forsyde = "forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
    final String root = "generateCode\\c\\single";
    ForSyDeSystemGraph model = Load.load(forsyde);
    Set<Vertex> s = new HashSet<Vertex>();
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return (v.hasTrait("platform::GenericProcessingModule")).booleanValue();
      }
    };
    final Function<Vertex, Schedule> _function_1 = new Function<Vertex, Schedule>() {
      public Schedule apply(final Vertex v) {
        return new Schedule(v);
      }
    };
    Set<Schedule> schedules = model.vertexSet().stream().filter(_function).<Schedule>map(_function_1).collect(Collectors.<Schedule>toSet());
    for (final Schedule p : schedules) {
      p.print();
    }
  }
}
