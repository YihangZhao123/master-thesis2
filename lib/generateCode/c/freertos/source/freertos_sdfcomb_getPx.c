#include <stdlib.h>
#include <stdio.h>
#include "../include/freertos_sdfcomb_getPx.h"
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
extern QueueHandle_t msg_queue_inputImage;
extern QueueHandle_t msg_queue_gxsig;
extern QueueHandle_t msg_queue_gysig;


/*
* Task Stack
*/
StackType_t task_getPx_stk[TASK_STACKSIZE];
StaticTask_t tcb_getPx;
/*
* Soft Timer and Semaphore
*/
SemaphoreHandle_t task_sem_getPx;
TimerHandle_t task_timer_getPx;

inline static void read_channel_in_imgInput(QueueHandle_t src_msg_queue, size_t num, token_inputImage  dst[]){
	
	for(size_t i=0;i <num;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		xQueueReceive(src_msg_queue,dst+i, portMAX_DELAY);		
	}
}


inline static void write_channel_in_gx(token_gxsig src[],size_t num,QueueHandle_t dst_msg_queue){
	
	for(size_t i=0 ; i < num ;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		BaseType_t ret=	xQueueSend(dst_msg_queue,src+i,portMAX_DELAY);
	}
} 

inline static void write_channel_in_gy(token_gysig src[],size_t num,QueueHandle_t dst_msg_queue){
	
	for(size_t i=0 ; i < num ;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		BaseType_t ret=	xQueueSend(dst_msg_queue,src+i,portMAX_DELAY);
	}
}

void timer_getPx_callback(TimerHandle_t xTimer){
	xSemaphoreGive(task_sem_getPx);
}				

//void func_actorName_combinator(portName[], portName_rate ....)
inline static void combinator(	
token_inputImage imgInput[] , const size_t imgInput_rate
 ,token_gxsig  gx[],const size_t gx_rate 
,token_gysig  gy[],const size_t gy_rate
){
	printf("in actor getPx\n");

}

			
void task_getPx(void* pdata){
	//array aiming to storing data from input ports
	long imgInput_rate = 1;
	token_inputImage imgInput[imgInput_rate];

		
	//array aiming to writing data to input ports
	long gx_rate = 6;
	token_gxsig gx[gx_rate];
	
	long gy_rate = 6;
	token_gysig gy[gy_rate];

		while(1){
		/*
		*	read from channel
		*/
		read_channel_in_imgInput(msg_queue_inputImage,imgInput_rate,imgInput);
		/*
		*	combinator function
		*/
		combinator(imgInput,imgInput_rate , gx,gx_rate , gy,gy_rate );	
	
		/*
		*	write from channel
		*/
		write_channel_in_gx(gx,gx_rate,msg_queue_gxsig);
		write_channel_in_gy(gy,gy_rate,msg_queue_gysig);
		
		xSemaphoreTake(task_sem_getPx, portMAX_DELAY);	
			
		
	}
	
}




