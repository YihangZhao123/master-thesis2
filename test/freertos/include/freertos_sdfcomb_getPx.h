#ifndef  GETPX_H_
#define GETPX_H_
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "timers.h"
void task_getPx(void* pdata);

void timer_getPx_callback(TimerHandle_t xTimer);
#endif		
