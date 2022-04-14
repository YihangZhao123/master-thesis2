/*
 * error.h
 *
 *  Created on: Apr 14, 2022
 *      Author: LEGION
 */

#ifndef FREERTOS_INCLUDE_ERROR_H_
#define FREERTOS_INCLUDE_ERROR_H_
#include "main.h"
#include "cmsis_os.h"
extern UART_HandleTypeDef huart2;
void write(uint8_t * p,uint16_t size);


#endif /* FREERTOS_INCLUDE_ERROR_H_ */
