package org.devoxx4kids;

import com.tinkerforge.BrickletDistanceIR;
import com.tinkerforge.BrickletPiezoSpeaker;
import com.tinkerforge.IPConnection;
import org.devoxx4kids.util.Bricklet;
import org.devoxx4kids.util.BrickletReader;

import java.util.ResourceBundle;

public class Einparkpieper {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;

    public static void main(String args[]) throws Exception {

        BrickletReader brickletReader = new BrickletReader();
        brickletReader.readBricklets(HOST, PORT);
        Bricklet distanceBricklet = brickletReader.getBrickletByDeviceId(BrickletDistanceIR.DEVICE_IDENTIFIER);
        Bricklet piezoBricklet = brickletReader.getBrickletByDeviceId(BrickletPiezoSpeaker.DEVICE_IDENTIFIER);

        IPConnection ipcon = new IPConnection();
        BrickletDistanceIR dir = new BrickletDistanceIR(distanceBricklet.getUid(), ipcon);
        BrickletPiezoSpeaker ps = new BrickletPiezoSpeaker(piezoBricklet.getUid(),
                ipcon);
        ipcon.connect(HOST, PORT);

        //Einstellen einer unteren Grenze, wenn ich näher als 20cm bin, soll er trotzdem immer 20cm ausgeben
        dir.setDistanceCallbackThreshold('<', (short) 200, (short) 200);
        ps.calibrate();

      ResourceBundle language = ResourceBundle.getBundle("language");
      System.out.println(language.getString("information"));

        //Jetzt will ich unendlich lange pieeeeepen können
        while (true) {
            //Ich will  piepen, wenn das Hindernis nöher als 80cm ist
            if (dir.getDistance() < 800) {
                //Ich piepe
                ps.morseCode(".", 800);
                //Ich schlafe vor dem nächsten Piep um so länger, je weiter das Hindernis weg ist
                Thread.sleep(dir.getDistance());
            }
        }
    }
}
