#include <stdlib.h>
#include <stdio.h>
#include "../include/freertos_sdfcomb_Gy.h"
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
extern QueueHandle_t msg_queue_gysig;
extern QueueHandle_t msg_queue_absysig;


/*
* Task Stack
*/
StackType_t task_Gy_stk[TASK_STACKSIZE];
StaticTask_t tcb_Gy;
/*
* Soft Timer and Semaphore
*/
SemaphoreHandle_t task_sem_Gy;
TimerHandle_t task_timer_Gy;

inline static void read_channel_in_gy(QueueHandle_t src_msg_queue, size_t num, token_gysig  dst[]){
	
	for(size_t i=0;i <num;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		xQueueReceive(src_msg_queue,dst+i, portMAX_DELAY);		
	}
}


inline static void write_channel_in_resy(token_absysig src[],size_t num,QueueHandle_t dst_msg_queue){
	
	for(size_t i=0 ; i < num ;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		BaseType_t ret=	xQueueSend(dst_msg_queue,src+i,portMAX_DELAY);
	}
}

void timer_Gy_callback(TimerHandle_t xTimer){
	xSemaphoreGive(task_sem_Gy);
}				

//void func_actorName_combinator(portName[], portName_rate ....)
inline static void combinator(	
token_gysig gy[] , const size_t gy_rate
 ,token_absysig  resy[],const size_t resy_rate
){
	printf("in actor Gy\n");

}

			
void task_Gy(void* pdata){
	//array aiming to storing data from input ports
	long gy_rate = 6;
	token_gysig gy[gy_rate];

		
	//array aiming to writing data to input ports
	long resy_rate = 1;
	token_absysig resy[resy_rate];

		while(1){
		/*
		*	read from channel
		*/
		read_channel_in_gy(msg_queue_gysig,gy_rate,gy);
		/*
		*	combinator function
		*/
		combinator(gy,gy_rate , resy,resy_rate );	
	
		/*
		*	write from channel
		*/
		write_channel_in_resy(resy,resy_rate,msg_queue_absysig);
		
		xSemaphoreTake(task_sem_Gy, portMAX_DELAY);	
			
		
	}
	
}




