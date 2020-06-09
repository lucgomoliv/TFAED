public class Lista {

    private Elemento ultimo, primeiro;

    public Lista(){
        this.ultimo = this.primeiro = new Elemento(null);
    }

    /**
     * Adiciona um elemento no final da lista
     * @param dado dado a ser adicionado
     * @return true ou false
     */
    public boolean adicionarElemento(IDado dado){
        this.ultimo.proximo = ultimo = new Elemento(dado);
        return true;
    }

    /**
     * Retira o primeiro elemento da lista
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
     * Retira o elemento correspondente ao mock
     * @param dado dado mockado
     * @return IDado correspondente ou nulo se não existir
     */
    public IDado retirarElemento(IDado dado){
        IDado retorno = null;
        Elemento aux = this.primeiro;
        while(aux.proximo != null){
            if(aux.proximo.dado.equals(dado)) {
                retorno = aux.proximo.dado;
                aux.proximo = aux.proximo.proximo;
                return retorno;
            }
            aux = aux.proximo;
        }
        return retorno;
    }

    /**
     * Retorna true se a lista está vazia
     * @return true ou false
     */
    public boolean isEmpty(){
        if(this.primeiro.proximo == null) return true;
        else return false;
    }

    /**
     * Retorna um array de IDado presentes na lista
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
     * Retorna o número de elementos da lista
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