package gen

import utils.Global
import forsyde.io.java.core.Vertex

class ChannelModule extends ModuleFather implements Module {
	
	override create() {
		Global.model.vertexSet()
					.stream()
					.filter([v|v.hasTrait("impl::TokenizableDataBlock")])
					.forEach([v|process(v)])		
	}
	 def process(Vertex vertex){
		templates.stream()
				 .forEach([template|template.create()])
	}	
}
