public class Estacionamento {
    public ArvoreAVL carros;
    public ArvoreAVL vagas;
    public ArvoreAVL usos;
    public int id;

    public Estacionamento(int id, ArvoreAVL carros, ArvoreAVL vagas, ArvoreAVL usos){
        this.id = id;
        this.carros = carros;
        this.vagas = vagas;
        this.usos = usos;
    }

    public void consultar(){
        
    }
}