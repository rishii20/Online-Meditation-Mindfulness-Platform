package com.mindful.dao;

import com.mindful.model.SessionLog;
import java.util.List;

public interface SessionDAO {
    int save(SessionLog s) throws Exception;
    List<SessionLog> findByUserId(int userId) throws Exception;
}
