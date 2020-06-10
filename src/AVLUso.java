import java.util.ArrayList;
import java.util.List;

public class AVLUso extends ArvoreAVL{
    private ArvoreAVL vagas;
    private ArvoreAVL datas;

    public AVLUso(){
        this.raiz = null;
        vagas = new ArvoreAVL();
        datas = new ArvoreAVL();
    }

    @Override
    public void inserir(IDado dado){
        super.inserir(dado);
        vagas.raiz = inserirVagas(vagas.raiz, (Uso)dado);
        datas.raiz = inserirDatas(datas.raiz, (Uso)dado);
    }

    private Elemento inserirVagas(Elemento arvore, Uso dado) {
        if (arvore == null)
            arvore = new Elemento(dado);
        else {
            int comparacao = dado.compareVaga(arvore.dado);
            if (comparacao > 0){
                arvore.direita = inserirVagas(arvore.direita, dado);
            }
            else {
                arvore.esquerda = inserirVagas(arvore.esquerda, dado);
            }
        }
        if(arvore != null) arvore.altura = maior(altura(arvore.esquerda), altura(arvore.direita))+1;
        arvore = balancear(arvore);
        return arvore;
    }

    private Elemento inserirDatas(Elemento arvore, Uso dado) {
        if (arvore == null)
            arvore = new Elemento(dado);
        else {
            int comparacao = dado.compareData(arvore.dado);
            if (comparacao > 0){
                arvore.direita = inserirDatas(arvore.direita, dado);
            }
            else {
                arvore.esquerda = inserirDatas(arvore.esquerda, dado);
            }
        }
        if(arvore != null) arvore.altura = maior(altura(arvore.esquerda), altura(arvore.direita))+1;
        arvore = balancear(arvore);
        return arvore;
    }

    private List<IDado> buscarTodosVaga(Elemento arvore, Uso dado, List<IDado> dados) {
        if (arvore == null)
            return dados;
        else {
            int comparacao = dado.compareVaga(arvore.dado);
            if (comparacao > 0)
                dados = buscarTodosVaga(arvore.direita, dado, dados);
            else if (comparacao < 0)
                dados = buscarTodosVaga(arvore.esquerda, dado, dados);
            else{
                dados = buscarTodosVaga(arvore.direita, dado, dados);
                dados = buscarTodosVaga(arvore.esquerda, dado, dados);
                dados.add(arvore.dado);
            }
            return dados;
        }
    }

    public List<IDado> buscarTodosVaga(Uso dado){
        return buscarTodosVaga(vagas.raiz, dado, new ArrayList<IDado>());
    }

    private List<IDado> buscarTodosData(Elemento arvore, Uso dado, List<IDado> dados) {
        if (arvore == null)
            return dados;
        else {
            int comparacao = dado.compareData(arvore.dado);
            if (comparacao > 0)
                dados = buscarTodosData(arvore.direita, dado, dados);
            else if (comparacao < 0)
                dados = buscarTodosData(arvore.esquerda, dado, dados);
            else{
                dados = buscarTodosData(arvore.direita, dado, dados);
                dados = buscarTodosData(arvore.esquerda, dado, dados);
                dados.add(arvore.dado);
            }
            return dados;
        }
    }

    public List<IDado> buscarTodosData(Uso dado){
        return buscarTodosData(datas.raiz, dado, new ArrayList<IDado>());
    }

    private List<IDado> ordenadoPorData(Elemento arvore, List<IDado> dados){
        if(arvore == null) return dados;
        else {
            ordenadoPorData(arvore.esquerda, dados);
            dados.add(arvore.dado);
            ordenadoPorData(arvore.direita, dados);
            return dados;
        }
    }

    public List<IDado> ordenadoPorData(){
        return ordenadoPorData(datas.raiz, new ArrayList<IDado>());
    } 
}