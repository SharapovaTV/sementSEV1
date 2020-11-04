package web.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    private User user;

    @Override
    public User getUserByName(String name) {
        User user = (User) entityManager.createQuery(" select u from User u where u.username = :name", User.class)
                .setParameter("name", name).getSingleResult();
        return user;
    }

    @Override
    public void add(User user) {
        //this.user = user;
        entityManager.persist(user);
    }

    @Override
    public List<User> allUsers() {
        List<User> users = (List<User>) entityManager.createQuery("SELECT u FROM User u").getResultList();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User users = entityManager.find(User.class, id);
        return users;
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }
}

