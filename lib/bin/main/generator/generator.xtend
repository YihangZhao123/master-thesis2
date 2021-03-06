package generator

import forsyde.io.java.core.ForSyDeSystemGraph
import java.util.HashSet
import java.util.Set
import template.Template
import utils.Global

import static utils.Global.*

import java.util.stream.Collectors
import schedule.Schedule

class generator {

	public static String root = null
	Set<Template> set = new HashSet
	Set<Schedule> schedules

	new(ForSyDeSystemGraph model, String root) {
		this.root = root
		Global.model = model
	}

	def create() {
		schedule()
		set.stream().forEach([template|template.create()])

	}

	def add(Template template) {
		set.add(template)
	}

	def schedule() {
		schedules = Global.model.vertexSet().stream().filter([v|v.hasTrait("platform::GenericProcessingModule")]).
			map([ v |
				new Schedule(v)
			]).collect(Collectors.toSet())
		Global.schedules = schedules

	}

}
