package com.klef.jfsd.exam;

import org.hibernate.*;

import org.hibernate.cfg.Configuration;


public class ClientDemo {
    public static void main(String[] args) {
        // Load configuration and build session factory
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Open a session
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // HQL Update query using positional parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, "Updated Department Name");
            query.setParameter(2, "Updated Location");
            query.setParameter(3, 1); // Assuming the Department ID to update is 1

            int rowsAffected = query.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
