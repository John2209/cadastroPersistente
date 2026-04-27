package alunos;
import java.io.Serializable;
/**
 * Classe para armazenar os dados de um aluno.
 * Herda nome e idade da classe Pessoa.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class Aluno extends Pessoa implements Serializable {
    // Guarda o RA do aluno
    private String ra;
    // Guarda o curso do aluno
    private String curso;
    // Guarda o semestre do aluno
    private int semestre;

    /**
     * Construtor da classe Aluno.
     *
     * @param nome nome do aluno
     * @param idade idade do aluno
     * @param ra RA do aluno
     * @param curso curso do aluno
     * @param semestre semestre do aluno
     */
    public Aluno(String nome, int idade, String ra, String curso, int semestre) {
        // Inicializa os dados herdados de pessoa
        super(nome, idade);
        // Define o RA do aluno
        this.ra = ra;
        // Define o curso do aluno
        this.curso = curso;
        // Define o semestre do aluno
        this.semestre = semestre;
    }

    /**
     * Retorna o nome do aluno em formato bibliográfico
     *
     * @return nome bibliográfico do aluno
     */
    public String getNomeBiblio() {
        // Reaproveita a conversão bibliográfica da classe base
        return super.getNomeBiblio();
    }

    /**
     * Retorna o RA do aluno
     *
     * @return RA do aluno
     */
    public String getRa() {
        // Retorna o RA atual
        return this.ra;
    }

    /**
     * Retorna o curso do aluno
     *
     * @return curso do aluno
     */
    public String getCurso() {
        // Retorna o curso atual
        return this.curso;
    }

    /**
     * Retorna o semestre do aluno
     *
     * @return semestre do aluno
     */
    public int getSemestre() {
        // Retorna o semestre atual
        return this.semestre;
    }

    /**
     * Atualiza o RA aluno
     *
     * @param ra novo RA do aluno
     */
    public void setRa(String ra) {
        // Atualiza o RA
        this.ra = ra;
    }

    /**
     * Atualiza o curso do aluno
     *
     * @param curso novo curso do aluno
     */
    public void setCurso(String curso) {
        // Atualiza o curso
        this.curso = curso;
    }

    /**
     * Atualiza o semestre do aluno
     *
     * @param semestre novo semestre do aluno
     */
    public void setSemestre(int semestre) {
        // Atualiza o semestre
        this.semestre = semestre;
    }

    /**
     * Retorna os dados do aluno em formato textual
     *
     * @return representação textual do aluno
     */
    public String toString() {
        // Monta a representação padrão do aluno
        return super.toString() + "\nRA: " + ra + "\nCurso: " + curso + "\nSemestre: " + semestre;
    }

    /**
     * Retorna os dados do aluno com nome em formato bibliográfico
     *
     * @return representação do aluno em formato bibliográfico
     */
    public String toStringBiblio() {
        // Monta a representação com nome bibliográfico
        return getNomeBiblio() + "\nIdade: " + getIdade() + "\nRA: " + getRa() + "\nCurso: " + getCurso() + "\nSemestre: " + getSemestre();
    }
}
