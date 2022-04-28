package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.network.client.ClientSideVisitor;
import it.polimi.ingsw.network.communication.NotificationVisitor;
import it.polimi.ingsw.network.communication.notification.ChooseGameModeNotification;
import it.polimi.ingsw.network.communication.notification.LoginNotification;
import it.polimi.ingsw.network.communication.notification.Notification;
import it.polimi.ingsw.observer.ClientObserver;
import it.polimi.ingsw.view.View;

import static java.lang.System.exit;

/**
 * A controller on client's side. It instantiates a {@link Client} when connection info are provided.
 * It observes the {@link View}.
 * From Server receives message through Client and update the view through the {@link ClientSideVisitor}
 * From View receives input and send them to the server through Client.
 * It uses a {@link NotificationVisitor} to dispatch the notification sent by the Server and to perform action on the view.
 */
public class ClientController implements ClientObserver {
    //TODO
    private final NotificationVisitor visitor;
    private final View view;
    private final Client client;
    private String nickname;

    public ClientController(View view) {
        this.view = view;
        this.client = new Client(this);
        visitor = new ClientSideVisitor(view);
    }

    /**
     * Receive messages from server and dispatch them using a {@link NotificationVisitor}
     * @param msg to be dispatched
     */
    public void receiveMessage(Notification msg) {
        msg.accept(visitor);
    }

    @Override
    public void updateConnection(String ip, int port) {
        if(client.connect(ip,port)){
            client.start();
            view.setNickname();
        }
        else{
            view.disconnectionHandler("Server not reachable! Exiting...");
            exit(0);
        }
    }

    @Override
    public void updateNickname(String nickname) {
        this.nickname = nickname;
        Notification login = new LoginNotification(nickname);
        login.setClientId(nickname);
        client.sendMessage(login);
    }

    @Override
    public void updateGameModeNumPlayer(String mode, int numOfPlayer) {
        boolean isExpert = mode.equalsIgnoreCase("Expert");
        Notification chooseGameMode = new ChooseGameModeNotification(numOfPlayer,isExpert);
        chooseGameMode.setClientId(nickname);
        client.sendMessage(chooseGameMode);
    }

    public void onDisconnection(){
        String s = "Connection closed with the server. Exiting...";
        view.disconnectionHandler(s);
        exit(0);
    }
}