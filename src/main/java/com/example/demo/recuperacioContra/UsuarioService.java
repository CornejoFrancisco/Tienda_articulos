package com.example.demo.recuperacioContra;


import com.example.demo.entities.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UsuarioService{
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<String, String> tokenMap = new HashMap<>();

    // Método para registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getGmail(), usuario);
    }

    // Método para buscar un usuario por su correo electrónico
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.get(email);
    }

    // Método para generar un token de recuperación
    public String generarTokenRecuperacion(String email) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, email);
        return token;
    }

    // Método para obtener un correo electrónico a partir de un token
    public String getEmailPorToken(String token) {
        return tokenMap.get(token);
    }

    // Método para eliminar un token después de su uso
    public void eliminarToken(String token) {
        tokenMap.remove(token);
    }

    // Método para actualizar la contraseña de un usuario
    public void actualizarContrasena(String email, String nuevaContrasena) {
        Usuario usuario = buscarUsuarioPorEmail(email);
        if (usuario != null) {
            usuario.setPassword(nuevaContrasena);
        }
    }
}
