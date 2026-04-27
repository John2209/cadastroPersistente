package armazenamento;
import alunos.*;
import java.io.Serializable;
/**
 * Implementa o armazenamento dos alunos em um vetor.
 * Disponibiliza operações de inserção, remoção e busca.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class ArmazenadorArray implements IArmazenador, Serializable {
    private Aluno[] arm;

    /**
     * Construtor do armazenador.
     *
     * @param qtde quantidade máxima de alunos suportada
     */
    ArmazenadorArray(int qtde) {
        this.arm = new Aluno[qtde];
    }

    /**
     * Retorna o aluno armazenado em uma determinada posição.
     *
     * @param pos posição desejada
     * @return aluno armazenado na posição informada
     */
    public Aluno getAluno(int pos) {
        return this.arm[pos];
    }

    /**
     * Insere um aluno na primeira posição disponível.
     *
     * @param a objeto aluno a ser inserido
     * @return true quando a inserção for realizada com sucesso
     */
    public boolean inserir(Aluno a) {
        int qtde = this.arm.length;
        boolean inserido = false;
        int cont = 0;

        while (!inserido && cont < qtde) {  //Enquanto nao tiver terminado a insercao e a quantidade for menor que a capacidade maxima, fica em loop
            if (arm[cont] == null) {
                arm[cont] = a;
                inserido = true;
            } else {
                cont++;
            }
        }

        return inserido;
    }

    /**
     * Remove um aluno a partir do RA informado.
     *
     * @param ra registro acadêmico do aluno
     * @return true quando a remoção for realizada com sucesso
     */
    public boolean remover(String ra) {
        int qtde = this.arm.length;
        boolean removido = false;
        int cont = 0;

        while (!removido && cont < qtde) {  //Enquanto nao tiver terminado a insercao e a quantidade for menor que a capacidade maxima, fica em loop
            if (arm[cont] != null && arm[cont].getRa().equals(ra)) {
                arm[cont] = null;
                removido = true;
            } else {
                cont++;
            }
        }
        return removido;
    }

    /**
     * Busca um aluno a partir do RA informado.
     *
     * @param ra registro acadêmico do aluno
     * @return aluno encontrado ou null quando não existir
     */
    public Aluno buscar(String ra) {
        int qtde = this.arm.length;
        for (int cont = 0; cont < qtde; cont++) {
            if (arm[cont] != null && arm[cont].getRa().equals(ra)) {
                return arm[cont];
            }
        }
        return null;
    }

    /**
     * Retorna a quantidade de alunos armazenados.
     *
     * @return quantidade de alunos armazenados
     */
    public int getQtde() {
        return arm.length;
    }

    /**
     * Verifica se o armazenamento está cheio.
     *
     * @return true se estiver cheio, false caso contrário
     */
    public boolean estaCheio() {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] == null) {
                return false;
            }
        }
        return true;
    }
}
