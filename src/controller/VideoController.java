package controller;

import DAO.VideoDao;
import model.Video;

import java.util.List;

public class VideoController {
    public static List<Video> FindVideoBYNameVideo(String ParamName){
        VideoDao videoDao = new VideoDao();
        List<Video> list= videoDao.FindVideoBYNameVideo(ParamName);
        return list;
    }

    public static String DelVideo(String text1, String text2){
        VideoDao videoDao = new VideoDao();
        String status = videoDao.DelVideoByNameVideoExtension(text1, text2);
        return status;
    }

    public static List<Video> ListAll(){
        VideoDao videoDao = new VideoDao();
        List<Video> list = videoDao.ListAll();
        return list;

    }

}
