package alunos;
import java.io.Serializable;
/**
 * Armazena um texto e disponibiliza operações simples de manipulação.
 *
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public class Texto implements Serializable{
    // Guarda o texto informado
    private String txt;

    /**
     * Construtor da classe Texto
     * O texto é armazenado já com os espaços excedentes removidos.
     *
     * @param txt texto que será armazenado
     */
    Texto(String txt) {
        // Salva o texto recebido
        setTxt(txt);
        // Remove espaços excedentes
        limpaEspacosExcedentes();
    }

    /**
     * Atualiza o texto armazenado
     *
     * @param t novo texto
     */
    private void setTxt(String t) {
        // Atualiza o conteúdo interno
        this.txt = t;
    }

    /**
     * Retorna o texto armazenado
     *
     * @return texto armazenado
     */
    public String getTxt() {
        // Retorna o texto atual
        return this.txt;
    }

    /**
     * Retorna o texto invertido
     *
     * @return texto invertido
     */
    public String inverterTexto() {
        // Guarda o texto invertido
        String txtInvertido = "";

        // Só inverte quando houver texto válido
        if (!(this.txt == null || this.txt.equals(""))) {
            // Percorre o texto do fim para o começo
            for (int i = this.txt.length() - 1; i >= 0; i--) {
                // Acumula os caracteres invertidos
                txtInvertido = txtInvertido + this.txt.charAt(i);
            }
        } else {
            // Retorna nulo para texto vazio
            return null;
        }

        // Retorna o texto invertido
        return txtInvertido;
    }

    /**
     * Retorna a quantidade de palavras do texto
     *
     * @return quantidade de palavras
     */
    public int getQtdePalavras() {
        // Conta as palavras separadas por espaço
        return getTxt().split(" ").length;
    }

    /**
     * Remove espaços excedentes do texto armazenado
     */
    private void limpaEspacosExcedentes() {
        // Remove espaços no início e no fim
        setTxt(this.txt.trim());

        // Monta o texto sem repetições de espaço
        String s = "";

        // Percorre todos os caracteres do texto
        for (int i = 0; i < this.txt.length(); i++) {
            // Copia o caractere atual
            s = s + this.txt.charAt(i);

            // Verifica se o caractere atual é espaço
            if (this.txt.charAt(i) == ' ') {
                // Pula os espaços repetidos seguintes
                while (this.txt.charAt(i + 1) == ' ') {
                    i++;
                }
            }
        }

        // Salva o texto já tratado
        setTxt(s);
    }

    /**
     * Retorna o texto armazenado
     *
     * @return representação textual do objeto
     */
    public String toString() {
        // Retorna o próprio texto
        return getTxt();
    }
}
