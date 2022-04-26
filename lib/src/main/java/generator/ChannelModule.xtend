package generator

import utils.Global
import utils.Save

class ChannelModule {
	def create(){
		Global.model.vertexSet()
					.stream()
					.filter([v|v.hasTrait("impl::TokenizableDataBlock")])
					.forEach([v|
						process(v)
					])		
	}
	
	def process(Vertex vertex){
		
	}
}