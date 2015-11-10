package music.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import music.business.Product;

public class ProductDB {

    public static void insert(Product product) {
        EntityManager em = DBUtil.getemFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            System.out.println("working...");
            trans.begin();
            em.persist(product);
            System.out.println("worked");
        } catch (Exception e) {
            trans.rollback();
            System.out.println("shit");
        } finally {
            em.close();
        }
    }

    public static void update(Product product) {
        EntityManager em = DBUtil.getemFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(product);
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Product product) {
        EntityManager em = DBUtil.getemFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(product));
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static boolean codeExists(String code) {
        Product p = selectProduct(code);
        return p != null;
    }

    public static Product selectProduct(String code) {
        EntityManager em = DBUtil.getemFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
                    "WHERE p.code = :code";
        
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("code", code);
        
        Product product = null;
        
        try {
            product = q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return product;
    }
    
    public static List<Product> selectProducts() {
        EntityManager em = DBUtil.getemFactory().createEntityManager();
        String qString = "SELECT p from Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        List<Product> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        
        return results;
    }
}