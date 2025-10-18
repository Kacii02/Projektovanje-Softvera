/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.Bibliotekar;
import model.Clan;
import model.Knjiga;
import model.TipClanstva;
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

    //BIBLIOTEKAR
    public Bibliotekar login(Bibliotekar bibliotekar) {
        ClientRequest request = new ClientRequest(bibliotekar, Operacija.LOGIN_BIBLIOTEKAR);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (Bibliotekar) response.getParams();
    }

    //KNJIGA
    public List<Knjiga> vratiSveKnjige() {
        ClientRequest request = new ClientRequest(null, Operacija.VRATI_SVE_KNJIGE);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (List<Knjiga>) response.getParams();
    }

    public int dodajKnjigu(Knjiga novaKnjiga) {
        ClientRequest request = new ClientRequest(novaKnjiga, Operacija.DODAJ_KNJIGU);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (int) response.getParams();
    }

    public int izmeniKnjigu(Knjiga izmenjenaKnjiga) {
        ClientRequest request = new ClientRequest(izmenjenaKnjiga, Operacija.IZMENI_KNJIGU);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (int) response.getParams();
    }

    public int obrisiKnjigu(Knjiga zaBrisanje) {
        ClientRequest request = new ClientRequest(zaBrisanje, Operacija.OBRISI_KNJIGU);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (int) response.getParams();
    }

    //CLAN
    public List<Clan> vratiSveClanove() {
        ClientRequest request = new ClientRequest(null, Operacija.VRATI_SVE_CLANOVE);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (List<Clan>) response.getParams();
    }

    public int dodajClana(Clan noviClan) {
        ClientRequest request = new ClientRequest(noviClan, Operacija.DODAJ_CLANA);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (int) response.getParams();
    }

    public int izmeniClana(Clan izabraniClan) {
        ClientRequest request = new ClientRequest(izabraniClan, Operacija.IZMENI_CLANA);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (int) response.getParams();
    }

    public int obrisiClana(Clan clanZaBrisanje) {
        ClientRequest request = new ClientRequest(clanZaBrisanje, Operacija.OBRISI_CLANA);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (int) response.getParams();
    }

    //TIP CLANSTVA
    public List<TipClanstva> vratiSveTipoveClanstva() {
        ClientRequest request = new ClientRequest(null, Operacija.VRATI_SVE_TIPOVE_CLANSTVA);
        sender.send(request);
        ServerResponse response = (ServerResponse) receiver.receive();
        return (List<TipClanstva>) response.getParams();
    }

}
