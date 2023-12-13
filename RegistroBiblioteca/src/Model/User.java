package Model;

public class User {

    // Atributos da classe User
    public int id;
    public String name;
    public String email;
    public String Password;

    // Construtor vazio necessário para a criação de objetos User
    public User() {
        // Construtor vazio
    }

    // Construtor com parâmetros para inicializar os atributos do usuário
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        Password = password;
    }

    // Métodos Getter e Setter para acessar e modificar os atributos do usuário
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
