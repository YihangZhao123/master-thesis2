package generator

import forsyde.io.java.core.ForSyDeSystemGraph

class generator2 {
	public static String root=null	
	public ForSyDeSystemGraph model
	def create(){
		channelLibraryModule()
		actorLibraryModule()
		subsystemModule()
	}
		
}