package com.company;

import UI.MiEventoListener;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Esquiador  implements Runnable {
    private String nombre;
    private int tipoDeVisitante; //segun lo que hara en el complejo: 0=Esquiar, 1=usar confiteria, 2=tomar clase...
    private ComplejoInvernal complejo;
    private static CabinaInstructores cabina;
    private Instructor [] array;
    private int ordenLlegada;
    private int medio;
    private int numSilla;
    private int menu;
    private boolean postre;
    private String menuElegido;
    private String postreElegido;
    private MiEventoListener listener;


    //CONTRUCTOR
    public Esquiador(String nom, ComplejoInvernal com, int men, CabinaInstructores c, Instructor [] array) {
        this.menu = men;
        this.nombre = nom+"*"+men;
        this.tipoDeVisitante = 1;
        this.complejo = com;
        this.postre = false;
        this.menuElegido = "";
        this.postreElegido = "";
        cabina = c;
        this.array = array;
        this.ordenLlegada = -1;
        setMedioInicial();
    }


    //OBSERVADORAS
    public boolean getPostre()    {
        return this.postre;
    }
    public void setPostre(){
        Random r = new Random();
        this.postre = r.nextBoolean();
    }
    public int getMenu() {
        return this.menu;
    }
    public  void setMenu(){
        Random r = new Random();
        this.menu = r.nextInt(2) + 1; // menu1 o menu2
    }
    public int getMedio()    {
        return this.medio;
    }
    public void setNumSilla(int numSillaAdquirida){
        this.numSilla = numSillaAdquirida;
    }
    public int verNumSilla(){
        return this.numSilla;
    }

    public void setMenuElegido(String nuevoMenu){
        this.menuElegido = nuevoMenu;
    }
    public void setPostreElegido(String nuevoPostre){
        this.postreElegido = nuevoPostre;
    }

    public String getMenuElegido(){
        return this.menuElegido;
    }
    public String getPostreElegido(){
        return this.postreElegido;
    }

    public String getNombre()
    {
        return this.nombre;
    }
    public ComplejoInvernal getComplejo()
    {
        return this.complejo;
    }

    public void setMedio(int m) {  this.medio = m;    }
    private  void setMedioInicial(){
        Random  num = new Random();
        this.medio  =  num.nextInt(4);
    }
    /*Modulo de clases de sky*/
    public void asignarOrden (int numOrden){
        this.ordenLlegada = numOrden;
    }
    public int verOrden(){
        return this.ordenLlegada;
    }
    public void ordenarSnow(int numOrdenSnow){
        this.ordenLlegada = numOrdenSnow;
    }
    public int verOrdenSnow(){
        return this.ordenLlegada;
    }

    public void despertarProfesor(Instructor ins) {

        int numProfe = -1;
        if(ins!= null)
            numProfe = ins.verNumProfe();
        else {
            //Log("ERROR despertarProfesor- ins es null");
            consolaLog("ERROR despertarProfesor- ins es null");
        }
        synchronized (array[numProfe]) {
            consolaLog("esq1"+nombre+" despierta al profe -->" + ins.name());
            consolaLog("esq1 num de profe"+ins.verNumProfe());
            //System.out.println(nombre+" despierta al profe -->" + ins.name());
            ins.notify(); //despierta al instructor correspondiente
        }
    }

    /**/
    public Instructor buscarProfesor(int index) {
        Instructor aux = array[index]; //obtengo el profe que le corresponde
        if(aux.duerme())
            return aux;
        else
            return null;
    }

    /*fin Modulos de clases de sky*/

    public  void run(){
        int i=0;
        Random num = new Random();
        if (this.complejo.complejoAbierto())//mientras este en horario-que no haya q cerrar complejo-
        {
            this.menu = num.nextInt(2)+1;   //seteo un nuevo menu, que puede ser 1 o 2
          //  this.postre = num.nextBoolean();
           // this.tipoDeVisitante = num.nextInt(3); //0=esquiar 1=confiteria 2=tomarClase

            this.tipoDeVisitante = num.nextInt(3);
            switch (this.tipoDeVisitante){
                case 0:{
                    this.irAEsquiar(); //va a visitar los medios de elevación
                    break;
                }
                case 1:{
                    this.visitarConfiteria();
                    break;
                }
                case  2:{
                    Random opcion = new Random(); //elige clase de SKY o SNOWBOARD
                    try{
                        if(opcion.nextBoolean())
                            cabina.llegaSky(this);
                        else
                            cabina.llegaSnow(this);
                        Log("Terminó la clase");
                    } catch (InterruptedException e) {     e.printStackTrace();    }
                    break;
                }
                default:{
                    break;
                }
            }
        }
        System.out.println("El complejo cerró y el esquiador se retira.");

    } //fin Run

    public void irAEsquiar()    {
        this.complejo.visitarMediosDeElevacion(this);
        //El esquiador elige un medio de elevación y sube a la cima.
    }


    public void visitarConfiteria()
    {
        Confiteria confiteria = this.complejo.getConfiteria();
        try {
            if ((this.complejo.complejoAbierto()) && confiteria.getMesas() < 100)   //mientras este en horario y haya lugar
            {
                consolaLog("CANTIDAD DE MESAS"+confiteria.getMesas());
                //VA A PAGAR
                confiteria.entrarACaja(this); //entra a la caja para abonar el menu que llevará¡
                //this.sleep(10);
                Thread.sleep(100);
                confiteria.salirDeCaja(this); //Sale de la caja al terminar de abonar
                //RETIRA EL MENU EN MOSTRADOR QUE CORRESPONDE Y LUEGO EL POSTRE SI LE CORRESPONDE
                Thread.sleep(100);

                setMenu(); //Elige el menu 1 o 2

                confiteria.entrarARetirarComida(this);
                Thread.sleep(100);
                if(this.getMenu() == 1)
                    consolaLog("esq21"+this.getMenuElegido());
                else
                    consolaLog("esq22"+this.getMenuElegido());


                setPostre(); //Elige si retira postre (T) o no(F)
                confiteria.entrarARetirarPostre(this);
                Thread.sleep(100);

                if(getPostre())
                    consolaLog("esq3 consumirá "+this.getPostreElegido());

                Random t = new Random();
                Thread.sleep(t.nextInt(10000)+20000); //tarda entre 20 y 30 minutos en comer
                confiteria.terminarDeComer(this);

            }else{
                if(!this.complejo.complejoAbierto()){
                    consolaLog("esq41 El complejo cerró, los esquiadores proceden a retirarse");
                    //El thread esquiador se terminará (ver Run)
                }else {
                    consolaLog("esq42 La confiteria se encuentra llena, se va.");
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Esquiador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    private void Log(String msj) {
        System.out.println(msj);
    }

    /*notifica a la interfaz gráfica cambios que debe imprimir en los textBoxs*/
    private void consolaLog(String mjs){
        if(listener != null){
            this.listener.cadenaCambio(this, mjs);
        }
    }

    public void addMiEventListener (MiEventoListener listen){
        this.listener = listen;
    }

}
