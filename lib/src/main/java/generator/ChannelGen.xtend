package generator

import utils.Global
import utils.Save

class ChannelGen {
	def create(){
		Global.model.vertexSet()
					.stream()
					.filter([v|v.hasTrait("impl::TokenizableDataBlock")])
					.forEach([v|
						Save.save(path(v),src(v))
						Save.save(path(v),inc(v))
					])		
	}
}