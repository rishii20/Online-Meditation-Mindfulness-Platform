package com.mindful.dao;

import com.mindful.model.Content;
import com.mindful.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAOImpl implements ContentDAO {

    @Override
    public Content findById(int id) throws Exception {
        String sql = "SELECT id,title,description,media_url,media_type,created_by FROM meditations WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, id);
             try (ResultSet rs = ps.executeQuery()) {
                 if (rs.next()) {
                     Content c = new Content();
                     c.setId(rs.getInt("id"));
                     c.setTitle(rs.getString("title"));
                     c.setDescription(rs.getString("description"));
                     c.setMediaUrl(rs.getString("media_url"));
                     c.setMediaType(rs.getString("media_type"));
                     c.setCreatedBy(rs.getInt("created_by"));
                     return c;
                 }
             }
        }
        return null;
    }

    @Override
    public List<Content> findAllPublished() throws Exception {
        String sql = "SELECT id,title,description,media_url,media_type,created_by FROM meditations WHERE status='PUBLISHED' ORDER BY created_at DESC";
        List<Content> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             while (rs.next()) {
                 Content c = new Content();
                 c.setId(rs.getInt("id"));
                 c.setTitle(rs.getString("title"));
                 c.setDescription(rs.getString("description"));
                 c.setMediaUrl(rs.getString("media_url"));
                 c.setMediaType(rs.getString("media_type"));
                 c.setCreatedBy(rs.getInt("created_by"));
                 list.add(c);
             }
        }
        return list;
    }

    @Override
    public int save(Content content) throws Exception {
        String sql = "INSERT INTO meditations(title,description,media_url,media_type,created_by,status) VALUES(?,?,?,?,?,'DRAFT')";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             ps.setString(1, content.getTitle());
             ps.setString(2, content.getDescription());
             ps.setString(3, content.getMediaUrl());
             ps.setString(4, content.getMediaType());
             ps.setInt(5, content.getCreatedBy());
             ps.executeUpdate();
             try (ResultSet keys = ps.getGeneratedKeys()) {
                 if (keys.next()) return keys.getInt(1);
             }
        }
        return -1;
    }

    @Override
    public boolean update(Content content) throws Exception {
        String sql = "UPDATE meditations SET title=?, description=?, media_url=?, media_type=? WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setString(1, content.getTitle());
             ps.setString(2, content.getDescription());
             ps.setString(3, content.getMediaUrl());
             ps.setString(4, content.getMediaType());
             ps.setInt(5, content.getId());
             return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM meditations WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, id);
             return ps.executeUpdate() > 0;
        }
    }
}
