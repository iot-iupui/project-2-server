package org.iupui.iot.project2.sensor.temperature;

import com.pi4j.io.gpio.*;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;
import com.pi4j.component.temperature.TemperatureSensor;

/**
 * Hybrid code between temperature example and pwm controller
 * which uses the temperature sensed to dictate the pwm interval.
 * This has the effect of brightening and dimming the LED as the temp
 * increases and decreases respectively.
 *
 * CITATIONS
 * https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/W1TempExample.java
 * https://www.youtube.com/watch?v=ea1dT-SqQ4Q
 * https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/PwmExample.java
 */
public class TempControlPWM {

    final Console console;
    GpioController gpio;
    Pin pin;
    GpioPinPwmOutput pwm;

    public TempControlPWM() {
        console = new Console();
        gpio = GpioFactory.getInstance();
        pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_01);

        // setup wiring pi mode
        com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
        com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
        com.pi4j.wiringpi.Gpio.pwmSetClock(50);
        // pin.setShutdownOptions(true, PinState.LOW);
        pwm = gpio.provisionPwmOutputPin(pin);
    }

    public double getTemp() {
		W1Master master = new W1Master();
		for (TemperatureSensor device : master.getDevices(TemperatureSensor.class)) {
            if(device.getName().contains("28-00000754ee85")) {
				return device.getTemperature(TemperatureScale.FARENHEIT);
			}
        }
        return 0;
	}
}