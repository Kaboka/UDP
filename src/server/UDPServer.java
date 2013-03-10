/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasper
 */
public class UDPServer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date date = new Date();
        String serverInfo = new Timestamp(date.getTime())+"\n" + "CPU Load: Core 1: 90%, Core2: 30%\nMemory consumption: 2GB\n"
                            + "Running processes: 94\nAvailable memory: 22GB";
        DatagramSocket serverSocket = null;
        while (true) {

            try {
                if (serverSocket == null) {
                    serverSocket = new DatagramSocket(5000);
                }

                byte[] buffer = new byte[255];
                DatagramPacket in = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(in);

                InetAddress address = in.getAddress();
                int port = in.getPort();
                DatagramPacket out = new DatagramPacket(serverInfo.getBytes(), serverInfo.getBytes().length, address, port);
                serverSocket.send(out);
            } catch (SocketException ex) {
                Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
