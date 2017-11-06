package rangesensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.text.DecimalFormat;
import java.text.Format;

/**
 * See https://www.modmypi.com/blog/hc-sr04-ultrasonic-range-sensor-on-the-raspberry-pi
 * 
 */
public class HC_SR04
{
  private final static Format DF22 = new DecimalFormat("#0.00");
  private final static Format DF_N = new DecimalFormat("#.##########################");

  private final static double SOUND_SPEED = 34029;       // in cm, 340.29 m/s
  private final static double DIST_FACT   = SOUND_SPEED / 2; // round trip
  
  private final static long BILLION      = (long)10E9;
  private final static int TEN_MICRO_SEC = 10_000; // In Nano secs

  final GpioController gpio;
  final GpioPinDigitalOutput trigPin;
  final GpioPinDigitalInput  echoPin;  


  public HC_SR04 () {
    
  
    System.out.println("GPIO Control - Range Sensor HC-SR04 Initialized.");
    
    //-------------------------------------------------------------------------
    // setup gpio controller and init sensor
    gpio = GpioFactory.getInstance();
    trigPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Trig", PinState.LOW);
    echoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,  "Echo");
    //-------------------------------------------------------------------------
  }

  public double getDistance () {

    System.out.println(">>> Waiting for the sensor to be ready (2s)...");
    Thread.sleep(2_000);
    

    //-------------------------------------------------------------------------
    // read distance
    trigPin.low();
    try { Thread.sleep(500); } catch (Exception ex) { ex.printStackTrace(); } 
    
    // Just to check...
    if (echoPin.isHigh())
      throw new Exception("echo pin read high before trigger pin set high");
        
    trigPin.high();

    try { Thread.sleep(0, TEN_MICRO_SEC); } catch (Exception ex) { ex.printStackTrace(); } 
    trigPin.low();

    // Block until the signal returns
    while (echoPin.isLow()); 

    // capture initial time 
    long start = System.nanoTime();
    
    // Block until echo's stop
    while (echoPin.isHigh());

    // capture ending time
    long end = System.nanoTime();
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    // calculate results
    if (end > start) 
    {
      double pulseDuration = (double)(end - start) / (double)BILLION; 
      double distance = pulseDuration * DIST_FACT;

      if (distance < 1_000) { // if greater than 10 meters throw
        throw new Exception("distance problems yo");
      }
      System.out.println("Distance : " + distance);
      return distance;
    }
    else 
    {
      throw new Exception("sleep problems yo");
    }
    //-------------------------------------------------------------------------
  }
}