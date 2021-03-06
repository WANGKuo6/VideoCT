package DAO;

import model.Company;
import model.Format;
import model.Video;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * the Dao of company
 */
public class CompanyDao {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
    public static EntityManager em = entityManagerFactory.createEntityManager();

    /**
     * add a company
     * @param nameCompany the name of company
     * @param web the web of company
     * @return true: add successfully or false: add failed
     */
    public boolean AddCompany(String nameCompany, String web){
        Company company1 = FindCompanyByNameCompany(nameCompany);
        Company company2 = FindCompanyByWeb(web);
        if (company1.getNameCompany()==null&&company2.getNameCompany()==null){
            em.getTransaction().begin();
            Company company = new Company(nameCompany,web);
            em.persist(company);
            em.getTransaction().commit();
            company = FindCompanyByNameCompany(nameCompany);
            if (company.getNameCompany()!=null){
                return true;
            }
            else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * find the company by the name of company
     * @param nameCompany the name of company
     * @return the company
     */
    public Company FindCompanyByNameCompany(String nameCompany){
        em.getTransaction().begin();
        Company company = em.find(Company.class,nameCompany);
        if (company==null){
            company = new Company();
        }
        em.getTransaction().commit();
        return company;
    }

    /**
     * find a company by the web of company
     * @param web the web of company
     * @return the company
     */
    public Company FindCompanyByWeb(String web){
        em.getTransaction().begin();
        String queryString = "select c.nameCompany from Company c where c.web=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,web);
        List results = query.getResultList();
        String nameCompany = null;
        Company company = new Company();
        if (results.size()!=0){
            nameCompany = results.get(0).toString();
            company = em.find(Company.class,nameCompany);
        }
        em.getTransaction().commit();
        return company;
    }

    /**
     * delete a company by the name of company
     * @param nameCompany the name of company
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelCompanyByNameCompany(String nameCompany){
        em.getTransaction().begin();
        Company company = em.find(Company.class,nameCompany);
        if (company!=null){
            em.remove(company);
            em.getTransaction().commit();
            company = em.find(Company.class,nameCompany);
            if (company==null){
                return true;
            }else {
                return false;
            }
        }
        else {
            em.getTransaction().commit();
            return false;
        }

    }

    /**
     * change the name of company
     * @param oldNameCompany the old name of the company
     * @param newNameCompany the new name of the company
     * @return true: change successfully or false: change failed
     */
    public boolean ChangeNameCompany(String oldNameCompany, String newNameCompany){
        Company company1 = em.find(Company.class,oldNameCompany);
        Company company2 = em.find(Company.class,newNameCompany);

        em.getTransaction().begin();
        if (company1!=null&&company2==null){
            em.detach(company1);
            company1.setNameCompany(newNameCompany);
            em.merge(company1);
            Company company3 = em.find(Company.class,oldNameCompany);
            em.remove(company3);
            em.getTransaction().commit();
            company1 = em.find(Company.class,newNameCompany);
            company2 = em.find(Company.class,oldNameCompany);
            if (company1!=null&&company2==null){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            em.getTransaction().commit();
            return false;
        }
    }

    /**
     * change the web of a company
     * @param nameCompany the name of company
     * @param newWeb the new web of the company
     * @return true: change successfully or false: change failed
     */
    public boolean ChangeWeb(String nameCompany, String newWeb){
        Company company1 = em.find(Company.class,nameCompany);
        Company company2 = FindCompanyByWeb(newWeb);
        em.getTransaction().begin();
        if (company1!=null&&company2.getNameCompany()==null){
            company1.setWeb(newWeb);
            em.getTransaction().commit();
            company1 = em.find(Company.class,nameCompany);
            if (company1.getWeb()==newWeb){
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
