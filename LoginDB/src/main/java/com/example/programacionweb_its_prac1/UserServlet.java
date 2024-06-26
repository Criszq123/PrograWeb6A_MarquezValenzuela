package com.example.programacionweb_its_prac1;


import dao.UserDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.example.programacionweb_its_prac1.AutenticacionServlet.generalKey;

@WebServlet("/user-servlet/*")
public class UserServlet extends HttpServlet {
    private final JsonResponse jResp = new JsonResponse();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        // Obtiene el token de autenticación del encabezado "Authorization"
        String authTokenHeader = req.getHeader("Authorization");
        if (authTokenHeader == null ) {
            // Si el token de autenticación es inválido o no existe, termina el método aquí
            jResp.failed(req, resp, "Sin autorización: Token faltante", 422);
            return;
        }
        else if(!authTokenHeader.contains(" ") || !validateAuthToken(req, resp, authTokenHeader.split(" ")[1])){
            return;
        }

        String pathInfo = req.getPathInfo();

        // Si pathInfo es null o es "/", entonces el usuario está solicitando /user-servlet/
        if (pathInfo == null || pathInfo.equals("/")) {
            consultaGeneral(req, resp);
        } else {
            // Si pathInfo no es null ni "/", entonces el usuario está solicitando /user-servlet/{id}
            Integer id = Integer.valueOf(pathInfo.substring(1)); // Obtiene el id de la URL
            consultaEspecifica(req, resp, id);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        registrar(req, resp);
    }

    private void registrar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        Integer id = null;

        //Revisa que los parametros existan en la URL
        if (username == null || password == null || fullName == null || email == null) {
            jResp.failed(req, resp, "Todos los campos son obligatorios", HttpServletResponse.SC_FORBIDDEN);
            return;
        }//Revisa que los parametros no esten vacios
        else if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
            jResp.failed(req, resp, "Es necesario que rellenes todos los datos", HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // Encrypt the password (You should use a stronger encryption algorithm)
        String encryptedPassword = encryptPassword(password);

        UserDAO userDao = new UserDAO();

        if(!userDao.verificarCorreo(email)){
            User user = new User(fullName, email, username, encryptedPassword, null);

            int estadoOperacion = userDao.registrarUsuario(user);

            if(estadoOperacion != -1){
                id = estadoOperacion;
                jResp.success(req, resp, "Usuario con id: " + id + " creado con éxito", user);//Respuesta de la solicitud
            } else{
                jResp.failed(req, resp, "No se pudo registrar al usuario", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else{
            jResp.failed(req, resp, "Ya existe un usuario con este correo electrónico", 422);
        }
    }

    private void consultaGeneral(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDAO userDao = new UserDAO();
        jResp.success(req, resp, "Listado de usuarios: ", userDao.consultar());
    }

    private void consultaEspecifica(HttpServletRequest req, HttpServletResponse resp, Integer id) throws IOException {
        UserDAO userDao = new UserDAO();
        if(userDao.consultarUsuario(id) == null){
            jResp.failed(req, resp, "El ID de usuario no se ha encontrado", HttpServletResponse.SC_NOT_FOUND);
        }else{
            jResp.success(req, resp, "Usuario con ID: " + id, userDao.consultarUsuario(id));
        }
    }

    private boolean validateAuthToken (HttpServletRequest req, HttpServletResponse resp, String token) throws IOException {
        JwtParser jwtParser = Jwts.parser()
                .verifyWith( generalKey() )
                .build();

        try {
            // Obtener los claims que guardamos anteriormente
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(token);
            jResp.failed(req, resp, "Unathorized: " + e.getMessage(), 422);
            return false;
        }
    }

    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}