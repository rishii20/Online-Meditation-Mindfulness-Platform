package com.mindful.dao;

import com.mindful.model.User;
import com.mindful.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findById(int id) throws Exception {
        String sql = "SELECT id,name,email,password_hash,role_id FROM users WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, id);
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return mapRow(rs);
                 }
             }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        String sql = "SELECT id,name,email,password_hash,role_id FROM users WHERE email=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setString(1, email);
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     return mapRow(rs);
                 }
             }
        }
        return null;
    }

    @Override
    public int save(User user) throws Exception {
        String sql = "INSERT INTO users(name,email,password_hash,role_id) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             ps.setString(1, user.getName());
             ps.setString(2, user.getEmail());
             ps.setString(3, user.getPasswordHash());
             ps.setInt(4, user.getRoleId());
             ps.executeUpdate();
             try (ResultSet keys = ps.getGeneratedKeys()) {
                 if (keys.next()) return keys.getInt(1);
             }
        }
        return -1;
    }

    @Override
    public boolean update(User user) throws Exception {
        String sql = "UPDATE users SET name=?, email=?, password_hash=?, role_id=? WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setString(1, user.getName());
             ps.setString(2, user.getEmail());
             ps.setString(3, user.getPasswordHash());
             ps.setInt(4, user.getRoleId());
             ps.setInt(5, user.getId());
             return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, id);
             return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        String sql = "SELECT id,name,email,password_hash,role_id FROM users";
        List<User> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             while (rs.next()) { list.add(mapRow(rs)); }
        }
        return list;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setRoleId(rs.getInt("role_id"));
        return u;
    }
}
