package org.academiadecodigo.roothless.service.User;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.User.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by codecadet on 23/03/17.
 */
@Service
public class UserServiceImplementation implements UserService {

    @Autowired private UserDao userDao;

    public UserServiceImplementation() {
    }

    @Override
    public boolean authenticate(String user, String pw) {
            User u = userDao.getUser(user);
            System.out.println("authenticating....");
        return u != null && u.getPassword().equals(pw);
    }

    @Override
    public boolean addUser(User user) {
            if(userDao.getUser(user.getUsername())==null){
                userDao.create(user);
                return true;
            }
        return false;
    }

    @Override
    public User findByName(String user) {
        return userDao.getUser(user);
    }

    @Override
    public void removeUser(User user) {
        userDao.delete(user);
    }

    @Override
    public int count() {
        return userDao.listAll().size();
    }

    @Override
    public List<User> findAll() {
        return userDao.listAll();
    }

    @Override
    public void updateUser(User user) {
        User u = userDao.getUser(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        userDao.update(u);
    }

    @Override
    public User findByID(int user_id) {
        return userDao.getUserByID(user_id);
    }

    @Override
    public String getName() {
        return UserService.class.getSimpleName();
    }

}
