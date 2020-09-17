#include <iostream> 
#include <string> 
#include <algorithm>
  
using namespace std; 

string replace(string str);
  
int main() 
{ 
    string first_string = "Test: & < > ";
    string new_string = replace(first_string);

    cout << first_string << endl;
    cout << new_string << endl;
    
    return 0; 
} 


string replace(string str){
    for(int i = 0; i < str.length(); i++){
        if(str[i] == '<'){
            str.replace(i, 1, "&lt");
        }
        else if(str[i] == '>'){
            str.replace(i, 1, "&gt");
        }
        else if(str[i] == '&'){
            str.replace(i, 1, "&amp");
        }
    }
    return str;
}
