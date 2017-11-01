const coap = require('coap');


let server = coap.createServer((req, res) => {
    console.log("In create server");
    if(req.method == 'GET') {
        res.write("Hello World")
    }
    req.end();
});

server.listen("coap://localhost:5683/", () => {
    console.log("I was hit");
});