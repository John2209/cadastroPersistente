import menu.*;
import Arquivo.*;
/**
 * Classe principal do cadastroAluno
 * Responsável por iniciar a interface e controlar o menu
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class App {
    /**
     * Inicia a aplicação e mantém o laço principal do menu.
     *
     * @param args argumentos recebidos pela linha de comando
     */
    public static void main(String[] args) {
        IMenu mn = IMenu.criar();
        int qtde = lerQuantidadeAlunos(mn);
        ISalvamento arq = ISalvamento.criar();
        String nomeArquivo;

    
        CadastroAlunos ca = new CadastroAlunos(qtde, mn);
        String[] itensMenu = {"1 - inserir", "2 - remover", "3 - listar", "4 - atualizar dados", "5 - Salvar", "6 - Acessar arquivo salvo", "7 - Sair"};

        int opcao = 0;

        do {
            opcao = mn.criarMenu(itensMenu);

            switch(opcao) { //switch de opcoes do menu
                case 1:
                    if (ca.inserirAluno(qtde)) {
                        mn.exibirMensagem("Aluno inserido com sucesso!!\n");
                    }
                    break;
                case 2:
                    if (ca.removerAluno(qtde)) {
                        mn.exibirMensagem("Aluno removido com sucesso!!\n");
                    }
                    break;
                case 3:
                    ca.listarAluno(qtde);
                    break;
                case 4:
                    if (ca.atualizarAluno(qtde)) {
                        mn.exibirMensagem("Aluno atualizado com sucesso!!\n");
                    }
                    break;
                case 5:
                    nomeArquivo = mn.lerEntrada("nome do arquivo");
                    arq.gravarObj(ca,nomeArquivo);
                    break;
                case 6:
                    nomeArquivo = mn.lerEntrada("nome do arquivo");
                    Object obj = arq.lerObj(nomeArquivo);
                    if (obj instanceof CadastroAlunos) {
                        ca = (CadastroAlunos) obj;
                        mn.exibirMensagem("Arquivo carregado com sucesso!\n");
                    }
                    break;

                    
            }
        } while(opcao > 0 && opcao < 7);    //se a opcao for diferente de 1, 2, 3, 4,5,6 sai do programa
    }

    /**
     * Solicita a capacidade máxima do cadastro de alunos.
     *
     * @param mn implementação de entrada e saída utilizada pela aplicação
     * @return quantidade máxima de alunos que poderão ser cadastrados
     */
    private static int lerQuantidadeAlunos(IMenu mn) {
        while (true) {
            String entrada = mn.lerEntrada("Forneca a quantidade de alunos: ");

            try {
                int qtde = Integer.parseInt(entrada);

                if (qtde > 0) {
                    return qtde;
                }
            } catch (NumberFormatException e) {
            }

            mn.exibirMensagem("Informe uma quantidade valida de alunos.\n");
        }
    }
}
