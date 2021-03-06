package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.attribute.standard.MediaSize;

import DAO.CodecDao;
import DAO.CompanyDao;
import model.Codec;
import model.Company;

import java.util.List;

/**
 * controller of Codec
 */

public class CodecController {

	/**
	 * find the commercial information of a Codec
	 * @param nameCodec the name of codec
	 * @return the name of company or codec not exist or open source
	 */
	public static String FindCompanyByCodec(String nameCodec){
		Codec codec = CodecDao.FindCodecByName(nameCodec);
		if(codec.getCompany()!=null) 
			return codec.getCompany().getNameCompany();
		else if (codec.getNameCodec()==null){
			return "codec not exist";
		}
		else 
			return "open source";
	}

	public static Codec FindCodecByName(String Name){
		CodecDao codecDao = new CodecDao();
		Codec codec= codecDao.FindCodecByName(Name);
		return codec;
	}

	public static boolean AddCodec(String text1, String text2,String text3,String text4,String text5){
		CodecDao codecDao = new CodecDao();
		boolean status = codecDao.AddCodec(text1, text2, text3, text4, text5);
		return status;
	}

	public static List<Codec> ListAll(){
		CodecDao codecDao = new CodecDao();
		List<Codec> list = codecDao.ListAll();
		return list;
	}


}
