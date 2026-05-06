package br.com.roland;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Receiver {
    // legenda: ct = conteúdo, mr = mensagem recebida, cr = crc recebido, cc = crc calculado, cp = campos

    public static void main(String[] args) {

        try {
            String[] ct = lerArquivo("dados_enviados.txt");

            String mr = ct[0]; // linha 1: mensagem
            long cr = Long.parseLong(ct[1]); // linha 2: crc32 enviado

            long cc = calcularCRC32(mr);

            System.out.println("\ndados recebidos:");
            System.out.println("mensagem : " + mr);
            System.out.println("crc32 recebido : " + cr);
            System.out.println("crc32 calculado : " + cc);

            if (cc == cr) {
                System.out.println("deu certo, os dados chegaram sem erro.\n");
                exibirDados(mr);
            } else {
                System.out.println("deu erro, os dados foram alterados.");
                System.out.println("mensagem descartada.");
            }

        } catch (IOException e) {
            System.out.println("arquivo não encontrado.");
            System.out.println("roda o main.java primeiro pra enviar os dados.");
        }
    }

    // lê as duas linhas do arquivo
    private static String[] lerArquivo(String na) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(na));
        String ms = br.readLine(); // linha 1: mensagem
        String cs = br.readLine(); // linha 2: crc32
        br.close();
        return new String[]{ms, cs};
    }

    // calcula de novo pra comparar
    private static long calcularCRC32(String tx) {
        return CacularCRC32.calcular(tx);
    }

    // mostra os dados separados
    private static void exibirDados(String ms) {
        String[] cp = ms.split(",");
        System.out.println("dados do usuário:");
        System.out.println("nome     : " + cp[0]);
        System.out.println("idade    : " + cp[1]);
        System.out.println("telefone : " + cp[2]);
    }
}
