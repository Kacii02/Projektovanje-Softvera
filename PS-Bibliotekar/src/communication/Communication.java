/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import model.Bibliotekar;
import operacije.Operacija;
import transfer.ClientRequest;
import transfer.Receiver;
import transfer.Sender;
import transfer.ServerResponse;

/**
 *
 * @author kacan
 */
public class Communication {

    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    private static Communication instance;

    private Communication() {
        try {
            socket = new Socket("localhost", 9000);
            receiver = new Receiver(socket);
            sender = new Sender(socket);
        } catch (IOException ex) {
            System.out.println("C: Greska prilikom povezivanja na server: " + ex.getMessage());
        }
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }
    
    //Bibliotekar
    //Login
    public Bibliotekar login(Bibliotekar bibliotekar) {
        ClientRequest request = new ClientRequest(bibliotekar, Operacija.LOGIN_BIBLIOTEKAR);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (Bibliotekar) response.getParams();
    }

}
