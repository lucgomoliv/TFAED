import java.util.Arrays;

public class HashNova {
    private Elemento[] tabela;
    private int count;
    public int colisoes;

    public HashNova(int tamanho) {
        tabela = new Elemento[tamanho];
        Arrays.fill(tabela, new Elemento());
        count = 0;
        colisoes = 0;
    }

    public int hash(IDado dado) {
        return Math.abs(dado.hashCode()) % tabela.length;
    }

    private int colisao(int pos, int tentativa) {
        return (pos + tentativa) % tabela.length;
    }

    public void inserir(IDado dado) {
        if (this.count < tabela.length) {
            int colisao = 0;
            int posOriginal = hash(dado);
            int pos = posOriginal;

            while (tabela[pos].ehValido()) {
                pos = colisao(posOriginal, ++colisao);
                ++colisoes;
            }

            this.tabela[pos] = new Elemento(dado);
            this.count++;
        }
    }

    public IDado buscar(IDado dado) {
        int posOriginal = hash(dado);
        int pos = posOriginal;
        int tentativa = 0;

        while ((this.tabela[pos].getDado() != null) && (!this.tabela[pos].getDado().equals(dado)))
            pos = colisao(posOriginal, ++tentativa);

        if (this.tabela[pos].ehValido())
            return this.tabela[pos].getDado();
        else
            return null;
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();

        for (int i = 0; i < tabela.length; i++) {
            aux.append(String.format("%2d", i) + " - ");
            if (tabela[i].ehValido())
                aux.append(tabela[i].getDado().toString());
            aux.append("\n");
        }

        return aux.toString();
    }

    public boolean cheia(){
        if(count == tabela.length) return true;
        else return false;
    }

}