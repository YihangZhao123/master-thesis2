package template.nonRTOS.subsystem;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import utils.Global;
import utils.Name;
import utils.Query;

@SuppressWarnings("all")
public class subsystemHelp {
  public static String actorParameter(final Vertex vertex) {
    String _xblockexpression = null;
    {
      String name = Name.name(vertex);
      final Predicate<EdgeInfo> _function = new Predicate<EdgeInfo>() {
        public boolean test(final EdgeInfo edgeinfo) {
          return (edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE) || edgeinfo.hasTrait(EdgeTrait.IMPL_DATAMOVEMENT));
        }
      };
      final Function<EdgeInfo, String> _function_1 = new Function<EdgeInfo, String>() {
        public String apply(final EdgeInfo e) {
          return e.getSourcePort().get();
        }
      };
      Set<String> out = Global.model.outgoingEdgesOf(vertex).stream().filter(_function).<String>map(_function_1).collect(Collectors.<String>toSet());
      final Predicate<EdgeInfo> _function_2 = new Predicate<EdgeInfo>() {
        public boolean test(final EdgeInfo edgeinfo) {
          return (edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE) || edgeinfo.hasTrait(EdgeTrait.IMPL_DATAMOVEMENT));
        }
      };
      final Function<EdgeInfo, String> _function_3 = new Function<EdgeInfo, String>() {
        public String apply(final EdgeInfo e) {
          return e.getTargetPort().get();
        }
      };
      Set<String> in = Global.model.incomingEdgesOf(vertex).stream().filter(_function_2).<String>map(_function_3).collect(Collectors.<String>toSet());
      TreeSet<String> inputPorts = new TreeSet<String>(in);
      TreeSet<String> outputPorts = new TreeSet<String>(out);
      StringConcatenation _builder = new StringConcatenation();
      {
        boolean _hasElements = false;
        for(final String port : inputPorts) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(",", "");
          }
          _builder.append("&channel_");
          String _sDFChannelName = Query.getSDFChannelName(vertex, port, Global.model);
          _builder.append(_sDFChannelName);
          _builder.append(",");
          int _portRate = Query.getPortRate(SDFComb.enforce(vertex), port);
          _builder.append(_portRate);
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements) {
          _builder.append(" ");
        }
      }
      {
        int _size = inputPorts.size();
        boolean _equals = (_size == 0);
        if (_equals) {
          {
            boolean _hasElements_1 = false;
            for(final String port_1 : outputPorts) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate(",", "");
              }
              _builder.append("&channel_");
              String _sDFChannelName_1 = Query.getSDFChannelName(vertex, port_1, Global.model);
              _builder.append(_sDFChannelName_1);
              _builder.append(",");
              int _portRate_1 = Query.getPortRate(SDFComb.enforce(vertex), port_1);
              _builder.append(_portRate_1);
              _builder.newLineIfNotEmpty();
            }
            if (_hasElements_1) {
              _builder.append(" ");
            }
          }
        } else {
          {
            boolean _hasElements_2 = false;
            for(final String port_2 : outputPorts) {
              if (!_hasElements_2) {
                _hasElements_2 = true;
              } else {
                _builder.appendImmediate(" ", "");
              }
              _builder.append(",&channel_");
              String _sDFChannelName_2 = Query.getSDFChannelName(vertex, port_2, Global.model);
              _builder.append(_sDFChannelName_2);
              _builder.append(",");
              int _portRate_2 = Query.getPortRate(SDFComb.enforce(vertex), port_2);
              _builder.append(_portRate_2);
              _builder.newLineIfNotEmpty();
            }
            if (_hasElements_2) {
              _builder.append(" ");
            }
          }
        }
      }
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
