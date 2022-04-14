	#include<stdlib.h>
	#include <stdio.h>	
	#include "../inc/sdfcomb_Abs.h"
	#include "../inc/sdfchannel_absysig.h" 
	#include "../inc/sdfchannel_absxsig.h"

	extern circularFIFO_absysig channel_absysig;
	extern token_absysig arr_absysig[];
	extern int buffersize_absysig;

	extern circularFIFO_absxsig channel_absxsig;
	extern token_absxsig arr_absxsig[];
	extern int buffersize_absxsig;
	
	void subsystem_tile2(){
		init_circularFIFO_absysig(&channel_absysig,arr_absysig,buffersize_absysig);
		init_circularFIFO_absxsig(&channel_absxsig,arr_absxsig,buffersize_absxsig);

		while(1){
			actor_Abs(&channel_absxsig,1,
			&channel_absysig,1
			);

					
		}	
		
	}



