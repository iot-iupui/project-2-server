package hello.world;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class GetHelloWorld extends CoapResource {

    public GetHelloWorld() {
        super("hello");

        // set display name
        getAttributes().setTitle("hello");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(new GreetingPojo("Hello World").toString());
    }
}
