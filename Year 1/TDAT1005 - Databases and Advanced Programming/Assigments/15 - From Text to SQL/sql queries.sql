CREATE TABLE Kandidat(
  	kandidat_nr    		INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY, 
  	fornavn 			VARCHAR(30) NOT NULL,
  	etternavn 		VARCHAR(30) NOT NULL,
	telefon			VARCHAR(30) NOT NULL,
  	epost			VARCHAR(30) NOT NULL
);

INSERT INTO Kandidat`(fornavn`, etternavn, telefon , epost) VALUES ("Thomas", "Skøien", "98765432", "thomas@mail.com");
INSERT INTO Kandidat`(fornavn`, etternavn, telefon , epost) VALUES ("Sabine", "Seljeseth", "69696969", "sabine@mail.com");
INSERT INTO Kandidat`(fornavn`, etternavn, telefon , epost) VALUES ("Sivert", "Utne", "94187801", "sivert@mail.com");
INSERT INTO Kandidat`(fornavn`, etternavn, telefon , epost) VALUES ("Sebastian", "ikin", "94187801", "sebastian@mail.com");
INSERT INTO Kandidat`(fornavn`, etternavn, telefon , epost) VALUES ("Jostein", "Tysse", "94187801", "jostein@mail.com");

CREATE TABLE Kvalifikasjon (
  	kval_nr			INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
  	kvalifikasjon		VARCHAR(30) NOT NULL
);

INSERT INTO Kvalifikasjon`(kvalifikasjon`) VALUES ("Matte");
INSERT INTO Kvalifikasjon`(kvalifikasjon`) VALUES ("Norsk");
INSERT INTO Kvalifikasjon`(kvalifikasjon`) VALUES ("Gym");

CREATE TABLE Kval_Kandidat ( 
  	kval_nr			INTEGER NOT NULL,
 	kandidat_nr		INTEGER NOT NULL,
CONSTRAINT kval_fk_1 FOREIGN KEY(kval_nr) REFERENCES Kvalifikasjon(kval_nr),
CONSTRAINT kval_fk_2 FOREIGN KEY(kandidat_nr) REFERENCES Kandidat(kandidat_nr)
);

INSERT INTO Kval_Kandidat`(kval_nr`, kandidat_nr) VALUES (1 , 1);
INSERT INTO Kval_Kandidat`(kval_nr`, kandidat_nr) VALUES (2 , 1);
INSERT INTO Kval_Kandidat`(kval_nr`, kandidat_nr) VALUES (3 , 1);
INSERT INTO Kval_Kandidat`(kval_nr`, kandidat_nr) VALUES (1 , 2);
INSERT INTO Kval_Kandidat`(kval_nr`, kandidat_nr) VALUES (2, 2);
INSERT INTO Kval_Kandidat`(kval_nr`, kandidat_nr) VALUES (2 , 3);
INSERT INTO Kval_Kandidat`(kval_nr`, kandidat_nr) VALUES (3, 4);

CREATE TABLE Bedrift (
  	org_nr	   		INTEGER AUTO_INCREMENT  NOT NULL PRIMARY KEY, 
  	bed_navn		VARCHAR(30) NOT NULL,
  	telefon			VARCHAR(30) NOT NULL, 
  	epost			VARCHAR(30) NOT NULL
);

INSERT INTO Bedrift`(bed_navn`, telefon , epost) VALUES ("NTNU", "98765432", "ntnu@mail.com");
INSERT INTO Bedrift`(bed_navn`, telefon , epost) VALUES ("BI", "98765432", "bi@mail.com");
INSERT INTO Bedrift`(bed_navn`, telefon , epost) VALUES ("VGS", "98765432", "vgs@mail.com");

CREATE TABLE Oppdrag (
  	oppdrag_nr    		INTEGER AUTO_INCREMENT  NOT NULL PRIMARY KEY, 
  	bed_navn		VARCHAR(30) NOT NULL,
	kval_nr			INTEGER NULL,
  	start_dato		DATE NOT NULL,
  	slutt_dato		DATE NOT NULL,
	kandidat_nr		INTEGER NULL,
-- CONSTRAINT oppdrag_fk_1 FOREIGN KEY(bed_navn) REFERENCES Bedrift(bed_navn)
CONSTRAINT oppdrag_fk_2 FOREIGN KEY(kandidat_nr) REFERENCES Kandidat(kandidat_nr)
-- CONSTRAINT oppdrag_fk_3 FOREIGN KEY(kval_nr) REFERENCES Kval_Kandidat(kval_nr)
);

INSERT INTO Oppdrag`(bed_navn`, kval_nr, start_dato , slutt_dato) VALUES ("NTNU", 1, DATE('2000-01-01'), DATE('2000-01-03'));
INSERT INTO Oppdrag`(bed_navn`, kval_nr, start_dato , slutt_dato) VALUES ("NTNU", 3, DATE('2000-02-01'), DATE('2000-02-03'));
INSERT INTO Oppdrag`(bed_navn`, kval_nr, start_dato , slutt_dato) VALUES ("BI", 1, DATE('2000-03-01'), DATE('2000-03-03'));
INSERT INTO Oppdrag`(bed_navn`, kval_nr, start_dato , slutt_dato) VALUES ("VGS", 2, DATE('2000-04-01'), DATE('2000-04-03'));
INSERT INTO Oppdrag`(bed_navn`, kval_nr, start_dato , slutt_dato) VALUES ("VGS", 1, DATE('2000-05-01'), DATE('2000-05-03'));
INSERT INTO Oppdrag`(bed_navn`, kval_nr, start_dato , slutt_dato) VALUES ("BI", NULL, DATE('2001-05-01'), DATE('2001-05-03'));
INSERT INTO Oppdrag`(bed_navn`, kval_nr, start_dato , slutt_dato) VALUES ("VGS", 3, DATE('2000-06-01'), DATE('2000-06-03'));

CREATE TABLE Sluttattest (
  	attest_nr   		INTEGER AUTO_INCREMENT  NOT NULL PRIMARY KEY, 
  	oppdrag_nr		INTEGER NOT NULL,
  	start_dato		DATE NOT NULL,
  	slutt_dato		DATE NOT NULL,
	ant_timer			INTEGER NOT NULL,
CONSTRAINT sluttattest_fk FOREIGN KEY(oppdrag_nr) REFERENCES Oppdrag(oppdrag_nr)
);

CREATE TABLE Jobbhistorikk (
  	kandidat_nr		INTEGER NOT NULL,
	oppdrag_nr		INTEGER NOT NULL,
PRIMARY KEY(kandidat_nr, oppdrag_nr),
CONSTRAINT Jobbhistorikk_fk_1 FOREIGN KEY(kandidat_nr) REFERENCES Kandidat(kandidat_nr),
CONSTRAINT Jobbhistorikk_fk_2 FOREIGN KEY(oppdrag_nr) REFERENCES Sluttattest(oppdrag_nr)
);




INSERT INTO Sluttattest`(oppdrag_nr` , start_dato, slutt_dato , ant_timer) VALUES (1, DATE('2000-01-01'), DATE('2000-01-03'), 10);
UPDATE Oppdrag SET kandidat_nr = 1 WHERE oppdrag_nr = 1;
INSERT INTO Jobbhistorikk`(kandidat_nr` , oppdrag_nr) VALUES (1,1);

INSERT INTO Sluttattest`(oppdrag_nr` , start_dato, slutt_dato , ant_timer) VALUES (2, DATE('2000-02-01'), DATE('2000-02-04'), 20);
UPDATE Oppdrag SET kandidat_nr = 4 WHERE oppdrag_nr = 2;
INSERT INTO Jobbhistorikk`(kandidat_nr` , oppdrag_nr) VALUES (4,2);

INSERT INTO Sluttattest`(oppdrag_nr` , start_dato, slutt_dato , ant_timer) VALUES (3, DATE('2000-03-02'), DATE('2000-03-05'), 13);
UPDATE Oppdrag SET kandidat_nr = 2 WHERE oppdrag_nr = 3;
INSERT INTO Jobbhistorikk`(kandidat_nr` , oppdrag_nr) VALUES (2,3);

INSERT INTO Sluttattest`(oppdrag_nr` , start_dato, slutt_dato , ant_timer) VALUES (5, DATE('2000-05-05'), DATE('2000-05-12'), 23);
UPDATE Oppdrag SET kandidat_nr = 1 WHERE oppdrag_nr = 5;
INSERT INTO Jobbhistorikk`(kandidat_nr` , oppdrag_nr) VALUES (1,5);

INSERT INTO Sluttattest`(oppdrag_nr` , start_dato, slutt_dato , ant_timer) VALUES (6, DATE('2000-06-13'), DATE('2000-06-19'), 17);
UPDATE Oppdrag SET kandidat_nr = 1 WHERE oppdrag_nr = 6;
INSERT INTO Jobbhistorikk`(kandidat_nr` , oppdrag_nr) VALUES (1,6);


-- Lag en liste over alle bedriftene. Navn, telefon og epost til bedriften skal skrives ut.
SELECT bed_navn, telefon, epost FROM Bedrift;

-- Lag en liste over alle oppdragene. Om hvert oppdrag skal du skrive ut oppdragets nummer samt navn og telefonnummer til bedriften som tilbyr oppdraget.
SELECT oppdrag_nr, Bedrift.bed_navn, Bedrift.telefon FROM Oppdrag 
JOIN Bedrift ON Oppdrag.bed_navn = Bedrift.bed_navn;

-- Lag en liste over kandidater og kvalifikasjoner. Kandidatnavn og kvalifikasjonsbeskrivelse skal med i utskriften i tillegg til løpenumrene som identifiserer kandidat og  kvalifikasjon.
SELECT Kandidat.kandidat_nr, fornavn, etternavn, Kval_Kandidat.kval_nr, kvalifikasjon FROM Kandidat 
JOIN Kval_Kandidat ON Kandidat.kandidat_nr = Kval_Kandidat.kandidat_nr
JOIN Kvalifikasjon ON Kval_Kandidat.kval_nr = Kvalifikasjon.kval_nr;

-- Som oppgave 3), men få med de kandidatene som ikke er registrert med kvalifikasjoner.
SELECT Kandidat.kandidat_nr, fornavn, etternavn, Kval_Kandidat.kval_nr, kvalifikasjon FROM Kandidat 
LEFT OUTER JOIN Kval_Kandidat ON Kandidat.kandidat_nr = Kval_Kandidat.kandidat_nr
LEFT OUTER JOIN Kvalifikasjon ON Kval_Kandidat.kval_nr = Kvalifikasjon.kval_nr

-- Skriv ut jobbhistorikken til en bestemt vikar, gitt kandidatnr. Vikarnavn, sluttdato, oppdragsnr og bedriftsnavn skal med.
SELECT fornavn, etternavn, Sluttattest.slutt_dato, Oppdrag.oppdrag_nr, Oppdrag.bed_navn FROM Kandidat 
JOIN Jobbhistorikk ON Kandidat.kandidat_nr = Jobbhistorikk.kandidat_nr
JOIN Sluttattest ON Sluttattest.oppdrag_nr = Jobbhistorikk.oppdrag_nr
JOIN Oppdrag ON Oppdrag.oppdrag_nr = Sluttattest.oppdrag_nr
WHERE Kandidat.kandidat_nr = 1;