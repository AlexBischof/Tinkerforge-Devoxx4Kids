package org.devoxx4kids;

import com.tinkerforge.BrickletDistanceIR;
import com.tinkerforge.BrickletPiezoSpeaker;
import com.tinkerforge.BrickletRotaryPoti;
import com.tinkerforge.IPConnection;

import java.util.ResourceBundle;

public class Main {

  //Einstellungen für Distanzsensor
  private final static int MIN_DISTANCE = 400;
  private final static int MAX_DISTANCE = 600;

  //Einstellungen für Rotiersensor
  private final static int MIN_ROTIER = 100;
  private final static int MAX_ROTIER = 120;

  private static final String HOST = "localhost";
  private static final int PORT = 4223;

  public static void main(String args[]) throws Exception {
	// Verbinde mich zur Schaltung

	ResourceBundle language = ResourceBundle.getBundle("language");

	BrickletReader brickletReader = new BrickletReader();
	brickletReader.readBricklets(HOST, PORT);
	Bricklet distanceBricklet = brickletReader.getBrickletByDeviceId(BrickletDistanceIR.DEVICE_IDENTIFIER);
	Bricklet piezoBricklet = brickletReader.getBrickletByDeviceId(BrickletPiezoSpeaker.DEVICE_IDENTIFIER);
	Bricklet potiBricklet = brickletReader.getBrickletByDeviceId(BrickletRotaryPoti.DEVICE_IDENTIFIER);

	IPConnection ipcon = new IPConnection();
	BrickletDistanceIR dir = new BrickletDistanceIR(distanceBricklet.getUid(), ipcon);
	BrickletPiezoSpeaker ps = new BrickletPiezoSpeaker(piezoBricklet.getUid(),
	                                                   ipcon);
	BrickletRotaryPoti lp = new BrickletRotaryPoti(potiBricklet.getUid(), ipcon);
	ipcon.connect(HOST, PORT);

	System.out.println(language.getString("information"));
	System.out.println(String.format(language.getString("information2"), MIN_DISTANCE / 10, MAX_DISTANCE / 10));
	System.out.println(String.format(language.getString("information3"), MIN_ROTIER, MAX_ROTIER));

	// Einstellen einer unteren Grenze, wenn ich näher als 20cm bin, soll er
	// trotzdem immer 20cm ausgeben
	dir.setDistanceCallbackThreshold('<', (short) 200, (short) 200);
	ps.calibrate();

	// Jetzt soll sich mein Tor Öffnen
	int count = 0;

	while (true) {

	  Thread.sleep(1000l);

	  // Es soll immer ein Ton herauskommen, wenn ich näher als 80cm bin
	  if (firstDistanceOK(dir) && secondDistanceOK(lp)) {
		ps.beep(10, 400);
		count++;
		Thread.sleep(100);

		if (20 == count) {
		  for (int i = 0; i < 20; i++)
			ps.calibrate();
		  break;
		}
	  }
	}
  }

  private static boolean secondDistanceOK(BrickletRotaryPoti lp)
		  throws Exception {
	return lp.getPosition() > MIN_ROTIER && lp.getPosition() < MAX_ROTIER;
  }

  private static boolean firstDistanceOK(BrickletDistanceIR dir)
		  throws Exception {
	return dir.getDistance() < MAX_DISTANCE && dir.getDistance() > MIN_DISTANCE;
  }
}
