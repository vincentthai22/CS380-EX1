/**
 * VINCENT THAI
 * CS 380
 * EchoServer.JAVA
 * PROFESSOR DAVARPANAH
 * SPRING 2017
 */


import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public final class EchoServer {

    String address;
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                serverSocket.setReuseAddress(true);
                try (Socket socket = serverSocket.accept()) {
                    new Thread(new EchoClientInstance(serverSocket)).start();
                }
            }
        }
    }
}
class EchoClientInstance implements Runnable{
    ServerSocket serverSocket;
    public EchoClientInstance(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try (Socket socket = serverSocket.accept()) {
            String inputLine, address = "";
            try {
                address = socket.getInetAddress().getHostAddress();
                System.out.printf("Client connected: %s%n", address);
                OutputStream os = socket.getOutputStream();
                PrintStream out = new PrintStream(os, true, "UTF-8");
                out.printf("Hi %s, thanks for connecting!%n", address);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.equals("exit"))
                            throw new SocketException();
                    out.println("Server> " + inputLine);
                }
            } catch (SocketException e) {
                System.out.printf("Client disconnected: %s%n", address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
/*
Runnable r = () -> {
                        String inputLine, address = "";
                        try {
                            address = socket.getInetAddress().getHostAddress();
                            System.out.printf("Client connected: %s%n", address);
                            OutputStream os = socket.getOutputStream();
                            PrintStream out = new PrintStream(os, true, "UTF-8");
                            out.printf("Hi %s, thanks for connecting!%n", address);
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                            while ((inputLine = in.readLine()) != null) {
                                if (inputLine.equals("exit"))
                                    try {
                                        throw new SocketException();
                                    } catch (SocketException e) {
                                        System.out.printf("Client disconnected: %s%n", address);
                                    }
                                out.println("Server> " + inputLine);
                            }
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    };
 */