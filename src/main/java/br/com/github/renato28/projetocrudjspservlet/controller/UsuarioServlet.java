package br.com.github.renato28.projetocrudjspservlet.controller;

import br.com.github.renato28.projetocrudjspservlet.dao.UsuarioDAO;
import br.com.github.renato28.projetocrudjspservlet.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
       String nome = request.getParameter("nome");
       String email = request.getParameter("email");

       Usuario usuario = new Usuario();
       usuario.setNome(nome);
       usuario.setEmail(email);

       dao.salvar(usuario);

       response.sendRedirect("usuarios");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usuarios", dao.listar());

        request.getRequestDispatcher(
                "/usuario-list.jsp")
                .forward(request, response);
    }
}
