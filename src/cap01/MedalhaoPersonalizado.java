package cap01;
/**
 * MedalhaoPersonalizado.java
 *
 * Primeiro projeto prático - Criação de um medalhão interativo
 * que representa a identidade única de cada desenvolvedor Java iniciante.
 *
 * Este programa demonstra:
 * - Entrada e saída de dados
 * - Variáveis e tipos de dados
 * - Estruturas condicionais
 * - Operações com Strings
 * - Formatação de saída
 * @author Weriton L. Petreca
 * @version 2.0 - Versão Inicial
 */

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MedalhaoPersonalizado {

    public static void main(String[] args) {
        // Criando scanner para entrada de dados
        Scanner entrada = new Scanner(System.in);

        // Exibindo cabeçalho do programa
        exibirCabecalho();

        // Coletando informações do usuário
        System.out.print("🧙 Digite seu nome, jovem aprendiz: ");
        String nomeAprendiz = entrada.nextLine();

        System.out.print("🏰 Escolha sua escola (Lobo/Grifo/Gato/Víbora/Urso): ");
        String escolaEscolhida = entrada.nextLine();

        System.out.print("⚡ Qual seu sinal favorito (Igni/Quen/Aard/Axii/Yrden): ");
        String sinalFavorito = entrada.nextLine();

        System.out.print("🎯 Quantas horas você pretende estudar Java por semana? ");
        int horasEstudo = entrada.nextInt();

        // Processando informações e determinando características
        String simboloEscola = determinarSimboloEscola(escolaEscolhida);
        String corSinal = determinarCorSinal(sinalFavorito);
        String nivelDedicacao = calcularNivelDedicacao(horasEstudo);
        String previsaoProgresso = calcularPrevisaoProgresso(horasEstudo);

        // Exibindo medalhão personalizado
        exibirMedalhao(nomeAprendiz, escolaEscolhida, simboloEscola,
                sinalFavorito, corSinal, nivelDedicacao, previsaoProgresso);

        // Salvando dados da sessão
        exibirResumoSessao(nomeAprendiz, horasEstudo);

        entrada.close();
    }

    /**
     * Exibe o cabeçalho artístico do programa
     */
    public static void exibirCabecalho() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              🏰 FORJA DE MEDALHÕES JAVA 🏰               ║");
        System.out.println("║                    Kaer Morhen Academy                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Bem-vindo à Forja Ancestral de Medalhões!");
        System.out.println("Aqui você criará seu primeiro artefato Java personalizado.");
        System.out.println();
    }

    /**
     * Determina o símbolo da escola baseado na escolha do usuário
     */
    public static String determinarSimboloEscola(String escola) {
        String escolhaLower = escola.toLowerCase();

        switch (escolhaLower) {
            case "lobo":
                return "🐺";
            case "grifo":
                return "🦅";
            case "gato":
                return "🐱";
            case "víbora":
            case "vibora":
                return "🐍";
            case "urso":
                return "🐻";
            default:
                return "⚔️"; // Símbolo genérico para escolas não reconhecidas
        }
    }

    /**
     * Determina a cor/elemento do sinal escolhido
     */
    public static String determinarCorSinal(String sinal) {
        String sinalLower = sinal.toLowerCase();

        switch (sinalLower) {
            case "igni":
                return "🔥 FOGO";
            case "quen":
                return "🛡️ PROTEÇÃO";
            case "aard":
                return "⚡ FORÇA";
            case "axii":
                return "🌀 MENTE";
            case "yrden":
                return "⭕ ARMADILHA";
            default:
                return "✨ MAGIA";
        }
    }

    /**
     * Calcula o nível de dedicação baseado nas horas de estudo
     */
    public static String calcularNivelDedicacao(int horas) {
        if (horas >= 20) {
            return "LENDÁRIO 🏆";
        } else if (horas >= 15) {
            return "DEVOTADO ⭐⭐⭐";
        } else if (horas >= 10) {
            return "DEDICADO ⭐⭐";
        } else if (horas >= 5) {
            return "INICIANTE ⭐";
        } else {
            return "CASUAL 🌱";
        }
    }

    /**
     * Calcula uma previsão de progresso baseada no tempo de estudo
     */
    public static String calcularPrevisaoProgresso(int horas) {
        int semanasParaDominio = (int) Math.ceil(200.0 / horas); // 200 horas para domínio básico

        if (semanasParaDominio <= 10) {
            return "Você dominará Java em " + semanasParaDominio + " semanas! ⚡";
        } else if (semanasParaDominio <= 20) {
            return "Domínio esperado em " + semanasParaDominio + " semanas. 🎯";
        } else {
            return "Jornada de " + semanasParaDominio + " semanas pela frente. 🗡️";
        }
    }

    /**
     * Exibe o medalhão personalizado com arte ASCII
     */
    public static void exibirMedalhao(String nome, String escola, String simbolo,
                                      String sinal, String cor, String dedicacao,
                                      String previsao) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          🏅 SEU MEDALHÃO JAVA PERSONALIZADO 🏅");
        System.out.println("=".repeat(60));
        System.out.println();

        // Arte do medalhão
        System.out.println("                    ╔════════════╗");
        System.out.println("                 ╔══╝            ╚══╗");
        System.out.println("              ╔══╝     " + simbolo + "  " + simbolo + "     ╚══╗");
        System.out.println("           ╔══╝       MEDALHÃO       ╚══╗");
        System.out.println("        ╔══╝           JAVA             ╚══╗");
        System.out.println("     ╔══╝              DE                  ╚══╗");
        System.out.println("  ╔══╝              " + nome.toUpperCase() + "              ╚══╗");
        System.out.println("  ║                                          ║");
        System.out.println("  ║    ESCOLA: " + escola.toUpperCase() + " " + simbolo + "                   ║");
        System.out.println("  ║    SINAL:  " + cor + "                   ║");
        System.out.println("  ║    NÍVEL:  " + dedicacao + "                ║");
        System.out.println("  ║                                         ║");
        System.out.println("  ╚══╗                                  ╔══╝");
        System.out.println("     ╚══╗                          ╔══╝");
        System.out.println("        ╚══╗                    ╔══╝");
        System.out.println("           ╚══╗              ╔══╝");
        System.out.println("              ╚══╗        ╔══╝");
        System.out.println("                 ╚══╗  ╔══╝");
        System.out.println("                    ╚══╝");
        System.out.println();
        System.out.println("📊 PREVISÃO DE PROGRESSO: " + previsao);
        System.out.println();
    }

    /**
     * Exibe resumo da sessão com informações técnicas
     */
    public static void exibirResumoSessao(String nome, int horas) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("─".repeat(60));
        System.out.println("📋 RESUMO DA SESSÃO DE FORJA");
        System.out.println("─".repeat(60));
        System.out.println("👤 Aprendiz: " + nome);
        System.out.println("⏰ Data/Hora: " + agora.format(formato));
        System.out.println("📚 Horas semanais comprometidas: " + horas);
        System.out.println("💻 Ambiente Java: Configurado e funcional");
        System.out.println("🎯 Status: Medalhão forjado com sucesso!");
        System.out.println();
        System.out.println("🔮 Próximo passo: Capítulo 2 - 'Os Ingredientes das Poções'");
        System.out.println("   (Tipos de dados primitivos em Java)");
        System.out.println();
        System.out.println("✨ Que sua jornada Java seja longa e próspera, " + nome + "!");
        System.out.println("=".repeat(60));
    }
}
