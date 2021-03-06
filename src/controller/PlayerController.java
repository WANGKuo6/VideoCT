package controller;

import DAO.*;
import jdk.dynalink.beans.StaticClass;
import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * the controller of player
 */
public class PlayerController {
    /**
     * find  the names of player which can play the format
     * @param paramFormat the name of format
     * @return list of player name
     */
    public static List<String> FindPlayerByForamt(String paramFormat){
        List<String> result = PlayerDao.getNamePlayer(paramFormat);
        return result;
    }

    public static Player FindPlayerByName(String name){
        PlayerDao playerDao = new PlayerDao();
        Player player = playerDao.FindPlayerByName(name);
        return player;
    }

    public static void AddPlayer(String ParamnamePlayer, String ParamnbKocketes, String ParamnameFormat){
        FormatDao formatDao = new FormatDao();
        CodecDao codecDao = new CodecDao();
        DllDao dllDao = new DllDao();
        PlayerDao playerDao = new PlayerDao();
        CompanyDao companyDao = new CompanyDao();

        String namePlayer = ParamnamePlayer;
        String nbKoctetsString = ParamnbKocketes;
        String nameFormat = ParamnameFormat;

        if(playerDao.FindPlayerByName(namePlayer).getNamePlayer() != null){
            JOptionPane.showMessageDialog(null, "The name of the player is already existed! please try again. ", " result", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Format format = formatDao.FindFormatBYName(nameFormat);
            if(format.getNameFormat() != null){
                Codec codec = codecDao.FindCodecByNameFormat(nameFormat);
                Dll dll = dllDao.FindDllByNameFormat(nameFormat);
                String extension = format.getExtension();
                String nameCodec = codec.getNameCodec();
                String nameCompany = codec.getCompany().getNameCompany();
                String web = codec.getCompany().getWeb();
                String nameDocument = dll.getNameDocument();

                int nbKoctets = Integer.parseInt(nbKoctetsString);
                List<Format> formats = new ArrayList<>();
                List<Codec> codecs = new ArrayList<>();
                List<Player> players = new ArrayList<>();
                Player player = new Player();
                Company company = new Company();
                formats.add(formatDao.FindFormatBYName(nameFormat));
                codecs.add(codecDao.FindCodecByName(nameCodec));
                boolean result1 = playerDao.AddPlayer(namePlayer,nbKoctets,formats,codecs);
                boolean result2 = codecDao.AddCodec2(nameCodec, nameCompany, nameDocument);
                boolean result3 = dllDao.AddDll(nameDocument, nameFormat);
                boolean result5 = companyDao.AddCompany(nameCompany,web);
                boolean result4 = formatDao.AddFormat(nameFormat,extension);
                JOptionPane.showMessageDialog(null, "Congratulations! You have successd add a player!", " result", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "This format is not exist! Please try again!", " result", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void DelPlayer(String ParamNamePlayer){
        PlayerDao playerDao = new PlayerDao();
        boolean result = playerDao.DelPlayerByNamePlayer(ParamNamePlayer);
        if(result){
            JOptionPane.showMessageDialog(null, "delete success!", " result", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "delete failed!", " result", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<Player> ListAllPlayers(){
        PlayerDao playerDao = new PlayerDao();
        List<Player> list = playerDao.ListAll();
        return list;
    }


}
