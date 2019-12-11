package net.jmsolotask.springMVC.dao.daoImpl;

import net.jmsolotask.springMVC.dao.RoleDAO;
import net.jmsolotask.springMVC.model.Role;
import org.hibernate.Session;
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
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Role getRoleById(long id) {
        return sessionFactory.getCurrentSession().find(Role.class, id);
    }

    @Override
    @Transactional
    public Role getRoleByName(String roleName) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Role where role = :paramName");
        query.setParameter("paramName", roleName);
        List roles = query.getResultList();
        return (roles.isEmpty()) ? null : (Role) roles.get(0);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().persist(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        Role result = getRoleById(role.getId());
        result.setRole(role.getAuthority());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        sessionFactory.getCurrentSession().remove(getRoleById(id));
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return sessionFactory.getCurrentSession().createQuery("FROM Role").getResultList();
    }
}
