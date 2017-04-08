package org.academiadecodigo.roothless.service.User;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.service.Service;

/**
 * Created by codecadet on 07/03/17.
 */
public interface UserService extends Service {

    boolean authenticate(String user, String pw);

    boolean addUser(User user);

    User findByName(String user);

    void removeUser(User user);

    int count();

    Object findAll();

    void updateUser(User user);

    User findByID(int user_id);
}
