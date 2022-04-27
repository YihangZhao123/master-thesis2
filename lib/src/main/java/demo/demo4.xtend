package demo

import forsyde.io.java.core.Vertex

import schedule.Schedule
import java.util.HashSet
import java.util.Set
import utils.Load
import java.util.stream.Collectors
import utils.Global
import utils.Name

class demo4 {
	def static void main(String[] args) {
		val forsyde="forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
		val root="generateCode\\c\\single"
		var model = Load.load(forsyde);	
		
		var Set<Vertex> s = new HashSet
		Global.model.vertexSet().stream().filter([v|v.hasTrait("impl::TokenizableDataBlock")])
		.forEach([v|println(Name.name(v))])
		
	}
}