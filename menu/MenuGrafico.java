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
        String itens = "";

        for (int i = 0; i < opcoes.length; i++) {
            itens = itens + "\n" + opcoes[i];
        }

        itens = itens + "\n\nSelecione a opcao: ";

        while (true) {
            String entrada = lerEntrada(itens);

            if (entrada == null) {
                return opcoes.length;
            }

            try {
                int opcao = Integer.parseInt(entrada);

                if (opcao > 0 && opcao <= opcoes.length) {
                    return opcao;
                }
            } catch (NumberFormatException e) {
            }

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
        return JOptionPane.showInputDialog(mensagem);
    }

    /**
     * Exibe uma mensagem ao usuário pela janela.
     *
     * @param mensagem texto a ser exibido
     */
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
