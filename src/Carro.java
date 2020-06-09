import java.util.Objects;

public class Carro implements IDado{
    private String placa;

    public Carro(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Carro)) {
            return false;
        }
        Carro carro = (Carro) o;
        return Objects.equals(placa, carro.placa);
    }
    
    @Override
    public String toString() {
        return "{" +
            " placa='" + getPlaca() + "'" +
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