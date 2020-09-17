DROP TABLE if exists TestKommentarer;
DROP TABLE if exists TestArtikkel;
DROP TABLE if exists TestKategori;


CREATE TABLE TestKategori
(

  kategori_id int NOT NULL AUTO_INCREMENT,
  navn        VARCHAR(30),
  PRIMARY KEY (kategori_id)
);


CREATE TABLE TestArtikkel
(

  id           int NOT NULL AUTO_INCREMENT,
  overskrift   VARCHAR(300) NOT NULL,
  innhold      VARCHAR(5000),
  tidspunkt    DATETIME NOT NULL DEFAULT current_timestamp,
  bilde_source TEXT,
  forfatter    varchar(50),
  viktighet    SMALLINT,
  kategori_id  int,
  PRIMARY KEY (id),
  FOREIGN KEY (kategori_id) REFERENCES TestKategori (kategori_id)



);

CREATE TABLE TestKommentarer
(
    kommentar_id int      not null auto_increment,
    tidspunkt    DATETIME NOT NULL DEFAULT current_timestamp,
    forfatter    varchar(30),
    innhold      varchar(250),
    artikkel_id  int,
    PRIMARY KEY (kommentar_id),
    FOREIGN KEY (artikkel_id) references TestArtikkel (id)


);

