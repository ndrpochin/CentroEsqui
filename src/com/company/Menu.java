package com.company;



import java.util.Observable;
import java.util.Random;

public class Menu extends Observable{

    private int opcion;
    private String menuPrincipal;
    private String postre;



    public  Menu(int tipoM){        //este parametro lo deje para que la interfaz funciones CORREGIR
        Random i = new Random();
        opcion = i.nextInt(4);  //0,1,2,3
        switch(opcion){
            case 0:{
                this.menuPrincipal = "Arroz con Pollo";
                this.postre = "Flan";
                break;
            }
            case 1:{
                this.menuPrincipal = "Hamburguesa";
                this.postre = "Helado";
                break;
            }
            case 2:{
                this.menuPrincipal = "Empanadas";
                this.postre = ""; //este menu no contiene postre
                break;
            }
            case 3:{
                this.menuPrincipal = "Pizza Napolitana";
                this.postre = ""; //este menu no contiene postre
                break;
            }
            default:{
                this.menuPrincipal = "Sopa";
                this.postre = "Manzana";
                break; //este caso no va a suceder
            }
        }//fin Switch
    }
    //void
    public String mostrarMenu(){
        String mensaje = "";

        if(postre == null)
            mensaje= this.menuPrincipal;
        else
            mensaje = menuPrincipal+" - "+this.postre;

      //  setChanged(); notifyObservers(mensaje);
        return mensaje;
    }
    //era void
    public String mostrarPostre(){
        //String mensaje = this.postre;
        //setChanged(); notifyObservers(mensaje);
        return  this.postre;
    }
/*

    public void mostrarMenu()
    {
        String mensaje = "";
        if(tipoMenu!=3)
        {
            //System.out.println("****************MENU******************");
            for(int i=0;i<2;i++)
            {
                System.out.println("-->"+this.menu[i]);
                mensaje = this.menu[i];
                setChanged(); notifyObservers(mensaje);
            }
        }

    }

    public void mostrarPostre()
    {//si es el menu 3 (es de postres)
        //int i=randomDeDosNum();

        Random r = new Random();
        int i = r.nextInt(2) + 1;
        //System.out.println("ENTEROOOO: "+i);
        //System.out.println("-->"+this.menu[i-1]);
        String mensaje = "-->"+this.menu[i-1];
        setChanged(); notifyObservers(mensaje);
    }
*/


/*  public int randomDeDosNum()
    {
        Random r=new Random();
        int rm=r.nextInt(2)+1;
        return rm;
    }

 public Menu(int tipoM)
    {
        this.tipoMenu = tipoM;
        this.menu = new String[2];
        if(this.tipoMenu==1)
        {//menu 1
            this.menu[0]="ENTRADA: Sopa de verduras";
            this.menu[1]="PLATO PRINCIPAL: Milanesas a la napolitana, con guarnicion";
        }
        else
        {
            if(this.tipoMenu==2)
            {//menu 2
                this.menu[0]="ENTRADA: Empanadita de trucha, con crema";
                this.menu[1]="PLATO PRICIPAL: Bifes a la criolla con pure de papa";

            }
            else//menu 3
            {
                this.menu[0]="POSTRE: Cheesecake, con salsa de frutos rojos";
                this.menu[1]="POSTRE: Flan con dulce de leche y crema";
            }
        }
    }*/

}
