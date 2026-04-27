package armazenamento;
import alunos.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implementa o armazenamento dos alunos em uma lista.
 * Disponibiliza operações de inserção, remoção e busca.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class ArmazenadorLista implements IArmazenador, Serializable {
    private ArrayList<Aluno> arm;


    ArmazenadorLista() {
        this.arm = new ArrayList<Aluno>();
    }

    /**
     * Retorna o aluno armazenado em uma determinada posição.
     *
     * @param pos posição do aluno na lista de armazenamento
     * @return o aluno armazenado na posição especificada
     */
    public Aluno getAluno(int pos) {
        return this.arm.get(pos);
    }

    /**
     * Insere um aluno na lista de armazenamento.
     *
     * @param a o objeto Aluno a ser inserido
     * @return true se o aluno foi inserido com sucesso, false caso contrário
     */
    public boolean inserir(Aluno a) {
        return this.arm.add(a);
    }

    /**
     * Remove um aluno do armazenamento a partir do registro acadêmico (RA) informado.
     *
     * @param ra o registro acadêmico do aluno a ser removido
     * @return true se o aluno foi removido com sucesso, false caso contrário
     */
    public boolean remover(String ra) {
        for (Aluno a : this.arm) {
            if (a != null && a.getRa().equals(ra)) {
                this.arm.remove(a);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca e retorna um aluno armazenado com base no registro acadêmico (RA) informado.
     *
     * @param ra o registro acadêmico do aluno a ser buscado
     * @return o aluno correspondente ao RA informado, ou null se não for encontrado
     */
    public Aluno buscar(String ra) {
        for (Aluno a : this.arm) {
            if (a != null && a.getRa().equals(ra)) {
                return a;
            }
        }
        return null;
    }



    /**
     * Retorna a quantidade de alunos armazenados.
     *
     * @return a quantidade de alunos armazenados
     */
    public int getQtde() {
        return this.arm.size();
    }

    /**
     * Verifica se o armazenamento está cheio.
     *
     * @return true se estiver cheio, false caso contrário
     */
    public boolean estaCheio() {
        return false;
    }
}