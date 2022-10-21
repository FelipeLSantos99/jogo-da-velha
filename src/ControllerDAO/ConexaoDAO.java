package ControllerDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexaoDAO {

    public Connection ConectarBD() {
        Connection conexao = null;

        try {
            String url = "jdbc:mysql://localhost:3306/jogovelha?user=root&=";
            conexao = DriverManager.getConnection(url);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ConectarDBDAO: " + erro);
        }
        return conexao;
    }

}
