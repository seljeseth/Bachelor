// @flow

import express from 'express';
import path from 'path';
import reload from 'reload';
import fs from 'fs';
import mysql from 'mysql';

let pool = mysql.createPool({
	host: 'mysql-ait.stud.idi.ntnu.no',
	user: 'sabines',
	password: 'YG5Z6XDl',
	database: 'sabines',
	dateStrings: true
});

const public_path = path.join(__dirname, '/../../client/public');

let app = express();

const ArtikkelDao = require("./queries.js");

let artikkelDao = new ArtikkelDao(pool);


app.use(express.static(public_path));
app.use(express.json());


app.get("/kategorier", (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.getCategories(feedback_from_database);
});

//hent alle artikler
app.get("/artikler", (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.getAll(feedback_from_database);
});

//hent ut de nyeste artiklene
app.get("/artikler/nyhetsoppdatering", (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.getFreshArticles(feedback_from_database);
});

//hent en spesifikk artikkel
app.get('/artikler/:id', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data[0]);
	}
	artikkelDao.getOne(request.params.id, feedback_from_database);
});

//hent alle kommentarene til en artikkel
app.get('/artikler/:id/kommentarer', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.getComments(request.params.id, feedback_from_database);
});

//Opprett en kommentar
app.post('/artikler/:id/kommentarer', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.postComment(request.body, feedback_from_database);
});

//hente alle artikkel fra en kategori
app.get('/artikler/kategori/:kategori_id', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.getAllInCategori(request.params.kategori_id, feedback_from_database);
});

//hent alle artikler med viktighet x
app.get('/artikler/viktighet/:viktighet', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.getArticleswithPriority(request.params.viktighet, feedback_from_database);
});

//Oppdater en artikkel
app.put('/artikler/:id', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.updateOne(request.body, request.params.id, feedback_from_database);
});

//Opprett en artikkel
app.post('/artikler', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send({id: data.insertId});
	}
	artikkelDao.createOne(request.body, feedback_from_database);
});

//Slett en artikkel
app.delete('/artikler/:id', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
	}
	artikkelDao.deleteOne(request.params.id, feedback_from_database);
});

//Slett en kommentar
app.delete('/artikler/:id/kommentarer/:kommentar_id', (request, result) =>
{
	function feedback_from_database(status, data)
	{
		result.status(status).send(data);
		console.log('i wanna die');
		console.log(request.params.kommentar_id);
	}
	artikkelDao.deleteComment(request.params.kommentar_id, feedback_from_database);
});



export let listen = new Promise<void>((resolve, reject) =>
{
	reload(app).then(reloader =>
	{
		app.listen(3000, (error: ?Error) =>
		{
			if (error) reject(error.message);
			console.log('Express server started');
			reloader.reload();
			fs.watch(public_path, () => reloader.reload());
			resolve();
		});
	});
});


