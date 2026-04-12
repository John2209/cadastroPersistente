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
        return new Armazenador(qtde);
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
     * @param qtde capacidade máxima do armazenamento
     * @return true quando a inserção for realizada com sucesso
     */
    boolean inserir(Aluno a, int qtde);

    /**
     * Remove um aluno do armazenamento.
     *
     * @param ra RA do aluno
     * @param qtde capacidade máxima do armazenamento
     * @return true quando a remoção for realizada com sucesso
     */
    boolean remover(String ra, int qtde);

    /**
     * Busca um aluno no armazenamento.
     *
     * @param ra RA do aluno
     * @param qtde capacidade máxima do armazenamento
     * @return aluno encontrado ou null quando não existir
     */
    Aluno buscar(String ra, int qtde);
}
