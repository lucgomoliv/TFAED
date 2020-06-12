import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class App {
    static ArvoreAVL carros;
    static HashNova vagas;
    static HashNova uso;

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(LocalDateTime.now());
        carros = lerCarros();
        vagas = lerVagas();
        uso = lerUso();
        System.out.println(LocalDateTime.now());
        consultarVeiculo(new Carro("PUB-7662"));
        System.console().readLine();
        consultarVaga(new Vaga(0, 1719), new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("14/01/2020"));
        System.console().readLine();
        ordenadoPorData();
        System.out.println(LocalDateTime.now());
    }

    public static ArvoreAVL lerCarros() throws IOException {
        ArvoreAVL avl = new ArvoreAVL();
        File dados = new File("dados/dadosCarros.txt");
        try {
            Scanner sc = new Scanner(dados);
            while (sc.hasNextLine()) {
                String info = sc.nextLine();
                avl.inserir(new Carro(info));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
            e.printStackTrace();
        }
        return avl;
    }

    public static HashNova lerVagas() throws IOException {
        Path path = Paths.get("dados/dadosVagas.txt");
        HashNova hash = new HashNova((int)Files.lines(path).count());
        File dados = new File("dados/dadosVagas.txt");
        try {
            Scanner sc = new Scanner(dados);
            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split(";");
                hash.inserir(new Vaga(Integer.parseInt(info[0].substring(1)), Integer.parseInt(info[1])));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
            e.printStackTrace();
        }
        //System.out.println(hash.colisoes);
        return hash;
    }

    public static HashNova lerUso() throws IOException, ParseException {
        /*Path path = Paths.get("dados/dadosuso.txt");
        HashNova hash = new HashNova((int)Files.lines(path).count());*/
        HashNova hash = new HashNova(365);
        Date first = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(first);
        do{
            hash.inserir(new Data(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }while(!hash.cheia());
        //System.out.println(hash);
        //System.out.println(hash.colisoes);
        ArvoreAVL avl = new ArvoreAVL();
        File dados = new File("dados/dadosUso.txt");
        try {
            Scanner sc = new Scanner(dados);
            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split(";");
                Date entrada = new Date();
                Date saida = new Date();
                try {
                    entrada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(info[2]);
                    saida = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(info[2].split(" ")[0] + " " + info[3]);
                } catch (ParseException e) {
                    System.out.println("Não foi possível converter as datas!");
                    e.printStackTrace();
                }
                Uso novo = new Uso((Carro) carros.buscar(new Carro(info[0])),
                                    (Vaga) vagas.buscar(new Vaga(0, Integer.parseInt(info[1]))), entrada, saida);
                ((Carro)carros.buscar(novo.getCarro())).getUsos().adicionarElemento(novo);
                ((Vaga)vagas.buscar(novo.getVaga())).getUsos().adicionarElemento(novo);
                ((Data)hash.buscar(new Data(entrada))).usos.inserir(novo);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: ");
            e.printStackTrace();
        }
        return hash;
    }

    public static void consultarVeiculo(Carro carro) {
        double precoTotal = 0;
        double precoAtual;
        for (IDado a : ((Carro)carros.buscar(carro)).getUsos().listarElementos()) {
            System.out.println(a.toString());
            precoAtual = (((Uso) a).getHoraEntrada().getTime() - ((Uso) a).getHoraSaida().getTime()) * 0.0000033 * -1;
            System.out.println(precoAtual);
            precoTotal += precoAtual;
        }
        System.out.println(precoTotal);
    }

    public static void consultarVaga(Vaga vaga, Date data1, Date data2) {
        for (IDado a : ((Vaga)vagas.buscar(vaga)).getUsos().listarElementos()) {
            if (data1.compareTo(((Uso) a).getHoraEntrada()) <= 0)
                if (data2.compareTo(((Uso) a).getHoraEntrada()) > 0)
                    System.out.println(a.toString());
        }
    }

    public static void ordenadoPorData() throws ParseException {
        Date first = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(first);
        int count = 0;
        do{
            for (IDado a : ((Data)uso.buscar(new Data(calendar.getTime()))).usos.inOrdem()) {
                System.out.println(a.toString());
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            ++count;
        }while(count < 365);
        
    }
}
