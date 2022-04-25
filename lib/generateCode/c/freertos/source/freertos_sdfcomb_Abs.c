#include <stdlib.h>
#include <stdio.h>
#include "../include/freertos_sdfcomb_Abs.h"
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
extern QueueHandle_t msg_queue_absxsig;
extern QueueHandle_t msg_queue_absysig;
extern QueueHandle_t msg_queue_outputImage;


/*
* Task Stack
*/
StackType_t task_Abs_stk[TASK_STACKSIZE];
StaticTask_t tcb_Abs;
/*
* Soft Timer and Semaphore
*/
SemaphoreHandle_t task_sem_Abs;
TimerHandle_t task_timer_Abs;

inline static void read_channel_in_resx(QueueHandle_t src_msg_queue, size_t num, token_absxsig  dst[]){
	
	for(size_t i=0;i <num;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		xQueueReceive(src_msg_queue,dst+i, portMAX_DELAY);		
	}
} 

inline static void read_channel_in_resy(QueueHandle_t src_msg_queue, size_t num, token_absysig  dst[]){
	
	for(size_t i=0;i <num;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		xQueueReceive(src_msg_queue,dst+i, portMAX_DELAY);		
	}
}


inline static void write_channel_in_imgOutput(token_outputImage src[],size_t num,QueueHandle_t dst_msg_queue){
	
	for(size_t i=0 ; i < num ;++i){
		// portMAX_DELAY: INCLUDE_vTaskSuspend is set to 1 in FreeRTOSConfig.h.
		// block forever
		BaseType_t ret=	xQueueSend(dst_msg_queue,src+i,portMAX_DELAY);
	}
}

void timer_Abs_callback(TimerHandle_t xTimer){
	xSemaphoreGive(task_sem_Abs);
}				

//void func_actorName_combinator(portName[], portName_rate ....)
inline static void combinator(	
token_absxsig resx[] , const size_t resx_rate,
token_absysig resy[] , const size_t resy_rate
 ,token_outputImage  imgOutput[],const size_t imgOutput_rate
){
	printf("in actor Abs\n");

}

			
void task_Abs(void* pdata){
	//array aiming to storing data from input ports
	long resx_rate = 1;
	token_absxsig resx[resx_rate];
	
	long resy_rate = 1;
	token_absysig resy[resy_rate];

		
	//array aiming to writing data to input ports
	long imgOutput_rate = 1;
	token_outputImage imgOutput[imgOutput_rate];

		while(1){
		/*
		*	read from channel
		*/
		read_channel_in_resx(msg_queue_absxsig,resx_rate,resx);
		read_channel_in_resy(msg_queue_absysig,resy_rate,resy);
		/*
		*	combinator function
		*/
		combinator(resx,resx_rate,resy,resy_rate , imgOutput,imgOutput_rate );	
	
		/*
		*	write from channel
		*/
		write_channel_in_imgOutput(imgOutput,imgOutput_rate,msg_queue_outputImage);
		
		xSemaphoreTake(task_sem_Abs, portMAX_DELAY);	
			
		
	}
	
}




