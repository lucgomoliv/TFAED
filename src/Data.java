import java.util.Calendar;
import java.util.Date;

public class Data implements IDado{
    public Date data;
    public ArvoreAVL usos;

    public Data(Date date){
        this.data = date;
        usos = new ArvoreAVL();
    }

    @Override
    public int hashCode() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    @Override
    public int compareTo(IDado o) {
        return data.compareTo(((Data)o).data);
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        return String.valueOf(calendar.get(Calendar.DAY_OF_YEAR));
    }

    @Override
    public boolean equals(Object obj) {
        Data data = (Data) obj;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.data);
        int a = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(data.data);
        int b = calendar.get(Calendar.DAY_OF_YEAR);
        if(a == b) return true;
        else return false;
    }
}