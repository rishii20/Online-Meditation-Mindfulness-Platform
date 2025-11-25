package com.mindful.dao;

import com.mindful.model.Progress;
import com.mindful.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgressDAOImpl implements ProgressDAO {

    @Override
    public boolean saveOrUpdate(Progress p) throws Exception {
        // naive upsert: try update, if 0 rows then insert
        String update = "UPDATE progress SET score=?, updated_on=? WHERE user_id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(update)) {
             ps.setInt(1, p.getScore());
             ps.setDate(2, new java.sql.Date(p.getUpdatedOn().getTime()));
             ps.setInt(3, p.getUserId());
             int updated = ps.executeUpdate();
             if (updated > 0) return true;
        }

        String insert = "INSERT INTO progress(user_id,score,updated_on) VALUES(?,?,?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(insert)) {
             ps.setInt(1, p.getUserId());
             ps.setInt(2, p.getScore());
             ps.setDate(3, new java.sql.Date(p.getUpdatedOn().getTime()));
             return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Progress> findByUserId(int userId) throws Exception {
        String sql = "SELECT id,user_id,score,updated_on FROM progress WHERE user_id=? ORDER BY updated_on DESC";
        List<Progress> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, userId);
             try (ResultSet rs = ps.executeQuery()) {
                 while (rs.next()) {
                     Progress p = new Progress();
                     p.setId(rs.getInt("id"));
                     p.setUserId(rs.getInt("user_id"));
                     p.setScore(rs.getInt("score"));
                     p.setUpdatedOn(new Date(rs.getDate("updated_on").getTime()));
                     list.add(p);
                 }
             }
        }
        return list;
    }
}
