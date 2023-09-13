package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void  save(User user) ;
    void update(int id, User user);
    void delete(int id);
    User  show(int id) ;
    List<User> index();
}
