/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.Clan;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;
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

    public Clan login(Clan clan) {
        ClientRequest request = new ClientRequest(clan, Operacija.LOGIN_CLAN);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (Clan) response.getParams();
    }

    public List<Iznajmljivanje> vratiSvaIznajmljivanja() {
        ClientRequest request = new ClientRequest(null, Operacija.VRATI_SVA_IZNAJMLJIVANJA);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (List<Iznajmljivanje>) response.getParams();
    }

    public List<StavkaIznajmljivanja> vratiSveStavkeIznajmljivanja(StavkaIznajmljivanja stavkaZaPretragu) {
        ClientRequest request = new ClientRequest(stavkaZaPretragu, Operacija.VRATI_SVE_STAVKE_IZNAJMLJIVANJA);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (List<StavkaIznajmljivanja>) response.getParams();
    }

}
