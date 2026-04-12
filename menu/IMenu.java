package menu;
/**
 * Define os serviços de entrada e saída da aplicação.
 * Responsável por iniciar a interface e controlar o menu
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public interface IMenu {
    /**
     * Cria o menu usada pela aplicação.
     * Para trocar a interface, basta alterar a linha de retorno.
     *
     * @return implementação de menu escolhida
     */
    static IMenu criar() {  //altera entre a interface gráfica e o terminal manualmente
        return new MenuGrafico();
        //return new MenuTexto();
    }

    /**
     * Exibe o menu principal e retorna a opção escolhida pelo usuário.
     *
     * @param opcoes vetor com as opções que serão exibidas no menu
     * @return opção selecionada pelo usuário
     */
    int criarMenu(String opcoes[]);

    /**
     * Solicita uma entrada textual ao usuário.
     *
     * @param mensagem texto apresentado ao usuário
     * @return valor informado
     */
    String lerEntrada(String mensagem);

    /**
     * Exibe uma mensagem ao usuário.
     *
     * @param mensagem texto a ser apresentado
     */
    void exibirMensagem(String mensagem);
}
