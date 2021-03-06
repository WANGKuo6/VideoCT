package DAO;

import model.Format;
import model.Video;
import DAO.FormatDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * the Dao of video
 */
public class VideoDao {
	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
    public static EntityManager em = entityManagerFactory.createEntityManager();

    /**
     * get all of videos
     * @return the list of video
     */
	public List<Video> ListAll(){
		em.getTransaction().begin();
		String queryString = "from Video v";
        Query query = em.createQuery(queryString);
        List<Video> results = query.getResultList();
        List<Video> videos = new ArrayList<Video>();
        int m = results.size();
        for(int i = 0; i < m; i++){
            Video video = em.find(Video.class, results.get(i).getId());
            videos.add(video);
        }
        em.getTransaction().commit();
		return videos;
	}

    /**
     * add a video
     * @param nameVideo the name of video
     * @param nameFormat the name of format
     * @return true: "Add Succeed!" or false: "Format dosen't exist!"
     */
    public String AddVideoBYFormatInfo(String nameVideo, String nameFormat){
        em.getTransaction().begin();
        DAO.FormatDao formatDao = new FormatDao();
        Format format = formatDao.FindFormatBYName(nameFormat);
        Video video;
        if (format.getNameFormat() != null) {
            video = new Video(nameVideo,format);
        	em.persist(video);
        	em.getTransaction().commit();
        	return "Add Succeed!";
        }
        else {
        	return "Format dosen't exist!";
        }
    }

    /**
     * add a video by the extension of format
     * @param nameVideo the name of video
     * @param extension the extension of format
     * @return true: add successfully or false: add failed
     */
    public boolean AddVideoBYExtension(String nameVideo,String extension){
        Video video1 = FindVideoByNameVideoExtension(nameVideo,extension);
        em.getTransaction().begin();
        if (video1.getNameVideo()==null){
            FormatDao formatDao = new FormatDao();
            Format format = formatDao.FindFormatBYExtension(extension);
            Video video = new Video();
            if (format.getNameFormat()!=null){
                video = new Video(nameVideo,format);
            }
            else {
                return false;
            }
            em.persist(video);
            em.getTransaction().commit();
            Video video2 = FindVideoByNameVideoExtension(nameVideo,extension);
            if (video2.getNameVideo()!=null){
                return true;
            }else{
                return false;
            }
        }else {
            em.getTransaction().commit();
            return false;
        }
    }

    /**
     * find the videos by the name of videos
     * @param nameVideo the name of videos
     * @return the list of videos
     */
    public List<Video> FindVideoBYNameVideo(String nameVideo){
        em.getTransaction().begin();
        String queryString = "from Video v where v.nameVideo=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,nameVideo);
        List<Video> results = query.getResultList();
        List<Video> videos = new ArrayList<Video>();
        int m = results.size();
        for(int i = 0; i < m; i++){
            Video video = em.find(Video.class, results.get(i).getId());
            videos.add(video);
        }
        em.getTransaction().commit();
        return videos;
    }

    /**
     * find the videos by name of format
     * @param nameFormat the name of video
     * @return the list of videos
     */
    public List<Video> FindVideoBYNameFormat(String nameFormat){
        em.getTransaction().begin();
        String queryString = "select v.id,v.nameVideo from Video v where v.format.nameFormat=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,nameFormat);
        List results = query.getResultList();
        List<Video> videos = new ArrayList<Video>();
        int m = results.size();
        for(int i=0;i<m;i++){
            Object[] result = (Object[])results.get(i);
            Video video = em.find(Video.class,result[0]);
            videos.add(video);
        }
        em.getTransaction().commit();
        return videos;
    }

    /**
     * find the videos by the extension of format
     * @param extension the extension of format
     * @return the list of videos
     */
    public List<Video> FindVideoBYExtension(String extension){
        em.getTransaction().begin();
        String queryString = "select v.id,v.nameVideo from Video v where v.format.extension=?1";
        Query query = em.createQuery(queryString);
        query.setParameter(1,extension);
        List results = query.getResultList();
        List<Video> videos = new ArrayList<Video>();
        int m = results.size();
        for(int i=0;i<m;i++){
            Object[] result = (Object[])results.get(i);
            Video video = em.find(Video.class,result[0]);
            videos.add(video);
        }
        em.getTransaction().commit();
        return videos;
    }

    /**
     * find the video by the name of format
     * @param nameVideo the name of video
     * @param nameFormat the name of format
     * @return the video
     */
    public Video FindVideoByNameVideoNameFormat(String nameVideo,String nameFormat) {
        em.getTransaction().begin();
        String queryString = "select v.id,v.nameVideo from Video v where v.nameVideo=?1 and v.format.nameFormat=?2";
        Query query = em.createQuery(queryString);
        query.setParameter(1, nameVideo);
        query.setParameter(2, nameFormat);
        List results = query.getResultList();
        Video video = new Video();
        int m = results.size();
        for (int i = 0; i < m; i++) {
            Object[] result = (Object[]) results.get(i);
            video = em.find(Video.class, result[0]);
        }
        em.getTransaction().commit();
        return video;
    }
    /**
     * find the video by the extension of video
     * @param nameVideo the name of video
     * @param extension the extension of video
     * @return the video
     */
    public Video FindVideoByNameVideoExtension(String nameVideo, String extension){
        em.getTransaction().begin();
        String queryString = "from Video v where v.nameVideo=?1 and v.format.extension=?2";
        Query query = em.createQuery(queryString);
        query.setParameter(1,nameVideo);
        query.setParameter(2,extension);
        List<Video> results = query.getResultList();
        Video video = em.find(Video.class, results.get(0).getId());
        return video;
    }

    /**
     * delete the video by the name of format
     * @param nameVideo the name of video
     * @param nameFormat the name of format
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelVideoByNameVideoNameFormat(String nameVideo,String nameFormat){
        Video video = FindVideoByNameVideoNameFormat(nameVideo,nameFormat);
        em.getTransaction().begin();
        if (video.getNameVideo()!=null){
            em.remove(video);
            em.getTransaction().commit();
            video = FindVideoByNameVideoNameFormat(nameVideo,nameFormat);
            if (video.getNameVideo()==null){
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
     * delete the video by the extension of video
     * @param nameVideo the name of video
     * @param extension the extension of video
     * @return true:  "Delete Successed" or false: "Delete Failed"
     */
    public String DelVideoByNameVideoExtension(String nameVideo,String extension){
        em.getTransaction().begin();
        String queryString = "from Video v where v.nameVideo=?1 and v.format.extension=?2";
        Query query = em.createQuery(queryString);
        query.setParameter(1,nameVideo);
        query.setParameter(2,extension);
        List<Video> results = query.getResultList();
        Video video;
        int m = results.size();
        for(int i = 0; i < m; i++){
            video = em.find(Video.class,results.get(i).getId());
            if(video.getNameFormat() != null) {
            	em.remove(video);
            	em.getTransaction().commit();
            	//em.close();
            	return "Delete Successed";
            }
            else {
            	 em.getTransaction().commit();
            	 //em.close();
            	 return "Delete Failed";
            }
        }

        return "Delete Failed";
    }

    /**
     * change the name of video
     * @param oldNameVideo the old name of video
     * @param nameFormat the name of format
     * @param newNameVideo the new name of video
     * @return change successfully or false: change failed
     */
    public boolean ChangeVideoName(String oldNameVideo, String nameFormat, String newNameVideo){
        Video video = FindVideoByNameVideoNameFormat(oldNameVideo,nameFormat);
        if (video.getNameVideo()!=null){
            Video video1 = FindVideoByNameVideoNameFormat(newNameVideo,nameFormat);
            if (video1.getNameVideo()==null){
                video.setNameVideo(newNameVideo);
                video = FindVideoByNameVideoNameFormat(newNameVideo,nameFormat);
                if (video.getNameVideo()!=null){
                    return true;
                }
                else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    }


}
