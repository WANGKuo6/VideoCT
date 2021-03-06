package DAO;

import model.Codec;
import model.Format;
import model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * the Dao of Player
 */
public class PlayerDao {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sgbd");
    public static EntityManager em = entityManagerFactory.createEntityManager();

    /**
     * get the names of players which can play the format
     * @param paramFormat the name format
     * @return the list of the names of players
     */
    public static List<String> getNamePlayer(String paramFormat){
        em.getTransaction().begin();
        String sql = "select namePlayer from Player_Format where Player_Format.nameFormat = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, paramFormat);
        List<String> results = query.getResultList();
        em.getTransaction().commit();
        return results;
    }

    /**
     * add a player
     * @param namePlayer the name of player
     * @param nbKoctets the number of koctets
     * @param formats the formats which the player can play
     * @param codecs the formats which the player own
     * @return true: add successfully or false: add failed
     */
    public boolean AddPlayer(String namePlayer, int nbKoctets, List<Format> formats, List<Codec> codecs) {
        Player player = em.find(Player.class,namePlayer);
        if (player==null){
            em.getTransaction().begin();
            player = new Player(namePlayer,nbKoctets,formats,codecs);
            em.persist(player);
            em.getTransaction().commit();
            player = em.find(Player.class,namePlayer);
            if (player!=null){
                return true;
            }else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * find a player by the name of player
     * @param namePlayer the name of player
     * @return the player
     */
    public Player FindPlayerByName(String namePlayer){
        em.getTransaction().begin();
        Player player = em.find(Player.class,namePlayer);
        if (player==null){
            player = new Player();
        }
        em.getTransaction().commit();
        return player;
    }

    /**
     * delete a player by the name of player
     * @param namePlayer the name of player
     * @return true: delete successfully or false: delete failed
     */
    public boolean DelPlayerByNamePlayer(String namePlayer){
        em.getTransaction().begin();
        Player player = em.find(Player.class,namePlayer);
        if (player!=null){
            em.remove(player);
            em.getTransaction().commit();
            player = em.find(Player.class,namePlayer);
            if (player==null){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * add a codec in a player
     * @param namePlayer the name player
     * @param nameCodec the name of codec
     */
    public void AddCodecInPlayer(String namePlayer, String nameCodec){
        Player player = em.find(Player.class,namePlayer);
        Codec codec = em.find(Codec.class,nameCodec);
        if (player!=null&&codec!=null){
            em.getTransaction().begin();
            List<Codec> codecs = player.getCodecs();
            codecs.add(codec);
            player.setCodecs(codecs);
            em.getTransaction().commit();
        }
    }

    /**
     * add a format in a player
     * @param namePlayer the name of player
     * @param nameFormat the name of format
     */
    public void AddFormatInPlayer(String namePlayer, String nameFormat){
        em.getTransaction().begin();
        Player player = em.find(Player.class,namePlayer);
        if (player!=null){
            Format format = em.find(Format.class,nameFormat);
            if (format!=null){
                List<Format> formats = player.getFormats();
                formats.add(format);
                player.setFormats(formats);
            }
        }
        em.getTransaction().commit();
    }

    /**
     * delete a codec in a player
     * @param namePlayer the name of player
     * @param nameCodec the name of codec
     */
    public void DelCodecInPlayer(String namePlayer, String nameCodec){
        em.getTransaction().begin();
        Player player = em.find(Player.class,namePlayer);
        if (player!=null){
            Codec codec = em.find(Codec.class,nameCodec);
            if (codec!=null){
                List<Codec> codecs = player.getCodecs();
                codecs.remove(codec);
                player.setCodecs(codecs);
            }
        }
        em.getTransaction().commit();
    }

    /**
     * delete a format in a player
     * @param namePlayer the name of player
     * @param nameFormat the name of format
     * @param nameFormat the name of format
     */
    public void DelFormatInPlayer(String namePlayer, String nameFormat){
        em.getTransaction().begin();
        Player player = em.find(Player.class,namePlayer);
        if (player!=null){
            Format format = em.find(Format.class,nameFormat);
            List<Format> formats = player.getFormats();
            if (format!=null){
                formats.remove(format);
                player.setFormats(formats);
            }
        }
        em.getTransaction().commit();
    }

    /**
     * change the number of koctets
     * @param namePlayer the name of player
     * @param newNBKoctets the new number of koctets
     * @return
     */
    public boolean ChangeNBKoctetsPlayer(String namePlayer, int newNBKoctets){
        em.getTransaction().begin();
        Player player = em.find(Player.class,namePlayer);
        if (namePlayer!=null){
            player.setNbKoctets(newNBKoctets);
            em.getTransaction().commit();
            player = em.find(Player.class,namePlayer);
            if (player.getNbKoctets()==newNBKoctets){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * get all of player
     * @return the list of players
     */
    public List<Player> ListAll(){
        em.getTransaction().begin();
        String query = "from Player p";
        Query query1 = em.createQuery(query);
        List<Player> players = query1.getResultList();
        List<Player> result = new ArrayList<Player>();
        Player player;
        for(int i = 0; i < players.size(); i++){
            player = em.find(Player.class, players.get(i).getNamePlayer());
            result.add(player);
        }
        em.getTransaction().commit();
        return result;
    }
}
