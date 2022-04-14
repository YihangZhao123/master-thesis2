package demo

import generator.generator

import template.nonRTOS.actor.actorInc
import template.nonRTOS.actor.actorSrc
import template.nonRTOS.fifo.circular.A.channelInc
import template.nonRTOS.fifo.circular.A.channelSrc
import utils.Load
import template.nonRTOS.subsystem.*
import template.nonRTOS.actor.spinlock.spinlock
/**
 * demo for multiprocessor
 */
class demo1 {
	def static void main(String[] args) {
		val forsyde="forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
		//val forsyde="forsyde-io\\test1.forsyde.xmi"
		val root="generateCode\\c\\multi"
		var model = Load.load(forsyde);	
		
		var gen = new generator(model,root)
		
		gen.add(new channelInc())
		gen.add(new channelSrc())
		gen.add(new spinlock())
		
		gen.add(new actorInc())
		gen.add(new actorSrc())
		
		gen.add(new subsystemMultiprocessor())
		gen.add(new configInc())
		
		gen.create()
		
		println("end!")
	}
}