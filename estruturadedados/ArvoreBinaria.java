import java.util.*;

public class ArvoreBinaria {
    private Elemento raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public boolean arvoreVazia() {
        if (raiz == null)
            return true;
        else
            return false;
    }

    private Elemento inserir(Elemento arvore, IDado dado) {
        if (arvore == null)
            arvore = new Elemento(dado);
        else {
            int comparacao = dado.compareTo(arvore.dado);
            if (comparacao > 0)
                arvore.direita = inserir(arvore.direita, dado);
            else if (comparacao < 0)
                arvore.esquerda = inserir(arvore.esquerda, dado);
        }
        return arvore;
    }

    public void inserir(IDado dado) {
        this.raiz = inserir(this.raiz, dado);
    }

    private IDado buscar(Elemento arvore, IDado dado) {
        if (arvore == null)
            return null;
        else {
            int comparacao = dado.compareTo(arvore.dado);
            if (comparacao > 0)
                return buscar(arvore.direita, dado);
            else if (comparacao < 0)
                return buscar(arvore.esquerda, dado);
            else
                return arvore.dado;
        }
    }

    public IDado buscar(IDado dado) {
        return buscar(this.raiz, dado);
    }

    private int numeroFolhas(Elemento arvore) {
        if (arvore == null)
            return 0;
        else if (arvore.direita == null && arvore.esquerda == null)
            return 1;
        else {
            return numeroFolhas(arvore.direita) + numeroFolhas(arvore.esquerda);
        }
    }

    public int numeroFolhas() {
        return numeroFolhas(this.raiz);
    }

    private Elemento removerFolhas(Elemento arvore) {
        if (arvore != null) {
            if (arvore.direita == null && arvore.esquerda == null)
                arvore = null;
            else {
                arvore.esquerda = removerFolhas(arvore.esquerda);
                arvore.direita = removerFolhas(arvore.direita);
            }
        }
        return arvore;
    }

    public void removerFolhas() {
        this.raiz = removerFolhas(this.raiz);
    }

    private boolean arvoreCheia(Elemento arvore) {
        if (arvore == null || (arvore.direita == null && arvore.esquerda == null))
            return false;
        else
            return arvoreCheia(arvore.esquerda) && arvoreCheia(arvore.direita);
    }

    public boolean arvoreCheia() {
        return arvoreCheia(this.raiz);
    }

    private Elemento retirar(Elemento arvore, IDado dado) {
        if (arvore == null)
            return arvore;
        else {
            if (arvore.dado.equals(dado)) {
                if (arvore.direita == null)
                    return (arvore.esquerda);
                else if (arvore.esquerda == null)
                    return (arvore.direita);
                else {
                    arvore.esquerda = antecessor(arvore, arvore.esquerda);
                    return (arvore);
                }
            } else {
                if (arvore.dado.compareTo(dado) > 0)
                    arvore.esquerda = retirar(arvore.esquerda, dado);
                else
                    arvore.direita = retirar(arvore.direita, dado);
                return arvore;
            }
        }
    }

    private Elemento antecessor(Elemento arvore, Elemento elemento) {
        if (arvore.direita != null) {
            arvore.direita = antecessor(elemento, arvore.direita);
            return arvore;
        } else {
            elemento.dado = arvore.dado;
            return arvore.esquerda;
        }
    }

    public void retirar(IDado dado) {
        retirar(this.raiz, dado);
    }

    // Código de impressão de árvores binárias retirado do link:
    // https://stackoverflow.com/a/29704252/13084524
    // e adaptado para o uso nas classes
    // OBS: este método retorna uma String que pode não caber na saída do console
    // portanto dependendo do tamanho da árvore, a saída sai desconfigurada
    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();

        List<List<String>> lines = new ArrayList<List<String>>();

        List<Elemento> level = new ArrayList<Elemento>();
        List<Elemento> next = new ArrayList<Elemento>();

        level.add(raiz);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (Elemento n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.toString();
                    line.add(aa);
                    if (aa.length() > widest)
                        widest = aa.length();

                    next.add(n.esquerda);
                    next.add(n.direita);

                    if (n.esquerda != null)
                        nn++;
                    if (n.direita != null)
                        nn++;
                }
            }

            if (widest % 2 == 1)
                widest++;

            lines.add(line);

            List<Elemento> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size(); // * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null)
                                c = '└';
                        }
                    }
                    aux.append(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            aux.append(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            aux.append(j % 2 == 0 ? " " : "─");
                        }
                        aux.append(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            aux.append(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                aux.append("\n");
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null)
                    f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    aux.append(" ");
                }
                aux.append(f);
                for (int k = 0; k < gap2; k++) {
                    aux.append(" ");
                }
            }
            aux.append("\n");

            perpiece /= 2;
        }
        return aux.toString();
    }
}