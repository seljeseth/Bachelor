# 8 - Authentication
### Task A - Password Cracking
<i>Dere har funnet nøkkelen (hash’en) og saltet til passordet til Ola på en server:

<b>nøkkelen:</b> “ab29d7b5c589e18b52261ecba1d3a7e7cbf212c6”

<b>saltet:</b> “Saltet til Ola”

Dere vet at på serveren brukes PBKDF2 med SHA1 (pbkdf2-funksjonen i OpenSSL skal brukes) med 2048 iterasjoner.
Dere vet også at Ola ikke gidder å bruke så mange bokstaver i passordet sitt. Hva er passordet til Ola?</i>

Om vi kjører koden Password_crack får vi ut at passordet er:

```bash
QwE
```
### Task B - Implementing PBKDF2 in server/client
<i>
Lag en web klient og server der klienten autentiserer seg mot serveren ved hjelp av 
PBKDF2. Passordet skal hashes både på klienten og serveren. 
For de som vil bruke JavaScript på både klient og server er crypto-js et ok alternativ.
</br>
-Frivillig: bruk Node.js Crypto (openssl bindinger) på serversiden

Når en klient autentiseres, send en access token til klienten som kan brukes,uten å autentisere seg på nytt, i etterfølgende forespørsler.
Opp til dere hvordan token’en ser ut.
Gå ut i fra at HTTPS blir brukt (slik at en kan bruke enkle accesstokens/bearer tokens)

</i>
