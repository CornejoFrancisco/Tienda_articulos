package com.example.demo.controllers.RecuperacionContra;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ArticuloRepository;
import com.example.demo.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private UsuarioRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String token, @RequestParam String contrasena) {
        try {
            if (token == null || token.isEmpty()) {
                throw new AuthorizationError("Token no provided");
            }

            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            Long userId = Long.valueOf(claims.getSubject());
            Optional<Usuario> optionalUsuario = userRepository.findById(userId);

            if (!optionalUsuario.isPresent()) {
                throw new NotFoundError("Usuario");
            }

            Usuario usuario = optionalUsuario.get();
            String oldPassword = usuario.getPassword();

            if (contrasena == null || contrasena.isEmpty()) {
                throw new OtherError("Debes ingresar la nueva contraseña");
            }
            if (passwordEncoder.matches(contrasena, oldPassword)) {
                throw new OtherError("Las contraseñas deben ser distintas");
            }

            usuario.setPassword(passwordEncoder.encode(contrasena));
            userRepository.save(usuario);

            return ResponseEntity.ok(new Response(true, "Contraseña modificada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(false, e.getMessage()));
        }
    }

    // Clase Response para manejar las respuestas
    private static class Response {
        private boolean success;
        private String message;

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
