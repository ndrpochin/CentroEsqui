package UI;

import com.company.Esquiador;
import com.company.Menu;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ObservadorTemporal extends JFrame implements  Observer {

    private  JPanel contenedor;
    JScrollPane  scroll ;
    private JTextArea area;

    public ObservadorTemporal() {
        contenedor = new JPanel();
        area = new JTextArea();
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        //area.setSize(400, 400);
        scroll.setSize(400,400);
        this.setVisible(true);
        contenedor.setLayout(null);
        setContentPane(contenedor);
        contenedor.add(scroll);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 400, 400);



    }
    @Override
    public void update(Observable o, Object arg) {
            if(o instanceof Esquiador)
                area.append((String) arg);
            //this.area.append("ha cambiado");
        }
    }


