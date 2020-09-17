'use strict';

const net = require('net');
var crypto = require('crypto');
let clients = [];
let nrOfClients = 0;

//  Client
const httpServer = net.createServer(connection => {
    connection.on('data', (data) => {
        let content = `<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
  </head>
  <body>
  øving 7
    <script>
      let ws = new WebSocket('ws://localhost:3001');
      ws.onmessage = event => alert(event.data);
      ws.onopen = () => ws.send('hello');
    </script>
  </body>
</html>
`;
        connection.write('HTTP/1.1 200 OK\r\nContent-Length: ' + content.length + '\r\n\r\n' + content);
    });
});

httpServer.on('error', error => {
    console.error('Error: ', error);
});
httpServer.listen(3000, () => {
    console.log('HTTP server listening on port 3000');
});



//Server
const wsServer = net.createServer(connection => {
    console.log('Client connected');

    connection.on('data', data => {
        let string = data.toString();
        //handshake om det er den første meldingen fra klienten altså en get melding.
        if (string.substring(0, 3) === 'GET') {
            let sec_websocket_key = string.substring(data.toString().indexOf("-Key: ") + 6, data.toString().indexOf("==") + 2);
            let key = GenerateKey(sec_websocket_key);


            let answer = "HTTP/1.1 101 Switching Protocols\n" +
                "Upgrade: websocket\n" +
                "Connection: Upgrade\n" +
                "Sec-WebSocket-Accept: " + key + "\r\n\r\n";

            connection.write(answer);
            clients.push(connection);
            nrOfClients++;

        }
        else
        {
            let fromClient = DatatoString(data);
            console.log('Data received from client: ' + fromClient);
            StringToData(fromClient, connection);
        }

    });

    //fjerner clienten fra lista med connections når den disconnecter
    connection.on('end', () => {
        console.log('Client disconnected');
        const index = clients.indexOf(connection);
        if (index > -1)
        {
            clients.splice(index, 1);
            nrOfClients--;
        }
    });
   connection.on('error', error => {
        console.error('Error: ', error);
    });
});

wsServer.on('error', error => {
    console.error('Error: ', error);
});
wsServer.listen(3001, () => {
    console.log('WebSocket server listening on port 3001');
});

/**
 * @return {string}
 */
//unmaskerer meldingen fra klienten
function DatatoString (data) {

    let bytes = Buffer.from(data);
    let length = bytes[1] & 127;
    let maskStart = 2;
    let dataStart = maskStart + 4;
    let output = "";
    for (let i = dataStart; i < dataStart + length; i++) {
        let byte = bytes[i] ^ bytes[maskStart + ((i - dataStart) % 4)];
        output += String.fromCharCode(byte);
    }
    return output;
}


//konverterer til bytes
function StringToData(str, connection)
{
        str = "\nReceived from client " + (clients.indexOf(connection)+1)  +" : "  + str + "\n NR OF CLIENTS CONNECTED: " + nrOfClients;
        let buffer = Buffer.concat
        ([
            new Buffer.from([0x81, "0x" + (str.length + 0x10000).toString(16).substr(-2).toUpperCase()]),
            Buffer.from(str)
        ]);
    //klientne skriver kun til andre klienter og ikke seg selv.
    clients.forEach(client =>
    {
        if (client !== connection) client.write(buffer);
    });
}

/**
 * @return {string}
 */
//genererer nøkkelen slik at klienten godtar handshaken og vi får koblet oss opp
function GenerateKey(sec_websocket_key) {
    return crypto
        .createHash('sha1')
        .update(sec_websocket_key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11")
        .digest("base64");


}

