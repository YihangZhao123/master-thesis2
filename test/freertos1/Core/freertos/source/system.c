
#include "../include/system.h"
#include "../include/freertos_StartTask.h"
#include "../include/freertos_config.h"
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "timers.h"
/*******************
*	StartTask stack*
********************/
extern StackType_t task_StartTask_stk[TASK_STACKSIZE]; 
extern StaticTask_t tcb_start;

int subsystem(){



	xTaskCreateStatic(StartTask,
				"init",
				TASK_STACKSIZE,
				NULL,
				configMAX_PRIORITIES-1,
				task_StartTask_stk,
				&tcb_start
				);
				

			
			
			vTaskStartScheduler();
			
			return 0;
}		
