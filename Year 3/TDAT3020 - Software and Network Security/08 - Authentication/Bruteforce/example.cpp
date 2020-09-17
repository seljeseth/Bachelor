#include <iomanip>
#include <iostream>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <sstream>
#include <string>
#include <cstring>
#include <algorithm>

using namespace std;

/// Return hex string from bytes in input string.
string hex(const string &input) {
  stringstream hex_stream;
  hex_stream << hex << internal << setfill('0');
  for (auto &byte : input)
    hex_stream << setw(2) << (int)(unsigned char)byte;
  return hex_stream.str();
}

/// Return the SHA-512 (512-bit) hash from input.
string sha512(const string &input) {
  string hash;
  hash.resize(512 / 8);
  SHA512((const unsigned char *)&input[0], input.size(), (unsigned char *)&hash[0]);
  return hash;
}

string pbkdf2(string input)
{

  string salt = "Saltet til Ola";
  string key;
  key.resize(160/8);
  PKCS5_PBKDF2_HMAC_SHA1(input.data(), input.size(),(unsigned char *)salt.data(), salt.size(), 2048, 20, (unsigned char *)key.data());
  return hex(key);
    
}
void crack(){
  string key = "ab29d7b5c589e18b52261ecba1d3a7e7cbf212c6";
  /*char str[] = {'A','B','C','D','E','F','G','H',
                'I','J','K','L','M','N','O','P',
                'Q','R','S','T','U','V','W','X',
                'Y','Z','a','b','c','d','e','f',
                'g','h','i','j','k','l','m','n',
                'o','p','q','r','s','t','u','v','w','x','y','z'};*/
  string chars[] = {"A", "B", "C", "D", "E", "F", "G",
                    "H", "I", "J", "K", "L", "M", "N",
                    "O", "P", "Q", "R", "S", "T", "U",
                    "V", "W", "Æ", "Ø", "Å", "X", "Y",
                    "Z","a", "b", "c", "d", "e", "f",
                    "g", "h", "i", "j", "k", "l", "m",
                    "n", "o", "p", "q", "r", "s", "t",
                    "u", "v", "w", "æ", "ø", "å", "x",
                    "y","z","0", "1", "2", "3", "4","5",
                     "6", "7", "8", "9"};
  int length_array = sizeof(chars)/sizeof(chars[0]);
  string output = "";
  for(int i = 0; i <2; i++)
  {
    string first_letter = chars[i];
    {
      for (int j = 0; i < 2; j++)
      {
        string output = first_letter + chars[j];
        cout << output << endl;
      }
      
    }
  }
}

  /*for(int i = 0; i < sizeof(str); i++){
    string check;
    check.push_back(str[i]);
    if(pbkdf2((output+check)) == key){
      cout<<check<<endl;
    }else
    {
      
    }*/
    
    
  


// Function to print permutations of string  
// This function takes three parameters:  
// 1. String  
// 2. Starting index of the string  
// 3. Ending index of the string.  
/*void permute(string a, int l, int r)  
{  
    // Base case  
    if (l == r)  
        cout<<a<<endl;  
    else
    {  
        // Permutations made  
        for (int i = l; i <= r; i++)  
        {  
  
            // Swapping done  
            swap(a[l], a[i]);  
  
            // Recursion called  
            permute(a, l+1, r);  
  
            //backtrack  
            swap(a[l], a[i]);  
        }  
    }  
}  */



int main() {
 

 crack();

}
