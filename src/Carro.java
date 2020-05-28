import java.util.Objects;

public class Carro {
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
}