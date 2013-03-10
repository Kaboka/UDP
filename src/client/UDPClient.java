/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasper
 */
public class UDPClient {
    
    private static String ip = "localhost";
    private static int port = 5000;

    public static void main(String[] args) throws IOException {
        
        String message = "Give server info!";
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(ip);   
            DatagramPacket out = new DatagramPacket(message.getBytes(),message.getBytes().length,address,port);           
            clientSocket.send(out);
            
            byte[] buffer = new byte[255];
            DatagramPacket in = new DatagramPacket(buffer,buffer.length);
            
            clientSocket.receive(in);
            System.out.println(new String(in.getData()));
        } catch (SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



 //   clientSocket
}
