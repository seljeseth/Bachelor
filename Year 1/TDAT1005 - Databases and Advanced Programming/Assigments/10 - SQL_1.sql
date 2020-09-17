--1.)Finn alle borettslag etablert i årene 1975-1985.
SELECT * FROM borettslag WHERE etabl_aar BETWEEN 1975 AND 1985

--2.)Skriv ut en liste over andelseiere. Listen skal ha linjer som ser slik ut (tekster i kursiv er data fra databasen): 
--"fornavn etternavn, ansiennitet: ansiennitet år". 
--Listen skal være sortert på ansiennitet, de med lengst ansiennitet øverst.
SELECT fornavn, etternavn,"Ansiennitet: ", ansiennitet, "år" FROM `andelseier` ORDER BY ansiennitet DESC

--3.)I hvilket år ble det eldste borettslaget etablert?
SELECT MIN(etabl_aar) FROM borettslag

--4.)Finn adressene til alle bygninger som inneholder leiligheter med minst tre rom.
SELECT bygn_adr FROM `bygning` NATURAL JOIN leilighet WHERE ant_rom > 3; --skriver ut adressen 3 gang

--5.)Finn antall bygninger i borettslaget "Tertitten".
SELECT COUNT(*) "Tertitten" FROM bygning WHERE bolag_navn = "Tertitten"

--6.)Lag en liste som viser antall bygninger i hvert enkelt borettslag. 
-- Listen skal være sortert på borettslagsnavn. Husk at det kan finnes borettslag uten bygninger 
-- de skal også med.
SELECT COUNT(bygn_id) AS Ant_byggninger, borettslag.bolag_navn 
FROM bygning 
RIGHT OUTER JOIN borettslag ON borettslag.bolag_navn = bygning.bolag_navn 
GROUP BY borettslag.bolag_navn

--7.)Finn antall leiligheter i borettslaget "Tertitten".
SELECT COUNT(*) AS Antall_leiligheter 
FROM leilighet WHERE bygn_id IN (SELECT DISTINCT bygn_id FROM bygning WHERE bolag_navn = "Tertitten")

--8.)Hvor høyt kan du bo i borettslaget "Tertitten"?
SELECT MAX(ant_etasjer) 
FROM bygning 
WHERE bolag_navn = "Tertitten"

--9.)Finn navn og nummer til andelseiere som ikke har leilighet.
SELECT fornavn,etternavn,telefon 
FROM andelseier 
WHERE and_eier_nr NOT IN (SELECT DISTINCT and_eier_nr FROM leilighet)

--10.)Finn antall andelseiere pr borettslag, sortert etter antallet. Husk at det kan finnes borettslag uten andelseiere - de skal også med.
SELECT COUNT(andelseier.and_eier_nr) AS Ant_andelseiere, borettslag.bolag_navn 
FROM andelseier 
RIGHT OUTER JOIN borettslag ON borettslag.bolag_navn = andelseier.bolag_navn GROUP BY borettslag.bolag_navn

--11.)Skriv ut en liste over alle andelseiere. For de som har leilighet, skal leilighetsnummeret skrives ut.
SELECT andelseier.*, leilighet.leil_nr 
FROM leilighet 
RIGHT OUTER JOIN andelseier ON andelseier.and_eier_nr = leilighet.and_eier_nr

--12.) 
 
--13.)Skriv ut en liste over antall andelseiere pr postnr og poststed, begrenset til de som bor i leiligheter tilknyttet et borettslag. Husk at postnummeret til disse er postnummeret til bygningen de bor i, og ikke postnummeret til borettslaget. Du trenger ikke ta med poststeder med 0 andelseiere. (Ekstraoppgave: Hva hvis vi vil ha med poststeder med 0 andelseiere?)
 SELECT poststed.postnr, COUNT(andelseier.and_eier_nr) AS antall_andelseiere FROM andelseier 
 RIGHT OUTER JOIN leilighet ON andelseier.and_eier_nr = leilighet.and_eier_nr 
 RIGHT OUTER JOIN bygning ON bygning.bygn_id = leilighet.bygn_id 
 RIGHT OUTER JOIN poststed ON bygning.postnr = poststed.postnr 
 GROUP BY postnr
