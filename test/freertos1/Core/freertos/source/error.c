/*
 * error.c
 *
 *  Created on: Apr 14, 2022
 *      Author: LEGION
 */
#include "FreeRTOS.h"
#include "task.h"
#include "semphr.h"
#include "timers.h"
#include "../include/error.h"
extern SemaphoreHandle_t sem_usart;
void write(uint8_t * p,uint16_t size){
	xSemaphoreTake(sem_usart, portMAX_DELAY);
	HAL_UART_Transmit(&huart2,p,size,100);
	xSemaphoreGive(sem_usart);
}
