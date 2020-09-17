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

app.use(express.static(public_path));
app.use(express.json()); // For parsing application/json

class Artikkel
{
    id: number;
    overskrift: string;
    innhold: string;
    tidspunkt: Date;
    bilde_source:string;
    kategori_id: number;
    viktighet: number;

}

//hente alle artikkel
app.get("/artikler", (req: express$Request, res: express$Response) =>
{
    console.log("Fikk request om å hente alle artikler fra klient");
    pool.query('select id, overskrift, innhold, tidspunkt, bilde_source, kategori_id, viktighet from Artikkel', (error, results) =>
    {
        if (error) {
            console.error(error);
            return res.status(500);
        }
        res.send(results);
    });
});

//hente alle artikkel fra en kategori
app.get("/artikler/kategori/:kategori_id", (req: express$Request, res: express$Response) =>
{
    console.log("Fikk request om å hente alle artikkel fra en kategori fra klient");
    pool.query('select id, overskrift, innhold, tidspunkt, bilde_source, viktighet from Artikkel where kategori_id = ?',
        [req.params.kategori_id], (error, results: Artikkel[]) =>
    {
        if (error) {
            console.error(error);
            return res.status(500);
        }
        if (results.length == 0) return res.sendStatus(404); // No row found

        res.send(results);
    });
});

//hente alle artikkel med viktighet x
app.get("/artikler/viktighet/:viktighet", (req: express$Request, res: express$Response) =>
{
    console.log("Fikk request om å hente alle artikkel med viktighet x fra klient");
    pool.query('select id, overskrift, innhold, tidspunkt, bilde_source, kategori_id from Artikkel where viktighet = ?',
        [req.params.viktighet], (error, results: Artikkel[]) =>
        {
            if (error) {
                console.error(error);
                return res.status(500);
            }
            if (results.length == 0) return res.sendStatus(404); // No row found

            res.send(results);
        });
});

//hente en spesifikk artikkel
app.get('/artikler/:id', (req: express$Request, res: express$Response) =>
{
    console.log("Fikk request om å hente en spesifikk artikkel fra klient");
    pool.query('select id, overskrift, innhold, tidspunkt, bilde_source, kategori_id, viktighet from Artikkel where id=?',
        [req.params.id], (error, results: Artikkel[]) =>
        {
            if (error) {
                console.error(error);
                return res.status(500);
            }
            if (results.length == 0) return res.sendStatus(404); // No row found

            res.send(results[0]);
        });
});

//endre artikkel
app.put('/artikler/:id', (req: {body: Artikkel}, res: express$Response) =>
{

    console.log("Fikk request om å endre en spesifikk artikkel fra klient");
    pool.query(

        'update Artikkel set overskrift = ?, innhold = ?, bilde_source = ?, viktighet = ?, kategori_id = ? where id = ?',
        [req.body.overskrift, req.body.innhold, req.body.bilde_source, req.body.viktighet, req.body.kategori_id, req.body.id],
        (error, results) =>
        {
            if (error) {
                console.error(error);
                return res.status(500);
            }
            if (results.affectedRows == 0) return res.sendStatus(500); // No row updated

            res.sendStatus(200);
        }
    );
});


//slett en artikkel
app.delete('/artikler/:id', (req: express$Request, res: express$Response) =>
{
    console.log("Fikk request om å slette en spesifikk artikkel fra klient");
    pool.query('delete from Artikkel where id=?',
        [req.params.id], (error, results: Artikkel[]) =>
        {
            if (error) {
                console.error(error);
                return res.status(500);
            }
            if (results.length == 0) return res.sendStatus(404); // No row found

            res.send(results[0]);
        });
});

//legge inn en artikkel
app.post("/artikler", (req: {body: Artikkel}, res: express$Response) =>
{
    console.log("Fikk request om å opprette artikkel");
    pool.query('INSERT INTO Artikkel (overskrift, innhold,  bilde_source, viktighet, kategori_id) VALUES (?,?,?,?,?)',
        [req.body.overskrift, req.body.innhold, req.body.bilde_source, req.body.viktighet, req.body.kategori_id], (error, results) =>
    {
        if (error) {
            console.error(error);
            return res.status(500);
        }
        if (results.length == 0) return res.sendStatus(404); // No row found

        res.send(results);
    });
});




// The listen promise can be used to wait for the web server to start (for instance in your tests)
export let listen = new Promise<void>((resolve, reject) =>
{
    // Setup hot reload (refresh web page on client changes)
    reload(app).then(reloader =>
    {
        app.listen(3000, (error: ?Error) =>
        {
            if (error) reject(error.message);
            console.log('Express server started');
            // Start hot reload (refresh web page on client changes)
            reloader.reload(); // Reload application on server restart
            fs.watch(public_path, () => reloader.reload());
            resolve();
        });
    });
});
