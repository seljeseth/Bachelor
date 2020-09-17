

#include "functions.h" 
#include <stddef.h> 
#include <stdint.h> 
int LLVMFuzzerTestOneInput(const uint8_t *data, size_t size) 
{   replace((char*)data);   
return 0; 
}

