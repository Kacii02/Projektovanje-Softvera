/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.net.Socket;
import model.Bibliotekar;
import model.Knjiga;
import transfer.ClientRequest;
import transfer.Receiver;
import transfer.Sender;
import transfer.ServerResponse;

/**
 *
 * @author kacan
 */
public class ClientThread extends Thread {

    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    private Controller controller = Controller.getInstance();

    public ClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (socket != null && !socket.isClosed()) {
            ClientRequest request = (ClientRequest) receiver.receive();
            ServerResponse response = new ServerResponse();
            if (request == null) {
                break;
            }

            switch (request.getOperacija()) {
                case LOGIN_BIBLIOTEKAR:
                    response.setParams(controller.vrati((Bibliotekar) request.getParams()));
                    break;
                case VRATI_SVE_KNJIGE:
                    response.setParams(controller.vratiSve(new Knjiga()));
                    break;
                case DODAJ_KNJIGU:
                    response.setParams(controller.dodaj((Knjiga) request.getParams()));
                    break;
                case IZMENI_KNJIGU:
                    response.setParams(controller.izmeni((Knjiga) request.getParams()));
                    break;
                case OBRISI_KNJIGU:
                    response.setParams(controller.obrisi((Knjiga) request.getParams()));
                    break;
                default:
                    throw new AssertionError();
            }
            sender.send(response);
        }
    }

}
