package Model;

import java.sql.Date;

public class Compra {

    // Atributos da classe Compra
    private int id;
    private int userId;
    private String userName;
    private int livroId;
    private double valorTotal;
    private Date dataHora;
    private int quantidade;

    // Construtores
    public Compra() {
        // Construtor vazio necessário para a criação de objetos Compra
    }

    // Construtor com parâmetros para inicializar os atributos da Compra
    public Compra(int id, int userId, String userName, int livroId, double valorTotal, Date dataHora, int quantidade) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.livroId = livroId;
        this.valorTotal = valorTotal;
        this.dataHora = dataHora;
        this.quantidade = quantidade;
    }

    // Métodos Getter e Setter para acessar e modificar os atributos da Compra
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
