#ifndef  GX_H_
#define GX_H_
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "timers.h"
void task_Gx(void* pdata);

void timer_Gx_callback(TimerHandle_t xTimer);
#endif		
