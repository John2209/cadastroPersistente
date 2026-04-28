package menu;
import javax.swing.JOptionPane;

/**
 * Menu gráfico aprimorado utilizando OptionDialog do Swing.
 * Gera botões clicáveis dinamicamente a partir do vetor de opções.
 */
public class SuperMenu implements IMenu {

    /**
     * Monta uma janela com botões para cada opção do menu.
     *
     * @param opcoes vetor com as opções que serão exibidas como botões
     * @return opção selecionada pelo usuário (ajustada para começar do 1)
     */
    @Override
    public int criarMenu(String[] opcoes) {
        
        // O showOptionDialog cria uma janela e transforma o array 'opcoes' em botões.
        // Ele trava o código aqui até o usuário clicar em algo e retorna o índice do botão (0, 1, 2...).
        int escolha = JOptionPane.showOptionDialog(
                null,                           // Componente pai (null centraliza na tela)
                "Selecione uma ação abaixo:",   // Mensagem exibida na janela
                "Sistema de Cadastro de Alunos",// Título da janela
                JOptionPane.DEFAULT_OPTION,     // Tipo de opção
                JOptionPane.PLAIN_MESSAGE,      // Tipo de mensagem (sem aquele ícone de exclamação)
                null,                           // Ícone customizado (null = padrão)
                opcoes,                         // Array que vai virar os botões!
                opcoes[0]                       // Qual botão fica selecionado por padrão
        );

        // Se o usuário fechar a janela no 'X', o Swing retorna -1.
        // Nesse caso, assumimos a última opção (que geralmente é o "Sair").
        if (escolha == -1) {
            return opcoes.length; 
        }

        // O OptionDialog retorna 0 para o primeiro botão, 1 para o segundo...
        // Como o seu switch-case no App.java espera 1, 2, 3..., nós somamos 1.
        return escolha + 1;
    }

    /**
     * Solicita uma entrada textual ao usuário.
     */
    @Override
    public String lerEntrada(String mensagem) {
        // Exibe uma caixa de entrada com um título mais agradável
        return JOptionPane.showInputDialog(
                null, 
                mensagem, 
                "Entrada de Dados", 
                JOptionPane.QUESTION_MESSAGE
        );
    }

    /**
     * Exibe uma mensagem ao usuário.
     */
    @Override
    public void exibirMensagem(String mensagem) {
        // Exibe uma caixa de informação com um título
        JOptionPane.showMessageDialog(
                null, 
                mensagem, 
                "Aviso do Sistema", 
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}