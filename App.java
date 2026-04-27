import menu.*;
import salvamento.*;
/**
 * Classe principal do cadastroAluno
 * Responsável por iniciar a interface e controlar o menu
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */

public class App {
    /**
     * Ponto de entrada da aplicação.
     * Gerencia a interação com o usuário para criar, importar, manipular e salvar cadastros de alunos.
     *
     * @param args argumentos recebidos pela linha de comando (não utilizados na lógica da aplicação)
     */
    public static void main(String[] args) {
        IMenu mn = IMenu.criar();
        ISalvamento arq = ISalvamento.criar();
        String nomeArquivo;

        String[] itensMenuInicial = {"1 - Criar um novo cadastro", "2 - Importar um cadastro", "3 - Sair"};
        int opcaoInicial = 0;
        CadastroAlunos ca = null;

        do {        // Menu inicial
            opcaoInicial = mn.criarMenu(itensMenuInicial);

            switch (opcaoInicial) {
                case 1:
                    ca = new CadastroAlunos(mn);  // Armazenamento com lista
                    //ca = new CadastroAlunos(lerQuantidadeAlunos(mn), mn); // Armazenamento com array
                    break;
                case 2:
                    nomeArquivo = mn.lerEntrada("nome do arquivo");
                    Object obj = arq.lerObj(nomeArquivo);
                    if (obj instanceof CadastroAlunos) {
                        ca = (CadastroAlunos) obj;
                        ca.setMenu(mn);
                        mn.exibirMensagem("Cadastro carregado com sucesso!\n");
                    } else {
                        mn.exibirMensagem("Nao foi possivel carregar o arquivo.\n");
                    }
                    break;
                case 3:
                    System.exit(0);
            }
        } while (ca == null);

        String[] itensMenuPrograma = {"1 - Inserir", "2 - Remover", "3 - Listar", "4 - Atualizar dados de aluno", "5 - Salvar", "6 - Sair"};
        int opcao = 0;

        do {        // Menu de opções
            opcao = mn.criarMenu(itensMenuPrograma);

            switch (opcao) {
                case 1:
                    if (ca.inserirAluno()) {
                        mn.exibirMensagem("Aluno inserido com sucesso!\n");
                    }
                    break;
                case 2:
                    if (ca.removerAluno()) {
                        mn.exibirMensagem("Aluno removido com sucesso!\n");
                    }
                    break;
                case 3:
                    ca.listarAluno();
                    break;
                case 4:
                    if (ca.atualizarAluno()) {
                        mn.exibirMensagem("Aluno atualizado com sucesso!\n");
                    }
                    break;
                case 5:
                    nomeArquivo = mn.lerEntrada("nome do arquivo");
                    arq.gravarObj(ca, nomeArquivo);
                    break;
            }
        } while (opcao > 0 && opcao < 6);
    }


    /**
     * Lê a quantidade de alunos a partir da entrada do usuário.
     * Repete a solicitação enquanto a entrada não for um número inteiro positivo.
     *
     * @param mn instância de IMenu utilizada para interagir com o usuário através da exibição de mensagens
     *           e a recepção de entradas de texto.
     * @return um valor inteiro maior que zero representando a quantidade de alunos informada pelo usuário.
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