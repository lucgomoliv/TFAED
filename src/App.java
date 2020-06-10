import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class App {
    static ArvoreAVL carros;
    static ArvoreAVL vagas;
    static ArvoreAVL uso;

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        carros = lerCarros();
        vagas = lerVagas();
        uso = lerUso();
        consultarVeiculo(new Carro("PUB-7662"));
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

    public static ArvoreAVL lerUso() {
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
                    saida = new SimpleDateFormat("HH:mm").parse(info[3]);

                } catch (ParseException e) {
                    System.out.println("Não foi possível converter as datas!");
                    e.printStackTrace();
                }
                avl.inserir(new Uso((Carro)carros.buscar(new Carro(info[0])), 
                                    (Vaga)vagas.buscar(new Vaga(0, Integer.parseInt(info[1]))), 
                                    entrada, saida));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: ");
            e.printStackTrace();
        }
        return avl;
    }

    public static void consultarVeiculo(Carro carro){
        int count = 0;
        for(IDado a : uso.buscarTodos(new Uso(carro, new Vaga(0, 0), new Date(), new Date()))){
            System.out.println(a.toString());
            System.out.println(++count);
        }
    }
}
