package com.company;


import UI.MiEventoListener;

public class ComplejoInvernal{

    private Confiteria confiteria;
    private static CabinaInstructores cabina;
    private long horaCierre;
    private Medio[] mediosDeElev;
    private MiEventoListener listener;



    public ComplejoInvernal(Confiteria c, Medio [] m, CabinaInstructores cab)    {
        this.confiteria = c;
        this.mediosDeElev = m;
        this.cabina = cab;
        this.horaCierre = System.currentTimeMillis() + 420000; //420.000 ms <=> 7 minutos - 1min *real* <=> 1hs simulación.
        crearHilosMedio();
    }
    //por cada medio tengo que "prender" las aerosillas.
    public void crearHilosMedio(){
        Medio.HiloMedio [] arreglo = new Medio.HiloMedio[4];
        for (int i = 0; i < 4; i++) {
            Medio mAux = this.mediosDeElev[i];
            arreglo[i] = new Medio.HiloMedio(mAux, horaCierre);
            arreglo[i].start();
        }

    }

    public Confiteria getConfiteria()
    {
        return this.confiteria;
    }

    public void visitarMediosDeElevacion(Esquiador esquiador)    {
        int medioAsignado = esquiador.getMedio();
        try {
            Medio mAux = mediosDeElev [medioAsignado];
            mAux.ascenso(esquiador);    //El medio asciende al esquiador
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cierreDiario()    {
              consolaLog("Visitaron la confiteria: "+confiteria.cantidadDeComensales()+"");
        //Log("Personas que pasaron por la confiteria: "+confiteria.cantidadDeComensales()+"");

        //consolaLog("Personas que pasaron por los medios de elevacion: ");
        //Log("Personas que pasaron por los medios de elevacion: ");
        for (int i = 0; i < 4; i++) {
            int j = this.mediosDeElev[i].personasPorMedio();
            consolaLog("Medio "+(i+1)+" cantidad de personas "+j);
          //  Log("Medio "+(i+1)+" cantidad de personas "+j);
        }
        //consolaLog("Cantidad de clases que se dictaron");
        consolaLog("CLASES SKY: "+this.cabina.getContadorClasesSky());
        consolaLog("CLASES SNOW: "+this.cabina.getContadorClasesSnow());
        //Log("Cantidad de clases que se dictaron");
        //Log("CLASES SKY: "+this.cabina.getContadorClasesSky());
        //Log("CLASES SNOW: "+this.cabina.getContadorClasesSnow());
    }

    private void consolaLog(String mjs){
        if(listener != null){
            this.listener.cadenaCambio(this, mjs);
        }
    }
    private void Log(String msj) {
        System.out.println(msj);
    }
    public boolean complejoAbierto()    {
        return (System.currentTimeMillis() < horaCierre);
        //si la hora actual es mayor que la hora de cierre (7min) No se permite el acceso a ningun visitante mas.
        //pero se espera a que los que están en el complejo se vayan.
    }
    public void addMiEventListener (MiEventoListener listen)
    {
        this.listener = listen;
    }
}
