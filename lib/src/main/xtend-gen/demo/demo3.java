package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import generator.generator;
import org.eclipse.xtext.xbase.lib.InputOutput;
import template.RTOS.actor.actorInc;
import template.RTOS.actor.actorSrc;
import template.RTOS.channel.channelInc;
import template.RTOS.channel.channelSrc;
import template.RTOS.subsystem.Config;
import template.RTOS.subsystem.StartTaskInc;
import template.RTOS.subsystem.StartTaskSrc;
import utils.Load;

/**
 * RTOS
 */
@SuppressWarnings("all")
public class demo3 {
  public static void main(final String[] args) {
    final String forsyde = "forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
    final String root = "generateCode\\c\\freertos";
    ForSyDeSystemGraph model = Load.load(forsyde);
    generator gen = new generator(model, root);
    actorInc _actorInc = new actorInc();
    gen.add(_actorInc);
    actorSrc _actorSrc = new actorSrc();
    gen.add(_actorSrc);
    channelSrc _channelSrc = new channelSrc();
    gen.add(_channelSrc);
    channelInc _channelInc = new channelInc();
    gen.add(_channelInc);
    Config _config = new Config();
    gen.add(_config);
    StartTaskInc _startTaskInc = new StartTaskInc();
    gen.add(_startTaskInc);
    StartTaskSrc _startTaskSrc = new StartTaskSrc();
    gen.add(_startTaskSrc);
    template.RTOS.subsystem.System _system = new template.RTOS.subsystem.System();
    gen.add(_system);
    gen.create();
    InputOutput.<String>println("end!");
  }
}
