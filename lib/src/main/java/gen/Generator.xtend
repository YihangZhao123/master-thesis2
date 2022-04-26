package gen

import java.util.Set
import java.util.HashSet
import utils.Global
import java.util.stream.Collectors

class Generator {
	Set<gen.Module> modules
	Set<Schedule> schedules

	new() {
		modules = new HashSet
	}

	def create() {
		this.schedule()
		modules.stream().forEach([module|module.create()])
	}

	def schedule() {
		schedules = Global.model.vertexSet().stream().filter([v|v.hasTrait("platform::GenericProcessingModule")]).map([ v |
			new Schedule(v)
		]).collect(Collectors.toSet())
		Global.schedules=schedules
		
	}
}
