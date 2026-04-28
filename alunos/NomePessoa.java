package alunos;
import java.io.Serializable;
/**
 * Classe para armazenar o nome de uma pessoa.
 * Também disponibiliza o nome em formato bibliográfico.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class NomePessoa implements Serializable{
    // Guarda o nome em um objeto de texto
    private Texto nome;

    /**
     * Construtor da classe NomePessoa
     *
     * @param nome nome da pessoa
     */
    public NomePessoa(String nome) {
        // Inicializa o nome recebido
        setNome(nome);
    }

    /**
     * Atualiza o nome armazenado
     *
     * @param nome novo nome
     */
    protected void setNome(String nome) {
        // Cria um novo objeto de texto para o nome
        this.nome = new Texto(nome);
    }

    /**
     * Retorna o nome armazenado
     *
     * @return nome da pessoa
     */
    public String getNome() {
        // Retorna o texto do nome
        return this.nome.getTxt();
    }

    /**
     * Retorna a quantidade de palavras do nome
     *
     * @return quantidade de palavras
     */
    public int getQtdePalavras() {
        // Retorna quantas palavras existem no nome
        return this.nome.getQtdePalavras();
    }

    /**
     * Retorna o nome invertido
     *
     * @return nome invertido
     */
    public String getNomeInvertido() {
        // Retorna o nome com caracteres invertidos
        return this.nome.inverterTexto();
    }

    /**
     * Retorna o nome em formato bibliográfico
     *
     * @return nome bibliográfico
     */
    public String getNomeBiblio() {
        // Separa o nome em palavras
        String[] vts = this.nome.getTxt().split(" ");
        // Guarda a quantidade de palavras
        int qtd = vts.length;
        // Começa pelo último sobrenome
        String sBib = vts[qtd - 1] + ", ";

        // Percorre as palavras anteriores
        for (int i = 0; i < (qtd - 1); i++) {
            // Normaliza a palavra para comparação
            String pal = vts[i].toLowerCase();

            // Ignora conectivos comuns do nome
            if (!verificaStr(pal)) {
                // Adiciona apenas a inicial da palavra
                sBib = sBib + vts[i].toUpperCase().charAt(0) + ". ";
            }
        }

        // Retorna o nome no formato bibliográfico
        return sBib;
    }

    /**
     * Verifica se a palavra deve ser desconsiderada na forma bibliográfica
     *
     * @param s palavra a ser verificada
     * @return true quando a palavra deve ser ignorada
     */
    private boolean verificaStr(String s) {
        // Lista de palavras ignoradas na abreviação.
        final String[] sRet = {"da", "de", "do", "di", "das", "dos", "e"};

        // Procura a palavra na lista de exclusão
        for (String string : sRet) {
            if (string.equals(s)) {
                return true;
            }
        }

        // Retorna falso se a palavra não estiver na lista
        return false;
    }

    /**
     * Retorna o nome em formato textual
     *
     * @return representação textual do nome
     */
    public String toString() {
        // Monta a representação textual do nome
        return "Nome: " + this.nome.toString();
    }
}
