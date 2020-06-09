public class Elemento {

    public IDado dado;
    public Elemento proximo;
    public Elemento anterior;
    public Elemento esquerda;
    public Elemento direita;
    public boolean valido;
    public int altura;
    public int fb;

    public Elemento(IDado dado){
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
        this.esquerda = null;
        this.direita = null;
        this.valido = true;
        this.altura = 0;
        this.fb = 0;
    }

    public Elemento(){
        this.dado = null;
        this.valido = false;
    }

    public void setFalso(){
        this.valido = false;
    }

    public void setVerdadeiro(){
        this.valido = true;
    }

    public boolean ehValido(){return valido;}
    public IDado getDado(){return dado;}

    @Override
    public String toString() {
        return dado.toString();
    }
}