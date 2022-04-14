package template.nonRTOS.actor

import forsyde.io.java.core.EdgeTrait
import forsyde.io.java.core.Vertex
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel
import java.util.TreeSet
import java.util.stream.Collectors
import template.Template
import utils.Global
import utils.Name
import generator.*
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb
import utils.Save

class actorInc implements Template {
	override create() {
		var set = Global.model.vertexSet()
			.stream()
			.filter([v|SDFComb.conforms(v)]).collect(Collectors.toSet())
			
		for(Vertex v: set){
			Save.save(path(v),v.inc())
		}
	}	
	
	private def String path(Vertex vertex){
		return generator.root+"/inc/sdfcomb_"+Name.name(vertex)+".h"
	}		
	
	
	def String inc(Vertex vertex) {
		var name = Name.name(vertex)
		var tmp = name.toUpperCase()
		tmp = tmp+"_H_"
		
		var sdfchannels=Global.model.vertexSet()
							 .stream()
							 .filter([v|SDFChannel::conforms(v)])
							 .map([v|SDFChannel::enforce(v)])
							 .collect(Collectors.toSet())
		


		var out= Global.model.outgoingEdgesOf(vertex).stream()
									.filter([edgeinfo|edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE)])
									.map([e|e.getSourcePort().get()])
									.collect(Collectors.toSet())
		
		
		var in = Global.model.incomingEdgesOf(vertex).stream()
									.filter([edgeinfo|edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE)])
									.map([e|e.getTargetPort().get()])
									.collect(Collectors.toSet())

		
		var TreeSet<String> inputPorts =new TreeSet(in)
		var TreeSet<String> outputPorts = new TreeSet(out)
					
		'''
			#ifndef  «tmp»
			#define «tmp»
			«FOR ch:sdfchannels SEPARATOR "" AFTER "\n" »
				#include "../inc/sdfchannel_«Name.name(ch)».h"
			«ENDFOR»
			void actor_«name»(«actorhelp.actorParameterSignature(vertex, inputPorts,outputPorts)»);
			
			
			#endif		
		'''
	}
	

	
}
