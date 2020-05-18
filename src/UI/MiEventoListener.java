package UI;

import java.util.EventListener;

public interface MiEventoListener extends EventListener{
    //estos son los eventos que pueden ser disparados *ok*

    void cadenaCambio (Object args, String mensaje);

}
