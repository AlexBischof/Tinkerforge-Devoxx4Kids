package org.devoxx4kids;

import com.tinkerforge.BrickletDistanceIR;
import com.tinkerforge.IPConnection;

import java.util.ResourceBundle;

/**
 * Created by alexanderbischof on 18.09.14.
 */
public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;

    public static void main(String args[]) throws Exception {

      final ResourceBundle language = ResourceBundle.getBundle("language");

      //Find UID
        BrickletReader brickletReader = new BrickletReader();
        brickletReader.readBricklets(HOST, PORT);
        Bricklet segmentBricklet = brickletReader.getBrickletByDeviceId(BrickletDistanceIR.DEVICE_IDENTIFIER);

        IPConnection ipcon = new IPConnection();
        BrickletDistanceIR dir = new BrickletDistanceIR(segmentBricklet.getUid(), ipcon); // Create device object
        ipcon.connect(HOST, PORT);

        dir.setDistanceCallbackPeriod(1000);
        dir.addDistanceListener(new BrickletDistanceIR.DistanceListener() {
            @Override
            public void distance(int distance) {
                System.out.println(language.getString("newValue")+ ": " + (distance / 10) + " cm");
            }
        });

        System.out.println(language.getString("exit"));
        System.in.read();

        ipcon.disconnect();
    }
}
