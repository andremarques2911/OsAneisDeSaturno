import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        try {

            String path = "../casos/caso1.txt";

            int tam =  getTamanhoLinha(path);

            Grafo G = new Grafo(tam, tam);

            File arquivo = new File(path);
            Scanner sc = new Scanner(arquivo);
            String entradaSaidaStr = sc.nextLine();
            String[] entradaSaida = entradaSaidaStr.split(" ");

            while (sc.hasNext()) {
                String linha = sc.nextLine();
                G.insereLinha(linha);
            }
            long timeIn = System.currentTimeMillis();
            int custo = G.encontraCaminho(entradaSaida[0], entradaSaida[1]);
            long timeOut = System.currentTimeMillis();
            System.out.println("Tempo de execucao: " + (timeOut - timeIn) + " milissegundos.");
            System.out.println("Custo: " + custo + " unidades de tempo.");

//            G.imprime();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static int getTamanhoLinha(String path) throws FileNotFoundException {
        File arquivo = new File(path);
        Scanner sc = new Scanner(arquivo);
        sc.nextLine();
        String linha = sc.nextLine();
        sc.close();
        return linha.length();
    }
}
