package services;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users; // Map pentru utilizatori (cheie: email)

    // Constructor
    public AuthService() {
        this.users = new HashMap<>();
    }

    // Adăugare utilizator nou
    public boolean registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            return false; // Email-ul deja există
        }
        User newUser = new User(users.size() + 1, name, email, password);
        users.put(email, newUser);
        return true;
    }

    // Autentificare utilizator
    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.validatePassword(password)) {
            return user;
        }
        return null; // Autentificare eșuată
    }

    // Obține utilizator după email
    public User getUserByEmail(String email) {
        return users.get(email);
    }
}
