package net.jmsolotask.springMVC.dao.daoImpl;

import net.jmsolotask.springMVC.dao.UserDao;
import net.jmsolotask.springMVC.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoHibernateImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        sessionFactory.getCurrentSession().remove(getUserById(id));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User us JOIN FETCH us.roles where us.name = :paramName");
        query.setParameter("paramName", userName);
        List user = query.getResultList();
        return (user.isEmpty()) ? null : (User) user.get(0);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User us JOIN FETCH us.roles").getResultList();
    }
}
