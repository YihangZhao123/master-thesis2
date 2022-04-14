package generator

import forsyde.io.java.core.ForSyDeSystemGraph
import java.util.HashSet
import java.util.Set
import template.Template
import utils.Global

import static utils.Global.*

class generator {

	public static String root=null	
	Set<Template> set=new HashSet
	
	
	
	new(ForSyDeSystemGraph model,String root){
		this.root=root
		Global.model=model
		
//		var include=root+"/include"
//		var src=root+"/source"
//		
//		var f1 = new File(include)
//		if(!f1.exists()){
//			f1.mkdirs()		
//		}	
//		
//		f1 = new File(src)
//		if(!f1.exists()){
//			f1.mkdirs()		
//		}
	}
	
	def create() {
		
		for(Template s:set){
			s.create()
		}
					
	}	

	def add(Template template) {
		set.add(template)
	}	


}