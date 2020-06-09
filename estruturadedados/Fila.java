public class Fila {

    private Elemento ultimo, primeiro;

    public Fila(){
        this.ultimo = this.primeiro = new Elemento(null);
    }

    /**
     * Adiciona um elemento no final da fila
     * @param dado
     * @return true ou false
     */
    public boolean adicionarElemento(IDado dado){
        this.ultimo.proximo = ultimo = new Elemento(dado);
        return true;
    }

    /**
     * Retira o primeiro elemento da fila
     * @return primeiro IDado ou nulo se não existir
     */
    public IDado retirarElemento(){
        if(this.primeiro == this.ultimo) return null;
        Elemento aux = this.primeiro.proximo;
        this.primeiro.proximo = aux.proximo;
        if(aux == this.ultimo) this.primeiro = this.ultimo;
        return aux.dado;
    }

    /**
     * Retorna true se a fila está vazia
     * @return true ou false
     */
    public boolean isEmpty(){
        if(this.primeiro.proximo == null) return true;
        else return false;
    }

    
    /**
     * Retorna um array de IDado presentes na fila
     * @return IDado[] 
     */
    public IDado[] listarElementos(){
        IDado[] dados = new IDado[numeroDeElementos()]; 
        Elemento aux = this.primeiro;
        int index = 0;
        while(aux.proximo != null){
            dados[index] = aux.proximo.dado;
            aux = aux.proximo;
            index++;
        }
        return dados;
    }

    /**
     * Retorna o número de elementos da fila
     * @return a number
     */
    public int numeroDeElementos(){
        int num = 0;
        Elemento aux = this.primeiro;
        while(aux.proximo != null){
            num++;
            aux = aux.proximo;
        }
        return num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Elemento aux = this.primeiro;
        while(aux.proximo != null){
            sb.append(aux.proximo.dado.toString() + "; ");
            aux = aux.proximo;
        }
        return sb.toString();
    }
}