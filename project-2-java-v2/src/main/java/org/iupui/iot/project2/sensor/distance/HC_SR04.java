package org.iupui.iot.project2.sensor.distance;

import com.pi4j.io.gpio.*;

public class HC_SR04 {

  private final static double SOUND_SPEED = 34029;       // in cm, 340.29 m/s
  private final static double DIST_FACT   = SOUND_SPEED / 2; // round trip

  private final static long BILLION      = (long)10E9;
  private final static int TEN_MICRO_SEC = 10000; // In Nano secs

  final GpioController gpio;
  final GpioPinDigitalOutput trigPin;
  final GpioPinDigitalInput echoPin;


  public HC_SR04 () {
    System.out.println("GPIO Control - Range Sensor HC-SR04 Initialized.");

    //-------------------------------------------------------------------------
    // setup gpio controller and init sensor
    gpio = GpioFactory.getInstance();
    trigPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Trig", PinState.LOW);
    echoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,  "Echo");
    //-------------------------------------------------------------------------
  }

  public double getDistance() throws Exception {

    System.out.println(">>> Waiting for the sensor to be ready (2s)...");
    Thread.sleep(2000);


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

      if (distance > 1000) { // if greater than 10 meters throw
        throw new Exception("distance problems yo");
      }
      return distance;
    }
    else
    {
      throw new Exception("sleep problems yo");
    }
    //-------------------------------------------------------------------------
  }
}
