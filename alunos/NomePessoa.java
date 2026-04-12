package alunos;
/**
 * Classe para armazenar o nome de uma pessoa.
 * Também disponibiliza o nome em formato bibliográfico.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class NomePessoa {
    private Texto nome;

    /**
     * Construtor da classe NomePessoa
     *
     * @param nome nome da pessoa
     */
    public NomePessoa(String nome) {
        setNome(nome);
    }

    /**
     * Atualiza o nome armazenado
     *
     * @param nome novo nome
     */
    protected void setNome(String nome) {
        this.nome = new Texto(nome);
    }

    /**
     * Retorna o nome armazenado
     *
     * @return nome da pessoa
     */
    public String getNome() {
        return this.nome.getTxt();
    }

    /**
     * Retorna a quantidade de palavras do nome
     *
     * @return quantidade de palavras
     */
    public int getQtdePalavras() {
        return this.nome.getQtdePalavras();
    }

    /**
     * Retorna o nome invertido
     *
     * @return nome invertido
     */
    public String getNomeInvertido() {
        return this.nome.inverterTexto();
    }

    /**
     * Retorna o nome em formato bibliográfico
     *
     * @return nome bibliográfico
     */
    public String getNomeBiblio() {
        String[] vts = this.nome.getTxt().split(" ");
        int qtd = vts.length;
        String sBib = vts[qtd - 1] + ", ";

        for (int i = 0; i < (qtd - 1); i++) {
            String pal = vts[i].toLowerCase();

            if (!verificaStr(pal)) {
                sBib = sBib + vts[i].toUpperCase().charAt(0) + ". ";
            }
        }

        return sBib;
    }

    /**
     * Verifica se a palavra deve ser desconsiderada na forma bibliográfica
     *
     * @param s palavra a ser verificada
     * @return true quando a palavra deve ser ignorada
     */
    private boolean verificaStr(String s) {
        final String[] sRet = {"da", "de", "do", "di", "das", "dos", "e"};

        for (String string : sRet) {
            if (string.equals(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retorna o nome em formato textual
     *
     * @return representação textual do nome
     */
    public String toString() {
        return "Nome: " + this.nome.toString();
    }
}
