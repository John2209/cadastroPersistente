package alunos;
/**
 * Armazena um texto e disponibiliza operações simples de manipulação.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class Texto {
    private String txt;

    /**
     * Construtor da classe Texto
     * O texto é armazenado já com os espaços excedentes removidos.
     *
     * @param txt texto que será armazenado
     */
    Texto(String txt) {
        setTxt(txt);
        limpaEspacosExcedentes();
    }

    /**
     * Atualiza o texto armazenado
     *
     * @param t novo texto
     */
    private void setTxt(String t) {
        this.txt = t;
    }

    /**
     * Retorna o texto armazenado
     *
     * @return texto armazenado
     */
    public String getTxt() {
        return this.txt;
    }

    /**
     * Retorna o texto invertido
     *
     * @return texto invertido
     */
    public String inverterTexto() {
        String txtInvertido = "";

        if (!(this.txt == null || this.txt.equals(""))) {
            for (int i = this.txt.length() - 1; i >= 0; i--) {
                txtInvertido = txtInvertido + this.txt.charAt(i);
            }
        } else {
            return null;
        }

        return txtInvertido;
    }

    /**
     * Retorna a quantidade de palavras do texto
     *
     * @return quantidade de palavras
     */
    public int getQtdePalavras() {
        return getTxt().split(" ").length;
    }

    /**
     * Remove espaços excedentes do texto armazenado
     */
    private void limpaEspacosExcedentes() {
        setTxt(this.txt.trim());

        String s = "";

        for (int i = 0; i < this.txt.length(); i++) {
            s = s + this.txt.charAt(i);

            if (this.txt.charAt(i) == ' ') {
                while (this.txt.charAt(i + 1) == ' ') {
                    i++;
                }
            }
        }

        setTxt(s);
    }

    /**
     * Retorna o texto armazenado
     *
     * @return representação textual do objeto
     */
    public String toString() {
        return getTxt();
    }
}
