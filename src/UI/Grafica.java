package UI;
import com.company.*;
import com.company.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Grafica {
    public static void main(String [] args){
        Marco marcoUno = new Marco();

    }

}

class Marco extends JFrame implements Observer{

    JTextArea menu, textMedio1, textMedio2, textMedio3, textMedio4,textFieldMostrador1, textFieldMostrador2, textFieldPostre;

    public  Marco(){

        //aca solo se crean todos los componentes de la ventana
        //que mas adelante se van a agregar
        textMedio1 = new JTextArea();
        textMedio2 = new JTextArea();
        textMedio3 = new JTextArea();
        textMedio4 = new JTextArea();
        JTextArea textFieldConfiteria = new JTextArea();
        textFieldMostrador1 = new JTextArea();
        textFieldMostrador2 = new JTextArea();
        textFieldPostre = new JTextArea();
        menu = new JTextArea();
        JLabel medio1 = new JLabel("Medio 1");
        JLabel medio2 = new JLabel("Medio 2");
        JLabel medio3 = new JLabel("Medio 3");
        JLabel medio4 = new JLabel("Medio 4");
        JLabel mostrador1 = new JLabel("Mostrador 1");
        JLabel mostrador2 = new JLabel("Mostrador 2");
        JLabel mostradorP = new JLabel("Mostrador Postre");
        JLabel menuLabel = new JLabel("Menu");
        JLabel claseSky = new JLabel("Lugares disponibles clase Sky");
        JLabel claseSnow = new JLabel("Lugares disponibles clase Snow");
        JTextArea lugDispConfiteria = new JTextArea();;
        JPanel contentPane = new JPanel();;



        /*propiedades generales del Marco*/
        this.setVisible(true);
        ImageIcon img = new ImageIcon("logo.png");
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("Complejo Invernal");
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setSize(905,650);
        centarEnPantalla(this);
        setResizable(false);
        /*no se puede agrandar ni achicar la ventana*/

        /*propiedades de los componentes
        * tamaño-posicion*/
        int ancho = 175, alto = 125;
        int espacioH = 10, espacioV = 30;

        //textMedio1.setBounds(espacioH, espacioV, ancho, alto);
        /*pruebas sobre os */

        //textMedio1.setText("Dale lfn  jflasfnasfgas,snlasnflkhjasvñsmfaioghsañ sajfsd j fasfjsofsmfaiou assnlasnflkhjasvñsmfaioghsañ sajfsd j fasfjsofsmfaiousnlasnflkhjasvñsmfaioghsañ sajfsd j fasfjsofsmfaiousnlasnflkhjasvñsmfaioghsañ sajfsd j fasfjsofsmfaioufnmkg  faofnagagfyaifnasmlñiugf ");
        textMedio1.setEditable(true);//no editable
        JScrollPane  scroll = new JScrollPane(textMedio1);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scroll);
        scroll.setBounds(espacioH,espacioV,ancho,alto);
        textMedio1.setLineWrap(true);
        textMedio1.setWrapStyleWord(true);


        /*fin de los tests*/
        textMedio2.setBounds(2*espacioH + ancho, espacioV, ancho, alto);
        textMedio3.setBounds(espacioH , 2*espacioV + alto, ancho, alto);
        textMedio4.setBounds(2*espacioH + ancho,2*espacioV + alto, ancho, alto);
        lugDispConfiteria.setBounds(600,20, 150,20);
        lugDispConfiteria.setText("Lugares Disponibles");
        medio1.setBounds(15,5,50,20);
        medio2.setBounds(195,5,50,20);
        medio3.setBounds(15,160,50,20);
        medio4.setBounds(195,160,50,20);

        //parte de confiteria
        textFieldMostrador1.setBounds(460, 30, 210, alto);
        textFieldMostrador2.setBounds(680, 30, 210, alto);
        textFieldPostre.setBounds(570, 185, 210, alto);
        mostrador1.setBounds(460, 5,70,20);
        mostrador2.setBounds(680, 5, 70,20);
        mostradorP.setBounds(570, 160, 100,20);
        menu.setBounds(455, 400, 435, 125);

        //cuadrados para las clases de snow y sky
        claseSky.setBounds(15, 370, 200,20);
        claseSnow.setBounds(15, 450,200,20);
        Boton sky1 = new Boton(15, 400);
        Boton sky2 = new Boton(60,400); //+45(Y)
        Boton sky3 = new Boton(105,400); //+45(Y)
        Boton sky4 = new Boton(150,400);

        Boton snow1 = new Boton(15, 480);
        Boton snow2 = new Boton(60,480);
        Boton snow3 = new Boton(105,480);
        Boton snow4 = new Boton(150,480);

        menuLabel.setBounds(455, 370, 50,20);




        //contentPane.add(textMedio1);
        contentPane.add(textMedio2);
        contentPane.add(textMedio3);           contentPane.add(textMedio4);

        //contentPane.add(lugDispConfiteria);

        contentPane.add(medio1);        contentPane.add(medio2);
        contentPane.add(medio3);        contentPane.add(medio4);
        contentPane.add(sky1);          contentPane.add(sky2);
        contentPane.add(sky3);          contentPane.add(sky4);
        contentPane.add(snow1);          contentPane.add(snow2);
        contentPane.add(snow3);     contentPane.add(snow4);
        contentPane.add(claseSky); contentPane.add(claseSnow);


        contentPane.add(textFieldMostrador1);        contentPane.add(textFieldMostrador2);        contentPane.add(textFieldPostre);
        contentPane.add(mostrador1);    contentPane.add(mostrador2);    contentPane.add(mostradorP);
        contentPane.add(menuLabel);     contentPane.add(menu);
    /*Contenido de cada componente*/

    }

    public static void centarEnPantalla(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    //este metodo va a ser muy coplejo porque tiene que analizar TODOS los cambios del modelo.
    //paso a paso
    public void update(Observable o, Object arg) {
        System.out.println(arg);
        if (o instanceof Esquiador) {
            this.textMedio1.append((String) arg);
        }
        if( o instanceof Confiteria)
            this.menu.append((String) arg);

        //this.area.setText((String) arg);
        //this.area.append((String) arg); //este va acumulando todo el texto que se va agregando al TextArea
        // area.setText(arg.toString()); //este actualiza el valor del TextArea, borra el estado anterior!
        //area.setBackground(Color.black);
    }
    class CuadroTexto extends JScrollPane{

        public CuadroTexto (int alto, int ancho){

            JTextArea cuadro = new JTextArea();
            cuadro.setEditable(false);//no editable
            JScrollPane  scroll = new JScrollPane(cuadro);
            scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
            scroll.setSize (ancho,alto);
            cuadro.setLineWrap(true);
            cuadro.setWrapStyleWord(true);
            //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            //scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            //contentPane.add(scroll); //esto va en el constructor del frame
        }
    }

    class Boton extends JButton{

        public  Boton (int posX, int posY){

            //JButton b = new JButton();
            setSize(30,30);
            setLocation(posX, posY);
            setBackground(Color.BLACK);
            setVisible(true);
            setEnabled(false);
        }
        public void onClick(){
            this.setBackground(Color.GREEN);
        }



    }

}
