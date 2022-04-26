package template.nonRTOS.subsystem

import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel
import gen.Schedule
import generator.generator
import java.util.stream.Collectors
import template.Template
import utils.Global
import utils.Name
import utils.Save

class multi implements Template{
	
	override create() {
		Global.schedules.stream().forEach([s| 
					Save.save(generator.root+"/inc/subsystem_"+s.tile.getIdentifier()+".h",inc(s))  
					Save.save(generator.root+"/src/subsystem_"+s.tile.getIdentifier()+".c",src(s))
		])
	}
	def String src(Schedule schedule){
		var tile=schedule.tile
		'''
		#include "../inc/subsystem_«Name.name(tile)».h"
		«FOR channel:schedule.channels SEPARATOR"" AFTER""»
			«IF channel!==null»
				«var channelName=Name.name(channel)»
				«IF SDFChannel.conforms(channel)»
					extern circularFIFO_«channelName» channel_«channelName»;
					extern token_«channelName» arr_«channelName»[];
					extern int buffersize_«channelName»;
				«ELSE»
					extern circularFIFO_«channelName» channel_«channelName»;
				«ENDIF»
			«ENDIF»
		«ENDFOR»
		
		void subsystem_«tile.getIdentifier()»(){
			«FOR channel:schedule.channels  SEPARATOR "" AFTER "" »
			«IF channel!==null»
			«var channelName=Name.name(channel)»
				«IF SDFChannel.conforms(channel)»
				init_circularFIFO_«channelName»(&channel_«channelName»,arr_«channelName»,buffersize_«channelName»);
				«ENDIF»
			«ENDIF»
			«ENDFOR»			
«««			«subsystemHelp.sdfDelayHelpA(channels)»
			
			while(1){
				
				
				«FOR actor:schedule.slots SEPARATOR "" AFTER "\n"»
				«IF actor!==null»
				actor_«Name.name(actor)»(«subsystemHelp.actorParameter(actor)»);
				«ENDIF»
				«ENDFOR»		
			
			}	
			
		}
		'''		
	}
	
	
	def String inc(Schedule schedule){
		var channels=Global.model.vertexSet().stream()
											 .filter([v|SDFChannel::conforms(v)])
											 .collect(Collectors.toSet())
		'''
		#ifndef SUBSYSTEM_«Name.name(schedule.tile).toUpperCase()»_H_
		#define SUBSYSTEM_«Name.name(schedule.tile).toUpperCase()»_H_
		#include<stdlib.h>
		#include <stdio.h>	
		«FOR actor:schedule.slots SEPARATOR " " AFTER ""»
		«IF actor!==null»
		#include "../inc/sdfcomb_«Name.name(actor)».h"
		«ENDIF»
		«ENDFOR»
		
		«FOR channel:schedule.channels SEPARATOR" " AFTER""»
		«IF channel!==null»
		#include "../inc/sdfchannel_«Name.name(channel)».h"
		«ENDIF»
		«ENDFOR»
		
		void subsystem_«Name.name(schedule.tile)»();
		#endif			
		'''
	}	
	
}