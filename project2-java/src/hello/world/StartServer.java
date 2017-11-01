package hello.world;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.Endpoint;

public class StartServer {

    CoapServer server;

    public StartServer() {
        this.server = new CoapServer();

        this.server.add(new GetHelloWorld());

        this.server.start();
    }
}
