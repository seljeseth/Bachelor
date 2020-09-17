import java.util.Arrays;
public class Temperaturer{
	
	final private String maned;
	private double[][] tempraturer;
	
	public Temperaturer(String maned, int antdager, int anttimer){
		this.maned = maned;
		this.tempraturer = new double[antdager][anttimer];	
	}
	
	public String getManed(){
		return maned;
	}
	
	public int finnAntDager(){
		return tempraturer.length;
	}
	
	public int finnAntTimer(){
		return (tempraturer.length > 0)? tempraturer[0].length: -1;	
	}
	/*registrerer tempen i en bestemt time.
	*returnerer true hvis tempen er registrert, og false hvis ugyldig time eller dag*/
	public boolean settTemp(int dag, int time, double nyTemp){ 
		if(gyldigeIndekser(dag,time)){
			tempraturer[dag][time] = (double) nyTemp; 
			return true;
		}else{
			return false;
		}
	}
	
	public double[] finnMiddelTempForDag(){
		double [] antalldager = new double[finnAntDager()];
		double tempMidVer = 0;
		double[] midTempDag = new double[finnAntDager()];
		for(int i = 0; i < finnAntDager(); i++){
			if(gyldigDager(i)){
			for(int j = 0; j < finnAntTimer(); j++){
				antalldager [i] += tempraturer[i][j];
				
			}	
			midTempDag[i] = antalldager [i]/finnAntDager();
		}
	}
	return midTempDag;
}
		
	public double[] finnMiddelTempForTime(){
		double [] antalltimer = new double[finnAntTimer()];
		double tempMidVer= 0;
		double[] midTempTime = new double [finnAntTimer()];
		for(int i = 0; i < finnAntTimer(); i++){
			if(gyldigTimer(i)){
			for(int j = 0; j < finnAntDager(); j++){
				antalltimer[i] += tempraturer[j][i];
			}
			midTempTime[i]=antalltimer[i]/finnAntTimer();
		}
	}
	return midTempTime;	
}
	public double finnGjenTempForManeden(){
		double sum = 0;
		double tempMidVer= 0;
		for(int dag = 0; dag < finnAntDager(); dag++){
			for(int time = 0; time < finnAntTimer(); time++){
				sum+=tempraturer[dag][time];
				tempMidVer = sum/(finnAntDager()*finnAntTimer());
			}
		}
		return tempMidVer;
	}
	
	public String middelIntervaller(){
		int underMinusFem = 0;
		int mellomMinusFemOgNull = 0;
		int mellomNullOgFem = 0;
		int mellomFemOgTi = 0;
		int overTi = 0;
		double [] tilfeller = new double [5];
		double [] midVerTime =  finnMiddelTempForTime();
		for(int i = 0; i < finnAntDager(); i++){
			if(midVerTime[i] < -5){
				underMinusFem++;
			}else if(midVerTime[i] > -5 &&  midVerTime[i] < 0){
				mellomMinusFemOgNull++;		
			}else if( midVerTime[i] > 0 &&  midVerTime[i] < 5){
				mellomNullOgFem++;
			}else if( midVerTime[i] > 5 &&  midVerTime[i] < 10){
				mellomFemOgTi++;
			}else if( midVerTime[i] > 10){
				overTi++;
			}
		}
		return "\nantall døgn med middeltemperaturen innen følgende grupper:" + "\n\n antall døgn med mindre enn -5 grader: " + underMinusFem + "\n\n døgn med middeltemp mellom -5 grader og 0 grader: " + mellomMinusFemOgNull + "\n\n døgn med middeltemp mellom 0 grader og 5 grader: " + mellomNullOgFem + "\n\n døgn med middeltemp mellom 5 grader og 10 grader: " + mellomFemOgTi + "\n\n døgn med middeltemp over 10 grader: " + overTi;
	}
	
    private boolean gyldigDager(int dag) {
      return (dag >= 0 && dag < finnAntDager());
    }

    private boolean gyldigTimer(int time) {
      return (finnAntDager() > 0 && time >= 0 && time < finnAntTimer());
    }
		
    private boolean gyldigeIndekser(int dag, int time) {
      return (gyldigDager(dag) && gyldigTimer(time));
    }
			

public static void main(String[] args){
	
	Temperaturer test = new Temperaturer("test", 2, 3);
	test.settTemp(0,0,-12); //dag 0
	test.settTemp(0,1,3);
	test.settTemp(0,2,4);
	
	test.settTemp(1,0,3); //dag 1
	test.settTemp(1,1,3.5);
	test.settTemp(1,2,8.7);
	
	System.out.println("Måneden er " + test.getManed());
	System.out.println("Måneden har " + test.finnAntDager() + " dager ");
	System.out.println("Hver dag har " + test.finnAntTimer() + " timer ");
	
	for(int dag = 0; dag < test.finnAntDager(); dag++){
		System.out.println("\nMiddeltemperaturen for dag " + dag + " er " + test.finnMiddelTempForDag()[dag] + " grader ");
	}
	for(int time = 0; time < test.finnAntTimer(); time++){
		System.out.println("\nMiddeltemperauturen for time " + time + " er " + test.finnMiddelTempForTime()[time]+ " grader ");
	}
	System.out.println("\n Middeltemperaturen for måneden er " + test.finnGjenTempForManeden() + " grader ");
	System.out.println(test.middelIntervaller());
  }
  
}