package br.com.roland;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Coleta os dados
        System.out.print("Qual o seu nome? ");
        String nome = scanner.nextLine();

        System.out.print("Qual a sua idade? ");
        String idade = scanner.nextLine();

        System.out.print("Qual o seu telefone? ");
        String telefone = scanner.nextLine();

        scanner.close();

        // Monta a mensagem
        // Todos os campos juntos em uma única string separada por vírgula
        String mensagem = nome + "," + idade + "," + telefone;

        // Calcula o CRC32
        long checksum = calcularCRC32(mensagem);

        // Exibe o que será enviado
        System.out.println("DADOS PRONTOS PARA ENVIO:");
        System.out.println("  Mensagem : " + mensagem);
        System.out.println("  CRC32    : " + checksum);
        System.out.println("----------------------------------------");

        // Grava no arquivo (simula o envio)
        try {
            enviarParaArquivo(mensagem, checksum);
            System.out.println("Dados enviados com sucesso! (arquivo: dados_enviados.txt)");
            System.out.println("Execute Receiver.java para verificar.");
        } catch (IOException e) {
            System.out.println("Erro ao enviar: " + e.getMessage());
        }
    }

    /**
     * Calcula o checksum CRC32 de uma String.
     * CRC32 transforma qualquer texto em um número de 32 bits.
     * Se o texto mudar, o número muda completamente.
     */
    private static long calcularCRC32(String texto) {
        ; // alimenta os bytes do texto
        return        // retorna o número calculado
    }

    /**
     * Grava a mensagem e o checksum em um arquivo de texto.
     * Usa BufferedWriter (IO) para escrita eficiente.
     */
    private static void enviarParaArquivo(String mensagem, long checksum) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("dados_enviados.txt"));
        writer.write(mensagem);  // linha 1: a mensagem
        writer.newLine();
        writer.write(String.valueOf(checksum)); // linha 2: o checksum
        writer.close();
    }
}