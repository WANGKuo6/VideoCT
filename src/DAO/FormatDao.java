package DAO;

import model.Format;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * the Dao of format
 */
public class FormatDao {
    public  static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
    public  static EntityManager em = entityManagerFactory.createEntityManager();

    /**
     * add a format
     * @param nameFormat the name of format
     * @param extension the extension of format
     * @return true: add successfully or false: add failed
     */
    public boolean AddFormat(String nameFormat, String extension ){
        em.getTransaction().begin();
        Format format1 = em.find(Format.class,nameFormat);
        if (format1==null){
            Format format = new Format(nameFormat,extension);
            em.persist(format);
            format1 = em.find(Format.class,nameFormat);
            em.getTransaction().commit();
            if (format1!=null){
                return true;
            }else {
                return false;
            }
        }else {
            em.getTransaction().commit();
            return false;
        }
    }

    /**
     * add a format
     * @param nameFormat the name of format
     * @param extension the extension of format
     */
    public void AddFormat2(String nameFormat, String extension ){
        em.getTransaction().begin();
        Format format = new Format(nameFormat,extension);
        em.persist(format);
        em.getTransaction().commit();
    }

    /**
     * find a format by name of format
     * @param nameFormat the name of format
     * @return the format
     */
    public Format FindFormatBYName(String nameFormat){
        em.getTransaction().begin();
        Format format = em.find(Format.class,nameFormat);
        if(format==null) {
            format = new Format();
        }
        em.getTransaction().commit();
        return format;

    }

    /**
     * find a format by the extension of format
     * @param extension the extension of format
     * @return the format
     */
    public  Format FindFormatBYExtension(String extension){
        em.getTransaction().begin();
        String queryString = "select f.nameFormat from Format f where f.extension=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,extension);
        String nameFormat = null;
        List results = query.getResultList();
        Format format = new Format();
        if(results.size()!=0){
            nameFormat = results.get(0).toString();
            format = em.find(Format.class,nameFormat);
        }
        em.getTransaction().commit();
        return format;
    }

    /**
     * delete a format by the name of format
     * @param nameFormat the name of format
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelFormatBYName(String nameFormat){
        em.getTransaction().begin();
        Format format = em.find(Format.class,nameFormat);
        if (format!=null) {
            em.remove(format);
            Format format1 = em.find(Format.class,nameFormat);
            em.getTransaction().commit();
            if(format1==null){
                return true;
            }else {
                return false;
            }
        }else {
            em.getTransaction().commit();
            return false;
        }
    }

    /**
     * delete a format by the extension of format
     * @param extension the extension of format
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelFormatBYExtension(String extension){

        Format format = FindFormatBYExtension(extension);
        em.getTransaction().begin();
        if (format.getNameFormat()!=null){
            em.remove(format);
            em.getTransaction().commit();
            Format format1 = FindFormatBYExtension(extension);
            if(format1.getNameFormat()==null){
                return true;
            }else {
                return false;
            }
        }else {
            em.getTransaction().commit();
            return false;
        }
    }

}
