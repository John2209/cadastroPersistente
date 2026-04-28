import menu.*;
import armazenamento.*;
import alunos.*;
/**
 * Coordena as operações do CadastroAlunos
 * Contém as operações principais do CadastroAluno
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class CadastroAlunos implements java.io.Serializable{
    // Guarda a estrutura usada para armazenar os alunos
    private IArmazenador arm;
    // Guarda a interface usada para interação
    private transient IMenu menu;

    /**
     * Construtor da classe.
     *
     * @param qtde capacidade máxima do armazenamento de alunos
     * @param menu implementação de entrada e saída utilizada pela aplicação
     */
    CadastroAlunos(int qtde, IMenu menu) {
        // Cria o armazenamento com capacidade fixa
        this.arm = IArmazenador.criar(qtde);
        // Guarda a interface recebida
        this.menu = menu;
    }

    /**
     * Construtor da classe CadastroAlunos.
     * Inicializa o armazenamento padrão para alunos e associa o menu de entrada e saída.
     *
     * @param menu instância de IMenu que implementa os serviços de entrada e saída do sistema
     */
    CadastroAlunos(IMenu menu) {
        // Cria o armazenamento padrão
        this.arm = IArmazenador.criar();
        // Guarda a interface recebida
        this.menu = menu;
    }

    /**
     * Realiza o cadastro de um novo aluno.
     *
     * @return true quando a inserção for realizada com sucesso
     */
    public boolean inserirAluno() {
        // Verifica se o armazenamento atingiu o limite
        boolean cheio = arm.estaCheio();

        // Impede inserção quando não há espaço
        if (cheio) {
            this.menu.exibirMensagem("Capacidade máxima atingida\n");
            return false;
        }

        // Guarda o nome
        String nome = "";

        while (nome.equals("")) {
            // Solicita o nome do aluno
            String entrada = this.menu.lerEntrada("Nome: ");

            // Remove espaços extras da entrada
            if (entrada != null) {
                nome = entrada.trim();
            }

            // Exige um nome não vazio
            if (nome.equals("")) {
                this.menu.exibirMensagem("Informe um nome valido\n");
            }
        }

        // Guarda a idade informada
        int idade = 0;

        while (idade < 1 || idade > 120) {
            // Solicita a idade do aluno
            String entrada = this.menu.lerEntrada("Idade: ");

            try {
                // Converte a idade para número
                idade = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                // Marca como inválida para repetir
                idade = 0;
            }

            // Exige idade dentro da faixa esperada
            if (idade < 1 || idade > 120) {
                this.menu.exibirMensagem("Informe uma idade entre 1 e 120\n");
            }
        }

        // Guarda o RA informado
        String ra = "";

        while (ra.equals("")) {
            // Solicita o RA do aluno
            String entrada = this.menu.lerEntrada("RA: ");

            // Remove espaços extras da entrada
            if (entrada != null) {
                ra = entrada.trim();
            }

            // Exige um RA não vazio
            if (ra.equals("")) {
                this.menu.exibirMensagem("Informe um valor valido\n");
            }
        }

        // Impede cadastro com RA repetido
        if (arm.buscar(ra) != null) {
            this.menu.exibirMensagem("Ja existe um aluno cadastrado com esse RA\n");
            return false;
        }

        // Guarda o curso informado
        String curso = "";

        while (curso.equals("")) {
            // Solicita o curso do aluno
            String entrada = this.menu.lerEntrada("Curso: ");

            // Remove espaços extras da entrada
            if (entrada != null) {
                curso = entrada.trim();
            }

            // Exige um curso não vazio
            if (curso.equals("")) {
                this.menu.exibirMensagem("Informe um valor valido\n");
            }
        }

        // Guarda o semestre informado
        int semestre = 0;

        while (semestre < 1 || semestre > 13) {
            // Solicita o semestre do aluno
            String entrada = this.menu.lerEntrada("Semestre: ");

            try {
                // Converte o semestre para número
                semestre = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                // Marca como inválido para repetir
                semestre = 0;
            }

            // Exige semestre válido
            if (semestre < 1 || semestre > 13) {
                this.menu.exibirMensagem("Informe um semestre valido [1-12]\n");
            }
        }

        // Cria o objeto aluno com os dados informados
        Aluno a = new Aluno(nome, idade, ra, curso, semestre);

        // Insere o aluno no armazenamento
        return arm.inserir(a);
    }

    /**
     * Remove um aluno a partir do RA informado.
     *
     * @return true quando a remoção for realizada com sucesso
     */
    public boolean removerAluno() {
        // Guarda o RA digitado
        String ra = "";

        while (ra.equals("")) {
            // Solicita o RA do aluno a remover
            String entrada = this.menu.lerEntrada("Digite o RA do aluno a ser removido: ");

            // Remove espaços extras da entrada
            if (entrada != null) {
                ra = entrada.trim();
            }

            // Exige um RA não vazio
            if (ra.equals("")) {
                this.menu.exibirMensagem("Informe um RA valido\n");
            }
        }

        // Confirma se o aluno existe antes de remover
        if (arm.buscar(ra) == null) {
            this.menu.exibirMensagem("Nao existe aluno com o RA informado\n");
            return false;
        }

        // Remove o aluno pelo RA
        return arm.remover(ra);
    }

    /**
     * Lista todos os alunos cadastrados.
     * Permite escolher entre exibição comum e bibliográfica.
     *
     */
    public void listarAluno() {
        // Obtém a quantidade de posições ou itens armazenados
        int qtde = this.arm.getQtde();
        // Indica se existe pelo menos um aluno cadastrado
        boolean temAluno = false;

        // Procura algum aluno válido no armazenamento
        for (int i = 0; i < qtde; i++) {
            if (arm.getAluno(i) != null) {
                temAluno = true;
            }
        }

        // Encerra se não houver alunos
        if (!temAluno) {
            this.menu.exibirMensagem("Nao ha alunos cadastrados\n");
            return;
        }

        // Guarda a escolha do formato de exibição
        String opcao = "";

        while (!opcao.equals("S") && !opcao.equals("N")) {
            // Pergunta se o nome deve sair em formato bibliográfico
            String entrada = this.menu.lerEntrada("Exibir nome bibliografico? (S/N): ");

            // Normaliza a resposta digitada
            if (entrada != null) {
                opcao = entrada.trim().toUpperCase();
            }

            // Exige apenas S ou N
            if (!opcao.equals("S") && !opcao.equals("N")) {
                this.menu.exibirMensagem("Informe apenas S ou N\n");
            }
        }

        // Monta o texto final da listagem
        String saida = "";
        // Conta apenas os alunos existentes
        int contador = 1;

        // Percorre todas as posições do armazenamento
        for (int i = 0; i < qtde; i++) {
            // Lê o aluno da posição atual
            Aluno a = arm.getAluno(i);

            // Ignora posições vazias
            if (a != null) {
                // Adiciona uma separação entre alunos
                if (!saida.equals("")) {
                    saida = saida + "\n\n";
                }

                // Monta a saída conforme o formato escolhido
                if (opcao.equals("S")) {
                    saida = saida + "Aluno " + contador + ": " + a.toStringBiblio();
                } else {
                    saida = saida + "Aluno " + contador + ": " + a.toString();
                }

                // Avança a numeração da listagem
                contador++;
            }
        }

        // Exibe a listagem montada
        this.menu.exibirMensagem(saida);
    }

    /**
     * Atualiza os dados de um aluno já cadastrado.
     *
     * @return true quando a atualização for realizada com sucesso
     */
public boolean atualizarAluno() {
        String ra = "";

        // Busca do aluno com proteção contra o botão "Cancelar"
        while (ra.equals("")) {
            String entrada = this.menu.lerEntrada("Digite o RA do aluno: ");
            
            // SEGREDO PARA NÃO TRAVAR O PROGRAMA:
            if (entrada == null) {
                return false; // Usuário clicou em cancelar ou fechou a janela. Aborta a operação.
            }
            
            ra = entrada.trim();
            if (ra.equals("")) {
                this.menu.exibirMensagem("Informe um valor valido.\n");
            }
        }

        Aluno a = arm.buscar(ra);

        if (a == null) {
            this.menu.exibirMensagem("Nao existe aluno cadastrado com o RA informado.\n");
            return false;
        }

        // --- ABA DE OPÇÕES ---
        String[] opcoesCampos = {
            "Alterar Nome", 
            "Alterar Idade", 
            "Alterar Curso", 
            "Alterar Semestre", 
            "Voltar"
        };
        
        int escolha = 0;

        do {
            escolha = this.menu.criarMenu(opcoesCampos);
            boolean atualizado = false; // Bandeira para controlar a mensagem de sucesso

            switch (escolha) {
                case 1: // Nome
                    String novoNome = "";
                    while (novoNome.equals("")) {
                        String entrada = this.menu.lerEntrada("Digite o novo nome:");
                        if (entrada == null) break; // Escapa se cancelar
                        novoNome = entrada.trim();
                        if (novoNome.equals("")) this.menu.exibirMensagem("Nome invalido!");
                    }
                    if (!novoNome.equals("")) { // Só atualiza se realmente preencheu
                        a.setNome(novoNome);
                        atualizado = true;
                    }
                    break;

                case 2: // Idade
                    int novaIdade = 0;
                    while (novaIdade < 1 || novaIdade > 120) {
                        String entrada = this.menu.lerEntrada("Digite a nova idade:");
                        if (entrada == null) break; // Escapa se cancelar
                        try {
                            novaIdade = Integer.parseInt(entrada);
                            if (novaIdade < 1 || novaIdade > 120) {
                                this.menu.exibirMensagem("Informe uma idade valida entre 1 e 120\n");
                            } else {
                                a.setIdade(novaIdade);
                                atualizado = true;
                            }
                        } catch (Exception e) {
                            this.menu.exibirMensagem("Idade invalida! Digite apenas numeros.");
                            novaIdade = 0; // Mantém no loop
                        }
                    }
                    break;

                case 3: // Curso
                    String novoCurso = "";
                    while (novoCurso.equals("")) {
                        String entrada = this.menu.lerEntrada("Digite o novo curso:");
                        if (entrada == null) break; // Escapa se cancelar
                        novoCurso = entrada.trim();
                        if (novoCurso.equals("")) this.menu.exibirMensagem("Curso invalido!");
                    }
                    if (!novoCurso.equals("")) { // Só atualiza se realmente preencheu
                        a.setCurso(novoCurso);
                        atualizado = true;
                    }
                    break;

                case 4: // Semestre
                    int novoSemestre = 0;
                    while (novoSemestre < 1 || novoSemestre > 13) {
                        String entrada = this.menu.lerEntrada("Digite o novo semestre:");
                        if (entrada == null) break; // Escapa se cancelar
                        try {
                            novoSemestre = Integer.parseInt(entrada);
                            if (novoSemestre < 1 || novoSemestre > 13) {
                                this.menu.exibirMensagem("Informe um semestre valido [1-12]\n");
                            } else {
                                a.setSemestre(novoSemestre);
                                atualizado = true;
                            }
                        } catch (Exception e) {
                            this.menu.exibirMensagem("Semestre invalido! Digite apenas numeros.");
                            novoSemestre = 0; // Mantém no loop
                        }
                    }
                    break;
            }
            
            // Só avisa que deu certo se uma das validações passou
            if (atualizado) {
                this.menu.exibirMensagem("Dado atualizado com sucesso!\n\nDados atuais:\n" + a.toString());
            }

        } while (escolha != 5 && escolha != -1);

        return true;
    }

    /**
     * Retorna o armazenamento de alunos.
     * @return
     */
    public IArmazenador getArm() {
        // Retorna o armazenamento atual
        return this.arm;
    }

    /**
     * Define o menu utilizado pela aplicação.
     *
     * @param menu instância de IMenu que implementa os serviços de entrada e saída do sistema
     */
    public void setMenu(IMenu menu) {
        // Atualiza a interface usada pelo cadastro
        this.menu = menu;
    }
}
