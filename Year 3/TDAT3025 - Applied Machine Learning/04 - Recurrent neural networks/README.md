## 4 Recurrent Neural Networks

##### a) Many to many LSTM
<i>ta utgangspunkt i koden gitt i forelesningen og tren modellen på
bokstavene “ hello world “. Bruk deretter modellen til å
generere 50 bokstaver etter inputen “ h”.</i>

Resultatet av koden i oppgave_a.py er denne utskriften:

```console
hlloo ll     d  d   d   d   d   d   d   d   d   d   
 hllo wrd     d                                      
 hlllo world   rld    rld   rld    rld   rld    rld  
 hello world  world  world  world  world  world  worl
 hello world  world  wrlld world  world  wrlld world 
 hello world  wrlld world  world  wrlld world  world 
 hello world  wrlld world  world world  world world  
 hello world  wrlld world  world world  world world  
 hello world world  wolld world  wolld world  wolld w
 hello world world  wolld world  wolld world world  w
 hello world world  wolld world world  wolld world wo
 hello world world  wolld world world  wolld world wo
 hello world world world  wolld world world world  wo
 hello world world world world world  wolld world wor
 hello world world world world world world world worl
 hello world world world world world world world worl
 hello world world world world world world world worl
```
##### b) Many to one LSTM
<i>tren modellen ulike ord (bruk fortsatt bokstavkoding som i
oppgave a) for emojis, for eksempel “hat ”: 🎩, “rat “: 🐀,
“cat “: 🐈, “flat”: 🏢, “matt”: 👨, “cap “: 🧢, “son ”: 👶.
For å kunne trene i batches er ordene padded med mellomrom
på slutten (hvis ordene er mindre enn makslengden).
Test deretter modellen på ord som “rt ” og “rats”, og se hvilken
emoji du får</i>

Jeg har

```console
emojies = ['⚽', '🐀️', '🧢️', '👶️', '💩', '🐈️', '🏢']
words = ['ball', 'rat ', 'cap ', 'son ', 'crap', 'cat ', 'flat']
```


```console
Epoch: 0
rt     =  👶️     
rats   =  👶️     
flat   =  🏢      
caps   =  💩      
sn     =  👶️     
cr     =  💩      
cat    =  🐈️     
fl     =  👶️     
bl     =  ⚽      

Epoch: 200
rt     =  🐀️     
rats   =  🐀️     
flat   =  🏢      
caps   =  🧢️     
sn     =  👶️     
cr     =  💩      
cat    =  🐈️     
fl     =  🏢      
bl     =  ⚽      

Epoch: 400
rt     =  🐀️     
rats   =  🐀️     
flat   =  🏢      
caps   =  🧢️     
sn     =  👶️     
cr     =  💩      
cat    =  🐈️     
fl     =  🏢      
bl     =  ⚽      

```

