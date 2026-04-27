package salvamento;
/**
 * Escreva uma descrição da classe ISalvamento aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public interface ISalvamento {


    static ISalvamento criar() {
        // Retorna a implementação de salvamento ativa
        return new ArquivoBinario();
    }
    
    // Grava um objeto em arquivo
    public void gravarObj(Object objeto, String nomeArq);
    
    // Lê um objeto de arquivo
    public Object lerObj(String nomeArq);
}
