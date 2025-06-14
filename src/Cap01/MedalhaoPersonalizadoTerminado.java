package Cap01;

/**
 * MedalhaoPersonalizado.java
 *
 * Primeiro projeto prático - Criação de um medalhão interativo
 * que representa a identidade única de cada desenvolvedor Java iniciante.
 *
 * Este programa demonstra:
 * - Entrada e saída de dados com validação robusta
 * - Variáveis e tipos de dados
 * - Estruturas condicionais e de repetição
 * - Operações com strings
 * - Formatação de saída
 * - Sistema de pontuação
 * - Personalização avançada
 * - Persistência em arquivo
 * - Tratamento de exceções
 * - Boas práticas de programação
 *
 * @author Desenvolvedor Java Aprendiz
 * @version 2.0 - Versão Completa
 */

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MedalhaoPersonalizadoTerminado {

    // ===== CONSTANTES DO PROGRAMA =====

    /**
     * Lista de escolas válidas de bruxos
     * Utilizada para validação de entrada do usuário
     */
    private static final List<String> ESCOLAS_VALIDAS = List.of(
            "lobo", "grifo", "gato", "víbora", "urso", "manticora"
    );

    /**
     * Lista de sinais mágicos válidos
     * Cada sinal possui características e pontuações específicas
     */
    private static final List<String> SINAIS_VALIDOS = List.of(
            "igni", "quen", "aard", "axii", "yrden"
    );

    /**
     * Cores disponíveis para personalização do medalhão
     * Cada cor representa um material diferente
     */
    private static final List<String> CORES_VALIDAS = List.of(
            "prata", "ouro", "bronze", "rubi", "esmeralda", "ametista"
    );

    /**
     * Tipos de borda disponíveis para o medalhão
     * Cada estilo possui uma representação ASCII diferente
     */
    private static final List<String> BORDAS_VALIDAS = List.of(
            "simples", "dupla", "ornamentada", "mística", "real"
    );

    // Constantes numéricas para validação
    private static final int HORAS_MINIMAS = 1;
    private static final int HORAS_MAXIMAS = 100;
    private static final int NOME_TAMANHO_MINIMO = 2;
    private static final int NOME_TAMANHO_MAXIMO = 30;
    private static final int LEMA_TAMANHO_MAXIMO = 50;

    // ===== CLASSE INTERNA PARA SISTEMA DE PONTUAÇÃO =====

    /**
     * Classe responsável por calcular e gerenciar a pontuação do medalhão
     * Utiliza encapsulamento para manter os dados organizados
     */
    private static class SistemaPontuacao {
        private int pontosEscola;
        private int pontosSinal;
        private int pontosHoras;
        private int pontosCor;
        private int bonusCombinacao;
        private int bonusPersonalizacao;

        /**
         * Calcula a pontuação total somando todos os componentes
         * @return pontuação total do medalhão
         */
        public int calcularTotal() {
            return pontosEscola + pontosSinal + pontosHoras +
                    pontosCor + bonusCombinacao + bonusPersonalizacao;
        }

        /**
         * Determina a classificação baseada na pontuação total
         * @return string com a classificação e emoji correspondente
         */
        public String getClassificacao() {
            int total = calcularTotal();
            if (total >= 150) return "Grão-Mestre Java 👑";
            if (total >= 120) return "Mestre Java 🏆";
            if (total >= 90) return "Bruxo Veterano ⭐⭐⭐⭐";
            if (total >= 60) return "Bruxo Sênior ⭐⭐⭐";
            if (total >= 30) return "Bruxo Júnior ⭐⭐";
            return "Aprendiz Promissor ⭐";
        }

        /**
         * Retorna um relatório detalhado da pontuação
         * @return string formatada com breakdown da pontuação
         */
        public String getRelatorioDetalhado() {
            StringBuilder relatorio = new StringBuilder();
            relatorio.append("📊 BREAKDOWN DA PONTUAÇÃO:\n");
            relatorio.append("   🏰 Escola: ").append(pontosEscola).append(" pontos\n");
            relatorio.append("   ⚡ Sinal: ").append(pontosSinal).append(" pontos\n");
            relatorio.append("   ⏰ Dedicação: ").append(pontosHoras).append(" pontos\n");
            relatorio.append("   🎨 Cor: ").append(pontosCor).append(" pontos\n");
            relatorio.append("   🔥 Bônus Combinação: ").append(bonusCombinacao).append(" pontos\n");
            relatorio.append("   ✨ Bônus Personalização: ").append(bonusPersonalizacao).append(" pontos\n");
            relatorio.append("   ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            relatorio.append("   🏅 TOTAL: ").append(calcularTotal()).append(" pontos");
            return relatorio.toString();
        }

        // Getters para acesso aos pontos individuais
        public int getPontosEscola() { return pontosEscola; }
        public int getPontosSinal() { return pontosSinal; }
        public int getPontosHoras() { return pontosHoras; }
        public int getPontosCor() { return pontosCor; }
        public int getBonusCombinacao() { return bonusCombinacao; }
        public int getBonusPersonalizacao() { return bonusPersonalizacao; }

        // Setters para definir pontuações
        public void setPontosEscola(int pontos) { this.pontosEscola = pontos; }
        public void setPontosSinal(int pontos) { this.pontosSinal = pontos; }
        public void setPontosHoras(int pontos) { this.pontosHoras = pontos; }
        public void setPontosCor(int pontos) { this.pontosCor = pontos; }
        public void setBonusCombinacao(int bonus) { this.bonusCombinacao = bonus; }
        public void setBonusPersonalizacao(int bonus) { this.bonusPersonalizacao = bonus; }
    }

    // ===== MÉTODO PRINCIPAL =====

    /**
     * Método principal - ponto de entrada da aplicação
     * Coordena todo o fluxo do programa de forma sequencial
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Criando scanner para entrada de dados - será usado em todo o programa
        Scanner entrada = new Scanner(System.in);

        try {
            // 1. Exibir cabeçalho artístico do programa
            exibirCabecalho();

            // 2. Coletar informações básicas do usuário com validação
            String nome = validarNome(entrada);
            String escola = validarEscola(entrada);
            String sinal = validarSinal(entrada);
            int horas = validarHoras(entrada);

            // 3. Coletar personalizações avançadas
            String cor = validarCor(entrada);
            String estiloBorda = escolherEstiloBorda(entrada);
            String lema = coletarLema(entrada);
            boolean medalhaoDuplo = perguntarMedalhaoDuplo(entrada);
            String simboloPersonal = escolherSimboloPersonal(entrada);

            // 4. Processar dados e calcular pontuação
            SistemaPontuacao pontuacao = calcularPontuacao(escola, sinal, horas, cor, lema, medalhaoDuplo);
            String simboloEscola = determinarSimboloEscola(escola);
            String corSinal = determinarCorSinal(sinal);
            String nivelDedicacao = calcularNivelDedicacao(horas);
            String previsaoProgresso = calcularPrevisaoProgresso(horas);

            // 5. Exibir medalhão personalizado
            exibirMedalhao(nome, escola, simboloEscola, sinal, corSinal,
                    cor, estiloBorda, lema, medalhaoDuplo, simboloPersonal,
                    nivelDedicacao, previsaoProgresso, pontuacao);

            // 6. Salvar dados em arquivo
            salvarMedalhaoArquivo(nome, escola, sinal, horas, cor, lema, pontuacao);

            // 7. Exibir resumo da sessão
            exibirResumoSessao(nome, horas, pontuacao);

        } catch (Exception e) {
            // Tratamento global de exceções
            System.out.println("\n❌ Erro inesperado: " + e.getMessage());
            System.out.println("🔧 Por favor, reinicie o programa e tente novamente.");
        } finally {
            // Garantir que o scanner seja fechado para liberar recursos
            entrada.close();
        }
    }

    // ===== MÉTODOS DE EXIBIÇÃO =====

    /**
     * Exibe o cabeçalho artístico do programa
     * Utiliza caracteres Unicode para criar uma interface visualmente atraente
     */
    public static void exibirCabecalho() {
        System.out.println("\033[2J\033[1;1H"); // Limpa a tela (compatível com a maioria dos terminais)

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              🏰 FORJA DE MEDALHÕES JAVA 🏰               ║");
        System.out.println("║                    Kaer Morhen Academy                   ║");
        System.out.println("║                     Versão 2.0 - Completa               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Bem-vindo à Forja Ancestral de Medalhões Java!");
        System.out.println("Aqui você criará seu primeiro artefato Java personalizado,");
        System.out.println("que representará sua jornada única como desenvolvedor.");
        System.out.println();
        System.out.println("⚔️ Cada escolha influenciará sua pontuação final");
        System.out.println("🎯 Combinações especiais concedem bônus extras");
        System.out.println("💾 Seu medalhão será salvo automaticamente");
        System.out.println();
        pausarExecucao();
    }

    /**
     * Pausa a execução aguardando o usuário pressionar Enter
     * Melhora a experiência do usuário permitindo leitura em seu próprio ritmo
     */
    public static void pausarExecucao() {
        System.out.print("Pressione Enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            // Ignora erro de I/O na pausa
        }
        System.out.println();
    }

    // ===== MÉTODOS DE VALIDAÇÃO =====

    /**
     * Valida o nome do usuário com múltiplos critérios
     * - Tamanho mínimo e máximo
     * - Apenas letras, espaços e acentos
     * - Não permite nomes vazios ou apenas espaços
     *
     * @param entrada Scanner para leitura de dados
     * @return nome válido fornecido pelo usuário
     */
    public static String validarNome(Scanner entrada) {
        while (true) {
            System.out.print("🧙 Digite seu nome, jovem aprendiz: ");
            String nome = entrada.nextLine().trim();

            // Validação: nome vazio
            if (nome.isEmpty()) {
                System.out.println("⚠️ O nome não pode estar vazio!");
                continue;
            }

            // Validação: tamanho mínimo
            if (nome.length() < NOME_TAMANHO_MINIMO) {
                System.out.println("⚠️ Nome deve ter pelo menos " + NOME_TAMANHO_MINIMO + " caracteres!");
                continue;
            }

            // Validação: tamanho máximo
            if (nome.length() > NOME_TAMANHO_MAXIMO) {
                System.out.println("⚠️ Nome deve ter no máximo " + NOME_TAMANHO_MAXIMO + " caracteres!");
                continue;
            }

            // Validação: apenas letras, espaços e acentos (regex para caracteres válidos)
            if (!nome.matches("[a-zA-ZÀ-ÿ\\u00C0-\\u017F ]+")) {
                System.out.println("⚠️ Use apenas letras e espaços no nome!");
                continue;
            }

            // Se chegou até aqui, o nome é válido
            System.out.println("✅ Nome aceito: " + nome);
            return nome;
        }
    }

    /**
     * Valida a escolha da escola de bruxo
     * Exibe opções disponíveis e valida a entrada
     *
     * @param entrada Scanner para leitura de dados
     * @return escola válida escolhida pelo usuário
     */
    public static String validarEscola(Scanner entrada) {
        System.out.println("\n🏰 ESCOLAS DE BRUXOS DISPONÍVEIS:");
        System.out.println("   🐺 Lobo - Versátil e adaptável");
        System.out.println("   🦅 Grifo - Nobre e preciso");
        System.out.println("   🐱 Gato - Ágil e furtivo");
        System.out.println("   🐍 Víbora - Místico e venenoso");
        System.out.println("   🐻 Urso - Forte e resistente");
        System.out.println("   🦁 Manticora - Raro e poderoso");
        System.out.println();

        while (true) {
            System.out.print("🎯 Escolha sua escola: ");
            String escola = entrada.nextLine().toLowerCase().trim();

            // Validação: entrada vazia
            if (escola.isEmpty()) {
                System.out.println("⚠️ Por favor, digite o nome de uma escola!");
                continue;
            }

            // Validação: escola válida
            if (ESCOLAS_VALIDAS.contains(escola)) {
                System.out.println("✅ Escola escolhida: " + capitalizar(escola));
                return escola;
            }

            // Feedback para entrada inválida
            System.out.println("⚠️ Escola não reconhecida!");
            System.out.println("💡 Opções válidas: " + String.join(", ", ESCOLAS_VALIDAS));
        }
    }

    /**
     * Valida a escolha do sinal mágico favorito
     * Apresenta informações sobre cada sinal
     *
     * @param entrada Scanner para leitura de dados
     * @return sinal válido escolhido pelo usuário
     */
    public static String validarSinal(Scanner entrada) {
        System.out.println("\n⚡ SINAIS MÁGICOS DISPONÍVEIS:");
        System.out.println("   🔥 Igni - Fogo devastador");
        System.out.println("   🛡️ Quen - Escudo protetor");
        System.out.println("   💨 Aard - Onda de choque");
        System.out.println("   🌀 Axii - Controle mental");
        System.out.println("   ⭕ Yrden - Armadilha mágica");
        System.out.println();

        while (true) {
            System.out.print("🎯 Qual seu sinal favorito? ");
            String sinal = entrada.nextLine().toLowerCase().trim();

            // Validação: entrada vazia
            if (sinal.isEmpty()) {
                System.out.println("⚠️ Por favor, digite o nome de um sinal!");
                continue;
            }

            // Validação: sinal válido
            if (SINAIS_VALIDOS.contains(sinal)) {
                System.out.println("✅ Sinal escolhido: " + capitalizar(sinal));
                return sinal;
            }

            // Feedback para entrada inválida
            System.out.println("⚠️ Sinal não reconhecido!");
            System.out.println("💡 Opções válidas: " + String.join(", ", SINAIS_VALIDOS));
        }
    }

    /**
     * Valida as horas de estudo semanais
     * Inclui tratamento de exceções para entradas não numéricas
     *
     * @param entrada Scanner para leitura de dados
     * @return número de horas válido
     */
    public static int validarHoras(Scanner entrada) {
        System.out.println("\n⏰ DEDICAÇÃO DE ESTUDO:");
        System.out.println("   📚 Quanto tempo você pretende dedicar ao Java por semana?");
        System.out.println("   💡 Recomendamos entre 5-20 horas para iniciantes");
        System.out.println();

        while (true) {
            System.out.print("🎯 Horas por semana (1-" + HORAS_MAXIMAS + "): ");

            try {
                int horas = entrada.nextInt();
                entrada.nextLine(); // Consumir quebra de linha

                // Validação: faixa válida
                if (horas >= HORAS_MINIMAS && horas <= HORAS_MAXIMAS) {
                    System.out.println("✅ Dedicação definida: " + horas + " horas/semana");
                    return horas;
                } else {
                    System.out.println("⚠️ Digite um número entre " + HORAS_MINIMAS + " e " + HORAS_MAXIMAS + "!");
                }

            } catch (InputMismatchException e) {
                // Tratamento para entrada não numérica
                System.out.println("⚠️ Por favor, digite apenas números!");
                entrada.nextLine(); // Limpar entrada inválida
            }
        }
    }

    /**
     * Valida a escolha da cor do medalhão
     * Cada cor representa um material diferente
     *
     * @param entrada Scanner para leitura de dados
     * @return cor válida escolhida pelo usuário
     */
    public static String validarCor(Scanner entrada) {
        System.out.println("\n🎨 CORES DISPONÍVEIS PARA SEU MEDALHÃO:");
        System.out.println("   🥈 Prata - Clássica e elegante");
        System.out.println("   🥇 Ouro - Luxuosa e brilhante");
        System.out.println("   🥉 Bronze - Rústica e durável");
        System.out.println("   💎 Rubi - Vermelha e poderosa");
        System.out.println("   💚 Esmeralda - Verde e mística");
        System.out.println("   💜 Ametista - Roxa e enigmática");
        System.out.println();

        while (true) {
            System.out.print("🎯 Escolha a cor do seu medalhão: ");
            String cor = entrada.nextLine().toLowerCase().trim();

            // Validação: entrada vazia
            if (cor.isEmpty()) {
                System.out.println("⚠️ Por favor, digite o nome de uma cor!");
                continue;
            }

            // Validação: cor válida
            if (CORES_VALIDAS.contains(cor)) {
                System.out.println("✅ Cor escolhida: " + capitalizar(cor));
                return cor;
            }

            // Feedback para entrada inválida
            System.out.println("⚠️ Cor não disponível!");
            System.out.println("💡 Opções válidas: " + String.join(", ", CORES_VALIDAS));
        }
    }

    // ===== MÉTODOS DE PERSONALIZAÇÃO AVANÇADA =====

    /**
     * Permite ao usuário escolher o estilo da borda do medalhão
     * Cada estilo possui uma representação visual diferente
     *
     * @param entrada Scanner para leitura de dados
     * @return string representando o estilo de borda escolhido
     */
    public static String escolherEstiloBorda(Scanner entrada) {
        System.out.println("\n🖼️ ESTILOS DE BORDA DISPONÍVEIS:");
        System.out.println("   1. ═══ Simples - Clean e minimalista");
        System.out.println("   2. ║││ Dupla - Elegante e forte");
        System.out.println("   3. ▓▓▓ Ornamentada - Rica em detalhes");
        System.out.println("   4. ✦✦✦ Mística - Símbolos mágicos");
        System.out.println("   5. ♦♦♦ Real - Fit para reis");
        System.out.println();

        while (true) {
            System.out.print("🎯 Escolha o estilo (1-5): ");

            try {
                int escolha = entrada.nextInt();
                entrada.nextLine(); // Consumir quebra de linha

                String estilo = switch (escolha) {
                    case 1 -> "simples";
                    case 2 -> "dupla";
                    case 3 -> "ornamentada";
                    case 4 -> "mística";
                    case 5 -> "real";
                    default -> null;
                };

                if (estilo != null) {
                    System.out.println("✅ Estilo escolhido: " + capitalizar(estilo));
                    return estilo;
                } else {
                    System.out.println("⚠️ Digite um número entre 1 e 5!");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Por favor, digite apenas números!");
                entrada.nextLine(); // Limpar entrada inválida
            }
        }
    }

    /**
     * Coleta um lema personalizado do usuário
     * O lema será exibido no medalhão como frase motivacional
     *
     * @param entrada Scanner para leitura de dados
     * @return lema válido fornecido pelo usuário
     */
    public static String coletarLema(Scanner entrada) {
        System.out.println("\n📜 LEMA PESSOAL:");
        System.out.println("   ✍️ Crie um lema que represente sua jornada Java");
        System.out.println("   💡 Exemplos: 'Código limpo, mente clara' ou 'Debug is my passion'");
        System.out.println("   📏 Máximo " + LEMA_TAMANHO_MAXIMO + " caracteres");
        System.out.println();

        while (true) {
            System.out.print("🎯 Seu lema (Enter para pular): ");
            String lema = entrada.nextLine().trim();

            // Permitir lema vazio (opcional)
            if (lema.isEmpty()) {
                System.out.println("✅ Nenhum lema definido");
                return "Sem lema definido";
            }

            // Validação: tamanho máximo
            if (lema.length() <= LEMA_TAMANHO_MAXIMO) {
                System.out.println("✅ Lema definido: \"" + lema + "\"");
                return lema;
            } else {
                System.out.println("⚠️ Lema muito longo! Máximo " + LEMA_TAMANHO_MAXIMO + " caracteres.");
                System.out.println("💡 Atual: " + lema.length() + " caracteres");
            }
        }
    }

    /**
     * Pergunta se o usuário deseja um medalhão duplo (frente e verso)
     * Medalhões duplos concedem bônus de personalização
     *
     * @param entrada Scanner para leitura de dados
     * @return true se o usuário escolher medalhão duplo
     */
    public static boolean perguntarMedalhaoDuplo(Scanner entrada) {
        System.out.println("\n🔄 MEDALHÃO DUPLO:");
        System.out.println("   ⚡ Deseja criar um medalhão com frente e verso?");
        System.out.println("   🎁 Medalhões duplos concedem +10 pontos de bônus!");
        System.out.println();

        while (true) {
            System.out.print("🎯 Medalhão duplo? (s/n): ");
            String resposta = entrada.nextLine().toLowerCase().trim();

            if (resposta.equals("s") || resposta.equals("sim") || resposta.equals("y") || resposta.equals("yes")) {
                System.out.println("✅ Medalhão duplo selecionado - Bônus aplicado!");
                return true;
            } else if (resposta.equals("n") || resposta.equals("não") || resposta.equals("nao") || resposta.equals("no")) {
                System.out.println("✅ Medalhão simples selecionado");
                return false;
            } else {
                System.out.println("⚠️ Responda com 's' para sim ou 'n' para não");
            }
        }
    }

    /**
     * Permite escolher um símbolo pessoal para o medalhão
     * Cada símbolo representa uma característica do desenvolvedor
     *
     * @param entrada Scanner para leitura de dados
     * @return emoji representando o símbolo escolhido
     */
    public static String escolherSimboloPersonal(Scanner entrada) {
        System.out.println("\n🔮 SÍMBOLO PESSOAL:");
        System.out.println("   Escolha um símbolo que represente sua personalidade:");
        System.out.println("   1. ⚔️ Espada - Determinação e coragem");
        System.out.println("   2. 🛡️ Escudo - Proteção e defesa");
        System.out.println("   3. 📚 Livro - Conhecimento e sabedoria");
        System.out.println("   4. 🔥 Fogo - Paixão e energia");
        System.out.println("   5. ⚡ Raio - Velocidade e poder");
        System.out.println("   6. 🌟 Estrela - Aspiração e brilho");
        System.out.println();

        while (true) {
            System.out.print("🎯 Escolha seu símbolo (1-6): ");

            try {
                int escolha = entrada.nextInt();
                entrada.nextLine(); // Consumir quebra de linha

                String simbolo = switch (escolha) {
                    case 1 -> "⚔️";
                    case 2 -> "🛡️";
                    case 3 -> "📚";
                    case 4 -> "🔥";
                    case 5 -> "⚡";
                    case 6 -> "🌟";
                    default -> null;
                };

                if (simbolo != null) {
                    System.out.println("✅ Símbolo escolhido: " + simbolo);
                    return simbolo;
                } else {
                    System.out.println("⚠️ Digite um número entre 1 e 6!");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Por favor, digite apenas números!");
                entrada.nextLine(); // Limpar entrada inválida
            }
        }
    }

    // ===== MÉTODOS DE PROCESSAMENTO DE DADOS =====

    /**
     * Determina o símbolo emoji baseado na escola escolhida
     * Cada escola possui seu símbolo característico
     *
     * @param escola nome da escola escolhida
     * @return emoji representando a escola
     */
    public static String determinarSimboloEscola(String escola) {
        return switch (escola.toLowerCase()) {
            case "lobo" -> "🐺";
            case "grifo" -> "🦅";
            case "gato" -> "🐱";
            case "víbora" -> "🐍";
            case "urso" -> "🐻";
            case "manticora" -> "🦁";
            default -> "⚔️"; // Símbolo genérico para escolas não reconhecidas
        };
    }

    /**
     * Determina o elemento/cor associado ao sinal escolhido
     * Cada sinal possui características elementais específicas
     *
     * @param sinal nome do sinal escolhido
     * @return string descrevendo o elemento do sinal
     */
    public static String determinarCorSinal(String sinal) {
        return switch (sinal.toLowerCase()) {
            case "igni" -> "🔥 FOGO";
            case "quen" -> "🛡️ PROTEÇÃO";
            case "aard" -> "💨 FORÇA";
            case "axii" -> "🌀 MENTE";
            case "yrden" -> "⭕ ARMADILHA";
            default -> "✨ MAGIA";
        };
    }

    /**
     * Calcula o nível de dedicação baseado nas horas de estudo
     * Diferentes faixas de horas resultam em diferentes classificações
     *
     * @param horas número de horas de estudo por semana
     * @return string com a classificação e emoji
     */
    public static String calcularNivelDedicacao(int horas) {
        if (horas >= 30) {
            return "OBSESSIVO 🔥🔥🔥";
        } else if (horas >= 20) {
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
     * Calcula uma previsão realista de progresso baseada no tempo de estudo
     * Utiliza uma estimativa de 200 horas para domínio básico de Java
     *
     * @param horas número de horas de estudo por semana
     * @return string com a previsão de progresso
     */
    public static String calcularPrevisaoProgresso(int horas) {
        final int HORAS_PARA_DOMINIO = 200; // Estimativa baseada em estudos
        int semanasParaDominio = (int) Math.ceil((double) HORAS_PARA_DOMINIO / horas);

        if (semanasParaDominio <= 8) {
            return "Domínio em " + semanasParaDominio + " semanas! ⚡ Progresso relâmpago!";
        } else if (semanasParaDominio <= 16) {
            return "Domínio em " + semanasParaDominio + " semanas. 🎯 Ritmo excelente!";
        } else if (semanasParaDominio <= 30) {
            return "Domínio em " + semanasParaDominio + " semanas. 📚 Progresso constante.";
        } else {
            return "Jornada de " + semanasParaDominio + " semanas. 🗡️ Paciência e perseverança!";
        }
    }

    /**
     * Calcula a pontuação completa do medalhão baseada em todas as escolhas
     * Sistema complexo que considera múltiplos fatores e bônus
     *
     * @param escola escola escolhida
     * @param sinal sinal favorito
     * @param horas horas de estudo
     * @param cor cor do medalhão
     * @param lema lema personalizado
     * @param medalhaoDuplo se escolheu medalhão duplo
     * @return objeto SistemaPontuacao com todas as pontuações calculadas
     */
    public static SistemaPontuacao calcularPontuacao(String escola, String sinal, int horas,
                                                     String cor, String lema, boolean medalhaoDuplo) {
        SistemaPontuacao sp = new SistemaPontuacao();

        // Pontuação base por escola (algumas escolas são mais raras/difíceis)
        sp.setPontosEscola(switch (escola.toLowerCase()) {
            case "manticora" -> 35; // Escola mais rara
            case "lobo" -> 30;      // Escola protagonista
            case "grifo" -> 25;     // Escola nobre
            case "víbora" -> 25;    // Escola mística
            case "urso" -> 20;      // Escola resistente
            case "gato" -> 20;      // Escola ágil
            default -> 15;          // Valor padrão
        });

        // Pontuação por sinal (baseada na dificuldade de domínio)
        sp.setPontosSinal(switch (sinal.toLowerCase()) {
            case "axii" -> 40;    // Sinal mais complexo
            case "yrden" -> 35;   // Sinal tático
            case "igni" -> 30;    // Sinal ofensivo
            case "quen" -> 25;    // Sinal defensivo
            case "aard" -> 25;    // Sinal básico
            default -> 20;        // Valor padrão
        });

        // Pontuação por dedicação (máximo 50 pontos)
        sp.setPontosHoras(Math.min(horas * 2, 50));

        // Pontuação por cor (algumas cores são mais valiosas)
        sp.setPontosCor(switch (cor.toLowerCase()) {
            case "rubi", "esmeralda", "ametista" -> 15; // Pedras preciosas
            case "ouro" -> 12;                          // Metal nobre
            case "prata" -> 10;                         // Metal clássico
            case "bronze" -> 8;                         // Metal básico
            default -> 5;                               // Valor padrão
        });

        // Bônus por combinações especiais (lore do The Witcher)
        int bonus = 0;

        // Combinação clássica: Escola do Lobo + Igni
        if (escola.equals("lobo") && sinal.equals("igni")) {
            bonus += 20;
        }

        // Combinação defensiva: Escola do Urso + Quen
        if (escola.equals("urso") && sinal.equals("quen")) {
            bonus += 15;
        }

        // Combinação furtiva: Escola do Gato + Axii
        if (escola.equals("gato") && sinal.equals("axii")) {
            bonus += 15;
        }

        // Combinação mística: Escola da Víbora + Yrden
        if (escola.equals("víbora") && sinal.equals("yrden")) {
            bonus += 18;
        }

        sp.setBonusCombinacao(bonus);

        // Bônus por personalização
        int bonusPersonalizacao = 0;

        // Bônus por lema personalizado (não usar o padrão)
        if (!lema.equals("Sem lema definido")) {
            bonusPersonalizacao += 5;
        }

        // Bônus por medalhão duplo
        if (medalhaoDuplo) {
            bonusPersonalizacao += 10;
        }

        // Bônus por alta dedicação
        if (horas >= 20) {
            bonusPersonalizacao += 8;
        }

        sp.setBonusPersonalizacao(bonusPersonalizacao);

        return sp;
    }

    // ===== MÉTODOS DE EXIBIÇÃO DO MEDALHÃO =====

    /**
     * Exibe o medalhão completo com todas as personalizações
     * Utiliza arte ASCII avançada para criar uma experiência visual rica
     *
     * @param nome nome do usuário
     * @param escola escola escolhida
     * @param simboloEscola emoji da escola
     * @param sinal sinal favorito
     * @param corSinal elemento do sinal
     * @param cor cor do medalhão
     * @param estiloBorda estilo da borda
     * @param lema lema personalizado
     * @param medalhaoDuplo se é medalhão duplo
     * @param simboloPersonal símbolo pessoal
     * @param nivelDedicacao classificação da dedicação
     * @param previsaoProgresso previsão de progresso
     * @param pontuacao sistema de pontuação completo
     */
    public static void exibirMedalhao(String nome, String escola, String simboloEscola,
                                      String sinal, String corSinal, String cor, String estiloBorda,
                                      String lema, boolean medalhaoDuplo, String simboloPersonal,
                                      String nivelDedicacao, String previsaoProgresso,
                                      SistemaPontuacao pontuacao) {

        // Limpar tela e preparar exibição
        System.out.println("\n" + "=".repeat(70));
        System.out.println("          🏅 SEU MEDALHÃO JAVA PERSONALIZADO 🏅");
        System.out.println("=".repeat(70));
        System.out.println();

        // Determinar caracteres da borda baseado no estilo
        String bordaChar = switch (estiloBorda) {
            case "simples" -> "═";
            case "dupla" -> "║";
            case "ornamentada" -> "▓";
            case "mística" -> "✦";
            case "real" -> "♦";
            default -> "─";
        };

        // Exibir medalhão principal (frente)
        exibirFrenteMedalhao(nome, simboloEscola, corSinal, cor, bordaChar,
                simboloPersonal, escola, sinal, nivelDedicacao);

        // Se for medalhão duplo, exibir verso
        if (medalhaoDuplo) {
            System.out.println("\n" + "─".repeat(50));
            System.out.println("           🔄 VERSO DO MEDALHÃO 🔄");
            System.out.println("─".repeat(50));

            exibirVersoMedalhao(lema, previsaoProgresso, pontuacao, bordaChar);
        }

        // Exibir informações complementares
        System.out.println("\n" + "═".repeat(60));
        System.out.println("📊 INFORMAÇÕES DO MEDALHÃO:");
        System.out.println("═".repeat(60));
        System.out.println("👤 Proprietário: " + nome);
        System.out.println("🏰 Escola: " + capitalizar(escola) + " " + simboloEscola);
        System.out.println("⚡ Sinal Favorito: " + capitalizar(sinal) + " " + corSinal);
        System.out.println("🎨 Material: " + capitalizar(cor));
        System.out.println("🖼️ Estilo: " + capitalizar(estiloBorda));
        System.out.println("💪 Dedicação: " + nivelDedicacao);
        System.out.println("📜 Lema: \"" + lema + "\"");
        System.out.println("🔄 Tipo: " + (medalhaoDuplo ? "Duplo (Frente e Verso)" : "Simples"));
        System.out.println();

        // Exibir breakdown da pontuação
        System.out.println(pontuacao.getRelatorioDetalhado());
        System.out.println("🏆 CLASSIFICAÇÃO FINAL: " + pontuacao.getClassificacao());
        System.out.println();
        System.out.println("📈 " + previsaoProgresso);
        System.out.println();
    }

    /**
     * Exibe a frente do medalhão com arte ASCII elaborada
     *
     * @param nome nome do usuário
     * @param simboloEscola emoji da escola
     * @param corSinal elemento do sinal
     * @param cor cor do medalhão
     * @param bordaChar caractere usado para a borda
     * @param simboloPersonal símbolo pessoal escolhido
     * @param escola nome da escola
     * @param sinal nome do sinal
     * @param nivelDedicacao classificação da dedicação
     */
    public static void exibirFrenteMedalhao(String nome, String simboloEscola, String corSinal,
                                            String cor, String bordaChar, String simboloPersonal,
                                            String escola, String sinal, String nivelDedicacao) {

        System.out.println("                    ╔" + bordaChar.repeat(16) + "╗");
        System.out.println("                 ╔" + bordaChar.repeat(2) + "╝            ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("              ╔" + bordaChar.repeat(2) + "╝     " + simboloEscola + "  " + simboloPersonal + "     ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("           ╔" + bordaChar.repeat(2) + "╝       MEDALHÃO       ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("        ╔" + bordaChar.repeat(2) + "╝           JAVA             ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("     ╔" + bordaChar.repeat(2) + "╝              DE                  ╚" + bordaChar.repeat(2) + "╗");

        // Nome centralizado (truncado se muito longo)
        String nomeExibicao = nome.length() > 14 ? nome.substring(0, 14) : nome;
        String nomeFormatado = centralizarTexto(nomeExibicao.toUpperCase(), 14);

        System.out.println("  ╔" + bordaChar.repeat(2) + "╝              " + nomeFormatado + "              ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("  " + bordaChar + "                                          " + bordaChar);
        System.out.println("  " + bordaChar + "    ESCOLA: " + centralizarTexto(escola.toUpperCase(), 8) + " " + simboloEscola + "                   " + bordaChar);
        System.out.println("  " + bordaChar + "    ELEMENTO: " + corSinal + "               " + bordaChar);
        System.out.println("  " + bordaChar + "    MATERIAL: " + centralizarTexto(cor.toUpperCase(), 8) + "                ❖    " + bordaChar);
        System.out.println("  " + bordaChar + "    NÍVEL: " + centralizarTexto(nivelDedicacao, 20) + "     " + bordaChar);
        System.out.println("  " + bordaChar + "                                          " + bordaChar);
        System.out.println("  ╚" + bordaChar.repeat(2) + "╗                                  ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("     ╚" + bordaChar.repeat(2) + "╗                          ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("        ╚" + bordaChar.repeat(2) + "╗                    ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("           ╚" + bordaChar.repeat(2) + "╗              ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("              ╚" + bordaChar.repeat(2) + "╗        ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("                 ╚" + bordaChar.repeat(2) + "╗  ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("                    ╚" + bordaChar.repeat(2) + "╝");
    }

    /**
     * Exibe o verso do medalhão com informações adicionais
     *
     * @param lema lema personalizado
     * @param previsaoProgresso previsão de progresso
     * @param pontuacao sistema de pontuação
     * @param bordaChar caractere da borda
     */
    public static void exibirVersoMedalhao(String lema, String previsaoProgresso,
                                           SistemaPontuacao pontuacao, String bordaChar) {

        System.out.println("              ╔" + bordaChar.repeat(20) + "╗");
        System.out.println("           ╔" + bordaChar.repeat(2) + "╝              ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("        ╔" + bordaChar.repeat(2) + "╝      VERSO DO        ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("     ╔" + bordaChar.repeat(2) + "╝        MEDALHÃO           ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("  ╔" + bordaChar.repeat(2) + "╝                              ╚" + bordaChar.repeat(2) + "╗");
        System.out.println("  " + bordaChar + "                                      " + bordaChar);

        // Exibir lema (quebrar em linhas se necessário)
        String[] linhasLema = quebrarTexto(lema, 30);
        System.out.println("  " + bordaChar + "  📜 LEMA:                           " + bordaChar);
        for (String linha : linhasLema) {
            System.out.println("  " + bordaChar + "     " + centralizarTexto(linha, 30) + "       " + bordaChar);
        }

        System.out.println("  " + bordaChar + "                                      " + bordaChar);
        System.out.println("  " + bordaChar + "  🏆 PONTUAÇÃO: " + centralizarTexto(String.valueOf(pontuacao.calcularTotal()), 5) + " pts          " + bordaChar);
        System.out.println("  " + bordaChar + "  📊 RANKING: " + centralizarTexto(pontuacao.getClassificacao(), 20) + " " + bordaChar);
        System.out.println("  " + bordaChar + "                                      " + bordaChar);
        System.out.println("  ╚" + bordaChar.repeat(2) + "╗                              ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("     ╚" + bordaChar.repeat(2) + "╗                          ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("        ╚" + bordaChar.repeat(2) + "╗                    ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("           ╚" + bordaChar.repeat(2) + "╗              ╔" + bordaChar.repeat(2) + "╝");
        System.out.println("              ╚" + bordaChar.repeat(16) + "╝");
    }

    // ===== MÉTODOS DE PERSISTÊNCIA =====

    /**
     * Salva as informações do medalhão em um arquivo de texto
     * Cria um registro permanente da criação do medalhão
     *
     * @param nome nome do usuário
     * @param escola escola escolhida
     * @param sinal sinal favorito
     * @param horas horas de estudo
     * @param cor cor do medalhão
     * @param lema lema personalizado
     * @param pontuacao sistema de pontuação completo
     */
    public static void salvarMedalhaoArquivo(String nome, String escola, String sinal,
                                             int horas, String cor, String lema,
                                             SistemaPontuacao pontuacao) {

        // Gerar nome de arquivo único com timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nomeArquivo = "medalhao_" + nome.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".txt";

        try (FileWriter writer = new FileWriter(nomeArquivo)) {

            // Cabeçalho do arquivo
            writer.write("══════════════════════════════════════════════════════════\n");
            writer.write("          🏅 MEDALHÃO JAVA PERSONALIZADO 🏅\n");
            writer.write("                Kaer Morhen Academy\n");
            writer.write("══════════════════════════════════════════════════════════\n\n");

            // Informações básicas
            writer.write("👤 INFORMAÇÕES DO APRENDIZ:\n");
            writer.write("   Nome: " + nome + "\n");
            writer.write("   Escola: " + capitalizar(escola) + "\n");
            writer.write("   Sinal Favorito: " + capitalizar(sinal) + "\n");
            writer.write("   Dedicação Semanal: " + horas + " horas\n");
            writer.write("   Material do Medalhão: " + capitalizar(cor) + "\n");
            writer.write("   Lema: \"" + lema + "\"\n\n");

            // Detalhes da pontuação
            writer.write("📊 SISTEMA DE PONTUAÇÃO:\n");
            writer.write("   Pontos por Escola: " + pontuacao.getPontosEscola() + "\n");
            writer.write("   Pontos por Sinal: " + pontuacao.getPontosSinal() + "\n");
            writer.write("   Pontos por Dedicação: " + pontuacao.getPontosHoras() + "\n");
            writer.write("   Pontos por Material: " + pontuacao.getPontosCor() + "\n");
            writer.write("   Bônus Combinação: " + pontuacao.getBonusCombinacao() + "\n");
            writer.write("   Bônus Personalização: " + pontuacao.getBonusPersonalizacao() + "\n");
            writer.write("   ──────────────────────────────────\n");
            writer.write("   PONTUAÇÃO TOTAL: " + pontuacao.calcularTotal() + " pontos\n");
            writer.write("   CLASSIFICAÇÃO: " + pontuacao.getClassificacao() + "\n\n");

            // Metadados da sessão
            writer.write("🔧 INFORMAÇÕES TÉCNICAS:\n");
            writer.write("   Data de Criação: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
            writer.write("   Hora de Criação: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
            writer.write("   Versão do Programa: 2.0 - Completa\n");
            writer.write("   Arquivo: " + nomeArquivo + "\n\n");

            // Rodapé motivacional
            writer.write("═══════════════════════════════════════════════════════════\n");
            writer.write("🗡️ \"Todo grande bruxo começou exatamente onde você está\n");
            writer.write("    agora - com curiosidade, determinação e seu primeiro\n");
            writer.write("    medalhão forjado com as próprias mãos.\"\n");
            writer.write("                                    - Vesemir de Kaer Morhen\n");
            writer.write("═══════════════════════════════════════════════════════════\n");

            System.out.println("💾 SALVAMENTO CONCLUÍDO:");
            System.out.println("   ✅ Arquivo criado: " + nomeArquivo);
            System.out.println("   📁 Localização: Diretório atual do programa");
            System.out.println("   📋 Conteúdo: Informações completas do medalhão");

        } catch (IOException e) {
            System.out.println("❌ ERRO AO SALVAR ARQUIVO:");
            System.out.println("   🔧 Detalhes técnicos: " + e.getMessage());
            System.out.println("   💡 Verifique permissões de escrita no diretório");
            System.out.println("   🔄 O programa continuará normalmente");
        }
    }

    // ===== MÉTODOS DE FINALIZAÇÃO =====

    /**
     * Exibe um resumo final da sessão de criação do medalhão
     * Fornece informações estatísticas e próximos passos
     *
     * @param nome nome do usuário
     * @param horas horas de estudo comprometidas
     * @param pontuacao sistema de pontuação final
     */
    public static void exibirResumoSessao(String nome, int horas, SistemaPontuacao pontuacao) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("─".repeat(70));
        System.out.println("           📋 RESUMO DA SESSÃO DE FORJA");
        System.out.println("─".repeat(70));
        System.out.println();

        System.out.println("🎯 OBJETIVOS ALCANÇADOS:");
        System.out.println("   ✅ Configuração do ambiente Java concluída");
        System.out.println("   ✅ Primeiro programa Java executado com sucesso");
        System.out.println("   ✅ Medalhão personalizado forjado");
        System.out.println("   ✅ Sistema de pontuação aplicado");
        System.out.println("   ✅ Dados salvos em arquivo permanente");
        System.out.println();

        System.out.println("📊 ESTATÍSTICAS DA SESSÃO:");
        System.out.println("   👤 Aprendiz: " + nome);
        System.out.println("   ⏰ Data/Hora: " + agora.format(formato));
        System.out.println("   📚 Comprometimento: " + horas + " horas/semana");
        System.out.println("   🏆 Pontuação Alcançada: " + pontuacao.calcularTotal() + " pontos");
        System.out.println("   🎖️ Classificação: " + pontuacao.getClassificacao());
        System.out.println("   💻 Status do Ambiente: ✅ Configurado e funcional");
        System.out.println();

        System.out.println("🔮 PRÓXIMOS PASSOS NA JORNADA:");
        System.out.println("   📖 Capítulo 2: 'Os Ingredientes das Poções'");
        System.out.println("      └─ Tipos de dados primitivos em Java");
        System.out.println("   📖 Capítulo 3: 'Receitas Básicas de Alquimia'");
        System.out.println("      └─ Operadores e expressões");
        System.out.println("   📖 Capítulo 4: 'Estruturas de Decisão'");
        System.out.println("      └─ Estruturas condicionais (if, switch)");
        System.out.println();

        System.out.println("💡 DICAS PARA CONTINUAR:");
        System.out.println("   🔄 Pratique executando e modificando seu medalhão");
        System.out.println("   🛠️ Experimente alterar valores e personalização");
        System.out.println("   📚 Revise os conceitos aprendidos regularmente");
        System.out.println("   💪 Mantenha a disciplina de estudo semanal");
        System.out.println();

        System.out.println("✨ MENSAGEM FINAL:");
        System.out.println("   Parabéns, " + nome + "! Você deu o primeiro passo em uma");
        System.out.println("   jornada extraordinária. Seu medalhão não é apenas código");
        System.out.println("   - é a prova tangível de que você tem o que é preciso para");
        System.out.println("   se tornar um verdadeiro mestre da programação Java.");
        System.out.println();
        System.out.println("   🗡️ Que sua jornada seja longa e próspera!");
        System.out.println("   🏰 Kaer Morhen Academy - Forjando Mestres desde 2025");
        System.out.println();
        System.out.println("═".repeat(70));
    }

    // ===== MÉTODOS UTILITÁRIOS =====

    /**
     * Capitaliza a primeira letra de uma string
     * Utilizado para formatação consistente de nomes e escolhas
     *
     * @param texto string a ser capitalizada
     * @return string com primeira letra maiúscula
     */
    public static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    /**
     * Centraliza um texto em uma largura específica
     * Utilizado para alinhamento visual em arte ASCII
     *
     * @param texto texto a ser centralizado
     * @param largura largura total desejada
     * @return string centralizada com espaços
     */
    public static String centralizarTexto(String texto, int largura) {
        if (texto.length() >= largura) {
            return texto.substring(0, largura);
        }

        int espacosTotal = largura - texto.length();
        int espacosEsquerda = espacosTotal / 2;
        int espacosDireita = espacosTotal - espacosEsquerda;

        return " ".repeat(espacosEsquerda) + texto + " ".repeat(espacosDireita);
    }

    /**
     * Quebra um texto longo em múltiplas linhas
     * Utilizado para formatação de lemas longos
     *
     * @param texto texto a ser quebrado
     * @param larguraMaxima largura máxima por linha
     * @return array de strings com o texto quebrado
     */
    public static String[] quebrarTexto(String texto, int larguraMaxima) {
        if (texto.length() <= larguraMaxima) {
            return new String[]{texto};
        }

        List<String> linhas = new ArrayList<>();
        String[] palavras = texto.split(" ");
        StringBuilder linhaAtual = new StringBuilder();

        for (String palavra : palavras) {
            if (linhaAtual.length() + palavra.length() + 1 <= larguraMaxima) {
                if (linhaAtual.length() > 0) {
                    linhaAtual.append(" ");
                }
                linhaAtual.append(palavra);
            } else {
                if (linhaAtual.length() > 0) {
                    linhas.add(linhaAtual.toString());
                    linhaAtual = new StringBuilder(palavra);
                } else {
                    // Palavra muito longa - truncar
                    linhas.add(palavra.substring(0, larguraMaxima));
                }
            }
        }

        if (linhaAtual.length() > 0) {
            linhas.add(linhaAtual.toString());
        }

        return linhas.toArray(new String[0]);
    }
}
