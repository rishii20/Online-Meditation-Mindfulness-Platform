package com.mindful.dao;

import com.mindful.model.SessionLog;
import com.mindful.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {

    @Override
    public int save(SessionLog s) throws Exception {
        String sql = "INSERT INTO session_logs(user_id, meditation_id, duration_seconds, started_at, ended_at, rating, notes) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             ps.setInt(1, s.getUserId());
             ps.setInt(2, s.getMeditationId());
             ps.setInt(3, s.getDurationSeconds());
             ps.setTimestamp(4, s.getStartedAt() == null ? null : new Timestamp(s.getStartedAt().getTime()));
             ps.setTimestamp(5, s.getEndedAt() == null ? null : new Timestamp(s.getEndedAt().getTime()));
             ps.setInt(6, s.getRating());
             ps.setString(7, s.getNotes());
             ps.executeUpdate();
             try (ResultSet keys = ps.getGeneratedKeys()) {
                 if (keys.next()) return keys.getInt(1);
             }
        }
        return -1;
    }

    @Override
    public List<SessionLog> findByUserId(int userId) throws Exception {
        String sql = "SELECT * FROM session_logs WHERE user_id=? ORDER BY started_at DESC";
        List<SessionLog> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1, userId);
             try (ResultSet rs = ps.executeQuery()) {
                 while (rs.next()) {
                     SessionLog s = new SessionLog();
                     s.setId(rs.getInt("id"));
                     s.setUserId(rs.getInt("user_id"));
                     s.setMeditationId(rs.getInt("meditation_id"));
                     s.setDurationSeconds(rs.getInt("duration_seconds"));
                     Timestamp st = rs.getTimestamp("started_at");
                     if (st != null) s.setStartedAt(new Date(st.getTime()));
                     Timestamp et = rs.getTimestamp("ended_at");
                     if (et != null) s.setEndedAt(new Date(et.getTime()));
                     s.setRating(rs.getInt("rating"));
                     s.setNotes(rs.getString("notes"));
                     list.add(s);
                 }
             }
        }
        return list;
    }
}
