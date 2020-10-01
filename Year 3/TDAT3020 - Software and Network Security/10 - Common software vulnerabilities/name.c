#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main()
{
  char local_28 [32];
  
  printf("Enter your name: ");
  fgets(local_28,0x20,stdin);
  printf("Hello ");
  printf("%s",local_28);
  putchar(10);
  return 0;
}
