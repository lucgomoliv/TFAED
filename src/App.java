import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class App {
    static ArvoreAVL carros;
    static ArvoreAVL vagas;
    static AVLUso uso;

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(LocalDateTime.now());
        carros = lerCarros();
        vagas = lerVagas();
        uso = lerUso();
        consultarVeiculo(new Carro("PUB-7662"));
        System.console().readLine();
        consultarVaga(new Vaga(0, 1719), new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("14/01/2020"));
        System.console().readLine();
        ordenadoPorData();
        System.out.println(LocalDateTime.now());
    }

    public static ArvoreAVL lerCarros() {
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

    public static ArvoreAVL lerVagas() {
        ArvoreAVL avl = new ArvoreAVL();
        File dados = new File("dados/dadosVagas.txt");
        try {
            Scanner sc = new Scanner(dados);
            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split(";");
                avl.inserir(new Vaga(Integer.parseInt(info[0].substring(1)), Integer.parseInt(info[1])));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
            e.printStackTrace();
        }
        return avl;
    }

    public static AVLUso lerUso() {
        AVLUso avl = new AVLUso();
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
                avl.inserir(new Uso((Carro) carros.buscar(new Carro(info[0])),
                        (Vaga) vagas.buscar(new Vaga(0, Integer.parseInt(info[1]))), entrada, saida));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: ");
            e.printStackTrace();
        }
        return avl;
    }

    public static void consultarVeiculo(Carro carro) {
        double precoTotal = 0;
        double precoAtual;
        for (IDado a : uso.buscarTodos(new Uso(carro, new Vaga(0, 0), new Date(), new Date()))) {
            System.out.println(a.toString());
            precoAtual = (((Uso) a).getHoraEntrada().getTime() - ((Uso) a).getHoraSaida().getTime()) * 0.0000033 * -1;
            System.out.println(precoAtual);
            precoTotal += precoAtual;
        }
        System.out.println(precoTotal);
    }

    public static void consultarVaga(Vaga vaga, Date data1, Date data2) {
        for (IDado a : uso.buscarTodosVaga(new Uso(new Carro(""), vaga, new Date(), new Date()))) {
            if (data1.compareTo(((Uso) a).getHoraEntrada()) <= 0)
                if (data2.compareTo(((Uso) a).getHoraEntrada()) > 0)
                    System.out.println(a.toString());
        }
    }

    public static void ordenadoPorData() {
        for (IDado a : uso.ordenadoPorData()) {
            System.out.println(a.toString());
        }
    }
}
