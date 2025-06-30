// 1. COMENTÁRIOS DE CABEÇALHO (Opcional, mas recomendado)
/**
 * Nome do Arquivo: ExemploCompleto.java
 * Propósito: Demonstrar estrutura completa de classe Java
 * Autor: [Weriton L. Petreca]
 * Data: [2025-06-13]
 * Versão: 1.0
 */

// 2. DECLARAÇÃO DE PACKAGE (Opcional para arquivos simples)
package cap01;

// 3. IMPORTS (Importações de bibliotecas externas)
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// 4. COMENTÁRIO DA CLASSE
/**
 * Esta classe demonstra a estrutura completa de um arquivo Java,
 * incluindo todos os elementos fundamentais que um desenvolvedor
 * deve conhecer.
 */

// 5. DECLARAÇÃO DA CLASSE PRINCIPAL
public class ExemploCompletoTerminado {

    // 6. ATRIBUTOS/CAMPOS DA CLASSE (Variáveis de instância)
    private String nomeAprendiz;
    private int nivelHabilidade;
    private String escolaOrigem;
    private int idade;

    // 7. CONSTANTES (Variáveis estáticas finais)
    public static final String ESCOLA = "Java Kaer Morhen";
    public static final int IDADE_MINIMA = 16;
    public static final int NIVEL_MAXIMO = 100;

    // Lista estática para armazenar todos os aprendizes criados
    private static List<cap01.ExemploCompletoTerminado> todosAprendizes = new ArrayList<>();

    // 8. CONSTRUTOR(ES)
    /**
     * Construtor padrão - inicializa um novo aprendiz
     */
    public ExemploCompletoTerminado() {
        this.nomeAprendiz = "Aprendiz Desconhecido";
        this.nivelHabilidade = 1;
        this.escolaOrigem = "Desconhecida";
        this.idade = IDADE_MINIMA;
    }

    /**
     * Construtor com parâmetros - inicializa aprendiz com dados específicos
     */
    public ExemploCompletoTerminado(String nome, int nivel) {
        this.nomeAprendiz = nome;
        this.nivelHabilidade = nivel;
        this.escolaOrigem = "Não especificada";
        this.idade = IDADE_MINIMA;
    }

    /**
     * Construtor completo - inicializa aprendiz com todos os dados
     */
    public ExemploCompletoTerminado(String nome, int nivel, String escola, int idade) {
        this.nomeAprendiz = nome;
        this.nivelHabilidade = nivel;
        this.escolaOrigem = escola;
        this.idade = idade;
    }

    // 9. MÉTODO PRINCIPAL (Ponto de entrada da aplicação)
    /**
     * Método main - onde a execução do programa começa
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exibir cabeçalho do programa
        exibirCabecalho();

        // Criando instância da classe com dados pré-definidos
        ExemploCompletoTerminado aprendiz = new ExemploCompletoTerminado("Geralt", 99, "Kaer Morhen", 95);
        todosAprendizes.add(aprendiz);

        System.out.println("🎯 Demonstração com aprendiz pré-cadastrado:");
        System.out.println("=" + "=".repeat(50));

        // Chamando métodos da instância
        aprendiz.apresentarse();
        aprendiz.demonstrarHabilidades();

        // Menu principal interativo
        boolean continuar = true;
        while (continuar) {
            continuar = exibirMenuPrincipal(scanner);
        }

        scanner.close();
        System.out.println("\n🏰 Obrigado por visitar Kaer Morhen! Até a próxima jornada!");
    }

    /**
     * Exibe o cabeçalho artístico do programa
     */
    public static void exibirCabecalho() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                🏰 KAER MORHEN ACADEMY 🏰                 ║");
        System.out.println("║              Sistema de Gestão de Aprendizes            ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Exibe o menu principal e processa as opções do usuário
     */
    public static boolean exibirMenuPrincipal(Scanner scanner) {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("🗡️ MENU PRINCIPAL - ESCOLHA SUA AÇÃO:");
        System.out.println("─".repeat(60));
        System.out.println("1. 🆕 Adicionar novo aprendiz");
        System.out.println("2. 📋 Listar todos os aprendizes");
        System.out.println("3. 🔍 Buscar aprendiz por nome");
        System.out.println("4. ⚡ Treinar habilidades de um aprendiz");
        System.out.println("5. 📊 Relatório estatístico da academia");
        System.out.println("6. 🚪 Sair da academia");
        System.out.print("\n🎯 Digite sua escolha (1-6): ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        switch (opcao) {
            case 1:
                adicionarNovoAprendiz(scanner);
                break;
            case 2:
                listarTodosAprendizes();
                break;
            case 3:
                buscarAprendizPorNome(scanner);
                break;
            case 4:
                treinarHabilidades(scanner);
                break;
            case 5:
                exibirRelatorioEstatistico();
                break;
            case 6:
                return false;
            default:
                System.out.println("❌ Opção inválida! Tente novamente.");
        }

        return true;
    }

    /**
     * Adiciona um novo aprendiz através de interação com o usuário
     */
    public static void adicionarNovoAprendiz(Scanner scanner) {
        System.out.println("\n🆕 CADASTRO DE NOVO APRENDIZ");
        System.out.println("=" + "=".repeat(40));

        System.out.print("🧙 Nome do aprendiz: ");
        String nome = scanner.nextLine();

        System.out.print("🎂 Idade do aprendiz: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        // Validação de idade mínima
        if (idade < IDADE_MINIMA) {
            System.out.println("⚠️ Idade mínima para treinamento é " + IDADE_MINIMA + " anos!");
            System.out.println("💡 Ajustando idade para o mínimo permitido...");
            idade = IDADE_MINIMA;
        }

        System.out.print("🏰 Escola de origem (ou 'Nova' para iniciantes): ");
        String escola = scanner.nextLine();

        System.out.print("⭐ Nível inicial de habilidade (1-" + NIVEL_MAXIMO + "): ");
        int nivel = scanner.nextInt();
        scanner.nextLine(); // Consumir quebra de linha

        // Validação de nível
        if (nivel < 1) {
            nivel = 1;
            System.out.println("💡 Nível ajustado para 1 (mínimo)");
        } else if (nivel > NIVEL_MAXIMO) {
            nivel = NIVEL_MAXIMO;
            System.out.println("💡 Nível ajustado para " + NIVEL_MAXIMO + " (máximo)");
        }

        // Criar novo aprendiz e adicionar à lista
        ExemploCompletoTerminado novoAprendiz = new ExemploCompletoTerminado(nome, nivel, escola, idade);
        todosAprendizes.add(novoAprendiz);

        System.out.println("\n✅ Aprendiz cadastrado com sucesso!");
        System.out.println("🎉 " + nome + " agora faz parte da Academia Kaer Morhen!");

        // Exibir dados do novo aprendiz
        novoAprendiz.apresentarse();
    }

    /**
     * Lista todos os aprendizes cadastrados
     */
    public static void listarTodosAprendizes() {
        System.out.println("\n📋 LISTA DE TODOS OS APRENDIZES");
        System.out.println("=" + "=".repeat(50));

        if (todosAprendizes.isEmpty()) {
            System.out.println("🚫 Nenhum aprendiz cadastrado ainda.");
            return;
        }

        for (int i = 0; i < todosAprendizes.size(); i++) {
            System.out.println("\n👤 APRENDIZ #" + (i + 1));
            System.out.println("─".repeat(30));
            todosAprendizes.get(i).apresentarse();
        }

        System.out.println("\n📊 Total de aprendizes: " + todosAprendizes.size());
    }

    /**
     * Busca um aprendiz específico por nome
     */
    public static void buscarAprendizPorNome(Scanner scanner) {
        System.out.println("\n🔍 BUSCAR APRENDIZ POR NOME");
        System.out.println("=" + "=".repeat(40));

        System.out.print("🎯 Digite o nome para buscar: ");
        String nomeBusca = scanner.nextLine().toLowerCase();

        boolean encontrado = false;
        for (cap01.ExemploCompletoTerminado aprendiz : todosAprendizes) {
            if (aprendiz.getNomeAprendiz().toLowerCase().contains(nomeBusca)) {
                System.out.println("\n✅ APRENDIZ ENCONTRADO:");
                System.out.println("─".repeat(30));
                aprendiz.apresentarse();
                aprendiz.demonstrarHabilidades();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("❌ Nenhum aprendiz encontrado com o nome: " + nomeBusca);
        }
    }

    /**
     * Sistema de treinamento para aumentar habilidades
     */
    public static void treinarHabilidades(Scanner scanner) {
        System.out.println("\n⚡ SISTEMA DE TREINAMENTO");
        System.out.println("=" + "=".repeat(40));

        if (todosAprendizes.isEmpty()) {
            System.out.println("🚫 Nenhum aprendiz disponível para treinamento.");
            return;
        }

        // Listar aprendizes disponíveis
        System.out.println("📋 Aprendizes disponíveis:");
        for (int i = 0; i < todosAprendizes.size(); i++) {
            cap01.ExemploCompletoTerminado aprendiz = todosAprendizes.get(i);
            System.out.println((i + 1) + ". " + aprendiz.getNomeAprendiz() +
                    " (Nível " + aprendiz.getNivelHabilidade() + ")");
        }

        System.out.print("\n🎯 Escolha um aprendiz (número): ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir quebra de linha

        if (indice >= 0 && indice < todosAprendizes.size()) {
            cap01.ExemploCompletoTerminado aprendiz = todosAprendizes.get(indice);

            System.out.print("💪 Quantos pontos de experiência adicionar? ");
            int pontos = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            int nivelAnterior = aprendiz.getNivelHabilidade();
            int novoNivel = Math.min(nivelAnterior + pontos, NIVEL_MAXIMO); // Retorna o nível máximo permitido caso ultrapasse

            aprendiz.setNivelHabilidade(novoNivel);

            System.out.println("\n🎉 TREINAMENTO CONCLUÍDO!");
            System.out.println("📈 " + aprendiz.getNomeAprendiz() +
                    " evoluiu do nível " + nivelAnterior +
                    " para o nível " + novoNivel + "!");

            aprendiz.demonstrarHabilidades();
        } else {
            System.out.println("❌ Índice inválido!");
        }
    }

    /**
     * Exibe relatório estatístico da academia
     */
    public static void exibirRelatorioEstatistico() {
        System.out.println("\n📊 RELATÓRIO ESTATÍSTICO DA ACADEMIA");
        System.out.println("=" + "=".repeat(50));

        if (todosAprendizes.isEmpty()) {
            System.out.println("🚫 Nenhum dado disponível para análise.");
            return;
        }

        int totalAprendizes = todosAprendizes.size();
        int somaIdes = 0;
        int somaNiveis = 0;
        int nivelMaiorTodos = 0;
        String aprendizMaisExperiente = "";

        for (cap01.ExemploCompletoTerminado aprendiz : todosAprendizes) {
            somaIdes += aprendiz.getIdade();
            somaNiveis += aprendiz.getNivelHabilidade();

            if (aprendiz.getNivelHabilidade() > nivelMaiorTodos) {
                nivelMaiorTodos = aprendiz.getNivelHabilidade();
                aprendizMaisExperiente = aprendiz.getNomeAprendiz();
            }
        }

        double idadeMedia = (double) somaIdes / totalAprendizes;
        double nivelMedio = (double) somaNiveis / totalAprendizes;

        System.out.println("👥 Total de aprendizes: " + totalAprendizes);
        System.out.println("🎂 Idade média: " + String.format("%.1f", idadeMedia) + " anos");
        System.out.println("⭐ Nível médio: " + String.format("%.1f", nivelMedio));
        System.out.println("🏆 Aprendiz mais experiente: " + aprendizMaisExperiente +
                " (Nível " + nivelMaiorTodos + ")");
        System.out.println("🏰 Data do relatório: " + LocalDateTime.now());
    }

    // 10. MÉTODOS DE INSTÂNCIA
    /**
     * Método que apresenta o aprendiz
     */
    public void apresentarse() {
        System.out.println("🏰 Escola: " + ESCOLA);
        System.out.println("🧙 Nome: " + nomeAprendiz);
        System.out.println("⭐ Nível: " + nivelHabilidade);
        System.out.println("🎂 Idade: " + idade + " anos");
        System.out.println("🏫 Origem: " + escolaOrigem);
        System.out.println("📅 Data atual: " + LocalDateTime.now());
    }

    /**
     * Demonstra diferentes habilidades baseadas no nível
     */
    public void demonstrarHabilidades() {
        System.out.println("\n🗡️ Habilidades Disponíveis:");

        if (nivelHabilidade >= 1) {
            System.out.println("  ✅ Igni (Fogo básico)");
        }
        if (nivelHabilidade >= 20) {
            System.out.println("  ✅ Quen (Escudo protetor)");
        }
        if (nivelHabilidade >= 40) {
            System.out.println("  ✅ Aard (Onda de choque)");
        }
        if (nivelHabilidade >= 60) {
            System.out.println("  ✅ Axii (Controle mental)");
        }
        if (nivelHabilidade >= 80) {
            System.out.println("  ✅ Yrden (Armadilha mágica)");
        }

        // Mostrar progresso até próxima habilidade
        if (nivelHabilidade < 80) {
            int proximoMilestone = 20;
            while (proximoMilestone <= nivelHabilidade) {
                proximoMilestone += 20;
            }
            int pontosRestantes = proximoMilestone - nivelHabilidade;
            System.out.println("  🎯 Próxima habilidade em " + pontosRestantes + " níveis");
        }
    }

    /**
     * Exemplo de interação básica com o usuário
     */
    public void interagirComUsuario() {
        Scanner entrada = new Scanner(System.in);

        System.out.print("\n🤔 Qual sinal você gostaria de aprender primeiro? ");
        String resposta = entrada.nextLine();

        System.out.println("💡 Excelente escolha! " + resposta +
                " é um sinal poderoso para iniciantes.");

        entrada.close();
    }

    // 11. MÉTODOS GETTERS E SETTERS (Acessores)
    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nome) {
        this.nomeAprendiz = nome;
    }

    public int getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(int nivel) {
        if (nivel >= 1 && nivel <= NIVEL_MAXIMO) {
            this.nivelHabilidade = nivel;
        }
    }

    public String getEscolaOrigem() {
        return escolaOrigem;
    }

    public void setEscolaOrigem(String escola) {
        this.escolaOrigem = escola;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade >= IDADE_MINIMA) {
            this.idade = idade;
        }
    }
}
