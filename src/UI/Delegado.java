package UI;

public class Delegado {
    private  String cadena;
    private  int numero;

    public Delegado(){
        this.cadena = "cadena";
        numero = 666;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
