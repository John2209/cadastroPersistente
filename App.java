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
        // Cria a interface usada pelo sistema
        IMenu mn = IMenu.criar();
        // Cria o serviço de leitura e gravação
        ISalvamento arq = ISalvamento.criar();
        // Guarda o nome do arquivo informado
        String nomeArquivo;

        // Define as opções do primeiro menu
        String[] itensMenuInicial = {"1 - Criar um novo cadastro", "2 - Importar um cadastro", "3 - Sair"};
        // Armazena a opção escolhida no menu inicial
        int opcaoInicial = 0;
        // Guarda o cadastro carregado ou criado
        CadastroAlunos ca = null;

        do {
            // Exibe o menu inicial e lê a escolha
            opcaoInicial = mn.criarMenu(itensMenuInicial);

            // Executa a ação da opção escolhida
            switch (opcaoInicial) {
                case 1:
                    // Cria um cadastro com armazenamento dinâmico
                    ca = new CadastroAlunos(mn);  // Armazenamento com lista

                    // Armazenamento fixo
                    //ca = new CadastroAlunos(lerQuantidadeAlunos(mn), mn); // Armazenamento com array
                    break;
                case 2:
                    // Pede o nome do arquivo ao usuário
                    nomeArquivo = mn.lerEntrada("nome do arquivo");
                    // Lê o objeto salvo no arquivo.
                    Object obj = arq.lerObj(nomeArquivo);
                    // Confirma se o objeto lido é um cadastro válido
                    if (obj instanceof CadastroAlunos) {
                        // Recupera o cadastro salvo
                        ca = (CadastroAlunos) obj;
                        // Reassocia o menu após a desserialização
                        ca.setMenu(mn);
                        // Informa sucesso no carregamento
                        mn.exibirMensagem("Cadastro carregado com sucesso!\n");
                    } else {
                        // Informa falha no carregamento
                        mn.exibirMensagem("Nao foi possivel carregar o arquivo.\n");
                    }
                    break;
                case 3:
                    // Encerra a aplicação
                    System.exit(0);
            }
        // Repete até existir um cadastro carregado ou criado
        } while (ca == null);

        // Define as opções do menu principal
        String[] itensMenuPrograma = {"1 - Inserir", "2 - Remover", "3 - Listar", "4 - Atualizar dados de aluno", "5 - Salvar", "6 - Sair"};
        // Armazena a opção escolhida
        int opcao = 0;

        do {
            // Exibe o menu principal e lê a escolha
            opcao = mn.criarMenu(itensMenuPrograma);

            // Executa a ação escolhida
            switch (opcao) {
                case 1:
                    // Tenta inserir um novo aluno
                    if (ca.inserirAluno()) {
                        // Informa sucesso na inserção
                        mn.exibirMensagem("Aluno inserido com sucesso!\n");
                    }
                    break;
                case 2:
                    // Tenta remover um aluno existente
                    if (ca.removerAluno()) {
                        // Informa sucesso na remoção
                        mn.exibirMensagem("Aluno removido com sucesso!\n");
                    }
                    break;
                case 3:
                    // Mostra os alunos cadastrados
                    ca.listarAluno();
                    break;
                case 4:
                    // Tenta atualizar os dados do aluno
                    if (ca.atualizarAluno()) {
                        // Informa sucesso na atualização
                        mn.exibirMensagem("Aluno atualizado com sucesso!\n");
                    }
                    break;
                case 5:
                    // Pede o nome do arquivo para salvar
                    nomeArquivo = mn.lerEntrada("nome do arquivo");
                    // Salva o cadastro no arquivo
                    arq.gravarObj(ca, nomeArquivo);
                    break;
            }
        // Repete enquanto a opção for válida e diferente de sair
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
        // Repete até receber um número válido
        while (true) {
            // Solicita a quantidade de alunos
            String entrada = mn.lerEntrada("Forneca a quantidade de alunos: ");

            try {
                // Converte a entrada para inteiro
                int qtde = Integer.parseInt(entrada);

                // Retorna se a quantidade for positiva
                if (qtde > 0) {
                    return qtde;
                }
            } catch (NumberFormatException e) {
                // Ignora erro e pede novamente
            }

            // Informa que a entrada é inválida
            mn.exibirMensagem("Informe uma quantidade valida de alunos.\n");
        }
    }
}
