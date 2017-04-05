/**
 * VINCENT THAI
 * CS 380
 * EchoServer.JAVA
 * PROFESSOR DAVARPANAH
 * SPRING 2017
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        String inputLine, address="";
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    out.printf("Hi %s, thanks for connecting!%n", address);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    while((inputLine = in.readLine()) != null){
                        if(inputLine.equals("exit"))
                            throw new SocketException();
                        out.println("Server> " + inputLine);
                    }

                }
            }
        } catch (SocketException e){
            System.out.printf("Client disconnected: %s%n", address);
        }

    }
}
