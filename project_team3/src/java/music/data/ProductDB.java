package music.data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import music.business.Product;

public class ProductDB {

    public static void insert(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static void update(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            System.out.println("trans.begin()");
            em.merge(product);
            trans.commit();
            System.out.println("em.merge()");
        } catch (Exception e) {
            trans.rollback();
            System.out.println(e);
        } finally {
            em.close();
            System.out.println("em.close()");
        }
    }

    public static void delete(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            System.out.println("trans.begin()");
            em.remove(em.merge(product));
            trans.commit();
            System.out.println("em.remove(em.merge())");
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
            System.out.println("em.close()");
        }
    }

    public static boolean exists(long id) {
        Product p = selectProduct(id);
        return p != null;
    }
    public static boolean exists(String code) {
        Product p = selectProduct(code);
        return p != null;
    }

    public static Product selectProduct(String code) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p " +
                "WHERE p.code = :code";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("code", code);
        Product result = null;
        try {
            result = q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        
        return (Product)result;
    }
    public static Product selectProduct(long productId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        return em.find(Product.class, productId);
    }
    
    public static List<Product> selectProducts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p from Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        List<Product> results = null;
        try {
            results = q.getResultList();
        } catch (Error ex) {
            System.out.println("error no list");
            return null;
        } finally {
            em.close();
        }
        
        return results;
    }
}