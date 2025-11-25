package com.mindful.dao;

import com.mindful.model.User;
import java.util.List;

public interface UserDAO {
    User findById(int id) throws Exception;
    User findByEmail(String email) throws Exception;
    int save(User user) throws Exception;
    boolean update(User user) throws Exception;
    boolean delete(int id) throws Exception;
    List<User> findAll() throws Exception;
}
