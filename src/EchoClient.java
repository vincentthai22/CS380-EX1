/**
 * VINCENT THAI
 * CS 380
 * EchoClient.JAVA
 * PROFESSOR DAVARPANAH
 * SPRING 2017
 */

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        String temp="";
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            String connectionChecker = br.readLine();
            if (connectionChecker.equals("null")) throw new NullPointerException();
            System.out.println("Server> " + connectionChecker);
            while(true) {
                System.out.print("Client> ");
                temp = stdIn.readLine();
                out.println(temp);
                if(temp.toLowerCase().trim().equals("exit")) break;
                System.out.println(br.readLine());
            }
        } catch (NullPointerException e){
            System.out.println("Failed to connect.. try again.");
        }
    }
}















