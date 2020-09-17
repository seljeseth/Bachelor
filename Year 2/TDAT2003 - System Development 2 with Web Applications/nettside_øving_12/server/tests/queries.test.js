// @flow
var mysql = require("mysql");
const ArtikkelDao = require("./test_queries.js");
const runsqlfile = require("./runsqlfile.js");

var pool = mysql.createPool({
	connectionLimit: 1,
	host: "mysql.stud.iie.ntnu.no",
	user: "sabines",
	password: "YG5Z6XDl",
	database: "sabines",
	debug: false,
	multipleStatements: true
});



let artikkelDao = new ArtikkelDao(pool);

beforeAll(done =>
{
	runsqlfile("tests/create_tables.sql", pool, () =>
	{
		runsqlfile("tests/create_testdata.sql", pool, done);
	});
});

afterAll(() =>
{
	pool.end();
});

test("hent alle artiklene fra db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data.length = " + data.length
		       );
		expect(data.length).toBe(6);
		done();
	}

	artikkelDao.getAll(callback);
});

test("hent en artikkel fra db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data = " + JSON.stringify(data)
		       );
		expect(data.length).toBe(1);
		expect(data[0].overskrift).toBe("Overskrift1");
		done();
	}

	artikkelDao.getOne(1, callback);
});


test("hent alle artiklene i en kategori fra db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data.length = " + data.length
		       );
		expect(data.length).toBe(2);
		done();
	}

	artikkelDao.getAllInCategori(1,callback);
});

test("hent alle artiklene med en prioritert fra db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data.length = " + data.length
		       );
		expect(data.length).toBe(3);
		done();
	}

	artikkelDao.getArticleswithPriority(1,callback);
});

test("hent alle nye artikler fra db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data.length = " + data.length
		       );
		expect(data.length).toBe(6);
		done();
	}

	artikkelDao.getFreshArticles(callback);
});

test("hent alle kategorier fra db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data.length = " + data.length
		       );
		expect(data.length).toBe(3);
		done();
	}

	artikkelDao.getCategories(callback);
});

test("hent alle kommentarer til en artikkel fra db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data.length = " + data.length
		       );
		expect(data.length).toBe(2);
		done();
	}

	artikkelDao.getComments(1, callback);
});

test("legg til en kommentar på en artikkel", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status=" + status + ", data=" + JSON.stringify(data)
		       );
		expect(data.affectedRows).toBeGreaterThanOrEqual(1);
		done();
	}

	artikkelDao.postComment({forfatter: "databasetest", innhold:"tester dette", artikkel_id: 2},callback);

});

test("slett kommentar på en artikkel", done =>
{

	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data = " + JSON.stringify(data)
		       );
		expect(data.affectedRows).toBe(1);
		done();
	}

	artikkelDao.deleteComment(3, callback);

});

test("legg til en artikkel i db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status=" + status + ", data=" + JSON.stringify(data)
		       );
		expect(data.affectedRows).toBeGreaterThanOrEqual(1);
		done();
	}

	artikkelDao.createOne
	         (
	         	//let info = [json.overskrift, json.innhold, json.bilde_source, json.viktighet, json.kategori_id];
		         {
			         overskrift: "nyArtikkel",
			         innhold:"innhold",
			         bilde_source:"https://1.vgc.no/drpublish/images/article/2015/08/17/23506999/1/big_10/2372441.jpg",
			         viktighet: 1,
			         kategori_id: 3
		         },
		         callback
	         );
});


test("slett artikkel fra db", done =>
{

	function callback(status, data)
	{
		console.log
		       (
			       "Test callback: status = " + status + ", data = " + JSON.stringify(data)
		       );
		expect(data.affectedRows).toBe(1);
		done();
	}

	artikkelDao.deleteOne(1, callback);

});

test("Oppdater en artikkel i db", done =>
{
	function callback(status, data)
	{
		console.log
		       (
			       "Test update a person: status=" + status + ", data=" + JSON.stringify(data)
		       );
		expect(data.affectedRows).toBe(1);
		done();
	}

	artikkelDao.updateOne
	           (
	           	{
			           overskrift: "Oppdatering",
			           innhold: "oppdatert innhold",
			           bilde_source: "https://1.vgc.no/drpublish/images/article/2015/08/17/23506999/1/big_10/2372441.jpg",
			           viktighet: 1,
			           kategori_id: 1
		           },
		            3,
		           callback
	           );
});