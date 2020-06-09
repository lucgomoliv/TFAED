import java.util.Arrays;

public class Hash {
    private final int TAMHASH;
    private int count;

    private Elemento[] dados;
    
    public int contColisao;
    
    
    public Hash(int n){
        if(n>10) this.TAMHASH = n;
        else this.TAMHASH = 10;
        this.dados = new Elemento[TAMHASH];
        Arrays.fill(dados, new Elemento()); // inicializa o vetor com todos os elementos iguais
        this.count = 0;
        this.contColisao=0;
    }

    public int hash(IDado d){
        return (Math.abs(d.hashCode()) % TAMHASH);
    }

    

    private int colisao(int pos, int tentativa){  //sondagem linear
        return (pos+tentativa)%TAMHASH;
    }

    public void add(IDado novo){
        if(this.count<TAMHASH){
            int colisao=0;      //quant. colisões na tentativa de inserção
            int posOriginal = hash(novo);
            int pos = posOriginal;

            //if(dados[posOriginal].ehValido()) this.contColisao++;
            
            while(dados[pos].ehValido()) {
                this.contColisao++;
            
                pos = colisao(posOriginal, ++colisao);
            }
            
            this.dados[pos] = new Elemento(novo);
            this.count++;
        }
    }

    public IDado find(IDado quem){
            int posOriginal = hash(quem);
            int pos = posOriginal;
            int tentativa=0;    //quant. tentativas de localização

            while((this.dados[pos].getDado()!=null) && ( !this.dados[pos].getDado().equals(quem)))
                pos = colisao(posOriginal, ++tentativa);

            if(this.dados[pos].ehValido())
                return this.dados[pos].getDado();    
            else 
                return null;
    }

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder();

        for(int i=0; i<dados.length; i++){
            aux.append(String.format("%2d",i)+" - ");
            if(dados[i].ehValido()) 
                aux.append(dados[i].getDado().toString());
            aux.append("\n");
        }
            
        return aux.toString();
    }

}