package menu;
import java.util.Scanner;

/**
 * Implementa as entradas e saídas da aplicação em modo texto.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class MenuTexto implements IMenu {
    // Lê entradas digitadas no console.
    private Scanner leitor;

    /**
     * Construtor padrão.
     * Inicializa o leitor pelo teclado.
     */
    public MenuTexto() {
        // Inicializa o leitor com a entrada padrão
        this.leitor = new Scanner(System.in);
    }

    /**
     * Monta o menu principal e realiza a leitura.
     *
     * @param opcoes vetor com as opções do menu
     * @return opção válida escolhida pelo usuário
     */
    public int criarMenu(String opcoes[]) {
        // Monta o texto do menu
        String itens = "";

        // Adiciona cada opção ao texto exibido
        for (int i = 0; i < opcoes.length; i++) {
            itens = itens + "\n" + opcoes[i];
        }

        // Adiciona a mensagem final de seleção
        itens = itens + "\n\nSelecione a opcao: ";

        // Repete até receber uma opção válida
        while (true) {
            // Lê a opção digitada
            String s = lerEntrada(itens);

            try {
                // Converte a opção para número
                int opcao = Integer.parseInt(s);

                // Retorna se a opção estiver no intervalo válido
                if (opcao > 0 && opcao <= opcoes.length) {
                    return opcao;
                }
            } catch (NumberFormatException e) {
                // Ignora erro e pede novamente
            }

            // Informa que a opção é inválida
            exibirMensagem("Opcao invalida");
        }
    }

    /**
     * Solicita uma entrada textual ao usuário.
     *
     * @param mensagem texto apresentado antes da leitura
     * @return valor informado pelo usuário
     */
    public String lerEntrada(String mensagem) {
        // Mostra a mensagem sem quebrar linha
        System.out.print(mensagem);
        // Lê a linha digitada
        return this.leitor.nextLine();
    }

    /**
     * Exibe uma mensagem no console.
     *
     * @param mensagem texto a ser exibido
     */
    public void exibirMensagem(String mensagem) {
        // Exibe a mensagem no console
        System.out.println(mensagem);
    }
}
