package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TelaLivros extends JFrame {

    // Mapa para rastrear a quantidade de livros no carrinho por ID
    private Map<Integer, Integer> livrosNoCarrinho = new HashMap<>();

    private JPanel TelaDeLivros;
    private JTable TabelaDeLivros;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton FinalizarTelaLivros;
    private DefaultTableModel tableModel;

    // Referência para a tela de carrinho
    private TelaCarrinho telaCarrinho;

    // Construtor da classe
    public TelaLivros() {
        super("Tela de Livros");
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuração do modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Título do Livro");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Preço");

        // Configuração da tabela de livros
        TabelaDeLivros = new JTable(tableModel);
        TabelaDeLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Configuração do painel principal
        TelaDeLivros = new JPanel(new BorderLayout());

        // Adição da tabela ao painel principal
        TelaDeLivros.add(new JScrollPane(TabelaDeLivros), BorderLayout.CENTER);

        // Configuração dos botões e adição ao painel principal
        configurarBotoes();
        TelaDeLivros.add(FinalizarTelaLivros, BorderLayout.SOUTH);

        // Carregamento dos livros do banco de dados
        carregarLivrosDoBanco();

        // Configuração da janela principal
        setContentPane(TelaDeLivros);
        setVisible(true);
    }

    // Método para configurar os botões
    private void configurarBotoes() {
        btn1 = new JButton(" Adquirir Livro 1");
        btn2 = new JButton(" Adquirir Livro 2");
        btn3 = new JButton(" Adquirir Livro 3");
        btn4 = new JButton(" Adquirir Livro 4");
        btn5 = new JButton(" Adquirir Livro 5");
        btn6 = new JButton("Adquirir Livro 6");
        btn7 = new JButton("Adquirir Livro 7");

        // Configuração dos eventos dos botões
        configurarBotao(btn1, 1);
        configurarBotao(btn2, 2);
        configurarBotao(btn3, 3);
        configurarBotao(btn4, 4);
        configurarBotao(btn5, 5);
        configurarBotao(btn6, 6);
        configurarBotao(btn7, 7);

        // Configuração do painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btn1);
        painelBotoes.add(btn2);
        painelBotoes.add(btn3);
        painelBotoes.add(btn4);
        painelBotoes.add(btn5);
        painelBotoes.add(btn6);
        painelBotoes.add(btn7);

        // Adição do painel de botões ao painel principal
        TelaDeLivros.add(painelBotoes, BorderLayout.NORTH);

        FinalizarTelaLivros = new JButton("Finalizar");
        FinalizarTelaLivros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCarrinho(); // Adiciona esta linha para chamar a tela de carrinho ao finalizar
            }
        });

        // Adição do botão Finalizar ao painel principal
        JPanel painelFinalizar = new JPanel(new FlowLayout());
        painelFinalizar.add(FinalizarTelaLivros);
        TelaDeLivros.add(painelFinalizar, BorderLayout.SOUTH);
    }

    // Método para configurar o evento do botão
    private void configurarBotao(JButton btn, int idLivro) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarAcao(idLivro);
            }
        });
    }

    // Método para carregar os livros do banco de dados
    private void carregarLivrosDoBanco() {
        String url = "jdbc:mysql://localhost/biblioteca";
        String usuario = "";
        String senha = "";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String query = "SELECT id, Titulo, autor, Preco FROM livro";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Preenchimento do modelo da tabela com os dados do banco
                while (resultSet.next()) {
                    Object[] livro = {
                            resultSet.getInt("id"),
                            resultSet.getString("Titulo"),
                            resultSet.getString("autor"),
                            resultSet.getDouble("Preco")
                    };
                    tableModel.addRow(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Ajuste de largura das colunas
        TableColumnModel columnModel = TabelaDeLivros.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(100);

        // Ajuste de altura das linhas
        TabelaDeLivros.setRowHeight(30); // Ajuste conforme necessário

        setVisible(true);
    }

    // Método para abrir a tela de carrinho
    private void abrirTelaCarrinho() {
        telaCarrinho = new TelaCarrinho();

        // Iteração sobre os livros no carrinho
        for (Map.Entry<Integer, Integer> entry : livrosNoCarrinho.entrySet()) {
            int idLivro = entry.getKey();
            int quantidade = entry.getValue();

            // Obtém informações do livro por ID e adiciona ao carrinho
            String tituloLivro = obterTituloLivroPorID(idLivro);
            String autorLivro = obterAutorLivroPorID(idLivro);
            double precoLivro = obterPrecoLivroPorID(idLivro);

            telaCarrinho.adicionarItemAoCarrinho(idLivro, tituloLivro, autorLivro, precoLivro, quantidade);
        }

    }


    // Método para obter o título do livro por ID
    private String obterTituloLivroPorID(int idLivro) {
        switch (idLivro) {
            case 1:
                return "O Senhor dos Anéis";
            case 2:
                return "1984";
            case 3:
                return "Dom Quixote";
            case 4:
                return "Orgulho e Preconceito";
            case 5:
                return "A Metamorfose";
            case 6:
                return "A Revolução dos Bichos";
            case 7:
                return "Crime e Castigo";
            default:
                return "Título Desconhecido";
        }
    }

    // Método para obter o autor do livro por ID
    private String obterAutorLivroPorID(int idLivro) {
        switch (idLivro) {
            case 1:
                return "J.R.R. Tolkien";
            case 2:
                return "George Orwell";
            case 3:
                return "Miguel de Cervantes";
            case 4:
                return "Jane Austen";
            case 5:
                return "Franz Kafka";
            case 6:
                return "George Orwell";
            case 7:
                return "Fiódor Dostoiévski";
            default:
                return "Autor Desconhecido";
        }
    }

    // Método para obter o preço do livro por ID
    private double obterPrecoLivroPorID(int idLivro) {
        switch (idLivro) {
            case 1:
                return 20.0;
            case 2:
                return 90.0;
            case 3:
                return 100.0;
            case 4:
                return 250.0;
            case 5:
                return 25.0;
            case 6:
                return 80.0;
            case 7:
                return 90.0;
            default:
                return 0.0;
        }
    }

    // Método para registrar ação de adição de livro ao carrinho
    private void registrarAcao(int idLivro) {
        int quantidade = livrosNoCarrinho.getOrDefault(idLivro, 0) + 1;
        livrosNoCarrinho.put(idLivro, quantidade);

        // Exibe uma mensagem informando que o livro foi adicionado ao carrinho
        JOptionPane.showMessageDialog(this, "Livro adicionado ao carrinho: " + obterTituloLivroPorID(idLivro));
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaLivros();
        });
    }
}
