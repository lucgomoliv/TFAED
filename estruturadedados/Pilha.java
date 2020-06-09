public class Pilha {
    Elemento topo, fundo;

    public Pilha(){
        this.topo = this.fundo = new Elemento(null);
    }

    public void push(IDado dado){
        Elemento elemento = new Elemento(dado);
        elemento.proximo = this.topo.proximo;
        this.topo.proximo = elemento;
        if(this.topo == this.fundo) this.fundo = elemento;
    } 

    public IDado pop(){
        Elemento aux = this.topo.proximo;
        if(aux!=null){
            this.topo.proximo = aux.proximo;
            aux.proximo = null;
            if(aux == this.fundo) this.fundo = this.topo;
            return aux.dado;
        }
        else return null;
    }

    public IDado peek(){
        if(this.topo.proximo == null) return null;
        else return this.topo.proximo.dado;
    }

    public boolean isEmpty(){
        return this.topo == this.fundo;
    }
}