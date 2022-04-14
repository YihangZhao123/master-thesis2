#ifndef  GY_H_
#define GY_H_
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "timers.h"
void task_Gy(void* pdata);

void timer_Gy_callback(TimerHandle_t xTimer);
#endif		
