package gen

import java.util.Set
import template.Template
import java.util.HashSet
import forsyde.io.java.core.Vertex

class ModuleFather {
	protected	Set<Template> templates
	new (){
		templates=new HashSet
	}
	new (Template a){
		templates=new HashSet
		templates.add(a)
	}
	new (Template a,Template b){
		templates=new HashSet
		templates.add(a)
		templates.add(b)
	}	

}