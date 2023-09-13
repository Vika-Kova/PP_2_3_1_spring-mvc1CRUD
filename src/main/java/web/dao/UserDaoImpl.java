package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setEmail(user.getEmail());
        entityManager.merge(userToBeUpdated);
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(show(id));
    }

    @Transactional(readOnly = true)
    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> index() {
        List<User> userList = entityManager.createQuery("select u from User u", User.class).getResultList();
        return userList;
    }
}











