package org.iupui.iot.project2.server;

import org.eclipse.californium.core.CoapServer;

public class StartServer {
    CoapServer server;

    public StartServer() {
        this.server = new CoapServer();

        this.server.add(new GetGreeting());

        this.server.start();
    }
}
