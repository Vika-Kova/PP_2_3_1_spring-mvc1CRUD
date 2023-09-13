package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

@Component
public class UserDao {
    // private static int USERS_COUNT;
    // private List<User> users;
    // {
    //  users = new ArrayList<>();
    //  users.add(new User(++USERS_COUNT, "Tom", 24, "tom@mail.ru"));
    // users.add(new User(++USERS_COUNT, "Bob", 52, "bob@mail.ru"));
    // users.add(new User(++USERS_COUNT, "Mike", 18, "mike@yahoo.com"));
    // users.add(new User(++USERS_COUNT, "Katy", 34, "katy@gmail.com"));
    //}
    //public List<User> index() {//список из людей
    //    return users;
    //}
    // public User show(int id) {//найти чела по id
    //   return users.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    // }
    // public void save(User user) {
    //user.setId(++USERS_COUNT);
    // users.add(user);
    //}
    // public void update(int id, User updatedUser) {
    // User userToBeUpdated = show(id);
    //userToBeUpdated.setName(updatedUser.getName());
    //userToBeUpdated.setAge(updatedUser.getAge());
    // userToBeUpdated.setEmail(updatedUser.getEmail());
    // }
    //public void delete(int id) {
    //    users.removeIf(p -> p.getId() == id);
    // }

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<User> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public User show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User userToBeUpdated = session.get(User.class, id);

        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setEmail(updatedUser.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }
}



