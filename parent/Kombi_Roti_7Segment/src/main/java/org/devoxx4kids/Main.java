package org.javaland4kids;

import com.tinkerforge.*;

/**
 * Created by alexanderbischof on 18.09.14.
 */
public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;

    public static void main(String args[]) throws Exception {

        //Find UID
        BrickletReader brickletReader = new BrickletReader();
        brickletReader.readBricklets(HOST, PORT);
        Bricklet rotiBricklet = brickletReader.getBrickletByDeviceId(BrickletRotaryPoti.DEVICE_IDENTIFIER);
        Bricklet segmentBricklet = brickletReader.getBrickletByDeviceId(BrickletSegmentDisplay4x7.DEVICE_IDENTIFIER);

        IPConnection ipcon = new IPConnection();

        BrickletRotaryPoti rotierSensor = new BrickletRotaryPoti(rotiBricklet.getUid(), ipcon); // Create device object

        final BrickletSegmentDisplay4x7 segmentDisplay4x7 = new BrickletSegmentDisplay4x7(segmentBricklet.getUid(), ipcon); // Create device object
        ipcon.connect(HOST, PORT);

        System.out.println("Drehe am Drehknopf und lies den Wert auf der 7-Segmentanzeige ab.");

        //CallbackHandler für Rotiersensor einrichten
        rotierSensor.setPositionCallbackPeriod(50);
        rotierSensor.addPositionListener(new BrickletRotaryPoti.PositionListener() {
            @Override
            public void position(short position) {
                int ersteZahlIndex = position < 0 ? 64 : 0;

                position=(short)Math.abs(position);

                //Ermitteln von den Stellen
                int zweiteZahlIndex = (position / 100 % 10);
                int dritteZahlIndex = (position / 10 % 10);
                int vierteZahlIndex = (position % 10);

                short[] segments = {position,
                        getByteForCharacter(zweiteZahlIndex),
                        getByteForCharacter(dritteZahlIndex),
                        getByteForCharacter(vierteZahlIndex)
                };
                try {
                    segmentDisplay4x7.setSegments(segments, (short) 7, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Press key to exit");
        System.in.read();

        ipcon.disconnect();
    }

    public static byte getByteForCharacter(int c) {

        char character = ("" + c).charAt(0);
        switch (character) {
            //Zahlen
            case '0':
                return 0x3f;
            case '1':
                return 0x06;
            case '2':
                return 0x5b;
            case '3':
                return 0x4f;
            case '4':
                return 0x66;
            case '5':
                return 0x6d;
            case '6':
                return 0x7d;
            case '7':
                return 0x07;
            case '8':
                return 0x7f;
            case '9':
                return 0x6f;

            //Kleinbuchstaben
            case 'a':
                return 0x5f;
            case 'b':
                return 0x7c;
            case 'c':
                return 0x58;
            case 'd':
                return 0x5e;
            case 'e':
                return 0x7b;
            case 'f':
                return 0x71;
            case 'g':
                return 0x6f;
            case 'h':
                return 0x74;
            case 'i':
                return 0x02;
            case 'j':
                return 0x1e;
            case 'k':
                return 0x00; //npr
            case 'l':
                return 0x06;
            case 'm':
                return 0x00; //npr
            case 'n':
                return 0x54;
            case 'o':
                return 0x5c;
            case 'p':
                return 0x73;
            case 'q':
                return 0x67;
            case 'r':
                return 0x50;
            case 's':
                return 0x6d;
            case 't':
                return 0x78;
            case 'u':
                return 0x1c;
            case 'v':
                return 0x00;//npr
            case 'w':
                return 0x00;//npr
            case 'x':
                return 0x00;//npr
            case 'y':
                return 0x6e;
            case 'z':
                return 0x00;//npr

            //Großbuchstaben
            case 'A':
                return 0x77;
            case 'B':
                return 0x7c;
            case 'C':
                return 0x39;
            case 'D':
                return 0x5e;
            case 'E':
                return 0x79;
            case 'F':
                return 0x71;
            case 'G':
                return 0x6f;
            case 'H':
                return 0x76;
            case 'I':
                return 0x06;
            case 'J':
                return 0x1e;
            case 'K':
                return 0x00; //npr
            case 'L':
                return 0x38;
            case 'M':
                return 0x00; //npr
            case 'N':
                return 0x54;
            case 'O':
                return 0x3f;
            case 'P':
                return 0x73;
            case 'Q':
                return 0x67;
            case 'R':
                return 0x50;
            case 'S':
                return 0x6d;
            case 'T':
                return 0x78;
            case 'U':
                return 0x3e;
            case 'V':
                return 0x00;//npr
            case 'W':
                return 0x00;//npr
            case 'X':
                return 0x00;//npr
            case 'Y':
                return 0x6e;
            case 'Z':
                return 0x00;//npr
        }
        return 0;
    }
}
