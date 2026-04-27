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
    // Guarda os alunos em posições fixas
    private Aluno[] arm;

    /**
     * Construtor do armazenador.
     *
     * @param qtde quantidade máxima de alunos suportada
     */
    ArmazenadorArray(int qtde) {
        // Cria o vetor com a capacidade informada
        this.arm = new Aluno[qtde];
    }

    /**
     * Retorna o aluno armazenado em uma determinada posição.
     *
     * @param pos posição desejada
     * @return aluno armazenado na posição informada
     */
    public Aluno getAluno(int pos) {
        // Retorna o aluno da posição informada
        return this.arm[pos];
    }

    /**
     * Insere um aluno na primeira posição disponível.
     *
     * @param a objeto aluno a ser inserido
     * @return true quando a inserção for realizada com sucesso
     */
    public boolean inserir(Aluno a) {
        // Guarda a capacidade total do vetor
        int qtde = this.arm.length;
        // Indica se a inserção foi concluída
        boolean inserido = false;
        // Controla a posição atual da busca
        int cont = 0;

        while (!inserido && cont < qtde) {
            // Insere na primeira posição vazia
            if (arm[cont] == null) {
                arm[cont] = a;
                inserido = true;
            } else {
                // Avança para a próxima posição
                cont++;
            }
        }

        // Retorna o resultado da inserção
        return inserido;
    }

    /**
     * Remove um aluno a partir do RA informado.
     *
     * @param ra registro acadêmico do aluno
     * @return true quando a remoção for realizada com sucesso
     */
    public boolean remover(String ra) {
        // Guarda a capacidade total do vetor
        int qtde = this.arm.length;
        // Indica se a remoção foi concluída
        boolean removido = false;
        // Controla a posição atual da busca
        int cont = 0;

        while (!removido && cont < qtde) {
            // Remove quando encontrar o RA informado
            if (arm[cont] != null && arm[cont].getRa().equals(ra)) {
                arm[cont] = null;
                removido = true;
            } else {
                // Avança para a próxima posição
                cont++;
            }
        }
        // Retorna o resultado da remoção
        return removido;
    }

    /**
     * Busca um aluno a partir do RA informado.
     *
     * @param ra registro acadêmico do aluno
     * @return aluno encontrado ou null quando não existir
     */
    public Aluno buscar(String ra) {
        // Guarda a capacidade total do vetor
        int qtde = this.arm.length;
        // Percorre todas as posições do vetor
        for (int cont = 0; cont < qtde; cont++) {
            // Retorna o aluno quando o RA coincidir
            if (arm[cont] != null && arm[cont].getRa().equals(ra)) {
                return arm[cont];
            }
        }
        // Retorna nulo se não encontrar o RA
        return null;
    }

    /**
     * Retorna a quantidade de alunos armazenados.
     *
     * @return quantidade de alunos armazenados
     */
    public int getQtde() {
        // Retorna a capacidade total do vetor
        return arm.length;
    }

    /**
     * Verifica se o armazenamento está cheio.
     *
     * @return true se estiver cheio, false caso contrário
     */
    public boolean estaCheio() {
        // Percorre todas as posições do vetor
        for (int i = 0; i < arm.length; i++) {
            // Ainda há espaço se encontrar posição vazia
            if (arm[i] == null) {
                return false;
            }
        }
        // Retorna verdadeiro se não houver espaço livre
        return true;
    }
}
