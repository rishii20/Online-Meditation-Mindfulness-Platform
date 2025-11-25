package com.mindful.dao;

import com.mindful.model.Message;
import com.mindful.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    @Override
    public int save(Message m) throws Exception {
        String sql = "INSERT INTO messages(from_user,to_user,content) VALUES(?,?,?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             ps.setInt(1, m.getFromUser());
             ps.setInt(2, m.getToUser());
             ps.setString(3, m.getContent());
             ps.executeUpdate();
             try (ResultSet keys = ps.getGeneratedKeys()) {
                 if (keys.next()) return keys.getInt(1);
             }
        }
        return -1;
    }

    @Override
    public List<Message> findByUserConversation(int userA, int userB) throws Exception {
        String sql = "SELECT * FROM messages WHERE (from_user=? AND to_user=?) OR (from_user=? AND to_user=?) ORDER BY sent_at ASC";
        List<Message> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, userA);
             ps.setInt(2, userB);
             ps.setInt(3, userB);
             ps.setInt(4, userA);
             try (ResultSet rs = ps.executeQuery()) {
                 while (rs.next()) {
                     Message m = new Message();
                     m.setId(rs.getInt("id"));
                     m.setFromUser(rs.getInt("from_user"));
                     m.setToUser(rs.getInt("to_user"));
                     m.setContent(rs.getString("content"));
                     m.setSentAt(new Date(rs.getTimestamp("sent_at").getTime()));
                     m.setRead(rs.getBoolean("is_read"));
                     list.add(m);
                 }
             }
        }
        return list;
    }
}
