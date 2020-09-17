const express = require("express");
const bodyParser = require("body-parser");
const app = express();
app.use(bodyParser.json()); // for å tolke JSON
const cors = require("cors");
app.use(cors({origin: true}));
var server = app.listen(3000);
var fs = require('fs');
const util = require('util');
const exec = util.promisify(require('child_process').exec);




app.post('/CompileAndRun', async function(req, res){

    //console.log(req.body.code);

    let code = req.body.code;
    fs.writeFile('../src/Main.cpp', code, async function (err) {
        if (err) throw err;
        //runCommand("ls").then((output) => res.send(output));

        console.log('Replaced!');
    });

    try {


        await runCommand("cd ../src && docker build -t dockerimage --no-cache=true .");
        let output = await runCommand("docker run --rm dockerimage");

        res.send({output: output});
        console.log("output: " + output);
    }catch(e)
    {
        console.log("e: " + e);
        res.send({output: e.toString()})
    }



});




async function runCommand(arg) {
    const {stdout, stderr} = await exec(arg);
    if(stderr) return stderr;
    return stdout;
}






