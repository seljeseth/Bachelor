
#include <stdio.h> 
#include <string.h>
//TODO : 
//1. ta inn en string
//2. looper igjennom stringen og erstatte de aktuelle tegnene
//3. returner strengen

int insert(int start, char* str, char* insert){
  for(int i = start; i < (strlen(insert)+start); i++){
    str[i] = insert[i-start];
  }
  return strlen(insert);

}


void replace(char* input)
{
  int new_string_length = 0;
  for(int i = 0; i < strlen(input); i++)
  {
    if(input[i] == '<')
    {
      new_string_length += strlen("&lt");
      
    }
    else if(input[i] == '>'){
      new_string_length += strlen("&gt");

    }
    else if(input[i] == '&')
    {
      new_string_length += strlen("&amp");

    }
    else{
      new_string_length++;
    }
  }
  
  char output[new_string_length];
  int teller = 0;


  for(int i = 0; i < strlen(input); i++)
  {
    
    if(input[i] == '<'){
      teller += insert(teller, output, "&lt");

    }else if(input[i] == '>')
    {
      teller += insert(teller, output, "&gt");

    }else if(input[i] == '&')
    {
      teller += insert(teller, output, "&amp");
    }
    else{

      output[teller] = input[i];
      teller++;
    }

  }
    printf("Old string: %s \nNew String: %s\n", input, output);
}


int main() {
   char *input = "Hei < > &";
   replace(input);
   return 0;
}
