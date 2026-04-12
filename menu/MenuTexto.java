package menu;
import java.util.Scanner;

/**
 * Implementa as entradas e saídas da aplicação em modo texto.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class MenuTexto implements IMenu {
    private Scanner leitor;

    /**
     * Construtor padrão.
     * Inicializa o leitor pelo teclado.
     */
    public MenuTexto() {
        this.leitor = new Scanner(System.in);
    }

    /**
     * Monta o menu principal e realiza a leitura.
     *
     * @param opcoes vetor com as opções do menu
     * @return opção válida escolhida pelo usuário
     */
    public int criarMenu(String opcoes[]) {
        String itens = "";

        for (int i = 0; i < opcoes.length; i++) {
            itens = itens + "\n" + opcoes[i];
        }

        itens = itens + "\n\nSelecione a opcao: ";

        while (true) {
            String s = lerEntrada(itens);

            try {
                int opcao = Integer.parseInt(s);

                if (opcao > 0 && opcao <= opcoes.length) {
                    return opcao;
                }
            } catch (NumberFormatException e) {
            }

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
        System.out.print(mensagem);
        return this.leitor.nextLine();
    }

    /**
     * Exibe uma mensagem no console.
     *
     * @param mensagem texto a ser exibido
     */
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
