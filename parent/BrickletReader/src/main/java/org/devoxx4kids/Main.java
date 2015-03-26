package org.devoxx4kids;

/**
 * Created by alexanderbischof on 22.09.14.
 */
public class Main {

    //build: jar cvf brickletreader.jar -C src org
    public static void main(String[] args) throws Exception {

        //Standard localhost und Port 4223 kann über Kommandozeilen-Parameter überschrieben werden.
        String host = "localhost";
        int port = 4223;
        if (args.length>=2){
            host = args[0];
            port = Integer.valueOf(args[1]);
        }

        BrickletReader brickletReader = new BrickletReader();
        System.out.println(brickletReader.readBricklets(host, port).getBrickletMap().values());
    }
}
