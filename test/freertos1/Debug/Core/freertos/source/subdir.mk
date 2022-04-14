################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (9-2020-q2-update)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Core/freertos/source/error.c \
../Core/freertos/source/freertos_StartTask.c \
../Core/freertos/source/freertos_sdfchannel_absxsig.c \
../Core/freertos/source/freertos_sdfchannel_absysig.c \
../Core/freertos/source/freertos_sdfchannel_gxsig.c \
../Core/freertos/source/freertos_sdfchannel_gysig.c \
../Core/freertos/source/freertos_sdfcomb_Abs.c \
../Core/freertos/source/freertos_sdfcomb_Gx.c \
../Core/freertos/source/freertos_sdfcomb_Gy.c \
../Core/freertos/source/freertos_sdfcomb_getPx.c \
../Core/freertos/source/system.c 

OBJS += \
./Core/freertos/source/error.o \
./Core/freertos/source/freertos_StartTask.o \
./Core/freertos/source/freertos_sdfchannel_absxsig.o \
./Core/freertos/source/freertos_sdfchannel_absysig.o \
./Core/freertos/source/freertos_sdfchannel_gxsig.o \
./Core/freertos/source/freertos_sdfchannel_gysig.o \
./Core/freertos/source/freertos_sdfcomb_Abs.o \
./Core/freertos/source/freertos_sdfcomb_Gx.o \
./Core/freertos/source/freertos_sdfcomb_Gy.o \
./Core/freertos/source/freertos_sdfcomb_getPx.o \
./Core/freertos/source/system.o 

C_DEPS += \
./Core/freertos/source/error.d \
./Core/freertos/source/freertos_StartTask.d \
./Core/freertos/source/freertos_sdfchannel_absxsig.d \
./Core/freertos/source/freertos_sdfchannel_absysig.d \
./Core/freertos/source/freertos_sdfchannel_gxsig.d \
./Core/freertos/source/freertos_sdfchannel_gysig.d \
./Core/freertos/source/freertos_sdfcomb_Abs.d \
./Core/freertos/source/freertos_sdfcomb_Gx.d \
./Core/freertos/source/freertos_sdfcomb_Gy.d \
./Core/freertos/source/freertos_sdfcomb_getPx.d \
./Core/freertos/source/system.d 


# Each subdirectory must supply rules for building sources it contributes
Core/freertos/source/%.o: ../Core/freertos/source/%.c Core/freertos/source/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m4 -std=gnu11 -g3 -DDEBUG -DUSE_HAL_DRIVER -DSTM32F401xE -c -I../Core/Inc -I../Drivers/STM32F4xx_HAL_Driver/Inc -I../Drivers/STM32F4xx_HAL_Driver/Inc/Legacy -I../Drivers/CMSIS/Device/ST/STM32F4xx/Include -I../Drivers/CMSIS/Include -I../Middlewares/Third_Party/FreeRTOS/Source/include -I../Middlewares/Third_Party/FreeRTOS/Source/CMSIS_RTOS_V2 -I../Middlewares/Third_Party/FreeRTOS/Source/portable/GCC/ARM_CM4F -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" --specs=nano.specs -mfpu=fpv4-sp-d16 -mfloat-abi=hard -mthumb -o "$@"

clean: clean-Core-2f-freertos-2f-source

clean-Core-2f-freertos-2f-source:
	-$(RM) ./Core/freertos/source/error.d ./Core/freertos/source/error.o ./Core/freertos/source/freertos_StartTask.d ./Core/freertos/source/freertos_StartTask.o ./Core/freertos/source/freertos_sdfchannel_absxsig.d ./Core/freertos/source/freertos_sdfchannel_absxsig.o ./Core/freertos/source/freertos_sdfchannel_absysig.d ./Core/freertos/source/freertos_sdfchannel_absysig.o ./Core/freertos/source/freertos_sdfchannel_gxsig.d ./Core/freertos/source/freertos_sdfchannel_gxsig.o ./Core/freertos/source/freertos_sdfchannel_gysig.d ./Core/freertos/source/freertos_sdfchannel_gysig.o ./Core/freertos/source/freertos_sdfcomb_Abs.d ./Core/freertos/source/freertos_sdfcomb_Abs.o ./Core/freertos/source/freertos_sdfcomb_Gx.d ./Core/freertos/source/freertos_sdfcomb_Gx.o ./Core/freertos/source/freertos_sdfcomb_Gy.d ./Core/freertos/source/freertos_sdfcomb_Gy.o ./Core/freertos/source/freertos_sdfcomb_getPx.d ./Core/freertos/source/freertos_sdfcomb_getPx.o ./Core/freertos/source/system.d ./Core/freertos/source/system.o

.PHONY: clean-Core-2f-freertos-2f-source

