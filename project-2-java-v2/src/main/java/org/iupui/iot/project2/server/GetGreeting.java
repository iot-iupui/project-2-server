package org.iupui.iot.project2.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class GetGreeting extends CoapResource {
    public GetGreeting() {
        super("hello");

        // set display name
        getAttributes().setTitle("hello");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(new HelloPojo("Hello Chris").toString());
    }

}
