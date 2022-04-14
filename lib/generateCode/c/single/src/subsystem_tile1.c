	#include<stdlib.h>
	#include <stdio.h>	
	#include "../inc/sdfcomb_getPx.h"
	#include "../inc/sdfchannel_gysig.h" 
	#include "../inc/sdfchannel_gxsig.h"

	extern circularFIFO_gysig channel_gysig;
	extern token_gysig arr_gysig[];
	extern int buffersize_gysig;

	extern circularFIFO_gxsig channel_gxsig;
	extern token_gxsig arr_gxsig[];
	extern int buffersize_gxsig;
	
	void subsystem_tile1(){
		init_circularFIFO_gysig(&channel_gysig,arr_gysig,buffersize_gysig);
		init_circularFIFO_gxsig(&channel_gxsig,arr_gxsig,buffersize_gxsig);

		while(1){
			actor_getPx(&channel_gxsig,6,
			&channel_gysig,6
			);

					
		}	
		
	}




