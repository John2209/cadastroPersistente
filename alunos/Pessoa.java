package alunos;
/**
 * Classe para armazenar dados básicos de uma pessoa.
 * Utiliza a classe NomePessoa por composição.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class Pessoa {
    private NomePessoa nome;
    private int idade;

    /**
     * Construtor da classe Pessoa
     *
     * @param nome nome da pessoa
     * @param idade idade da pessoa
     */
    Pessoa(String nome, int idade) {
        this.nome = new NomePessoa(nome);
        this.idade = idade;
    }

    /**
     * Atualiza o nome da pessoa
     *
     * @param nome novo nome da pessoa
     */
    public void setNome(String nome) {
        this.nome.setNome(nome);
    }

    /**
     * Atualiza a idade da pessoa
     *
     * @param idade nova idade da pessoa
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * Retorna o nome da pessoa
     *
     * @return nome da pessoa
     */
    public String getNome() {
        return this.nome.getNome();
    }

    /**
     * Retorna a idade da pessoa
     *
     * @return idade da pessoa
     */
    public int getIdade() {
        return this.idade;
    }

    /**
     * Retorna o nome da pessoa em formato bibliográfico
     *
     * @return nome bibliográfico da pessoa
     */
    public String getNomeBiblio() {
        return this.nome.getNomeBiblio();
    }

    /**
     * Retorna os dados da pessoa em formato textual
     *
     * @return representação textual da pessoa
     */
    public String toString() {
        return this.nome.toString() + "\nIdade: " + idade;
    }
}
