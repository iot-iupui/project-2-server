package org.iotiupui.project2.hello.world;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class GetHelloWorld extends CoapResource {

    public GetHelloWorld() {
        super("main/java/org/iotiupui/project2/hello");

        // set display name
        getAttributes().setTitle("main/java/org/iotiupui/project2/hello");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(new GreetingPojo("Hello Chris").toString());
    }
}
