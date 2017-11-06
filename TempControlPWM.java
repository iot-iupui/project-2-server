import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;

import java.io.*;
import com.pi4j.io.gpio.*;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;


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
        Gpio    = GpioFactory.getInstance();
        pin     = CommandArgumentParser.getPin(RaspiPin.class,    // pin provider class to obtain pin instance from
                                               RaspiPin.GPIO_01,  // default pin if no pin argument found
                                               args);             // argument array to search in

        // you can optionally use these wiringPi methods to further customize the PWM generator
        // see: http://wiringpi.com/reference/raspberry-pi-specifics/
        com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
        com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
        com.pi4j.wiringpi.Gpio.pwmSetClock(50);
        // pin.setShutdownOptions(true, PinState.LOW);
        pwm = gpio.provisionPwmOutputPin(pin);
    }
    
    public static double getTemp() {
		W1Master master = new W1Master();
		for (TemperatureSensor device : master.getDevices(TemperatureSensor.class)) {
            if(device.getName().contains("28-00000754ee85")) {
				return device.getTemperature(TemperatureScale.FARENHEIT);
			}
        }
        return 0; 
	}
}