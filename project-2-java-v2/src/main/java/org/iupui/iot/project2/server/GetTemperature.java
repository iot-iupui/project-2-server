package org.iupui.iot.project2.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.iupui.iot.project2.sensor.temperature.TempControlPWM;

public class GetTemperature extends CoapResource {
    TempControlPWM sensor;

    public GetTemperature() {
        super("temperature");
        // set display name
        getAttributes().setTitle("temperature");

        sensor = new TempControlPWM();
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        try {
            String response = String.format("{'temperature': %f }", sensor.getTemp());
            exchange.respond(response);
        } catch (Exception e) {
            exchange.respond("Something went wrong");
        }
    }
}
