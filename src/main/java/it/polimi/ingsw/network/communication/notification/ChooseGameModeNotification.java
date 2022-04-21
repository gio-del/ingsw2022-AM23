package it.polimi.ingsw.network.communication.notification;

import it.polimi.ingsw.network.communication.NotificationVisitor;

import java.io.Serial;

/**
 * This notification is used by the client to tell server what game mode he chose
 */
public class ChooseGameModeNotification implements Notification {
    @Serial
    private static final long serialVersionUID = 7549463621798772302L;

    private final int nPlayers;
    private final boolean isExpertGame;
    private int clientId;

    public ChooseGameModeNotification(int nPlayers, boolean isExpertGame) {
        this.nPlayers = nPlayers;
        this.isExpertGame = isExpertGame;
    }

    @Override
    public void accept(NotificationVisitor visitor) {
        visitor.visit(this);
    }

    public int getNPlayers() {
        return nPlayers;
    }

    public boolean isExpertGame() {
        return isExpertGame;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
