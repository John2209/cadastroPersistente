package menu;
import javax.swing.JOptionPane;

/**
 * Implementa as entradas e saídas do cadstroAluno em modo gráfico.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class MenuGrafico implements IMenu {

    /**
     * Monta o menu principal e realiza a leitura.
     *
     * @param opcoes vetor com as opções do menu
     * @return opção escolhida pelo usuário
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
            // Lê a opção digitada na janela
            String entrada = lerEntrada(itens);

            // Trata cancelamento como saída
            if (entrada == null) {
                return opcoes.length;
            }

            try {
                // Converte a entrada para número
                int opcao = Integer.parseInt(entrada);

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
     * Solicita uma entrada ao usuário pela janela.
     *
     * @param mensagem texto apresentado ao usuário
     * @return valor informado
     */
    public String lerEntrada(String mensagem) {
        // Abre uma janela para leitura de texto
        return JOptionPane.showInputDialog(mensagem);
    }

    /**
     * Exibe uma mensagem ao usuário pela janela.
     *
     * @param mensagem texto a ser exibido
     */
    public void exibirMensagem(String mensagem) {
        // Exibe a mensagem em uma janela
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
