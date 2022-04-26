package generator;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import gen.Schedule;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import template.Template;
import utils.Global;

@SuppressWarnings("all")
public class generator {
  public static String root = null;
  
  private Set<Template> set = new HashSet<Template>();
  
  private Set<Schedule> schedules;
  
  public generator(final ForSyDeSystemGraph model, final String root) {
    generator.root = root;
    Global.model = model;
  }
  
  public void create() {
    this.schedule();
    for (final Template s : this.set) {
      s.create();
    }
  }
  
  public boolean add(final Template template) {
    return this.set.add(template);
  }
  
  public Set<Schedule> schedule() {
    Set<Schedule> _xblockexpression = null;
    {
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
      this.schedules = Global.model.vertexSet().stream().filter(_function).<Schedule>map(_function_1).collect(Collectors.<Schedule>toSet());
      _xblockexpression = Global.schedules = this.schedules;
    }
    return _xblockexpression;
  }
}
