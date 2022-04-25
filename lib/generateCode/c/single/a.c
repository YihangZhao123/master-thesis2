#include <stdio.h>

typedef struct {
    double data[2][2];
}token;

int main(){
    token a[10];

    token b;
    b.data[0][0]=0;
    b.data[0][1]=1;
    b.data[1][0]=2;
    b.data[1][1]=3;

    a[0]=b; 
    
    return 0;
}