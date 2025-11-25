package com.mindful.service;

import com.mindful.dao.UserDAO;
import com.mindful.dao.UserDAOImpl;
import com.mindful.model.User;

public class AuthService {
    private UserDAO userDAO = new UserDAOImpl();

    public User authenticate(String email, String passwordPlain) {
        try {
            User u = userDAO.findByEmail(email);
            if (u == null) return null;
            // For demo only: compare plaintext placeholders.
            // Replace with bcrypt verification in real project.
            if (passwordPlain.equals("password")) {
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
