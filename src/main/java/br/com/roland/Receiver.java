package br.com.roland;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.CRC32;

/**
 * DESTINATÁRIO — lê os dados do arquivo, recalcula o CRC32
 * e verifica se a mensagem chegou sem corrupção.
 */
public class Receiver {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("       SISTEMA DE VERIFICAÇÃO           ");
        System.out.println("========================================");

        try {
            // ── Lê o arquivo enviado pelo Main ───────────────
            String[] conteudo = lerArquivo("dados_enviados.txt");

            String mensagemRecebida   = conteudo[0]; // linha 1: mensagem
            long   checksumRecebido   = Long.parseLong(conteudo[1]); // linha 2: checksum original

            // ── Recalcula o CRC32 da mensagem recebida ───────
            long checksumCalculado = calcularCRC32(mensagemRecebida);

            // ── Exibe o que foi recebido ─────────────────────
            System.out.println("\nDADOS RECEBIDOS:");
            System.out.println("  Mensagem         : " + mensagemRecebida);
            System.out.println("  CRC32 recebido   : " + checksumRecebido);
            System.out.println("  CRC32 calculado  : " + checksumCalculado);
            System.out.println("----------------------------------------");

            // ── Compara os checksums ─────────────────────────
            if (checksumCalculado == checksumRecebido) {
                System.out.println("✅ INTEGRIDADE CONFIRMADA!");
                System.out.println("   Os dados chegaram sem corrupção.\n");
                exibirDados(mensagemRecebida);
            } else {
                System.out.println("❌ CORRUPÇÃO DETECTADA!");
                System.out.println("   Os dados foram alterados durante o envio.");
                System.out.println("   Descartando mensagem...");
            }

        } catch (IOException e) {
            System.out.println("❌ Arquivo não encontrado.");
            System.out.println("   Execute Main.java primeiro para enviar os dados.");
        }
    }

    /**
     * Lê as duas linhas do arquivo: mensagem e checksum.
     * Usa BufferedReader (IO) para leitura eficiente.
     */
    private static String[] lerArquivo(String nomeArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
        String mensagem  = reader.readLine(); // lê a linha 1
        String checksum  = reader.readLine(); // lê a linha 2
        reader.close();
        return new String[]{mensagem, checksum};
    }

    /**
     * Recalcula o CRC32 da mensagem recebida.
     * Deve dar o mesmo número que o Main gerou — se der diferente,
     * significa que algo mudou no meio do caminho.
     */
    private static long calcularCRC32(String texto) {

        return
    }

    /**
     * Exibe os campos separados da mensagem de forma legível.
     */
    private static void exibirDados(String mensagem) {
        String[] campos = mensagem.split(",");
        System.out.println("DADOS DO USUÁRIO:");
        System.out.println("  Nome     : " + campos[0]);
        System.out.println("  Idade    : " + campos[1]);
        System.out.println("  Telefone : " + campos[2]);
    }
}