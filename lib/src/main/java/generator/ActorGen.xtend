package generator

class ActorGen {
	def gen(){
		var set = Global.model.vertexSet()
			.stream()
			.filter([v|SDFComb.conforms(v)]).collect(Collectors.toSet())
			
		for(Vertex v: set){
			create(v)
		}				
	}	
}