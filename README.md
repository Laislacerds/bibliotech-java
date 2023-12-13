# Biblioteca Online em Java

Bem-vindo ao repositório da Biblioteca Online em Java! Este projeto é uma implementação de uma pequena biblioteca online em Java, utilizando a biblioteca gráfica JavaSwing para a interface do usuário.

## Estrutura do Projeto

O projeto está organizado em três principais pacotes:

### Controller

- **ComprasController:** Gerencia operações relacionadas a compras no sistema.
- **EmprestimosController:** Controla as transações de empréstimos de livros.
- **LivroController:** Responsável pelo gerenciamento de operações relacionadas aos livros.
- **UserController:** Lida com operações relacionadas aos usuários do sistema.

### Model

- **Compra:** Representa informações relacionadas a compras no sistema.
- **Emprestimos:** Modela as transações de empréstimos de livros.
- **LivroDao:** Fornece acesso aos dados dos livros no sistema.
- **Livro:** Representa informações de um livro no sistema.
- **User:** Modelo para representação de usuários no sistema.

### View

- **Login:** Interface para autenticação de usuários no sistema.
- **RegistroForm:** Formulário para o registro de novos usuários, utilizando um banco de dados para armazenar informações.
- **TelaCarrinho:** Interface para visualização e gerenciamento do carrinho de compras.
- **TelaLivros:** Apresenta a lista de livros disponíveis na biblioteca.

## Configuração do Banco de Dados

O projeto utiliza um banco de dados para armazenar informações essenciais. Certifique-se de criar as seguintes tabelas em seu banco de dados antes de executar o projeto:

1. **Users:**
   - Tabela para armazenar informações dos usuários do sistema, como nome de usuário, senha, etc.

2. **Livro:**
   - Tabela para armazenar informações sobre os livros disponíveis na biblioteca, como título, autor, etc.

3. **Emprestimos:**
   - Tabela para registrar transações de empréstimos de livros, mantendo informações sobre o livro, usuário e datas relevantes.

4. **Compras:**
   - Tabela para registrar informações relacionadas a compras no sistema, incluindo detalhes sobre os itens comprados e transações financeiras.

### Configuração do Arquivo de Conexão com o Banco de Dados

Certifique-se de configurar corretamente o arquivo de conexão com o banco de dados em seu projeto. Normalmente, isso envolve a definição do URL do banco de dados, o nome de usuário e a senha no arquivo de configuração apropriado.

Ao rodar o projeto pela primeira vez, você pode ser solicitado a fornecer o nome de usuário e senha do seu banco de dados root.

```xml
<!-- Exemplo de configuração do arquivo persistence.xml para JPA -->

<persistence-unit name="LibraryPU" transaction-type="RESOURCE_LOCAL">
    <properties>
        <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/nome_do_banco"/>
        <property name="javax.persistence.jdbc.user" value="seu_usuario"/>
        <property name="javax.persistence.jdbc.password" value="sua_senha"/>
        <!-- ... outras propriedades ... -->
    </properties>
</persistence-unit>
