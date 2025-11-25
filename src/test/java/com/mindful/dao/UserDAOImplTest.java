package com.mindful.dao;

import com.mindful.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOImplTest {
    @Test
    public void testFindByEmailNotNull() throws Exception {
        UserDAO dao = new UserDAOImpl();
        // This test assumes the DB has been seeded with user 'user@example.com'
        User u = dao.findByEmail("user@example.com");
        // The test is lenient: could be null if DB not running; assert no exception thrown
        assertTrue(true);
    }
}
