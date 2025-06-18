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

// 4. COMENTÁRIO DA CLASSE
/**
 * Esta classe demonstra a estrutura completa de um arquivo Java,
 * incluindo todos os elementos fundamentais que um desenvolvedor
 * deve conhecer.
 */

// 5. DECLARAÇÃO DA CLASSE PRINCIPAL
public class ExemploCompleto {

    // 6. ATRIBUTOS/CAMPOS DA CLASSE (Variáveis de instância)
    private String nomeAprendiz;
    private int nivelHabilidade;

    // 7. CONSTANTES (Variáveis estáticas finais)
    public static final String ESCOLA = "Kaer Morhen";
    public static final int IDADE_MINIMA = 16;

    // 8. CONSTRUTOR(ES)
    /**
     * Construtor padrão - inicializa um novo aprendiz
     */
    public ExemploCompleto() {
        this.nomeAprendiz = "Aprendiz Desconhecido";
        this.nivelHabilidade = 1;
    }

    /**
     * Construtor com parâmetros - inicializa aprendiz com dados específicos
     */
    public ExemploCompleto(String nome, int nivel) {
        this.nomeAprendiz = nome;
        this.nivelHabilidade = nivel;
    }

    // 9. MÉTODO PRINCIPAL (Ponto de entrada da aplicação)
    /**
     * Método main - onde a execução do programa começa
     */
    public static void main(String[] args) {
        // Criando instância da classe
        ExemploCompleto aprendiz = new ExemploCompleto("Geralt", 99);

        // Chamando métodos da instância
        aprendiz.apresentarse();
        aprendiz.demonstrarHabilidades();

        // Exemplo de interação com usuário
        aprendiz.interagirComUsuario();
    }

    // 10. MÉTODOS DE INSTÂNCIA
    /**
     * Método que apresenta o aprendiz
     */
    public void apresentarse() {
        System.out.println("🏰 Escola: " + ESCOLA);
        System.out.println("🧙 Nome: " + nomeAprendiz);
        System.out.println("⭐ Nível: " + nivelHabilidade);
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
        if (nivel >= 1 && nivel <= 100) {
            this.nivelHabilidade = nivel;
        }
    }
}
