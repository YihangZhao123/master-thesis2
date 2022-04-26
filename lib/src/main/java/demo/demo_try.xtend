package demo

import utils.Load
import forsyde.io.java.core.ForSyDeSystemGraph
import forsyde.io.java.drivers.ForSyDeModelHandler

class demo_try {
	def static void main(String[] args) {
		val path="forsyde-io\\sobel-application.fiodl"
		
		var ForSyDeModelHandler handler= null;
		var ForSyDeSystemGraph model = null;		
		handler= new ForSyDeModelHandler();
		model = handler.loadModel(path);			
	}
}