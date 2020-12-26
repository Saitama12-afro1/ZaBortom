package Network.Event;

import java.io.Serializable;

// Сообщает о добавлении игрока в лобби

// Сервер клиенту
public class AddToGameEvent extends Event implements Serializable {

    public AddToGameEvent(String[] players_names) {
        super("add_to_game");
        this.players_names = players_names.clone();
    }

    public String[] players_names;
}
