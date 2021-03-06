package DAO;

import model.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Dao of codec
 */
public class CodecDao {
	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
    public static EntityManager em = entityManagerFactory.createEntityManager();

    /**
     * get all of codec
     * @return the list of all codec
     */
	public List<Codec> ListAll(){
		em.getTransaction().begin();
		String queryString = "from Codec c";
        Query query = em.createQuery(queryString);
        List<Codec> results = query.getResultList();
        List<Codec> codecs = new ArrayList<Codec>();
        int m = results.size();
        Codec codec;
        for(int i = 0; i < m; i++){
            codec = em.find(Codec.class, results.get(i).getNameCodec());
            codecs.add(codec);
        }
        em.getTransaction().commit();
		return codecs;
	}

    /**
     * add a codec(Complex version)
     * @param nameCodec the name of codec
     * @param nameDocument the name of document dll
     * @param nameFormat  the name of format
     * @param Extension  the extension of format
     * @param InfoCommercial the commercial information of codec
     * @return true: add successfully or false: add failed
     */
	public boolean AddCodec(String nameCodec, String nameDocument, String nameFormat, String Extension, String InfoCommercial){
        Codec codec = em.find(Codec.class, nameCodec);
        Dll dll = em.find(Dll.class, nameDocument);
        if (codec == null && dll != null){
            em.getTransaction().begin();
            codec = new Codec(nameCodec,dll);
            Company company = em.find(Company.class,InfoCommercial);
            if (company == null && InfoCommercial != null ){
                company = new Company();
                company.setNameCompany(InfoCommercial);
                em.persist(company);
                codec.setCompany(company);
            }
            em.persist(codec);
            codec = em.find(Codec.class, nameCodec);
            em.getTransaction().commit();
           
            em.close();
            if (codec.getNameCodec() != null)
                return true;
            else
                return false;
        }
        else if(codec == null && dll == null) {

        	Format format = new Format(nameFormat, Extension);
        	DllDao dlldao = new DllDao();
        	if(em.find(Format.class, nameFormat) ==  null) {
        		 FormatDao formatdao = new FormatDao();
        		 formatdao.AddFormat(nameFormat, Extension);
        	}
        	dlldao.AddDll2(nameDocument, format);
        	Dll dll_new = new Dll(nameDocument, format);
            codec = new Codec(nameCodec, dll_new);
            Company company = em.find(Company.class,InfoCommercial);
            em.getTransaction().begin();
            if (company == null && InfoCommercial != "" ){
                company = new Company();
                company.setNameCompany(InfoCommercial);
                em.persist(company);
                codec.setCompany(company);
            }
            em.persist(codec);
            codec = em.find(Codec.class, nameCodec);
            em.getTransaction().commit();
            em.close();
            if (codec.getNameCodec() != null)
                return true;
            else
                return false;
        }
        else
        	return false;
    }

    /**
     * add a codec
     * @param nameCodec the name of codec
     * @param nameCompany the name of company
     * @param nameDocument the name of document
     * @return true: add successfully or false: add failed
     */
	 public boolean AddCodec2(String nameCodec, String nameCompany, String nameDocument){
	        Codec codec = em.find(Codec.class,nameCodec);
	        Company company = em.find(Company.class,nameCompany);
	        Dll dll = em.find(Dll.class,nameDocument);
	        if (codec==null && dll!=null && company!=null){
	            em.getTransaction().begin();
	           codec = new Codec(nameCodec,dll,company);
	            em.persist(codec);
	            em.getTransaction().commit();
	            codec = em.find(Codec.class,nameCodec);
	            if (codec!=null){
	                return true;
	            }else {
	                return false;
	            }
	        }else {
	            return false;
	        }
	    }

    /**
     * find a codec by the name of codec
     * @param nameCodec the name of codec
     * @return the codec
     */
    public static Codec FindCodecByName(String nameCodec){
        em.getTransaction().begin();
        Codec codec = em.find(Codec.class, nameCodec);
        if (codec == null){
            codec = new Codec();
        }
        em.getTransaction().commit();
        return codec;
    }

    /**
     * find a codec by the name of format
     * @param nameFormat the name of format
     * @return the codec
     */
    public Codec FindCodecByNameFormat(String nameFormat){
        em.getTransaction().begin();
        String queryString = "select c.nameCodec from Codec c where c.dll.format.nameFormat=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,nameFormat);
        List results = query.getResultList();
        String nameCodec =null ;
        Codec codec = new Codec();
        if (results.size()!=0){
            nameCodec = results.get(0).toString();
            codec = em.find(Codec.class,nameCodec);
        }
        em.getTransaction().commit();
        return codec;
    }

    /**
     * find a codec by the extension
     * @param extension the extension
     * @return the codec
     */
    public Codec FindCodecByExtension(String extension){
        em.getTransaction().begin();
        String queryString = "select c.nameCodec from Codec c where c.dll.format.extension=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,extension);
        List results = query.getResultList();
        String nameCodec =null ;
        Codec codec = new Codec();
        if (results.size()!=0){
            nameCodec = results.get(0).toString();
            codec = em.find(Codec.class,nameCodec);
        }
        em.getTransaction().commit();
        return codec;
    }

    /**
     * find the codecs in a certain company
     * @param nameCompany the name of company
     * @return the list of codec
     */
    public List<Codec> FindCodecByNameCompany(String nameCompany){
        em.getTransaction().begin();
        List<Codec> codecs = new ArrayList<Codec>();
        String queryString = "select c.nameCodec from Codec c where c.company.nameCompany=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,nameCompany);
        List results = query.getResultList();
        Codec codec = new Codec();
        int m = results.size();
        for(int i=0;i<m;i++){
            String result = results.get(i).toString();
            codec = em.find(Codec.class,result);
            codecs.add(codec);
        }
        em.getTransaction().commit();
        return codecs;
    }

    /**
     * delete a codec by entering the name of codec
     * @param nameCodec the name of codec
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelCodecByNameCodec(String nameCodec){
    	Codec codec = em.find(Codec.class, nameCodec);
        if (codec!=null){
        	em.getTransaction().begin();
        	DllDao dllDao = new DllDao();
        	dllDao.DelDllByNameDocument(codec.getDll().getNameDocument());
        	em.remove(codec);
        	codec = em.find(Codec.class,nameCodec);
        	em.getTransaction().commit();
        	em.close();
        	if (codec == null)
        		return true;
        	else
        		return false;
        }
        else
        	return false;
    }

    /**
     * delete a codec by the name of format
     * @param nameFormat the name of format
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelCodecByNameFormat(String nameFormat){
        Codec codec = FindCodecByNameFormat(nameFormat);
        if (codec.getNameCodec()!=null){
            em.getTransaction().begin();
            DllDao dllDao = new DllDao();
            dllDao.DelDllByNameDocument(codec.getDll().getNameDocument());
            em.remove(codec);
            em.getTransaction().commit();
            codec = FindCodecByNameFormat(nameFormat);
            if (codec.getNameCodec()==null){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    /**
     * delete a codec by the extension
     * @param extension the extension
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelCodecByExtension(String extension){
        Codec codec = FindCodecByExtension(extension);
        if (codec.getNameCodec()!=null){
            em.getTransaction().begin();
            DllDao dllDao = new DllDao();
            dllDao.DelDllByNameDocument(codec.getDll().getNameDocument());
            em.remove(codec);
            em.getTransaction().commit();
            codec = FindCodecByExtension(extension);
            if (codec.getNameCodec()==null){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    /**
     * change the commercial information of certain codec
     * @param nameCodec the name of codec
     * @param newCompanyName the name of new company
     * @return true: change successfully or false: change failed
     */
    public boolean ChangeCompanyCodec(String nameCodec, String newCompanyName){

        Codec codec = em.find(Codec.class,nameCodec);
        Company company = em.find(Company.class,newCompanyName);
        if (codec!=null&&company!=null){
            em.getTransaction().begin();
            codec.setCompany(company);
            em.getTransaction().commit();
            codec = em.find(Codec.class,nameCodec);
            if (codec.getCompany().getNameCompany()==newCompanyName){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}

