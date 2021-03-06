package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import generator.generator;
import org.eclipse.xtext.xbase.lib.InputOutput;
import template.nonRTOS.actor.actorInc;
import template.nonRTOS.actor.actorSrc;
import template.nonRTOS.fifo.circular.A.channelInc;
import template.nonRTOS.fifo.circular.A.channelSrc;
import template.nonRTOS.spinlock.spinlock;
import template.nonRTOS.subsystem.configInc;
import template.nonRTOS.subsystem.multi;
import utils.Load;

/**
 * demo for multiprocessor
 */
@SuppressWarnings("all")
public class demo2 {
  public static void main(final String[] args) {
    final String path1 = "forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
    final String path2 = "forsyde-io\\sobel-application.fiodl";
    final String root = "generateCode\\c\\multi";
    ForSyDeSystemGraph model = Load.load(path1);
    generator gen = new generator(model, root);
    channelInc _channelInc = new channelInc();
    gen.add(_channelInc);
    channelSrc _channelSrc = new channelSrc();
    gen.add(_channelSrc);
    spinlock _spinlock = new spinlock();
    gen.add(_spinlock);
    actorInc _actorInc = new actorInc();
    gen.add(_actorInc);
    actorSrc _actorSrc = new actorSrc();
    gen.add(_actorSrc);
    multi _multi = new multi();
    gen.add(_multi);
    configInc _configInc = new configInc();
    gen.add(_configInc);
    gen.create();
    InputOutput.<String>println("end!");
  }
}
