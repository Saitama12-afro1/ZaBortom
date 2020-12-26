package Network.Event;

import java.io.Serializable;

public class ErrorEvent extends Event implements Serializable {

    public ErrorEvent(String message) {
        super("error");
        this.message = message;
    }

    public String message;
}
