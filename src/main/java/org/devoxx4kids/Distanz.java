package org.devoxx4kids;

import com.tinkerforge.BrickletDistanceIR;
import com.tinkerforge.IPConnection;

import java.util.ResourceBundle;

/**
 * Created by alexanderbischof on 18.09.14.
 */
public class Distanz {

  private static final String HOST = "localhost";
  private static final int PORT = 4223;

  public static void main(String args[]) throws Exception {

	//Find UID
	BrickletReader brickletReader = new BrickletReader();
	brickletReader.readBricklets(HOST, PORT);
	Bricklet segmentBricklet = brickletReader.getBrickletByDeviceId(BrickletDistanceIR.DEVICE_IDENTIFIER);

	IPConnection ipcon = new IPConnection();
	BrickletDistanceIR dir = new BrickletDistanceIR(segmentBricklet.getUid(), ipcon); // Create device object
	ipcon.connect(HOST, PORT);

	try {
	  while (true) {
		// Get current distance (unit is mm)
		int distance = dir.getDistance();

		System.out.println(ResourceBundle.getBundle("language").getString("distance") + ": " + distance / 10.0 + " cm");
		Thread.sleep(1000l);
	  }
	} finally {
	  ipcon.disconnect();
	}
  }
}
