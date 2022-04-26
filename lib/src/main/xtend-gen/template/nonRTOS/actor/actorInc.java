package template.nonRTOS.actor;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import generator.generator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.Template;
import utils.Global;
import utils.Name;
import utils.Query;
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
      TreeSet<String> inputPorts = actorhelp.findInPutPort(vertex);
      TreeSet<String> outputPorts = actorhelp.findOutPutPort(vertex);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#ifndef  ");
      _builder.append(tmp);
      _builder.newLineIfNotEmpty();
      _builder.append("#define ");
      _builder.append(tmp);
      _builder.append("\t\t\t");
      _builder.newLineIfNotEmpty();
      {
        boolean _hasElements = false;
        for(final String port : inputPorts) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate("", "");
          }
          _builder.append("#include \"../inc/sdfchannel_");
          String _channelName = Query.getChannelName(vertex, port, Global.model);
          _builder.append(_channelName);
          _builder.append(".h\"");
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements) {
          _builder.append("");
        }
      }
      {
        boolean _hasElements_1 = false;
        for(final String port_1 : outputPorts) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          } else {
            _builder.appendImmediate("", "");
          }
          _builder.append("#include \"../inc/sdfchannel_");
          String _channelName_1 = Query.getChannelName(vertex, port_1, Global.model);
          _builder.append(_channelName_1);
          _builder.append(".h\"");
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements_1) {
          _builder.append("");
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
      _builder.append("#endif\t\t");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
