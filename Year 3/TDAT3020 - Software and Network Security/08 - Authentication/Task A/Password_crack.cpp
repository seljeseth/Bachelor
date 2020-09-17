#include <iomanip>
#include <iostream>
#include <openssl/evp.h>
#include <openssl/sha.h>
#include <sstream>
#include <string>
#include <cstring>
#include <algorithm>
#include <vector>

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
string crack()
{
    //string key = pbkdf2("AA");
  string key = "ab29d7b5c589e18b52261ecba1d3a7e7cbf212c6";
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

  //vector<string> chars = {"A", "B"};
  string output = "";
  vector<string> current_guesses;
  vector<string> wrong_guesses = {output};
  while(true){


    for(string s: wrong_guesses)
    {

      for(string a : chars)
      {
        if(pbkdf2((s+a)) == key) return (s+a);
        current_guesses.push_back((s + a));
        
      }
      
        
    }
    wrong_guesses = current_guesses;
    current_guesses = {};
    cout << wrong_guesses[1].length() << endl;
    
    }
    
}

int main() {
 

 cout << crack() << endl;

}