package com.company;

public class Mostrador {

    private Menu menu;
    private int tipoMenu; //indica que menu es: 1,2 o 3

    public Mostrador(int tipoM){         //1, 2 o 3
        this.menu = new Menu(tipoM);//guarda el menu 1, 2 o 3
        this.tipoMenu = tipoM; //indica si es el menu 1, 2 o 3
    }

    public String mostrar()   {
        return this.menu.mostrarMenu();
    }

    public String mostrarPostre()
    {
        return (this.menu.mostrarPostre());
    }
}
