//@flow
import {Artikkel, Kommentarer} from "./classes";

const Dao = require('./dao.js');
module.exports = class ArtikkelDao extends Dao
{
	//hent alle artikler
	getAll(feedback_from_database: JSON)
	{
		super.query('SELECT id, overskrift, innhold, DATE_FORMAT(tidspunkt, "%e %M %y %k:%i") AS tidspunkt, bilde_source, forfatter, kategori_id, viktighet FROM Artikkel', [], feedback_from_database);
	}



	//hent en artikkel
	getOne(id: number, feedback_from_database: JSON)
	{
		super.query('SELECT id, overskrift, innhold, DATE_FORMAT(tidspunkt, "%e %M %y %k:%i") AS tidspunkt, bilde_source, forfatter, kategori_id, viktighet FROM Artikkel WHERE id = ?', [id], feedback_from_database);
	}



	//hent alle artikler fra en spesifikk kategori
	getAllInCategori(id: number, feedback_from_database: JSON)
	{
		super.query('SELECT id, overskrift, innhold, DATE_FORMAT(tidspunkt, "%e %M %y %k:%i") AS tidspunkt, bilde_source, forfatter , viktighet FROM Artikkel WHERE kategori_id = ? LIMIT 20', [id], feedback_from_database);
	}



	//hent alle med viktighet x
	getArticleswithPriority(viktighet: number, feedback_from_database: JSON)
	{
		super.query('SELECT id, overskrift, innhold, DATE_FORMAT(tidspunkt, "%e %M %y %k:%i") AS tidspunkt, bilde_source, forfatter ,kategori_id FROM Artikkel WHERE viktighet = ?', [viktighet], feedback_from_database);
	}



	//slett en artikkel
	deleteOne(id: number, feedback_from_database: JSON)
	{
		super.query('DELETE FROM Kommentarer WHERE artikkel_id = ?', [id], function () {});
		super.query('DELETE FROM Artikkel WHERE id = ?', [id], feedback_from_database);
	}



	//opprett en artikkel
	createOne(artikkel: Artikkel, feedback_from_database: JSON)
	{
		let info = [artikkel.overskrift, artikkel.innhold, artikkel.bilde_source, artikkel.forfatter , artikkel.viktighet, artikkel.kategori_id];
		super.query('INSERT INTO Artikkel (overskrift, innhold,  bilde_source, forfatter, viktighet, kategori_id) VALUES (?,?,?,?,?,?)', info, feedback_from_database);
	}



	//oppdater en artikkel
	updateOne(artikkel: Artikkel, id: number, feedback_from_database: JSON)
	{
		let info = [artikkel.overskrift, artikkel.innhold, artikkel.bilde_source, artikkel.viktighet, artikkel.kategori_id, id];
		super.query('UPDATE Artikkel SET overskrift = ?, innhold = ?, bilde_source = ?, viktighet = ?, kategori_id = ? WHERE id = ?', info, feedback_from_database);
	}

	//hent ut de nyeste artiklene
	getFreshArticles(feedback_from_database: JSON)
	{
		super.query('SELECT id, overskrift, innhold, DATE_FORMAT(tidspunkt, "%e %M %y %k:%i") AS tidspunkt, bilde_source, forfatter, kategori_id, viktighet FROM Artikkel ORDER BY id DESC LIMIT 6', [], feedback_from_database);
	}


	getCategories(feedback_from_database: JSON)
	{
		super.query('SELECT kategori_id, navn FROM Kategori', [], feedback_from_database);
	}

	getComments(artikkel_id: number, feedback_from_database: JSON)
	{
		super.query('SELECT kommentar_id, DATE_FORMAT(tidspunkt, "%e %M %y %k:%i") AS tidspunkt, forfatter, innhold, artikkel_id FROM Kommentarer WHERE artikkel_id = ?', [artikkel_id], feedback_from_database);
	}

	postComment(kommentar:Kommentarer, feedback_from_database: JSON)
	{
		let info = [kommentar.forfatter, kommentar.innhold, kommentar.artikkel_id];
		super.query('INSERT INTO Kommentarer(FORFATTER, INNHOLD, ARTIKKEL_ID) VALUES (?,?,?)', info, feedback_from_database);
	}

	deleteComment(kommentar_id: number, feedback_from_database: JSON)
	{
		super.query('DELETE FROM Kommentarer WHERE kommentar_id = ?', [kommentar_id], feedback_from_database);

	}



};

