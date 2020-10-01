# 10 - Common software vulnerabilities

### 1. Decompilation and analysis of an x64 binary

Når vi dekompilerer binærfila i ghidra får vi ut main-en:

</br>

!["main file"](ghidra_screenshot.png)

Her ser vi at printf() blir brukt uten å ta hensyn til hva brukeren skal skrive inn. Dette åpner opp for vulnerabilities som f.eks:
* Kræsje programmet med å foreksempel skrive: printf ("%s%s%s%s%s%s%s%s%s%s%s%s")
* Se innholde på stacken: printf ("%08x %08x %08x %08x %08x\n")

</br>

Om vi kompilerer vil vi få feilmeldingen som advarer om dette:

</br>

!["error"](error_message.png)

</br>

Om vi endrer koden slik at printf() tar hensyn til dette unngår vi at noen kan utnytte hva de kan skrive inn i programmet.
Dette gjør vi med å :

```bash
  #endrer fra
  printf(local_28);

  #til
  printf("%s",local_28);
```

### 2. Micro-CMS v1
#### FLAG 1
Merket at om jeg opprettet en ny side ble nummeret på siden 12, men de ut ifra nettsiden så det ut som det kun var 2 sider. Etter å ha 
#### FLAG 2
#### FLAG 3
#### FLAG 4



