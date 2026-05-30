package br.com.github.renato28.projetocrudjspservlet.dao;

import br.com.github.renato28.projetocrudjspservlet.ConnectionFactory;
import br.com.github.renato28.projetocrudjspservlet.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome,email) VALUES (?,?,?)";

        try(Connection conn = ConnectionFactory.getConnection()){

            PreparedStatement  stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
