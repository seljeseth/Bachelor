import static javax.swing.JOptionPane.*;

class KlientprogramForOppgaveoversikt{
	public static void main(String[] args){
		
		Student[] test = {new Student("Svettlana Olsen", 10),new Student("Bob Roger", 2),new Student("Ole Olsen", 6)};
		Oppgaveoversikt oppgaveoversikt = new Oppgaveoversikt(test);
		final String[] alternativer = {"Sjekk oppgaver", "Registrer ny student", "Antall studenter", "Legg til oppgaver" ,"Avslutt"};
		
		while(true){
			int valg = showOptionDialog(null, "Hva vil du gjøre?", "Oppgaveoversikt", 0, QUESTION_MESSAGE, null, alternativer, alternativer[4]);
			switch(valg){
				case 0: String lesStudent = showInputDialog("Hvilken student vil du sjekke oppgavene til?" + "\n" + oppgaveoversikt.studenterToString());
						int indexTilStudent = Integer.parseInt(lesStudent);
						int oppgaverGjort = oppgaveoversikt.antallOppgaverGjort(oppgaveoversikt.hentStudent(indexTilStudent));
						showMessageDialog(null, "Studenten har gjort " + oppgaverGjort + " oppgaver");
				    	break;
						
				case 1: String lesStudNavn = showInputDialog("Hva skal studenten hete?");
						String lesStudOppg = showInputDialog("Hvor mange oppgaver har studenten gjort?");
						int studOppgG = Integer.parseInt(lesStudOppg);
						oppgaveoversikt.registrerNyStudent(lesStudNavn, studOppgG);
						showMessageDialog(null, lesStudNavn + " er registrert med " + studOppgG + " antall oppgaver gjort ");
						break;
			
				case 2:	showMessageDialog(null,"Det er "+ oppgaveoversikt.finnAntallRegistrerteStudenter() + " Studenter: \n" + oppgaveoversikt.studentToString());	
						break;	
			
			    case 3: String studOk = showInputDialog("Hvem har gjort flere oppgaver? " + "\n" + oppgaveoversikt.studenterToString());
						int indexStud = Integer.parseInt(studOk);
						String stringOkning = showInputDialog("Hvor mange har dem gjort?");
						int okning = Integer.parseInt(stringOkning);
						int oppgaverGjortNy = oppgaveoversikt.setAntallOppgaver(oppgaveoversikt.hentStudent(indexStud), okning);
						showMessageDialog(null, "Studenten har nå gjort " +  oppgaverGjortNy + " oppgaver");
			 	   		break;
				default:
						return;
					}
				}
			}
		}
