package com.mindful.dao;

import com.mindful.model.Message;
import java.util.List;

public interface MessageDAO {
    int save(Message m) throws Exception;
    List<Message> findByUserConversation(int userA, int userB) throws Exception;
}
