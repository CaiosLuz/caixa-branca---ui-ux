package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class User
 * Responsável pelo gerenciamento de usuário no sistema.
 */
public class User {

    public String nome = "";
    public boolean result = false;
    
    /**
     * Conecta ao banco de dados MySQL.
     * @return uma conexão válida ou null em caso de erro
     * @throws SQLException se houver falha ao fechar a conexão no try-with-resources
     */
    public Connection conectarDB() throws SQLException {
    	Connection conn = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		String url = "jdbc:mysql://127.0.0.1/testw?user=root&password=1234";
    		conn = DriverManager.getConnection(url);
    	} catch (Exception e) {
    		System.out.println("Erro ao conectar: " + e.getMessage());
    	}
    	return conn;
    }

    /**
     * Verifica se existe um usuário no banco com login e senha informados.
     * Atualiza o atributo 'nome' se encontrado.
     * @param login login do usuário
     * @param senha senha do usuário
     * @return true se o usuário existir, false caso contrário
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "SELECT nome FROM usuarios WHERE login = '" + login + "' AND senha = '" + senha + "'";
        
        try {
    		try(Connection conn = conectarDB()) {
	        	if (conn == null) {
	        		System.out.println("Conexão nula!");
	        		return false;
	        	}
        	
	        	try(Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
	        		if (rs.next()) {
	        			nome =  rs.getString("nome");
	        			result = true;
	        		}
	        	} catch (SQLException e) {
	        		System.out.println("Erro ao verificar usuário: " + e.getMessage());
				}
    		}
        } catch (SQLException e) {
        	System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return false;
		}
        return result;
    }
    
    /**
     * Método de teste da classe User.
     * Tenta verificar um usuário e imprime o resultado no console.
     */
    public static void main(String[] args) {
    	User user = new User();
    	boolean existe = user.verificarUsuario("caioss", "1234");
    	System.out.println("Usuário encontrado? " + existe);
    	if (existe) {
    		System.out.println("Login efetuado com sucesso!");
    	} else {
    		System.out.println("Login inválido");
    	}
    }
}
