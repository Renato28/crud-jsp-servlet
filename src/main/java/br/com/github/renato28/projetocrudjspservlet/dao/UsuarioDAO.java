package br.com.github.renato28.projetocrudjspservlet.dao;

import br.com.github.renato28.projetocrudjspservlet.connection.ConnectionFactory;
import br.com.github.renato28.projetocrudjspservlet.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome,email) VALUES (?,?,?)";

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listar() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (Connection connection = ConnectionFactory.getConnection();

             PreparedStatement statement = connection.prepareStatement(sql);

             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(resultSet.getLong("id"));

                usuario.setNome(resultSet.getString("nome"));

                usuario.setEmail(resultSet.getString("email"));

                usuarios.add(usuario);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }
}
