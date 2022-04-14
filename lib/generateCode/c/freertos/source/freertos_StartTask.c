#include "../include/freertos_StartTask.h"
#include "../include/freertos_sdfchannel_gysig.h"
#include "../include/freertos_sdfchannel_gxsig.h"
#include "../include/freertos_sdfchannel_absysig.h"
#include "../include/freertos_sdfchannel_absxsig.h"

#include "../include/freertos_sdfcomb_Gx.h"
#include "../include/freertos_sdfcomb_Abs.h"
#include "../include/freertos_sdfcomb_Gy.h"
#include "../include/freertos_sdfcomb_getPx.h"

#include "../include/freertos_config.h"
/*******************
*	Task stack     *
********************/
StackType_t task_StartTask_stk[TASK_STACKSIZE]; 
StaticTask_t tcb_start;
extern StackType_t task_Gx_stk[TASK_STACKSIZE];
extern StaticTask_t tcb_Gx;
extern StackType_t task_Abs_stk[TASK_STACKSIZE];
extern StaticTask_t tcb_Abs;
extern StackType_t task_Gy_stk[TASK_STACKSIZE];
extern StaticTask_t tcb_Gy;
extern StackType_t task_getPx_stk[TASK_STACKSIZE];
extern StaticTask_t tcb_getPx;

/*******************
*	 Message Queue *
********************/
extern int queue_length_gysig;
extern long item_size_gysig;
extern QueueHandle_t msg_queue_gysig;

extern int queue_length_gxsig;
extern long item_size_gxsig;
extern QueueHandle_t msg_queue_gxsig;

extern int queue_length_absysig;
extern long item_size_absysig;
extern QueueHandle_t msg_queue_absysig;

extern int queue_length_absxsig;
extern long item_size_absxsig;
extern QueueHandle_t msg_queue_absxsig;



/**************************
*			Soft Timer and semaphore
***************************/
extern TimerHandle_t task_timer_Gx;
extern TimerHandle_t task_timer_Abs;
extern TimerHandle_t task_timer_Gy;
extern TimerHandle_t task_timer_getPx;

extern  SemaphoreHandle_t task_sem_Gx;
extern  SemaphoreHandle_t task_sem_Abs;
extern  SemaphoreHandle_t task_sem_Gy;
extern  SemaphoreHandle_t task_sem_getPx;



void StartTask(void* pdata){
	/*
		Message Queue creation
	*/
	msg_queue_gysig=xQueueCreate(queue_length_gysig,item_size_gysig);
	msg_queue_gxsig=xQueueCreate(queue_length_gxsig,item_size_gxsig);
	msg_queue_absysig=xQueueCreate(queue_length_absysig,item_size_absysig);
	msg_queue_absxsig=xQueueCreate(queue_length_absxsig,item_size_absxsig);

		/*
		Soft Timer amd Semephore Initilization
	*/				
	task_timer_Gx=xTimerCreate(
											"timer_Gx"
											, pdMS_TO_TICKS(4000)
											, pdTRUE
											,0
											,timer_Gx_callback
											);
	
	task_sem_Gx=xSemaphoreCreateBinary();
	task_timer_Abs=xTimerCreate(
											"timer_Abs"
											, pdMS_TO_TICKS(4000)
											, pdTRUE
											,0
											,timer_Abs_callback
											);
	
	task_sem_Abs=xSemaphoreCreateBinary();
	task_timer_Gy=xTimerCreate(
											"timer_Gy"
											, pdMS_TO_TICKS(4000)
											, pdTRUE
											,0
											,timer_Gy_callback
											);
	
	task_sem_Gy=xSemaphoreCreateBinary();
	task_timer_getPx=xTimerCreate(
											"timer_getPx"
											, pdMS_TO_TICKS(4000)
											, pdTRUE
											,0
											,timer_getPx_callback
											);
	
	task_sem_getPx=xSemaphoreCreateBinary();

		
	/*
		Tasks Initilization
	*/
	xTaskCreateStatic(task_Gx
						,"Gx"
						,TASK_STACKSIZE
						,NULL
						,configMAX_PRIORITIES-2
						,task_Gx_stk,
						&tcb_Gx
						);
	
	xTaskCreateStatic(task_Abs
						,"Abs"
						,TASK_STACKSIZE
						,NULL
						,configMAX_PRIORITIES-2
						,task_Abs_stk,
						&tcb_Abs
						);
	
	xTaskCreateStatic(task_Gy
						,"Gy"
						,TASK_STACKSIZE
						,NULL
						,configMAX_PRIORITIES-2
						,task_Gy_stk,
						&tcb_Gy
						);
	
	xTaskCreateStatic(task_getPx
						,"getPx"
						,TASK_STACKSIZE
						,NULL
						,configMAX_PRIORITIES-2
						,task_getPx_stk,
						&tcb_getPx
						);

		
	/*
		Start the soft timer
	*/
	xTimerStart(task_timer_Gx, portMAX_DELAY);
	xTimerStart(task_timer_Abs, portMAX_DELAY);
	xTimerStart(task_timer_Gy, portMAX_DELAY);
	xTimerStart(task_timer_getPx, portMAX_DELAY);

		
	vTaskDelete(NULL); 
}		
