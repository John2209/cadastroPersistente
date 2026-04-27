package armazenamento;
import alunos.*;

/**
 * Define as operações de armazenamento utilizadas pelo cadastro de alunos.
 * Permite substituir a estrutura de dados sem alterar o restante da aplicação.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public interface IArmazenador {
    /**
     * Cria a implementação de armazenamento usada pelo cadastroAluno.
     * Para trocar o armazenamento, basta alterar a linha de retorno.
     *
     * @param qtde capacidade máxima do armazenamento
     * @return implementação de armazenamento escolhida
     */
    static IArmazenador criar(int qtde) {
        // Retorna a implementação com Lista
        return new ArmazenadorLista();

        // Tamanho fixo
        //return new ArmazenadorArray(qtde);
    }

    /**
     * Cria uma implementação de armazenamento padrão para o cadastro de alunos.
     * Atualmente, utiliza a implementação baseada em uma lista.
     *
     * @return uma instância de IArmazenador utilizando a implementação ArmazenadorLista
     */
    static IArmazenador criar() {
        // Retorna o armazenamento padrão
        return new ArmazenadorLista();
    }

    /**
     * Retorna o aluno armazenado em uma determinada posição.
     *
     * @param pos posição desejada
     * @return aluno armazenado na posição
     */
    Aluno getAluno(int pos);

    /**
     * Insere um aluno no armazenamento.
     *
     * @param a objeto aluno a ser inserido
     * @return true quando a inserção for realizada com sucesso
     */
    boolean inserir(Aluno a);

    /**
     * Remove um aluno do armazenamento.
     *
     * @param ra RA do aluno
     * @return true quando a remoção for realizada com sucesso
     */
    boolean remover(String ra);

    /**
     * Busca um aluno no armazenamento.
     *
     * @param ra RA do aluno
     * @return aluno encontrado ou null quando não existir
     */
    Aluno buscar(String ra);

    /**
     * Retorna a quantidade de alunos armazenados.
     *
     * @return quantidade de alunos armazenados
     */
    int getQtde();

    /**
     * Verifica se o armazenamento está cheio.
     *
     * @return true se estiver cheio, false caso contrário
     */
    boolean estaCheio();
}
