package gen;

import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import utils.Global;

@SuppressWarnings("all")
public class Generator {
  private Set<gen.Module> modules;
  
  private Set<Schedule> schedules;
  
  public Generator() {
    HashSet<gen.Module> _hashSet = new HashSet<gen.Module>();
    this.modules = _hashSet;
  }
  
  public void create() {
    this.schedule();
    final Consumer<gen.Module> _function = new Consumer<gen.Module>() {
      public void accept(final gen.Module module) {
        module.create();
      }
    };
    this.modules.stream().forEach(_function);
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
