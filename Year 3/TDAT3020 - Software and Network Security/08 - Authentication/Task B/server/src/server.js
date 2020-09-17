const express = require('express');
const path = require('path');
const reload = require('reload');
const fs = require('fs');
const public_path = path.join(__dirname, '/../../client/public');

let app = express();

require("./routes/middleware")(app);
require("./routes/appRoutes")(app);

// Setup hot reload (refresh web page on client changes)
let listen = new Promise((resolve, reject) =>
{
	reload(app).then(reloader =>
	{
		app.listen(3001, (error) =>
		{
			if (error) reject(error.message);
			console.log('Server running...');
			reloader.reload();
			fs.watch(public_path, () => reloader.reload());
			resolve();
		});
	});
});

module.exports = {listen};