package ControllerDAO;

import ModelDTO.JogadorDTO;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class JogadorDAO {

    Connection conexao;
    PreparedStatement pStatement;

    public void cadastrarJogador(JogadorDTO objJogadorDTO) {
        String sql = "INSERT INTO jogador (nome, score) VALUES (?,0)";

        conexao = new ConexaoDAO().ConectarBD();

        try {

            pStatement = conexao.prepareStatement(sql);
            pStatement.setString(1, objJogadorDTO.getNome());

            pStatement.execute();
            pStatement.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "CadastrarJogadorDAO: " + erro);
        }

    }

    public ResultSet listarJogadorComboBox() {
        String sql = "SELECT nome FROM jogador";

        conexao = new ConexaoDAO().ConectarBD();

        try {
            pStatement = conexao.prepareStatement(sql);
            return pStatement.executeQuery();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "ListarJogadorDAO: " + error);
            return null;
        }
    }

    public ArrayList listarJogadorTabela() {
        String sql = "SELECT nome, score FROM jogador ORDER BY score DESC";

        conexao = new ConexaoDAO().ConectarBD();
        ArrayList<JogadorDTO> lista = new ArrayList();

        try {
            pStatement = conexao.prepareStatement(sql);
            ResultSet rset = pStatement.executeQuery();

            while (rset.next()) {
                JogadorDTO objJogadorDTO = new JogadorDTO();
                objJogadorDTO.setNome(rset.getString("nome"));
                objJogadorDTO.setScore(rset.getInt("score"));

                lista.add(objJogadorDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ListarJogadorTabelaDAO: " + erro);
            return null;
        }
        return lista;
    }

    public void adicionarScore(JogadorDTO objJogadorDTO) {
        String sql = "UPDATE jogador SET score = score + 1 WHERE nome = ?";

        conexao = new ConexaoDAO().ConectarBD();

        try {

            pStatement = conexao.prepareStatement(sql);
            pStatement.setString(1, objJogadorDTO.getNome());

            pStatement.execute();
            pStatement.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "AdcionarScoreDAO: " + erro);
        }

    }

}
