package salvamento;
import java.io.*;
/**
 * @author julio
 * Classe para gravar e ler arquivo binario
 */
public class ArquivoBinario implements ISalvamento {

    /**
     * Method gravarObj grava um objeto em arquivo
     *
     * @param objeto objeto a ser gravado
     * @param nomeArq nome do arquivo
     */
    public void gravarObj(Object objeto, String nomeArq) {
        // Declara o fluxo de escrita
        ObjectOutputStream output = null;
        try {
            // Cria a referência do arquivo
            File file = new File(nomeArq);
            // Abre o fluxo de escrita binária
            output = new ObjectOutputStream(new FileOutputStream(file));
            // Escreve o objeto no arquivo.
            output.writeObject(objeto);  // escreve o objeto no arquivo
        } catch(Exception e) {
            // Exibe o erro ocorrido
            System.out.println(e.toString());
        } finally {
            try {
                // Fecha o fluxo se ele foi aberto
                output.close();
            } catch(Exception ex) {
                // Não faz nada
            }
        }
    }

    /**
     * Method lerObj
     *
     * @param nomeArq nome do arquivo a ser lido
     * @return Object o objeto lido
     */
    public Object lerObj(String nomeArq) {
        // Guarda o objeto lido.
        Object objeto = null;
        // Declara o fluxo de leitura.
        ObjectInputStream input = null;
        try {
            // Cria a referência do arquivo.
            File file = new File(nomeArq);
            // Abre o fluxo de leitura binária.
            input = new ObjectInputStream(new FileInputStream(file));
            // Lê o objeto armazenado.
            objeto = (Object)input.readObject();  // le o objeto do arquivo
        }
        catch(Exception e) {
            // Exibe o erro ocorrido.
            System.out.println(e.toString());
        } finally {
            try {
                // Fecha o fluxo se ele foi aberto.
                input.close();
            } catch(Exception ex) {
                // Nao faz nada!
            }
        }
        // Retorna o objeto lido.
        return objeto;
    }
}
