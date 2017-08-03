package UI;

import com.company.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Observador{

    public static void main(String [] args){
        Ventana v = new Ventana();
        Observado observado = new Observado();
        observado.addObserver(v);
        while(true){
            observado.Cambiar();
        }
    }



}
class Ventana extends JFrame implements Observer{

    private  JPanel contenedor;
    JScrollPane  scroll ;
    private JTextArea area;
    public Ventana() {
        contenedor = new JPanel();
        area = new JTextArea();
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        //area.setSize(400, 400);
        scroll.setSize(200,200);
        this.setVisible(true);
        contenedor.setLayout(null);
        setContentPane(contenedor);
        contenedor.add(scroll);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 200, 200);



    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("**************************************************************");
        //this.area.setText((String) arg);
        this.area.append((String) arg); //este va acumulando todo el texto que se va agregando al TextArea
        // area.setText(arg.toString()); //este actualiza el valor del TextArea, borra el estado anterior!
        //area.setBackground(Color.black);
    }
}
