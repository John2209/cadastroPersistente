package menu;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Menu gráfico com janela única que atualiza seu conteúdo sem "piscar".
 * Utiliza o Java Swing para desenhar a interface, e bloqueios de thread 
 * (CountDownLatch) para pausar a execução enquanto aguarda o clique do usuário.
 */
public class SuperMenu implements IMenu {

    // Guarda a janela principal da aplicação
    private JFrame frame;
    // Guarda o painel onde os botões e textos são colocados
    private JPanel panel;

    /**
     * Construtor padrão do SuperMenu.
     * Prepara e exibe a janela principal da aplicação.
     */
    public SuperMenu() {
        /*
         * SwingUtilities.invokeLater garante que a interface gráfica 
         * seja montada na thread correta (Event Dispatch Thread), 
         * evitando travamentos.
         */
        SwingUtilities.invokeLater(() -> {
            try {
                // Tenta deixar o visual da janela parecido com o do sistema operacional (Windows, etc)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {}

            // Cria e configura a janela principal
            frame = new JFrame("Sistema de Cadastro de Alunos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela
            
            // Cria o painel que vai receber os componentes e o adiciona à janela
            panel = new JPanel();
            frame.add(panel);
            frame.setVisible(true);
        });
    }

    /**
     * Monta o menu principal exibindo botões e realiza a leitura do clique.
     *
     * @param opcoes vetor com as opções de texto dos botões
     * @return opção escolhida pelo usuário
     */
    @Override
    public int criarMenu(String[] opcoes) {
        // CountDownLatch funciona como um cadeado que trava o código até o usuário clicar em uma opção
        CountDownLatch latch = new CountDownLatch(1);
        
        // AtomicInteger guarda o valor da escolha do usuário de forma segura entre as threads
        AtomicInteger escolha = new AtomicInteger(opcoes.length);

        SwingUtilities.invokeLater(() -> {
            // Limpa qualquer coisa que já estivesse na tela antes
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Cria e adiciona o título do menu no topo (NORTH)
            JLabel titulo = new JLabel("Selecione uma ação abaixo:", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(titulo, BorderLayout.NORTH);

            // Cria um painel central para organizar os botões verticalmente
            JPanel botoesPanel = new JPanel();
            botoesPanel.setLayout(new GridLayout(opcoes.length, 1, 10, 10));
            botoesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

            // Cria um botão para cada opção passada no vetor
            for (int i = 0; i < opcoes.length; i++) {
                JButton btn = new JButton(opcoes[i]);
                btn.setFont(new Font("Arial", Font.PLAIN, 14));
                final int index = i + 1; // As opções retornadas para o sistema começam do 1 (1, 2, 3...)
                
                // O que acontece quando clica no botão:
                btn.addActionListener(e -> {
                    escolha.set(index); // Salva a opção escolhida
                    latch.countDown();  // Destrava o cadeado para o código continuar rodando
                });
                botoesPanel.add(btn);
            }

            // Adiciona a lista de botões com rolagem, se houver muitos
            panel.add(new JScrollPane(botoesPanel), BorderLayout.CENTER);
            
            // Atualiza o visual da tela
            panel.revalidate();
            panel.repaint();
        });

        try {
            // Trava o programa aqui até o usuário clicar (latch.countDown() ser chamado)
            latch.await();
        } catch (InterruptedException e) {}

        // Retorna o número da opção que o usuário escolheu
        return escolha.get();
    }

    /**
     * Monta uma tela solicitando que o usuário digite algum texto.
     *
     * @param mensagem texto exibido acima da caixa de digitação
     * @return texto digitado ou null caso tenha cancelado
     */
    @Override
    public String lerEntrada(String mensagem) {
        // Cadeado para travar o código até digitar
        CountDownLatch latch = new CountDownLatch(1);
        
        // AtomicReference permite salvar o texto inserido de forma segura
        AtomicReference<String> entrada = new AtomicReference<>(null);

        SwingUtilities.invokeLater(() -> {
            // Prepara a tela limpando-a
            panel.removeAll();
            panel.setLayout(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            // Coloca a instrução
            JLabel lblMsg = new JLabel(mensagem, SwingConstants.CENTER);
            lblMsg.setFont(new Font("Arial", Font.BOLD, 14));
            panel.add(lblMsg, BorderLayout.NORTH);

            // Cria o campo onde o usuário vai digitar
            JTextField textField = new JTextField();
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            
            // Limita o tamanho do campo de digitação na tela
            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.add(textField, BorderLayout.NORTH);
            panel.add(centerPanel, BorderLayout.CENTER);

            // Cria e organiza os botões OK e Cancelar
            JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            JButton btnOk = new JButton("OK");
            JButton btnCancelar = new JButton("Cancelar");

            // Evento do botão OK
            btnOk.addActionListener(e -> {
                entrada.set(textField.getText());
                latch.countDown();
            });

            // Evento do botão Cancelar
            btnCancelar.addActionListener(e -> {
                entrada.set(null); // Retorna nulo, o programa deve interpretar como cancelamento
                latch.countDown();
            });

            // Permite submeter também apertando a tecla "Enter" no teclado
            textField.addActionListener(e -> {
                entrada.set(textField.getText());
                latch.countDown();
            });

            // Coloca os botões no fundo (SOUTH) da tela
            botoesPanel.add(btnOk);
            botoesPanel.add(btnCancelar);
            panel.add(botoesPanel, BorderLayout.SOUTH);

            // Refaz a tela para mostrar tudo o que foi adicionado
            panel.revalidate();
            panel.repaint();
            
            // Deixa o cursor já piscando na caixa de texto para facilidade do usuário
            textField.requestFocusInWindow();
        });

        try {
            // Espera até que o OK, Cancelar ou Enter destravem o cadeado
            latch.await();
        } catch (InterruptedException e) {}

        // Devolve a resposta (ou nulo)
        return entrada.get();
    }

    /**
     * Exibe uma tela para avisos, contendo texto grande e um botão de fechar.
     *
     * @param mensagem texto longo a ser exibido para o usuário
     */
    @Override
    public void exibirMensagem(String mensagem) {
        // Cadeado
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            // Prepara a tela
            panel.removeAll();
            panel.setLayout(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // JTextArea cria uma grande área de texto. É melhor para textos grandes (como listagem de alunos)
            JTextArea textArea = new JTextArea(mensagem);
            textArea.setEditable(false); // Impede o usuário de apagar o texto sem querer
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            
            // Coloca uma barra de rolagem no texto caso fique maior que a janela
            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane, BorderLayout.CENTER);

            // Botão OK para simplesmente dispensar a mensagem
            JButton btnOk = new JButton("OK");
            btnOk.addActionListener(e -> latch.countDown());
            
            // Posiciona o botão no final
            JPanel botPanel = new JPanel();
            botPanel.add(btnOk);
            panel.add(botPanel, BorderLayout.SOUTH);

            // Mostra os componentes novos
            panel.revalidate();
            panel.repaint();
            
            // Já foca no botão OK para que ao apertar "Enter", a mensagem seja fechada rápido
            btnOk.requestFocusInWindow();
        });

        try {
            // Aguarda fechar
            latch.await();
        } catch (InterruptedException e) {}
    }
}