package demo

import forsyde.io.java.core.ForSyDeSystemGraph
import forsyde.io.java.drivers.ForSyDeXMIDriver
import utils.Load
import forsyde.io.java.core.Vertex
import forsyde.io.java.core.Trait
import java.util.Set
import java.util.HashSet
import forsyde.io.java.core.VertexAcessor

class demo4 {
	def static void main(String[] args) {
		val forsyde="forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
		val root="generateCode\\c\\single"
		var model = Load.load(forsyde);	
		var Set<Vertex> s = new HashSet
		for(Vertex v: model.vertexSet()) {
			if(v.hasTrait("WCET")) {
				println(v.getIdentifier())
				s.add(v)
			}

		}
		
	}
}