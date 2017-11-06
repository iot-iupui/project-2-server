package org.iupui.iot.project2.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.iupui.iot.project2.sensor.distance.HC_SR04;

public class GetDistance extends CoapResource {
    HC_SR04 sensor;

    public GetDistance() {
        super("distance");

        // set display name
        getAttributes().setTitle("distance");
        sensor = new HC_SR04();

    }

    @Override
    public void handleGET(CoapExchange exchange) {
        try {
            String response = String.format("{'distance': %f}", sensor.getDistance());
            exchange.respond(response);
        } catch (Exception e) {
            exchange.respond("Something Went Wrong");
        }
    }
}
