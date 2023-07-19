package com.example312.Boot.dao;


import com.example312.Boot.model.Role;
import com.example312.Boot.model.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();

    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u JOIN FETCH u.roles where u.name = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public void addUser(User user, String role) {
        user.setRoles(Set.of(getRole(role)));
        entityManager.persist(user);
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }


    @Override
    public void updateUser(long id, User updatedUser, String role) {
        User user = entityManager.find(User.class, id);
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        user.setAge(updatedUser.getAge());
        user.setPassword(updatedUser.getPassword());
        user.setRoles(Set.of(getRole(role)));
        entityManager.persist(user);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void addRoles() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        entityManager.persist(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        entityManager.persist(roleUser);
        User user = new User("admin", "admin", "123", (byte) 30, Set.of(roleAdmin, roleUser));
        entityManager.persist(user);
        user = new User("user", "user", "456", (byte) 25, Set.of(roleUser));
        entityManager.persist(user);
    }

    private Role getRole(String name) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
