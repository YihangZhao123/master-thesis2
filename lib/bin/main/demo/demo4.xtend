package demo

import forsyde.io.java.core.Vertex
import gen.Schedule
import java.util.HashSet
import java.util.Set
import utils.Load
import java.util.stream.Collectors
import utils.Global

class demo4 {
	def static void main(String[] args) {
		val forsyde="forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
		val root="generateCode\\c\\single"
		var model = Load.load(forsyde);	
		
		var Set<Vertex> s = new HashSet
		
		var schedules=model.vertexSet().stream()
						 .filter([v|v.hasTrait("platform::GenericProcessingModule")])
						 .map([v|new Schedule(v)]).collect(Collectors.toSet())
		for(Schedule p:schedules){
			p.print()
		}
	}
}