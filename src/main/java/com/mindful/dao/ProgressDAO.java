package com.mindful.dao;

import com.mindful.model.Progress;
import java.util.List;

public interface ProgressDAO {
    boolean saveOrUpdate(Progress p) throws Exception;
    List<Progress> findByUserId(int userId) throws Exception;
}
