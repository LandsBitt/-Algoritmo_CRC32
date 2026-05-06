package br.com.roland;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    // legenda: nm = nome, id = idade, tl = telefone, ms = mensagem, cs = crc32

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("qual seu nome? ");
        String nm = sc.nextLine();

        System.out.print("qual sua idade? ");
        String id = sc.nextLine();

        System.out.print("qual seu telefone? ");
        String tl = sc.nextLine();

        sc.close();

        String ms = nm + "," + id + "," + tl;
        long cs = calcularCRC32(ms);

        System.out.println("dados enviados:");
        System.out.println("mensagem : " + ms);
        System.out.println("crc32    : " + cs);

        try {
            enviarParaArquivo(ms, cs);
            System.out.println("arquivo salvo: dados_enviados.txt");
        } catch (IOException e) {
            System.out.println("erro ao enviar: " + e.getMessage());
        }
    }

    // calcula o crc32 da mensagem
    private static long calcularCRC32(String tx) {
        return CacularCRC32.calcular(tx);
    }

    // salva a mensagem e o crc32 no arquivo
    private static void enviarParaArquivo(String ms, long cs) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("dados_enviados.txt"));
        bw.write(ms);  // linha 1: mensagem
        bw.newLine();
        bw.write(String.valueOf(cs)); // linha 2: crc32
        bw.close();
    }
}
