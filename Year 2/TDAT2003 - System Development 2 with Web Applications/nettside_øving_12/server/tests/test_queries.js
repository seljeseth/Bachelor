const Dao = require('./dao.js');

module.exports = class ArtikkelDao extends Dao
{
	//hent alle artikler
	getAll(feedback_from_database)
	{
		super.query('SELECT id, overskrift, innhold, tidspunkt, bilde_source, kategori_id, viktighet FROM TestArtikkel', [], feedback_from_database);
	}



	//hent en artikkel
	getOne(id, feedback_from_database)
	{
		super.query('SELECT id, overskrift, innhold, tidspunkt, bilde_source, kategori_id, viktighet FROM TestArtikkel WHERE id = ?', [id], feedback_from_database);
	}



	//hent alle artikler fra en spesifikk kategori
	getAllInCategori(id, feedback_from_database)
	{
		super.query('SELECT id, overskrift, innhold, tidspunkt, bilde_source, viktighet FROM TestArtikkel WHERE kategori_id = ?', [id], feedback_from_database);
	}



	//hent alle med viktighet x
	getArticleswithPriority(viktighet, feedback_from_database)
	{
		super.query('SELECT id, overskrift, innhold, tidspunkt, bilde_source, kategori_id FROM TestArtikkel WHERE viktighet = ?', [viktighet], feedback_from_database);
	}



	//slett en artikkel
	deleteOne(id, feedback_from_database)
	{
		super.query('DELETE FROM TestKommentarer WHERE artikkel_id = ?', [id], function (){});
		super.query('DELETE FROM TestArtikkel WHERE id = ?', [id], feedback_from_database);
	}



	//opprett en artikkel
	createOne(json, feedback_from_database)
	{
		let info = [json.overskrift, json.innhold, json.bilde_source, json.viktighet, json.kategori_id];
		super.query('INSERT INTO TestArtikkel (overskrift, innhold,  bilde_source, viktighet, kategori_id) VALUES (?,?,?,?,?)', info, feedback_from_database);
	}



	//oppdater en artikkel
	updateOne(json, id, feedback_from_database)
	{
		let info = [json.overskrift, json.innhold, json.bilde_source, json.viktighet, json.kategori_id, id];
		super.query('UPDATE TestArtikkel SET overskrift = ?, innhold = ?, bilde_source = ?, viktighet = ?, kategori_id = ? WHERE id = ?', info, feedback_from_database);
	}

	//hent ut de nyeste artiklene
	getFreshArticles(feedback_from_database)
	{
		super.query('SELECT id, overskrift, innhold, tidspunkt, bilde_source, kategori_id, viktighet FROM TestArtikkel ORDER BY id DESC LIMIT 6', [], feedback_from_database);
	}


	getCategories(feedback_from_database)
	{
		super.query('SELECT kategori_id, navn FROM TestKategori', [], feedback_from_database);
	}

	getComments(artikkel_id, feedback_from_database)
	{
		super.query('SELECT tidspunkt, forfatter, innhold, artikkel_id FROM TestKommentarer WHERE artikkel_id = ?', [artikkel_id], feedback_from_database);
	}

	postComment(json, feedback_from_database)
	{
		let info = [json.forfatter, json.innhold, json.artikkel_id];
		super.query('INSERT INTO TestKommentarer(FORFATTER, INNHOLD, ARTIKKEL_ID) VALUES (?,?,?)', info, feedback_from_database);
	}

	deleteComment(kommentar_id, feedback_from_database)
	{
		super.query('DELETE FROM TestKommentarer WHERE kommentar_id = ?', [kommentar_id], feedback_from_database);

	}



};

