package org.devoxx4kids;

import com.tinkerforge.BrickletPiezoSpeaker;
import com.tinkerforge.IPConnection;

/**
 * Created by alexanderbischof on 18.09.14.
 */
public class Morsen {

  private static final String HOST = "localhost";
  private static final int PORT = 4223;

  public static void main(String args[]) throws Exception {
	//Find UID
	BrickletReader brickletReader = new BrickletReader();
	brickletReader.readBricklets(HOST, PORT);
	Bricklet bricklet = brickletReader.getBrickletByDeviceId(BrickletPiezoSpeaker.DEVICE_IDENTIFIER);

	IPConnection ipcon = new IPConnection();
	BrickletPiezoSpeaker ps = new BrickletPiezoSpeaker(bricklet.getUid(),
	                                                   ipcon);
	ipcon.connect(HOST, PORT);
	ps.calibrate();

	try {
	  while (true) {
		System.out.print("Gib einen Text zum Morsen ein: ");
		String stringToMorse = System.console().readLine();

		String morseCode = "";
		for (int i = 0; i < stringToMorse.length(); i++) {
		  morseCode += encode(stringToMorse.substring(i, i + 1)) + " ";
		}

		System.out.println(morseCode);
		ps.morseCode(morseCode, 800);
	  }
	} finally {
	  ipcon.disconnect();
	}
  }

  public static String encode(String toEncode) {
	String morse = toEncode;

	if (toEncode.equalsIgnoreCase("a"))
	  morse = ".-";
	if (toEncode.equalsIgnoreCase("b"))
	  morse = "-...";
	if (toEncode.equalsIgnoreCase("c"))
	  morse = "-.-.";
	if (toEncode.equalsIgnoreCase("d"))
	  morse = "-..";
	if (toEncode.equalsIgnoreCase("e"))
	  morse = ".";
	if (toEncode.equalsIgnoreCase("f"))
	  morse = "..-.";
	if (toEncode.equalsIgnoreCase("g"))
	  morse = "--.";
	if (toEncode.equalsIgnoreCase("h"))
	  morse = "....";
	if (toEncode.equalsIgnoreCase("i"))
	  morse = "..";
	if (toEncode.equalsIgnoreCase("j"))
	  morse = ".---";
	if (toEncode.equalsIgnoreCase("k"))
	  morse = "-.-";
	if (toEncode.equalsIgnoreCase("l"))
	  morse = ".-..";
	if (toEncode.equalsIgnoreCase("m"))
	  morse = "--";
	if (toEncode.equalsIgnoreCase("n"))
	  morse = "-.";
	if (toEncode.equalsIgnoreCase("o"))
	  morse = "---";
	if (toEncode.equalsIgnoreCase("p"))
	  morse = ".--.";
	if (toEncode.equalsIgnoreCase("q"))
	  morse = "--.-";
	if (toEncode.equalsIgnoreCase("r"))
	  morse = ".-.";
	if (toEncode.equalsIgnoreCase("s"))
	  morse = "...";
	if (toEncode.equalsIgnoreCase("t"))
	  morse = "-";
	if (toEncode.equalsIgnoreCase("u"))
	  morse = "..-";
	if (toEncode.equalsIgnoreCase("v"))
	  morse = "...-";
	if (toEncode.equalsIgnoreCase("w"))
	  morse = ".--";
	if (toEncode.equalsIgnoreCase("x"))
	  morse = "-..-";
	if (toEncode.equalsIgnoreCase("y"))
	  morse = "-.--";
	if (toEncode.equalsIgnoreCase("z"))
	  morse = "--..";
	if (toEncode.equalsIgnoreCase("0"))
	  morse = "-----";
	if (toEncode.equalsIgnoreCase("1"))
	  morse = ".----";
	if (toEncode.equalsIgnoreCase("2"))
	  morse = "..---";
	if (toEncode.equalsIgnoreCase("3"))
	  morse = "...--";
	if (toEncode.equalsIgnoreCase("4"))
	  morse = "....-";
	if (toEncode.equalsIgnoreCase("5"))
	  morse = ".....";
	if (toEncode.equalsIgnoreCase("6"))
	  morse = "-....";
	if (toEncode.equalsIgnoreCase("7"))
	  morse = "--...";
	if (toEncode.equalsIgnoreCase("8"))
	  morse = "---..";
	if (toEncode.equalsIgnoreCase("9"))
	  morse = "----.";
	if (toEncode.equalsIgnoreCase("."))
	  morse = ".-.-";
	if (toEncode.equalsIgnoreCase(","))
	  morse = "--..--";
	if (toEncode.equalsIgnoreCase("?"))
	  morse = "..--..";

	return morse;
  }
}
