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
public class CadastroAlunos {
    private IArmazenador arm;
    private IMenu menu;

    /**
     * Construtor da classe.
     *
     * @param qtde capacidade máxima do armazenamento de alunos
     * @param menu implementação de entrada e saída utilizada pela aplicação
     */
    CadastroAlunos(int qtde, IMenu menu) {
        this.arm = IArmazenador.criar(qtde);
        this.menu = menu;
    }

    /**
     * Realiza o cadastro de um novo aluno.
     *
     * @param qtde capacidade máxima do cadastro
     * @return true quando a inserção for realizada com sucesso
     */
    public boolean inserirAluno(int qtde) {
        boolean cheio = true;

        for (int i = 0; i < qtde; i++) {
            if (arm.getAluno(i) == null) {
                cheio = false;
            }
        }

        if (cheio) {
            this.menu.exibirMensagem("Capacidade máxima atingida\n");
            return false;
        }

        String nome = "";

        while (nome.equals("")) {   //enquanto nao for inserido o nome, fica em loop  
            String entrada = this.menu.lerEntrada("Nome: ");

            if (entrada != null) {
                nome = entrada.trim();
            }

            if (nome.equals("")) {
                this.menu.exibirMensagem("Informe um nome valido\n");
            }
        }

        int idade = 0;

        while (idade < 1 || idade > 120) {  //Repete o menu enquanto a idade for invalida
            String entrada = this.menu.lerEntrada("Idade: ");

            try {
                idade = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                idade = 0;
            }

            if (idade < 1 || idade > 120) {
                this.menu.exibirMensagem("Informe uma idade entre 1 e 120\n");
            }
        }

        String ra = "";

        while (ra.equals("")) { //Enquanto RA nao for inserido, fica em loop
            String entrada = this.menu.lerEntrada("RA: ");

            if (entrada != null) {
                ra = entrada.trim();
            }

            if (ra.equals("")) {
                this.menu.exibirMensagem("Informe um valor valido\n");
            }
        }

        if (arm.buscar(ra, qtde) != null) {
            this.menu.exibirMensagem("Ja existe um aluno cadastrado com esse RA\n");
            return false;
        }

        String curso = "";

        while (curso.equals("")) {  //Enquanto o curso nao for inserido, fica em loop
            String entrada = this.menu.lerEntrada("Curso: ");

            if (entrada != null) {
                curso = entrada.trim();
            }

            if (curso.equals("")) {
                this.menu.exibirMensagem("Informe um valor valido\n");
            }
        }

        int semestre = 0;

        while (semestre < 1 || semestre > 13) {  //Enquanto o semestre nao for digitado, fica em loop
            String entrada = this.menu.lerEntrada("Semestre: ");

            try {
                semestre = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                semestre = 0;
            }

            if (semestre < 1 || semestre > 13) {
                this.menu.exibirMensagem("Informe um semestre valido [1-12]\n");
            }
        }

        Aluno a = new Aluno(nome, idade, ra, curso, semestre);

        return arm.inserir(a, qtde);
    }

    /**
     * Remove um aluno a partir do RA informado.
     *
     * @param qtde capacidade máxima do cadastro
     * @return true quando a remoção for realizada com sucesso
     */
    public boolean removerAluno(int qtde) {
        String ra = "";

        while (ra.equals("")) { //Enquanto o RA nao for digitado, fica em loop
            String entrada = this.menu.lerEntrada("Digite o RA do aluno a ser removido: ");

            if (entrada != null) {
                ra = entrada.trim();
            }

            if (ra.equals("")) {
                this.menu.exibirMensagem("Informe um RA valido\n");
            }
        }

        if (arm.buscar(ra, qtde) == null) {
            this.menu.exibirMensagem("Nao existe aluno com o RA informado\n");
            return false;
        }

        return arm.remover(ra, qtde);
    }

    /**
     * Lista todos os alunos cadastrados.
     * Permite escolher entre exibição comum e bibliográfica.
     *
     * @param qtde capacidade máxima do cadastro
     */
    public void listarAluno(int qtde) {
        boolean temAluno = false;

        for (int i = 0; i < qtde; i++) {
            if (arm.getAluno(i) != null) {
                temAluno = true;
            }
        }

        if (!temAluno) {
            this.menu.exibirMensagem("Nao ha alunos cadastrados\n");
            return;
        }

        String opcao = "";

        while (!opcao.equals("S") && !opcao.equals("N")) {  //Enquanto S ou N nao for digitado, fica em loop
            String entrada = this.menu.lerEntrada("Exibir nome bibliografico? (S/N): ");

            if (entrada != null) {
                opcao = entrada.trim().toUpperCase();
            }

            if (!opcao.equals("S") && !opcao.equals("N")) {
                this.menu.exibirMensagem("Informe apenas S ou N\n");
            }
        }

        String saida = "";
        int contador = 1;

        for (int i = 0; i < qtde; i++) {
            Aluno a = arm.getAluno(i);

            if (a != null) {
                if (!saida.equals("")) {
                    saida = saida + "\n\n";
                }

                if (opcao.equals("S")) {
                    saida = saida + "Aluno " + contador + ": " + a.toStringBiblio();
                } else {
                    saida = saida + "Aluno " + contador + ": " + a.toString();
                }

                contador++;
            }
        }

        this.menu.exibirMensagem(saida);
    }

    /**
     * Atualiza os dados de um aluno já cadastrado.
     *
     * @param qtde capacidade máxima do cadastro
     * @return true quando a atualização for realizada com sucesso
     */
    public boolean atualizarAluno(int qtde) {
        String ra = "";

        while (ra.equals("")) {
            String entrada = this.menu.lerEntrada("Digite o RA do aluno: ");

            if (entrada != null) {
                ra = entrada.trim();
            }

            if (ra.equals("")) {
                this.menu.exibirMensagem("Informe um valor valido.\n");
            }
        }

        Aluno a = arm.buscar(ra, qtde);

        if (a == null) {
            this.menu.exibirMensagem("Nao existe aluno cadastrado com o RA informado.\n");
            return false;
        }

        this.menu.exibirMensagem("Atualize os dados do aluno:");

        String nome = "";

        while (nome.equals("")) {
            String entrada = this.menu.lerEntrada("Nome: ");

            if (entrada != null) {
                nome = entrada.trim();
            }

            if (nome.equals("")) {
                this.menu.exibirMensagem("Informe um nome valido\n");
            }
        }

        a.setNome(nome);

        int idade = 0;

        while (idade < 1 || idade > 120) {  //Verifica idade
            String entrada = this.menu.lerEntrada("Idade: ");

            try {
                idade = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                idade = 0;
            }

            if (idade < 1 || idade > 120) {
                this.menu.exibirMensagem("Informe uma idade valida entre 1 e 120\n");
            }
        }

        a.setIdade(idade);

        String curso = "";

        while (curso.equals("")) {  //Enquanto um curso nao for informado, fica em loop
            String entrada = this.menu.lerEntrada("Curso: ");

            if (entrada != null) {
                curso = entrada.trim();
            }

            if (curso.equals("")) {
                this.menu.exibirMensagem("Informe um curso.\n");
            }
        }

        a.setCurso(curso);

        int semestre = 0;

        while (semestre < 1 || semestre > 13) {
            String entrada = this.menu.lerEntrada("Semestre: ");

            try {
                semestre = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                semestre = 0;
            }

            if (semestre < 1 || semestre > 13) {
                this.menu.exibirMensagem("Informe um semestre valido [1-12]\n");
            }
        }

        a.setSemestre(semestre);

        return true;
    }
}
