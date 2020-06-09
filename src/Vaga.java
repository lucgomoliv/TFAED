
public class Vaga implements IDado{
    private int idEstacionamento;
    private int idVaga;

    public Vaga(int idEstacionamento, int idVaga) {
        this.idEstacionamento = idEstacionamento;
        this.idVaga = idVaga;
    }

    public int getIdEstacionamento() {
        return this.idEstacionamento;
    }

    public void setIdEstacionamento(int idEstacionamento) {
        this.idEstacionamento = idEstacionamento;
    }

    public int getIdVaga() {
        return this.idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vaga)) {
            return false;
        }
        Vaga vaga = (Vaga) o;
        return idEstacionamento == vaga.idEstacionamento && idVaga == vaga.idVaga;
    }

    @Override
    public String toString() {
        return "{" +
            " idEstacionamento='" + getIdEstacionamento() + "'" +
            ", idVaga='" + getIdVaga() + "'" +
            "}";
    }

    @Override
    public int compareTo(IDado o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }
}
