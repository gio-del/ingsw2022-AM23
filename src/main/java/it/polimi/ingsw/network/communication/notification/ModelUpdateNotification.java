package it.polimi.ingsw.network.communication.notification;

import it.polimi.ingsw.model.ShortModel;
import it.polimi.ingsw.network.communication.NotificationVisitor;

import java.io.Serial;

public class ModelUpdateNotification extends Notification {
    @Serial
    private static final long serialVersionUID = -8631764770471499733L;

    private final ShortModel model;

    public ModelUpdateNotification(ShortModel model) {
        this.model = model;
    }

    @Override
    public void accept(NotificationVisitor visitor) {
        visitor.visit(this);
    }

    public ShortModel getModel() {
        return model;
    }
}
