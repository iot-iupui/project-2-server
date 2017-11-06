package org.iotiupui.project2.hello.world;

import org.eclipse.californium.core.CoapServer;

public class StartServer {

    CoapServer server;

    public StartServer() {
        this.server = new CoapServer();

        this.server.add(new GetHelloWorld());

        this.server.start();
    }
}
