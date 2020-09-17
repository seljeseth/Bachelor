/**
 * Salgstall.java  - "Programmering i Java", 4.utgave - 2011-01-31
 *
 * Klassen Salgstall vedlikeholder en todimensjonal tabell med
 * salgsdata for en periode på et gitt antall uker. Antall uker
 * og antall dager pr.. uke bestemmes av klienten når et objekt av
 * klassen lages. Tabellen opprettes i konstruktøren.
 * Klienten gir verdi til én dag av gangen ved å bruke metoden settSalg().
 */

class Salgstall {
  final private String avdeling;
  private int[][] salg;

  public Salgstall(String avdeling, int antuker, int antdager) {
    this.avdeling = avdeling;
    this.salg = new int[antuker][antdager]; // alle verdiene lik 0
  }

  public String getAvdeling() {
    return avdeling;
  }

  /**
   * Metoden finner antall uker.
   */
  public int finnAntUker() {
    return salg.length;
  }

  /**
   * Metoden finner antall dager pr. uke. Alle ukene er like lange.
   */
  public int finnAntDgPrUke() {
    /*
     * Hvis salg.length > 0, så er data registrert. salg.length gir antall uker.
     *  salg[n].length gir antall dager i uke n. Her er alle ukene like lange,
     *  derfor bruker vi like godt salg[0].length.
     */
    return (salg.length > 0) ? salg[0].length : -1;
  }

  /**
   *  Metoden registrerer salg på en bestemt dag.
   *  Returnerer true hvis salg registrert, false hvis ugyldig dag- eller ukenr.
   */
  public boolean settSalg(int ukenr, int dagnr, int nyttSalg) {
    if (gyldigeIndekser(ukenr, dagnr)) {
      salg[ukenr][dagnr] = nyttSalg;
      return true;
    } else {
      return false;
    }
  }

  /**
   *  Metoden finner salget en bestemt dag.
   *  Returnerer -1 hvis ugyldig dag- eller ukenr.
   */
  public int finnSalg(int ukenr, int dagnr) {
    return (gyldigeIndekser(ukenr, dagnr)) ? salg[ukenr][dagnr] : -1;
  }

  /**
   *  Metoden finner salget en bestemt uke.
   *  Returnerer -1 hvis ugyldig ukenr.
   */
  public int finnSalgForHelUke(int ukenr) {
    if (gyldigUkenr(ukenr)) {
      int sum = 0;
      for (int i = 0; i < finnAntDgPrUke(); i++) {
        sum += salg[ukenr][i];
      }
      return sum;
    } else {
      return -1;
    }
  }

  /**
   *  Metoden finner salget for en bestemt ukedag, summert over alle uker.
   *  Returnerer -1 hvis ingen data eller ugyldig dag.
   */
  public int finnSalgForUkedag(int dagnr) {
    if (gyldigDagnr(dagnr)) {
      int sum = 0;
      for (int i = 0; i < finnAntUker(); i++) {
        sum += salg[i][dagnr];
      }
      return sum;
    } else {
      return -1;
    }
  }

  /**
   *  Metoden finner totalsalget.
   */
  public int finnTotalsalg() {
    int sum = 0;
    for (int uke = 0; uke < finnAntUker(); uke++) {
      for (int dag = 0; dag < finnAntDgPrUke(); dag++) {
        sum += salg[uke][dag];
      }
    }
    return sum;
  }

  /**
   *  Metoden finner den mest lønnsomme ukedagen, hele
   *  perioden sett under ett. Returnerer -1 hvis data ikke registrert.
   */
  public int finnMestLønnsommeUkedag() {
    if (finnAntUker() > 0) {
      int hittilMestLønnsomme = 0;
      int sumMaksSalg = finnSalgForUkedag(0);
      for (int dag = 1; dag < finnAntDgPrUke(); dag++) {
        int salg = finnSalgForUkedag(dag);
        if (salg > sumMaksSalg) {
          hittilMestLønnsomme = dag;
          sumMaksSalg = salg;
        }
      }
      return hittilMestLønnsomme;
    }
    return -1;
  }

  /*
   * Tre private hjelpemetoder for å kontrollere gyldigheten av
   * indekser. De er ikke av interesse for klientene.
   */
  private boolean gyldigUkenr(int ukenr) {
    return (ukenr >= 0 && ukenr < finnAntUker());
  }

  private boolean gyldigDagnr(int dagnr) {
    return (finnAntUker() > 0 // minst en uke er registrert
        && dagnr >= 0 && dagnr < finnAntDgPrUke());
  }

  private boolean gyldigeIndekser(int ukenr, int dagnr) {
    return (gyldigUkenr(ukenr) && gyldigDagnr(dagnr));
  }
}