#ifndef  ABS_H_
#define ABS_H_
#include "../inc/sdfchannel_absysig.h"
#include "../inc/sdfchannel_gxsig.h"
#include "../inc/sdfchannel_gysig.h"
#include "../inc/sdfchannel_absxsig.h"

void actor_Abs(circularFIFO_absxsig* channel_resx_ptr, const size_t resx_rate,
circularFIFO_absysig* channel_resy_ptr, const size_t resy_rate
);


#endif		
