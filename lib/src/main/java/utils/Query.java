package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexAcessor;
import forsyde.io.java.core.VertexProperty;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import java.lang.Math;
public class Query {

	
	public static Integer getFiringSlot(SDFComb comb) {
		Map<String, VertexProperty> a = comb.getProperties();
		var  b = a.get("firingSlots").unwrap();
		var c =  (ArrayList<Integer>)b;
		
		return c.get(0);

	}


	public static int getBufferSize(SDFChannel ch){
		 Map<String, VertexProperty> a = ch.getProperties();
		Integer b = (Integer)a.get("maximumTokens").unwrap();
		
		return  b;
	}	
	
	/**
	 * return the number of tokens in the channel.
	 * @param ch is a channel
	 * @return the number of tokens in the channel ch
	 */
	public static int getBufferSize(Vertex ch){
		
		 Map<String, VertexProperty> a = ch.getProperties();
		 if(ch.hasTrait("moc::sdf::SDFChannel")) {
			 Integer ret = (Integer)a.get("maximumTokens").unwrap();
			 return ret;
		 }else {
			 long b = (Long)a.get("tokenSizeInBits").unwrap();
			 long c = (Long)a.get("maxSizeInBits").unwrap();
			 return (int) (c/b);
		 }
		
	}		
	public static long getTokenSize(SDFChannel ch) {
		Map<String, VertexProperty> a = ch.getProperties();
		long b = (Long)a.get("tokenSizeInBits").unwrap();
		
		return (long)Math.ceil( ((float)b)/8  ); 
		
	}
	public static long getTokenSize(Vertex ch) {
		Map<String, VertexProperty> a = ch.getProperties();
		long b = (Long)a.get("tokenSizeInBits").unwrap();
		return (long)Math.ceil( ((float)b)/8  ); 
		
	}	
	
	
	
	public static String getSDFChannelName(Vertex vertex, String port,ForSyDeSystemGraph model){
		var optional = VertexAcessor.getNamedPort(model
				,vertex
				,port
				,VertexTrait.MOC_SDF_SDFCHANNEL
				, VertexAcessor.VertexPortDirection.BIDIRECTIONAL
			);


		if(optional.isPresent()){
			return Name.name(optional.get());
		}
		
		
		optional = VertexAcessor.getNamedPort(model
				,vertex
				,port
				,VertexTrait.IMPL_TOKENIZABLEDATABLOCK
				, VertexAcessor.VertexPortDirection.BIDIRECTIONAL
			);		
		
		if(optional.isPresent()){
			return Name.name(optional.get());
		}		
	return "UNDEFINED";
	}


	public static String getChannelName(Vertex vertex, String port,ForSyDeSystemGraph model){
		
		
		var optional = VertexAcessor.getNamedPort(model
				,vertex
				,port
				,VertexTrait.MOC_SDF_SDFCHANNEL
				, VertexAcessor.VertexPortDirection.BIDIRECTIONAL
			);


		if(optional.isPresent()){
			return Name.name(optional.get());
		}
		
		
		optional = VertexAcessor.getNamedPort(model
				,vertex
				,port
				,VertexTrait.IMPL_TOKENIZABLEDATABLOCK
				, VertexAcessor.VertexPortDirection.BIDIRECTIONAL
			);		
		
		if(optional.isPresent()){
			return Name.name(optional.get());
		}		
	return "UNDEFINED";
		
	}
	
	
	
	
	public static  long getTokenSizeInBits(Vertex vertex) {

			Map<String, VertexProperty> a = vertex.getProperties();
			long b = (Long)a.get("tokenSizeInBits").unwrap();
			return b;
		
	}
	
	
	
	

	
	
	
	
	public static  int getPortRate(SDFComb sdf, String port) {
		if(sdf.getProduction()!=null &&  sdf.getProduction().containsKey(port)) {
			return sdf.getProduction().get(port);
			
		}else if(sdf.getConsumption()!=null &&  sdf.getConsumption().containsKey(port)) {
			return sdf.getConsumption().get(port);
		}else {
			return 1;
		}	
		
	}

	
	/**
	 * @param vertex	vertex must be a has trait SDFComb
	 */
	public static  int getWCET(Vertex vertex,ForSyDeSystemGraph model) {
		 Optional<Vertex> a;
		 Set<Vertex> wcet=new HashSet<>();
		for(Vertex v: model.vertexSet()) {
			if(v.hasTrait("WCET")) {
				wcet.add(v);
				
			}
		}
		
		for(Vertex v:wcet) {
			a= VertexAcessor.getNamedPort(model, v,"application",VertexTrait.MOC_SDF_SDFCOMB );
			if(a.isPresent()&&a.get()==vertex) {
				Map<String, VertexProperty> b = v.getProperties();
				int c = (int)b.get("time").unwrap();
				return c;
			 }
		}
		return 1;
	}
}
