package UI;
import com.company.*;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Grafica {
    public static void main(String [] args){
        Marco marcoUno = new Marco();

    }

}

class Marco extends JFrame implements MiEventoListener{

    JTextArea menu, textMedio1, textMedio2, textMedio3, textMedio4, textfieldCaja, textFieldMostrador1, textFieldMostrador2, textFieldPostre, clases;
    Boton sky1, sky2, sky3, sky4, snow1, snow2, snow3, snow4, profe1, profe2, profe3, profe4, profe5;
    static  final  int ancho = 175, alto = 125;
    static final int espacioH = 10, espacioV = 30;
    JLabel informeSky, informeSnow, informeM1, informeM2, informeM3, informeM4, informeConfiteria;


    public  Marco(){

        //aca solo se crean todos los componentes de la ventana
        //que mas adelante se van a agregar
        textMedio1 = new JTextArea();
        textMedio2 = new JTextArea();
        textMedio3 = new JTextArea();
        textMedio4 = new JTextArea();
        clases = new JTextArea();
        JTextArea textFieldConfiteria = new JTextArea();
        textfieldCaja = new JTextArea();
        textFieldMostrador1 = new JTextArea();
        textFieldMostrador2 = new JTextArea();
        textFieldPostre = new JTextArea();
        menu = new JTextArea();

        JLabel medio1 = new JLabel("Medio 1");
        JLabel medio2 = new JLabel("Medio 2");
        JLabel medio3 = new JLabel("Medio 3");
        JLabel medio4 = new JLabel("Medio 4");

        JLabel caja = new JLabel("Caja");
        JLabel mostrador1 = new JLabel("Mostrador 1");
        JLabel mostrador2 = new JLabel("Mostrador 2");
        JLabel mostradorP = new JLabel("Mostrador Postre");
        JLabel menuLabel = new JLabel("Menu");

        JLabel claseSky = new JLabel("Alumnos esperando clase sky");
        JLabel claseSnow = new JLabel("Alumnos esperando clase snow");
        JLabel profes = new JLabel("Cantidad de Instructores disponibles");
        JTextArea lugDispConfiteria = new JTextArea();
        JPanel contentPane = new JPanel();

        informeSky = new JLabel("CANTIDAD CLASES SKY");        informeSnow = new JLabel("CANTIDAD CLASES SNOW");
        informeM1 = new JLabel("PASAJEROS MEDIO 1");        informeM2 = new JLabel("PASAJEROS MEDIO 2");
        informeM3 = new JLabel("PASAJEROS MEDIO 3");        informeM4 = new JLabel("PASAJEROS MEDIO 4");
        informeConfiteria  = new JLabel("CANTIDAD COMENSALES");


                /*propiedades generales del Marco*/

        ImageIcon img = new ImageIcon("logo.png");
        setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("Complejo Invernal");
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setSize(905,700);
        centarEnPantalla(this);
        setResizable(false);
        //this.setBackground(Color.blue);
        contentPane.setBackground(Color.GRAY);
                /*no se puede agrandar ni achicar la ventana*/

        textMedio1.setEditable(false);//no editable
        JScrollPane  scroll = new JScrollPane(textMedio1);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scroll);
        scroll.setBounds(espacioH, espacioV, ancho, alto);
        ((DefaultCaret)textMedio1.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textMedio1.setLineWrap(true);
        textMedio1.setWrapStyleWord(true);



        //textMedio2.setBounds(2*espacioH + ancho, espacioV, ancho, alto);
        textMedio2.setEditable(true);//no editable
        JScrollPane  scroll2 = new JScrollPane(textMedio2);
        scroll2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scroll2);
        scroll2.setBounds(2*espacioH + ancho, espacioV, ancho, alto);
        ((DefaultCaret)textMedio2.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textMedio2.setLineWrap(true);
        textMedio2.setWrapStyleWord(true);

        //
        //textMedio3.setBounds(espacioH , 2*espacioV + alto, ancho, alto);
        textMedio3.setEditable(true);//no editable
        JScrollPane  scroll3 = new JScrollPane(textMedio3);
        scroll3.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scroll3);
        scroll3.setBounds(espacioH , 2*espacioV + alto, ancho, alto);
        ((DefaultCaret)textMedio3.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textMedio3.setLineWrap(true);
        textMedio3.setWrapStyleWord(true);


        //textMedio4.setBounds(2*espacioH + ancho,2*espacioV + alto, ancho, alto);
        textMedio4.setEditable(true);//no editable
        JScrollPane  scroll4 = new JScrollPane(textMedio4);
        scroll4.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scroll4);
        scroll4.setBounds(2*espacioH + ancho,2*espacioV + alto, ancho, alto);
        ((DefaultCaret)textMedio4.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textMedio4.setLineWrap(true);
        textMedio4.setWrapStyleWord(true);


        //**************

        lugDispConfiteria.setBounds(600,20, 150,20);
        lugDispConfiteria.setText("Lugares Disponibles");
        medio1.setBounds(15,5,50,20);
        medio2.setBounds(195,5,50,20);
        medio3.setBounds(15,160,50,20);
        medio4.setBounds(195,160,50,20);

        //parte de confiteria
       /* textfieldCaja.setBounds(400, 30, 210, alto);
        textFieldMostrador1.setBounds(460, 30, 210, alto);
        textFieldMostrador2.setBounds(680, 185, 210, alto);
        textFieldPostre.setBounds(680, 185, 210, alto);*/

        caja.setBounds(460,5,70,20); //revisar
        mostrador1.setBounds(680, 5,70,20);
        mostrador2.setBounds(460, 160, 70,20);
        mostradorP.setBounds(680, 160, 100,20);
        menu.setBounds(455, 400, 435, 125);
        menu.setText(">MENU 1: Arroz con Pollo" +" POSTRE: Flan <MOSTRADOR 1> \n" +
                ">MENU 2: Hamburguesa" +" POSTRE: Helado <MOSTRADOR 1> \n" +
                ">MENU 3: Empanadas <MOSTRADOR 2> \n" +
                ">MENU 4: Pizza Napolitana <MOSTRADOR 2> \n");


        //*************************************
        textfieldCaja.setEditable(true);//no editable
        JScrollPane  scrollCaja = new JScrollPane(textfieldCaja);
        scrollCaja.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scrollCaja);
        scrollCaja.setBounds(460, 30,210, alto);
        ((DefaultCaret)textfieldCaja.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textfieldCaja.setLineWrap(true);
        textfieldCaja.setWrapStyleWord(true);

        textFieldMostrador1.setEditable(true);//no editable
        JScrollPane  scrollMostrador1 = new JScrollPane(textFieldMostrador1);
        scrollMostrador1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scrollMostrador1);
        scrollMostrador1.setBounds(680, 30,210, alto);
        ((DefaultCaret)textFieldMostrador1.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textFieldMostrador1.setLineWrap(true);
        textFieldMostrador1.setWrapStyleWord(true);

        textFieldMostrador2.setEditable(true);//no editable
        JScrollPane  scrollMostrador2 = new JScrollPane(textFieldMostrador2);
        scrollMostrador2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scrollMostrador2);
        scrollMostrador2.setBounds(460,185,210, alto);
        ((DefaultCaret)textFieldMostrador2.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textFieldMostrador2.setLineWrap(true);
        textFieldMostrador2.setWrapStyleWord(true);

        textFieldPostre.setEditable(true);//no editable
        JScrollPane  scrollPostre = new JScrollPane(textFieldPostre);
        scrollPostre.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scrollPostre);
        scrollPostre.setBounds(680, 185, 210, alto);
        ((DefaultCaret)textFieldPostre.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textFieldPostre.setLineWrap(true);
        textFieldPostre.setWrapStyleWord(true);
        //******************
        contentPane.add(caja);   contentPane.add(mostrador1);    contentPane.add(mostrador2);    contentPane.add(mostradorP);
        contentPane.add(menuLabel);     contentPane.add(menu);



        //cuadrados para las clases de snow y sky
        claseSky.setBounds(15, 340, 200,20);
        claseSnow.setBounds(15, 400,200,20);
        profes.setBounds(15, 460, 250, 20);
        //posY 400 originalmente
        sky1 = new Boton(15, 360, Color.BLACK);
        sky2 = new Boton(60,360, Color.BLACK); //+45(Y)
        sky3 = new Boton(105,360, Color.BLACK); //+45(Y)
        sky4 = new Boton(150,360, Color.BLACK);

        snow1 = new Boton(15, 420, Color.BLACK);
        snow2 = new Boton(60,420, Color.BLACK); //+45(Y)
        snow3 = new Boton(105,420, Color.BLACK); //+45(Y)
        snow4 = new Boton(150,420, Color.BLACK);

        profe1 = new Boton(15, 480, Color.GREEN);
        profe2 = new Boton(60,480, Color.GREEN);
        profe3 = new Boton(105,480, Color.GREEN);
        profe4 = new Boton(150,480, Color.GREEN);
        profe5 = new Boton(195, 480, Color.GREEN);

        //
        clases.setEditable(false);//no editable
        JScrollPane  scrollClases = new JScrollPane(clases);
        scrollClases.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        contentPane.add(scrollClases);
        scrollClases.setBounds(15, 520, 2*ancho+espacioH, alto);
        ((DefaultCaret)clases.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        clases.setLineWrap(true);
        clases.setWrapStyleWord(true);

        menuLabel.setBounds(455, 370, 50,20);

        //contentPane.add(lugDispConfiteria);

        contentPane.add(medio1);        contentPane.add(medio2);
        contentPane.add(medio3);        contentPane.add(medio4);
        contentPane.add(sky1);          contentPane.add(sky2);
        contentPane.add(sky3);          contentPane.add(sky4);
        contentPane.add(snow1);          contentPane.add(snow2);
        contentPane.add(snow3);          contentPane.add(snow4);

        contentPane.add(profe1);          contentPane.add(profe2);     contentPane.add(profe3);     contentPane.add(profe4); contentPane.add(profe5);
        contentPane.add(claseSky);      contentPane.add(claseSnow); contentPane.add(profes);
        //inicializarBotonesProfe();
        this.setVisible(true);

            /*Contenido de cada componente*/

        this.informeSky.setBounds(450, 550, 150,20);
        this.informeSnow.setBounds(450, 580, 150,20);

        this.informeM1.setBounds(650, 550, 200,20);
        this.informeM2.setBounds(650, 580, 200,20);
        this.informeM3.setBounds(650, 610, 200,20);
        this.informeM4.setBounds(650, 640, 200,20);

        this.informeConfiteria.setBounds(450, 610, 150,20);

        contentPane.add(informeSky);        contentPane.add(informeSnow);
        contentPane.add(informeM1);        contentPane.add(informeM2);
        contentPane.add(informeM3);        contentPane.add(informeM4);
        contentPane.add(informeConfiteria);
    }

    public static void centarEnPantalla(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    
    @Override //maneja todos los cambios que se van a generar en el modelo
    public void cadenaCambio(Object args, String mensaje) {
        if(args instanceof Confiteria){
            if(mensaje.contains("conf1")){ //entra a la confiteria, entra a la caja
                this.textfieldCaja.append(mensaje.substring(5)+"\n");

            }
            if(mensaje.contains("conf2")){ //sale de la caja
                this.textfieldCaja.append(mensaje.substring(5)+"\n");
            }
            if(mensaje.contains("conf31")){ //eventos mostrador 1
                this.textFieldMostrador1.append(mensaje.substring(6)+"\n");
            }
            if(mensaje.contains("conf32")){     //eventos mostrador 2
                this.textFieldMostrador2.append(mensaje.substring(6)+"\n");
            }
            if(mensaje.contains("conf4")){      //mostrador postre
                this.textFieldPostre.append(mensaje.substring(5)+"\n");
            }
            if(mensaje.contains("conf5")){      //se va, termina de comer
                this.textFieldPostre.append(mensaje.substring(5)+"\n");
            }
            //System.out.println("evento lanzado desde confiteria");
        }
        if (args instanceof Medio){  //Acá entran todos los mensajes enviados desde el metodo "ascender()" principalmente
            char verMedio = mensaje.charAt(0);     //el primer caracter me dice cuál medio envió el mensaje
            switch (verMedio){
                case '0': {
                    this.textMedio1.append(mensaje.substring(1)+"\n"); break;
                }
                case  '1':{
                    this.textMedio2.append(mensaje.substring(1)+"\n"); break;
                }
                case  '2':{
                    this.textMedio3.append(mensaje.substring(1) + "\n"); break;
                }
                case  '3':{
                    this.textMedio4.append(mensaje.substring(1)+"\n"); break;
                }
                default: {
                    this.textMedio3.append(mensaje.substring(1)+"\n"); break;
                }
            }
            //System.out.println("evento lanzado desde Medio");
        }
        if (args instanceof CabinaInstructores){

            if(mensaje.contains("profe++")){
                this.clases.append("Profe volvió del descanso \n");
                pintarVolvioProfe();
            }

            if(mensaje.contains("despierta")){
                this.clases.append(mensaje+"\n");
            }
            if(mensaje.contains("Nohay")){
                this.clases.append(mensaje+"\n");
            }

            if(mensaje.contains("ERROR")){
                this.clases.append(mensaje+"\n");
            }


            /*+++++++++mensajes de sky++++++++*/
            if(mensaje.contains("skybye")){
                this.clases.append("El profesor terminó la clase de sky y despidió a sus alumnos \n");
            }
            if(mensaje.contains("sky1")){       //arranca la clase
                this.clases.append(mensaje.substring(4)+ "\n");
            }
            if(mensaje.contains("profesky--")){
                this.clases.append("Profe se fue a dar una clase de sky\n");
                pintarSeFueProfe();
                resetSky();
            }
            if(mensaje.equalsIgnoreCase("sky++")) { //llego un alumno de sky
                pintarBotonSky();
            }
            if(mensaje.contains("sky2")){   //desarmar grupo de sky
                this.clases.append(mensaje.substring(5)+ "\n");
                resetSky();
            }
            if(mensaje.contains("sky3")){   //no hay profes
                this.clases.append(mensaje.substring(5)+ "\n");
            }



            /*+++++++++mensajes de snow++++++++*/
            if(mensaje.contains("snowbye")){
                this.clases.append("El profesor terminó la clase de snow y despidió a sus alumnos \n");
            }
            if(mensaje.contains("snow1")){      //arranca clase snow
                this.clases.append(mensaje.substring(5)+ "\n");
            }
            if(mensaje.contains("profesnow--")){
                this.clases.append("Profe se fue a dar una clase de snow\n");
                pintarSeFueProfe();
                resetSnow();
            }
            if(mensaje.equalsIgnoreCase("snow++")) {    //llego un alumno de snow
                pintarBotonSnow();
            }
            if(mensaje.contains("snow2")){      //desarmar grupo de snow
                this.clases.append(mensaje.substring(6)+ "\n");
                resetSnow();
            }
            if(mensaje.contains("snow3")){  //no hay profes
                this.clases.append(mensaje.substring(6)+ "\n");
            }
        }


        if (args instanceof Instructor){
            if(mensaje.contains("ins1")){       //se retira de la clase un alumno
                this.clases.append(mensaje.substring(4)+"\n");
            }
            if(mensaje.contains("ins2")){       //profe toma un descanso
                this.clases.append(mensaje.substring(4)+"\n");
            }
            if(mensaje.contains("ins3")){       //durmiendo
                this.clases.append(mensaje.substring(4)+"\n");
            }

        }
        if (args instanceof Esquiador){
            if(mensaje.contains("esq1")){       //despierta a X instructor
                this.clases.append(mensaje.substring(4)+"\n");
            }
            if(mensaje.contains("esq21")){   //muestra el menu elegido y que lo retira en mostrador 1
                this.textFieldMostrador1.append(mensaje.substring(5)+"\n");
            }
            if(mensaje.contains("esq22")){   //muestra el menu elegido y que lo retira en mostrador 2
                this.textFieldMostrador2.append(mensaje.substring(5)+"\n");
            }
            if(mensaje.contains("esq3")){   //muestra el postre elegido
                this.textFieldPostre.append(mensaje.substring(4)+"\n");
            }
            if(mensaje.contains("esq41")){   //cerró la confiteria
                this.textfieldCaja.append(mensaje.substring(5)+"\n");
            }
            if(mensaje.contains("esq42")){   //confiteria llena
                this.textfieldCaja.append(mensaje.substring(5)+"\n");
            }
        }
        if(args instanceof  ComplejoInvernal){
            if(mensaje.contains("confiteria")){
                this.informeConfiteria.setText(mensaje);
            }
            if(mensaje.contains("Medio 1")){
                this.informeM1.setText(mensaje);
            }
            if(mensaje.contains("Medio 2")){
                this.informeM2.setText(mensaje);
            }
            if(mensaje.contains("Medio 3")){
                this.informeM3.setText(mensaje);
            }
            if(mensaje.contains("Medio 4")){
                this.informeM4.setText(mensaje);
            }
            if(mensaje.contains("SKY")){
                this.informeSky.setText(mensaje);
            }
            if(mensaje.contains("SNOW")){
                this.informeSnow.setText(mensaje);
            }
            //imprimir el informe final
        }
    }

    private void pintarBotonSky(){
        if(sky1.getBackground().equals(Color.GREEN)){
            if(sky2.getBackground().equals(Color.GREEN)){
                if(sky3.getBackground().equals(Color.GREEN)){
                    sky4.setBackground(Color.GREEN);
                }else{
                    sky3.setBackground(Color.GREEN);
                }
            }else{
                sky2.setBackground(Color.GREEN);
            }
        }else{
            sky1.setBackground(Color.GREEN);
        }
    }

    private void pintarBotonSnow(){
        if(snow1.getBackground().equals(Color.GREEN)){
            if(snow2.getBackground().equals(Color.GREEN)){
                if(snow3.getBackground().equals(Color.GREEN)){
                    snow4.setBackground(Color.GREEN);
                }else{
                    snow3.setBackground(Color.GREEN);
                }
            }else{
                snow2.setBackground(Color.GREEN);
            }
        }else{
            snow1.setBackground(Color.GREEN);
        }
    }

    private  void pintarSeFueProfe(){
        if(profe5.getBackground().equals(Color.GREEN)){
            profe5.setBackground(Color.RED);
        }else{
            if(profe4.getBackground().equals(Color.GREEN)){
                profe4.setBackground(Color.RED);
            }else{
                if(profe3.getBackground().equals(Color.GREEN)){
                    profe3.setBackground(Color.RED);
                }else{
                    if(profe2.getBackground().equals(Color.GREEN)){
                        profe2.setBackground(Color.RED);
                    }else{
                        profe1.setBackground(Color.RED);
                        //nunca se va a pasar de acá porque cuando no queden mas profes disponibles, simplemente no acepta mas esquiadores
                    }
                }
            }
        }
    }
    private void pintarVolvioProfe (){
        if(profe1.getBackground().equals(Color.GREEN)) {
            if (profe2.getBackground().equals(Color.GREEN)) {
                if (profe3.getBackground().equals(Color.GREEN)) {
                    if (profe4.getBackground().equals(Color.GREEN)) {
                        profe5.setBackground(Color.GREEN);
                    } else {
                        profe4.setBackground(Color.GREEN);
                    }
                } else {
                    profe3.setBackground(Color.GREEN);
                }
            } else {
                profe2.setBackground(Color.GREEN);
            }
        }else{
            profe1.setBackground(Color.GREEN);
        }
    }
    private void quitarBotonSky(){
        if(sky3.getBackground().equals(Color.GREEN)){
            sky3.setBackground(Color.BLACK);
        }else{
            if(sky2.getBackground().equals(Color.GREEN))
                sky2.setBackground(Color.BLACK);
            else
                sky1.setBackground(Color.BLACK);
        }
    }

    private void quitarBotonSnow(){
        if(profe3.getBackground().equals(Color.GREEN)){
            profe3.setBackground(Color.BLACK);
        }else{
            if(profe2.getBackground().equals(Color.GREEN))
                profe2.setBackground(Color.BLACK);
            else
                profe1.setBackground(Color.BLACK);
        }
    }
    private void resetSky(){
        //*his.clases.append("Se formó un grupo, comienza la clase de Sky \n");
        try {
            Thread.sleep(500); //solo para ver el cambio
        } catch (InterruptedException e) {       e.printStackTrace(); }
        this.sky1.setBackground(Color.BLACK);
        this.sky2.setBackground(Color.BLACK);
        this.sky3.setBackground(Color.BLACK);
        this.sky4.setBackground(Color.BLACK);
    }
    private void resetSnow(){
        try {
            Thread.sleep(500); //solo para poder ver el cambio
        } catch (InterruptedException e) {       e.printStackTrace(); }
        this.snow1.setBackground(Color.BLACK);
        this.snow2.setBackground(Color.BLACK);
        this.snow3.setBackground(Color.BLACK);
        this.snow4.setBackground(Color.BLACK);
    }

    /*inicializa los botones para mostrar que hay profesores
    private void inicializarBotonesProfe (){
        this.profe1.setBackground(Color.BLUE);
        this.profe2.setBackground(Color.BLUE);
        this.profe3.setBackground(Color.BLUE);
        this.profe4.setBackground(Color.BLUE);
        this.profe5.setBackground(Color.BLUE);
        //this.clases.append("Llegaron todos los profesores \n");
    }*/

    class Boton extends JButton{

        public  Boton (int posX, int posY, Color colorBack){

            //JButton b = new JButton();
            setSize(30,30);
            setLocation(posX, posY);
            setBackground(colorBack);
            setVisible(true);
            setEnabled(false);
        }
      /*  public void onClick(){
            this.setBackground(Color.GREEN);
        }*/



    }

}
