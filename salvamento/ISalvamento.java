package salvamento;
/**
 * Escreva uma descrição da classe ISalvamento aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public interface ISalvamento {


    static ISalvamento criar() {
        return new ArquivoBinario();
    }
    
    public void gravarObj(Object objeto, String nomeArq);
    
    public Object lerObj(String nomeArq);
}
