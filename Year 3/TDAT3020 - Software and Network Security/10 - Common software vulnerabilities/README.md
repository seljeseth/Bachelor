# 10 - Common software vulnerabilities
### 1. Decompilation and analysis of an x64 binary
Når vi dekompilerer binærfila i ghidra får vi ut main-en:
</br>
!["main file"](ghidra_screenshot.png)
</br>
Her ser vi at printf() blir brukt uten å ta hensyn til hva brukeren skal skrive inn. Dette åpner opp for vulnerabilities som f.eks:
* Kræsje programmet med å foreksempel skrive: printf ("%s%s%s%s%s%s%s%s%s%s%s%s")
* Se innholde på stacken: printf ("%08x %08x %08x %08x %08x\n")
</br>
Om vi kompilerer vil vi få feilmeldingen som advarer om dette:
!["error message"](error_message.png)


