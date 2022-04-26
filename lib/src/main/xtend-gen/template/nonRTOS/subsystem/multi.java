package template.nonRTOS.subsystem;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import generator.generator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import schedule.Schedule;
import template.Template;
import utils.Global;
import utils.Name;
import utils.Save;

@SuppressWarnings("all")
public class multi implements Template {
  public void create() {
    final Consumer<Schedule> _function = new Consumer<Schedule>() {
      public void accept(final Schedule s) {
        String _identifier = s.tile.getIdentifier();
        String _plus = ((generator.root + "/inc/subsystem_") + _identifier);
        String _plus_1 = (_plus + ".h");
        Save.save(_plus_1, multi.this.inc(s));
        String _identifier_1 = s.tile.getIdentifier();
        String _plus_2 = ((generator.root + "/src/subsystem_") + _identifier_1);
        String _plus_3 = (_plus_2 + ".c");
        Save.save(_plus_3, multi.this.src(s));
      }
    };
    Global.schedules.stream().forEach(_function);
  }
  
  public String src(final Schedule schedule) {
    String _xblockexpression = null;
    {
      Vertex tile = schedule.tile;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#include \"../inc/subsystem_");
      String _name = Name.name(tile);
      _builder.append(_name);
      _builder.append(".h\"");
      _builder.newLineIfNotEmpty();
      {
        boolean _hasElements = false;
        for(final Vertex channel : schedule.channels) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate("", "");
          }
          String channelName = Name.name(channel);
          _builder.newLineIfNotEmpty();
          {
            Boolean _conforms = SDFChannel.conforms(channel);
            if ((_conforms).booleanValue()) {
              _builder.append("extern circularFIFO_");
              _builder.append(channelName);
              _builder.append(" channel_");
              _builder.append(channelName);
              _builder.append(";");
              _builder.newLineIfNotEmpty();
              _builder.append("extern token_");
              _builder.append(channelName);
              _builder.append(" arr_");
              _builder.append(channelName);
              _builder.append("[];");
              _builder.newLineIfNotEmpty();
              _builder.append("extern int buffersize_");
              _builder.append(channelName);
              _builder.append(";");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("extern circularFIFO_");
              _builder.append(channelName);
              _builder.append(" channel_");
              _builder.append(channelName);
              _builder.append(";");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        if (_hasElements) {
          _builder.append("");
        }
      }
      _builder.newLine();
      _builder.append("void subsystem_");
      String _identifier = tile.getIdentifier();
      _builder.append(_identifier);
      _builder.append("(){");
      _builder.newLineIfNotEmpty();
      {
        boolean _hasElements_1 = false;
        for(final Vertex channel_1 : schedule.channels) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          } else {
            _builder.appendImmediate("", "\t");
          }
          _builder.append("\t");
          String channelName_1 = Name.name(channel_1);
          _builder.newLineIfNotEmpty();
          {
            Boolean _conforms_1 = SDFChannel.conforms(channel_1);
            if ((_conforms_1).booleanValue()) {
              _builder.append("\t");
              _builder.append("init_circularFIFO_");
              _builder.append(channelName_1, "\t");
              _builder.append("(&channel_");
              _builder.append(channelName_1, "\t");
              _builder.append(",arr_");
              _builder.append(channelName_1, "\t");
              _builder.append(",buffersize_");
              _builder.append(channelName_1, "\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        if (_hasElements_1) {
          _builder.append("", "\t");
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("while(1){");
      _builder.newLine();
      {
        boolean _hasElements_2 = false;
        for(final Vertex actor : schedule.slots) {
          if (!_hasElements_2) {
            _hasElements_2 = true;
          } else {
            _builder.appendImmediate("", "\t\t");
          }
          {
            if ((actor != null)) {
              _builder.append("\t\t");
              _builder.append("actor_");
              String _name_1 = Name.name(actor);
              _builder.append(_name_1, "\t\t");
              _builder.append("(");
              String _actorParameter = subsystemHelp.actorParameter(actor);
              _builder.append(_actorParameter, "\t\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        if (_hasElements_2) {
          _builder.append("\n", "\t\t");
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public String inc(final Schedule schedule) {
    String _xblockexpression = null;
    {
      final Predicate<Vertex> _function = new Predicate<Vertex>() {
        public boolean test(final Vertex v) {
          return (SDFChannel.conforms(v)).booleanValue();
        }
      };
      Set<Vertex> channels = Global.model.vertexSet().stream().filter(_function).collect(Collectors.<Vertex>toSet());
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#ifndef SUBSYSTEM_");
      String _upperCase = Name.name(schedule.tile).toUpperCase();
      _builder.append(_upperCase);
      _builder.append("_H_");
      _builder.newLineIfNotEmpty();
      _builder.append("#define SUBSYSTEM_");
      String _upperCase_1 = Name.name(schedule.tile).toUpperCase();
      _builder.append(_upperCase_1);
      _builder.append("_H_");
      _builder.newLineIfNotEmpty();
      _builder.append("#include<stdlib.h>");
      _builder.newLine();
      _builder.append("#include <stdio.h>\t");
      _builder.newLine();
      {
        boolean _hasElements = false;
        for(final Vertex actor : schedule.slots) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(" ", "");
          }
          {
            if ((actor != null)) {
              _builder.append("#include \"../inc/sdfcomb_");
              String _name = Name.name(actor);
              _builder.append(_name);
              _builder.append(".h\"");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        if (_hasElements) {
          _builder.append("");
        }
      }
      _builder.newLine();
      {
        boolean _hasElements_1 = false;
        for(final Vertex channel : schedule.channels) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          } else {
            _builder.appendImmediate(" ", "");
          }
          {
            if ((channel != null)) {
              _builder.append("#include \"../inc/sdfchannel_");
              String _name_1 = Name.name(channel);
              _builder.append(_name_1);
              _builder.append(".h\"");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        if (_hasElements_1) {
          _builder.append("");
        }
      }
      _builder.newLine();
      _builder.append("void subsystem_");
      String _name_2 = Name.name(schedule.tile);
      _builder.append(_name_2);
      _builder.append("();");
      _builder.newLineIfNotEmpty();
      _builder.append("#endif\t\t\t");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
