package org.academiadecodigo.roothless.model.dao.User;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.DAO;

/**
 * Created by codecadet on 23/03/17.
 */
public interface UserDao extends DAO<User> {
    User getUser(String user);
    User getUserByID(int user_id);
}
