/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import config.FileReaderConfig;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author kacan
 */
public class Server extends Thread {

    private int port = 9000;
    private ServerSocket serverSocket;
    private boolean pokrenut = false;

    @Override
    public void run() {
        try {
            FileReaderConfig fileReader = new FileReaderConfig();
            fileReader.readFile("server.config");
            port = fileReader.getPort();
            serverSocket = new ServerSocket(port);
            pokrenut = true;
            System.out.println("S: Server je pokrenut na portu " + port);

            while (pokrenut) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("S: Klijent je povezan");
                    ClientThread clientThread = new ClientThread(clientSocket);
                    clientThread.start();
                } catch (IOException e) {
                    if (pokrenut) {
                        System.out.println("S: Greska prilikom prihvatanja klijenta: " + e.getMessage());
                    } else {
                        System.out.println("S: Server je zaustavljen.");
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("S: Greska prilikom pokretanja servera: " + ex.getMessage());
        }
    }

    public void stopServer() {
        pokrenut = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            System.out.println("S: Server je uspesno zaustavljen.");
        } catch (IOException e) {
            System.out.println("S: Greska prilikom zaustavljanja servera: " + e.getMessage());
        }
    }

}
