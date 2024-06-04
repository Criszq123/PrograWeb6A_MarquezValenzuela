package com.example.programacionweb_its_prac1;

import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Base64;

import static com.example.programacionweb_its_prac1.AutenticacionServlet.generalKey;

@WebServlet("/user-servlet/*")
public class UserServlet extends HttpServlet {
    private final JsonResponse jResp = new JsonResponse();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String authTokenHeader = req.getHeader("Authorization");
        validateAuthToken(req, resp, authTokenHeader.split(" ")[1]);
    }

    /**
     * Método que se utiliza para validar el token de autenticación. Si el token es válido, se envía una respuesta exitosa.
     * Si el token no es válido, se envía una respuesta fallida.
     * @param req
     * @param resp
     * @param token Token de autenticación
     * @throws IOException
     */
    private void validateAuthToken (HttpServletRequest req, HttpServletResponse resp, String token) throws IOException {
        JwtParser jwtParser = Jwts.parser().verifyWith(AutenticacionServlet.generalKey()).build();
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            User user = AutenticacionServlet.users.get(username);

            //generamos un objetoJson, para verificar cada uno de los valores del usuario, para identificarlos

            JsonObject userJson = new JsonObject();
            userJson.addProperty("fullName", user.getFullName());
            userJson.addProperty("email", user.getEmail());
            userJson.addProperty("username", user.getUsername());
            userJson.addProperty("jwt", user.getJwt());

            jResp.success(req, resp, "Autenticación probada", userJson);
        } catch (Exception e) {
            jResp.failed(req, resp, "Unauthorized: " + e.getMessage(), 401);
}

    }

}
