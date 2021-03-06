package DAO;

import model.Company;
import model.Dll;
import model.Format;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * the Dao of dll document
 */
public class DllDao {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
    public static EntityManager em = entityManagerFactory.createEntityManager();

    /**
     * add a document dll
     * @param nameDocument the name of document
     * @param nameFormat the name of format which the document associates
     * @return true: add successfully or false: add failed
     */
    public boolean AddDll(String nameDocument, String nameFormat){
        Dll dll1 = FindDllByNameFormat(nameFormat);
        em.getTransaction().begin();
        Dll dll = em.find(Dll.class,nameDocument);
        Format format = em.find(Format.class,nameFormat);
        if (dll==null&&dll1.getNameDocument()==null&&format!=null){
            dll = new Dll(nameDocument,format);
            em.persist(dll);
            em.getTransaction().commit();
            dll = em.find(Dll.class,nameDocument);
            if (dll!=null){
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
     * add a document dll
     * @param nameDocument the name of document which the document associates
     * @param format the format
     */
    public void AddDll2(String nameDocument, Format format){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Dll dll = new Dll(nameDocument,format);
        em.persist(dll);
        em.getTransaction().commit();
    }

    /**
     * find the document dll by the name of document
     * @param nameDocument the name of document
     * @return the document dll
     */
    public Dll FindDllByNameDocument(String nameDocument){
        em.getTransaction().begin();
        Dll dll = em.find(Dll.class,nameDocument);
        if (dll==null){
            dll = new Dll();
        }
        em.getTransaction().commit();
        return dll;
    }

    /**
     * find the document dll by the name of format
     * @param nameFormat the name of format
     * @return the document dll
     */
    public Dll FindDllByNameFormat(String nameFormat){
        em.getTransaction().begin();
        String queryString = "select d.nameDocument from Dll d where d.format.nameFormat=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,nameFormat);
        String nameDocument = null;
        List results = query.getResultList();
        Dll dll = new Dll();
        if(results.size()!=0){
            nameDocument = results.get(0).toString();
            dll = em.find(Dll.class,nameDocument);
        }
        em.getTransaction().commit();
        return dll;
    }

    /**
     * find the document dll by the extension of format
     * @param extension the extension of format
     * @return the document dll
     */
    public Dll FindDllByExtension(String extension){
        em.getTransaction().begin();
        String queryString = "select d.nameDocument from Dll d where d.format.extension=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,extension);
        String nameDocument = null;
        List results = query.getResultList();
        Dll dll = new Dll();
        if(results.size()!=0){
            nameDocument = results.get(0).toString();
            dll = em.find(Dll.class,nameDocument);
        }
        em.getTransaction().commit();
        return dll;
    }

    /**
     * delete the document dll by the name of document
     * @param nameDocument the name of document
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelDllByNameDocument(String nameDocument){
        Dll dll = em.find(Dll.class,nameDocument);
        if (dll!=null){
            FormatDao formatDao = new FormatDao();
            formatDao.DelFormatBYName(dll.getFormat().getNameFormat());
            em.getTransaction().begin();
            em.remove(dll);
            Dll dll1 = em.find(Dll.class,nameDocument);
            em.getTransaction().commit();
            if (dll1==null){
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * delete the document dll by the name of format
     * @param nameFormat the name of format
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelDllByNameFormat(String nameFormat){
        Dll dll = FindDllByNameFormat(nameFormat);
        if (dll.getNameDocument()!=null){
            FormatDao formatDao = new FormatDao();
            formatDao.DelFormatBYName(dll.getFormat().getNameFormat());
            em.getTransaction().begin();
            em.remove(dll);
            em.getTransaction().commit();
            dll = FindDllByNameFormat(nameFormat);
            if (dll.getNameDocument()==null){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * delete the document dll by the extension of format
     * @param extension the extension of format
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelDllByExtension(String extension){
        Dll dll = FindDllByExtension(extension);
        if (dll.getNameDocument()!=null){
            FormatDao formatDao = new FormatDao();
            formatDao.DelFormatBYName(dll.getFormat().getNameFormat());
            em.getTransaction().begin();
            em.remove(dll);
            em.getTransaction().commit();
            dll = FindDllByExtension(extension);
            if (dll.getNameDocument()==null){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}
