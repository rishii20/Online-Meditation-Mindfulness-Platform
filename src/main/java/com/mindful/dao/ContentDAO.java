package com.mindful.dao;

import com.mindful.model.Content;
import java.util.List;

public interface ContentDAO {
    Content findById(int id) throws Exception;
    List<Content> findAllPublished() throws Exception;
    int save(Content content) throws Exception;
    boolean update(Content content) throws Exception;
    boolean delete(int id) throws Exception;
}
