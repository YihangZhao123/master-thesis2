package template.nonRTOS.actor;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import generator.generator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.Template;
import utils.Global;
import utils.Name;
import utils.Save;

@SuppressWarnings("all")
public class actorInc implements Template {
  public void create() {
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return (SDFComb.conforms(v)).booleanValue();
      }
    };
    Set<Vertex> set = Global.model.vertexSet().stream().filter(_function).collect(Collectors.<Vertex>toSet());
    for (final Vertex v : set) {
      Save.save(this.path(v), this.inc(v));
    }
  }
  
  private String path(final Vertex vertex) {
    String _name = Name.name(vertex);
    String _plus = ((generator.root + "/inc/sdfcomb_") + _name);
    return (_plus + ".h");
  }
  
  public String inc(final Vertex vertex) {
    String _xblockexpression = null;
    {
      String name = Name.name(vertex);
      String tmp = name.toUpperCase();
      tmp = (tmp + "_H_");
      final Predicate<Vertex> _function = new Predicate<Vertex>() {
        public boolean test(final Vertex v) {
          return (SDFChannel.conforms(v)).booleanValue();
        }
      };
      final Function<Vertex, SDFChannel> _function_1 = new Function<Vertex, SDFChannel>() {
        public SDFChannel apply(final Vertex v) {
          return SDFChannel.enforce(v);
        }
      };
      Set<SDFChannel> sdfchannels = Global.model.vertexSet().stream().filter(_function).<SDFChannel>map(_function_1).collect(Collectors.<SDFChannel>toSet());
      final Predicate<EdgeInfo> _function_2 = new Predicate<EdgeInfo>() {
        public boolean test(final EdgeInfo edgeinfo) {
          return edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE);
        }
      };
      final Function<EdgeInfo, String> _function_3 = new Function<EdgeInfo, String>() {
        public String apply(final EdgeInfo e) {
          return e.getSourcePort().get();
        }
      };
      Set<String> out = Global.model.outgoingEdgesOf(vertex).stream().filter(_function_2).<String>map(_function_3).collect(Collectors.<String>toSet());
      final Predicate<EdgeInfo> _function_4 = new Predicate<EdgeInfo>() {
        public boolean test(final EdgeInfo edgeinfo) {
          return edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE);
        }
      };
      final Function<EdgeInfo, String> _function_5 = new Function<EdgeInfo, String>() {
        public String apply(final EdgeInfo e) {
          return e.getTargetPort().get();
        }
      };
      Set<String> in = Global.model.incomingEdgesOf(vertex).stream().filter(_function_4).<String>map(_function_5).collect(Collectors.<String>toSet());
      TreeSet<String> inputPorts = new TreeSet<String>(in);
      TreeSet<String> outputPorts = new TreeSet<String>(out);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#ifndef  ");
      _builder.append(tmp);
      _builder.newLineIfNotEmpty();
      _builder.append("#define ");
      _builder.append(tmp);
      _builder.newLineIfNotEmpty();
      {
        boolean _hasElements = false;
        for(final SDFChannel ch : sdfchannels) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate("", "");
          }
          _builder.append("#include \"../inc/sdfchannel_");
          String _name = Name.name(ch);
          _builder.append(_name);
          _builder.append(".h\"");
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements) {
          _builder.append("\n");
        }
      }
      _builder.append("void actor_");
      _builder.append(name);
      _builder.append("(");
      String _actorParameterSignature = actorhelp.actorParameterSignature(vertex, inputPorts, outputPorts);
      _builder.append(_actorParameterSignature);
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.newLine();
      _builder.append("#endif\t\t");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
