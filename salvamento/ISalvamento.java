package salvamento;
/**
 * Define as operações de persistência utilizadas pela aplicação.
 * Permite salvar e carregar objetos do cadastro sem acoplar o restante do sistema
 * a uma implementação específica de arquivo.
 * 
 * @author Guilherme Pereira de Rivoredo, João Batistella da Costa, Pedro Henrique Freire Pereira, Rafael Barros Infantini
 * @version 1.0 2026/04/07
 */
public interface ISalvamento {


    /**
     * Cria a implementação de salvamento usada pela aplicação.
     *
     * @return implementação de salvamento escolhida
     */
    static ISalvamento criar() {
        // Retorna a implementação de salvamento ativa
        return new ArquivoBinario();
    }
    
    /**
     * Grava um objeto em arquivo.
     *
     * @param objeto objeto a ser gravado
     * @param nomeArq nome do arquivo de destino
     */
    public void gravarObj(Object objeto, String nomeArq);
    
    /**
     * Lê um objeto gravado em arquivo.
     *
     * @param nomeArq nome do arquivo de origem
     * @return objeto lido do arquivo
     */
    public Object lerObj(String nomeArq);
}
