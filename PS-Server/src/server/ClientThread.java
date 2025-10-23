/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.net.Socket;
import model.Bibliotekar;
import model.Clan;
import model.Iznajmljivanje;
import model.Knjiga;
import model.RadnoVreme;
import model.Smena;
import model.StavkaIznajmljivanja;
import model.TipClanstva;
import model.pomocni.IznajmljivanjeSaStavkama;
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
                //BIBLIOTEKAR
                case LOGIN_BIBLIOTEKAR:
                    response.setParams(controller.vrati((Bibliotekar) request.getParams()));
                    break;
                //KNJIGA
                case VRATI_SVE_KNJIGE:
                    response.setParams(controller.vratiSve((Knjiga) request.getParams()));
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
                //CLAN
                case VRATI_SVE_CLANOVE:
                    response.setParams(controller.vratiSve((Clan) request.getParams()));
                    break;
                case DODAJ_CLANA:
                    response.setParams(controller.dodaj((Clan) request.getParams()));
                    break;
                case IZMENI_CLANA:
                    response.setParams(controller.izmeni((Clan) request.getParams()));
                    break;
                case OBRISI_CLANA:
                    response.setParams(controller.obrisi((Clan) request.getParams()));
                    break;
                //TIP CLANSTVA
                case VRATI_SVE_TIPOVE_CLANSTVA:
                    response.setParams(controller.vratiSve(new TipClanstva()));
                    break;
                //IZNAJMLJIVANJE
                case VRATI_SVA_IZNAJMLJIVANJA:
                    response.setParams(controller.vratiSve(new Iznajmljivanje()));
                    break;
                case DODAJ_IZNAJMLJIVANJE_SA_STAVKAMA:
                    IznajmljivanjeSaStavkama zaDodavanje = (IznajmljivanjeSaStavkama) request.getParams();
                    response.setParams(controller.kreirajIznajmljivanje(zaDodavanje.getIznajmljivanje(), zaDodavanje.getStavke()));
                    break;
                case VRATI_SVE_STAVKE_IZNAJMLJIVANJA:
                    response.setParams(controller.vratiSve((StavkaIznajmljivanja) request.getParams()));
                    break;
                case IZMENI_IZNAJMLJIVANJE:
                    IznajmljivanjeSaStavkama zaIzmenu = (IznajmljivanjeSaStavkama) request.getParams();
                    response.setParams(controller.izmeniIznajmljivanje(zaIzmenu.getIznajmljivanje(), zaIzmenu.getStavke()));
                    break;
                //RADNO VREME
                case VRATI_SVA_RADNA_VREMENA:
                    response.setParams(controller.vratiSve(new RadnoVreme()));
                    break;
                case VRATI_SVE_SMENE:
                    response.setParams(controller.vratiSve(new Smena()));
                    break;
                case DODAJ_RADNO_VREME:
                    response.setParams(controller.dodaj((RadnoVreme) request.getParams()));
                    break;
                case OBRISI_RADNO_VREME:
                    response.setParams(controller.obrisi((RadnoVreme) request.getParams()));
                    break;
                //CLAN - Projekat
                case LOGIN_CLAN:
                    response.setParams(controller.vrati((Clan) request.getParams()));
                    break;
                default:
                    throw new AssertionError();
            }
            sender.send(response);
        }
    }

}
