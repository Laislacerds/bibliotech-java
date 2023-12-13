package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaCarrinho extends JFrame {
    private JTable TabelaCarrinho;
    private JButton FinalizarCarrinho;
    private JPanel PainelCarrinho;
    private JLabel rotuloTotal;
    private JLabel rotuloHora;
    private DefaultTableModel tableModel;

    public TelaCarrinho() {
        // Inicializa o modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Título do Livro");
        tableModel.addColumn("Preço Unitário");
        tableModel.addColumn("Quantidade");

        TabelaCarrinho = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(TabelaCarrinho);

        PainelCarrinho = new JPanel(new BorderLayout());
        PainelCarrinho.add(scrollPane, BorderLayout.CENTER);

        // Adiciona rótulos para exibir total e hora atual
        rotuloTotal = new JLabel("Total: ");
        rotuloHora = new JLabel("Hora: ");

        JPanel painelSuperior = new JPanel(new GridLayout(1, 2));
        painelSuperior.add(rotuloTotal);
        painelSuperior.add(rotuloHora);

        PainelCarrinho.add(painelSuperior, BorderLayout.NORTH);

        FinalizarCarrinho = new JButton("Comprar");

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(FinalizarCarrinho);

        FinalizarCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCarrinho();
            }
        });

        PainelCarrinho.add(painelBotoes, BorderLayout.SOUTH);

        this.add(PainelCarrinho);

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Adiciona um item ao carrinho com os detalhes fornecidos
    public void adicionarItemAoCarrinho(int idLivro, String tituloLivro, String autorLivro, double precoLivro, int quantidade) {
        Object[] livroNoCarrinho = {
                idLivro,
                tituloLivro,
                precoLivro,
                quantidade
        };
        tableModel.addRow(livroNoCarrinho);
    }

    // Exibe o painel do carrinho
    public void exibirPainelCarrinho() {
        this.setVisible(true);
    }

    // Finaliza o carrinho, calcula o total da compra
    private void finalizarCarrinho() {
        double total = calcularTotalCompra();
        exibirMensagemTotal(total);

        limparTabela();
    }

    // Calcula o total da compra
    private double calcularTotalCompra() {
        double total = 0;

        // Itera sobre todas as linhas da tabela
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            // Obtém os valores da coluna na linha atual
            double precoUnitario = (double) tableModel.getValueAt(i, 2);
            int quantidade = (int) tableModel.getValueAt(i, 3);

            // Calcula o subtotal para a linha atual e adiciona ao total
            total += precoUnitario * quantidade;
        }

        return total;
    }

    // Exibe mensagem com total e hora atual usando JOptionPane
    private void exibirMensagemTotal(double total) {
        // Obtém a hora atual e formata
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String horaAtual = dateFormat.format(new Date());

        // Cria a mensagem a ser exibida
        String mensagem = "Compra finalizada!\nTotal: " + total + "\nHora: " + horaAtual;

        // Exibe a mensagem usando JOptionPane
        JOptionPane.showMessageDialog(this, mensagem);

        // Atualiza rótulos com total e hora
        rotuloTotal.setText("Total: ");
        rotuloHora.setText("Hora: ");
    }

    // Limpa todas as linhas da tabela
    private void limparTabela() {
        tableModel.setRowCount(0);
    }

    // Método principal para iniciar a TelaCarrinho
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCarrinho());
    }
}