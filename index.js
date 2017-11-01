const http = require("http");

http.createServer((req, res) => {
    res.write(JSON.stringify({
        "greeting": "Hello World"
    }));
    res.end();
}).listen(8080);