#ifndef  GETPX_H_
#define GETPX_H_
#include "../inc/sdfchannel_absysig.h"
#include "../inc/sdfchannel_gxsig.h"
#include "../inc/sdfchannel_gysig.h"
#include "../inc/sdfchannel_absxsig.h"

void actor_getPx(circularFIFO_gxsig* channel_gx_ptr,const size_t gx_rate,
circularFIFO_gysig* channel_gy_ptr,const size_t gy_rate
);


#endif		
