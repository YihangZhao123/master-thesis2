#include <stdlib.h>
#include <stdio.h>
#include "../include/freertos_sdfcomb_Gx.h"
#include "../include/freertos_config.h"
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "timers.h"
#include "../include/freertos_sdfchannel_absysig.h" 

#include "../include/freertos_sdfchannel_gxsig.h" 

#include "../include/freertos_sdfchannel_gysig.h" 

#include "../include/freertos_sdfchannel_absxsig.h"

/*
* Message Queue
*/
extern QueueHandle_t msg_queue_gxsig;
extern QueueHandle_t msg_queue_absxsig;



/*
* Task Stack
*/
StackType_t task_Gx_stk[TASK_STACKSIZE];
StaticTask_t tcb_Gx;
/*
* Soft Timer and Semaphore
*/
SemaphoreHandle_t task_sem_Gx;
TimerHandle_t task_timer_Gx;

inline static void read_channel_in_gx(QueueHandle_t src_msg_queue, size_t num, token_gxsig  dst[]){
	
	for(size_t i=0;i <num;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		xQueueReceive(src_msg_queue,dst+i, portMAX_DELAY);		
	}
}


inline static void write_channel_in_resx(token_absxsig src[],size_t num,QueueHandle_t dst_msg_queue){
	
	for(size_t i=0 ; i < num ;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		BaseType_t ret=	xQueueSend(dst_msg_queue,src+i,portMAX_DELAY);
	}
}

void timer_Gx_callback(TimerHandle_t xTimer){
	xSemaphoreGive(task_sem_Gx);
}				

//void func_actorName_combinator(portName[], portName_rate ....)
inline static void combinator(	
token_gxsig gx[] , const size_t gx_rate
 ,token_absxsig  resx[],const size_t resx_rate
){
	printf("in actor Gx\n");

}

			
void task_Gx(void* pdata){
	//array aiming to storing data from input ports
	long gx_rate = 6;
	token_gxsig gx[gx_rate];

		
	//array aiming to writing data to input ports
	long resx_rate = 1;
	token_absxsig resx[resx_rate];

		while(1){
		/*
		*	read from channel
		*/
		read_channel_in_gx(msg_queue_gxsig,gx_rate,gx);
		/*
		*	combinator function
		*/
		combinator(gx,gx_rate , resx,resx_rate );	
	
		/*
		*	write from channel
		*/
		write_channel_in_resx(resx,resx_rate,msg_queue_absxsig);
		
		xSemaphoreTake(task_sem_Gx, portMAX_DELAY);	
			
		
	}
	
}




