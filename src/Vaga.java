import java.util.Objects;

public class Vaga implements IDado {
    private int idEstacionamento;
    private int idVaga;
    private Lista usos;

    public Vaga(int idEstacionamento, int idVaga) {
        this.idEstacionamento = idEstacionamento;
        this.idVaga = idVaga;
        this.usos = new Lista();
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

    public Lista getUsos(){
        return this.usos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vaga)) {
            return false;
        }
        Vaga vaga = (Vaga) o;
        return idVaga == vaga.idVaga;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idVaga);
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
        Vaga vaga = (Vaga) o;
        return Integer.compare(this.idVaga, vaga.idVaga);
    }
}
