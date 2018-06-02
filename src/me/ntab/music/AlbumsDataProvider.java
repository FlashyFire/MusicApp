package me.ntab.music;

import me.ntab.music.entites.AlbumsEntity;
import me.ntab.music.entites.ArtistsEntity;
import me.ntab.music.entites.SongsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class AlbumsDataProvider implements Serializable {
    private static final String ALBUMS_ORDERED_BY_TITLE = "from AlbumsEntity o order by o.title";
    private static final String ALBUM_WITH_ID = "from AlbumsEntity o where o.albumId = :albumId";
    private static final String ARTIST_WITH_NAME = "from ArtistsEntity o where o.name = :name";

    private AlbumsEntity fetchedAlbum = null;

    public AlbumsDataProvider() {
    }

    public List<AlbumsEntity> getAlbumList() {
        List<AlbumsEntity> result = new ArrayList();
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery(ALBUMS_ORDERED_BY_TITLE);
            result = query.list();
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public AlbumsEntity currentAlbum() {
        return this.fetchedAlbum;
    }

    public void fetchAlbum(String albumId) {
        int id = Integer.parseInt(albumId);
        try (Session session = HibernateUtils.getSession()){
            this.fetchedAlbum = getAlbum(session, id);
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    public static void addAlbum(String title, String artistName, String genre, String year) {
        Transaction tx = null;
        try (Session session = HibernateUtils.getSession()){
            tx = session.beginTransaction();
            ArtistsEntity artist = getArtist(session, artistName);
            AlbumsEntity album = new AlbumsEntity();
            album.setTitle(title);
            album.setGenre(genre);
            if (!isNull(year) && !year.isEmpty()) {
                album.setYear(Integer.parseInt(year));
            }
            album.setArtist(artist);
            session.save(album);
            tx.commit();
        }
        catch (HibernateException ex) {
            tx.rollback();
            ex.printStackTrace();
        }
    }

    public static void addSong(String albumId, String title, String artistName, String trackNum, String duration) {
        Transaction tx = null;
        try (Session session = HibernateUtils.getSession()){
            tx = session.beginTransaction();
            AlbumsEntity album = getAlbum(session, Integer.parseInt(albumId));
            ArtistsEntity artist = getArtist(session, artistName);
            SongsEntity song = new SongsEntity();
            song.setArtist(artist);
            song.setAlbum(album);
            song.setTitle(title);
            if (!isNull(trackNum) && !trackNum.isEmpty()) {
                song.setTrackNum(Integer.parseInt(trackNum));
            }
            if (!isNull(duration) && !duration.isEmpty()) {
                String[] parts = duration.split(":");
                if (parts.length == 1)
                    song.setDuration(Integer.parseInt(parts[0]));
                else if (parts.length == 2)
                    song.setDuration(Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]));
            }
            session.save(song);
            tx.commit();
        }
        catch (HibernateException ex) {
            tx.rollback();
            ex.printStackTrace();
        }
    }

    private static AlbumsEntity getAlbum(Session session, int albumId) {
        Query query = session.createQuery(ALBUM_WITH_ID);
        query.setParameter("albumId", albumId);
        return (AlbumsEntity)query.uniqueResult();
    }

    private static ArtistsEntity getArtist(Session session, String artistName) {
        if (isNull(artistName) || artistName.isEmpty())
            return null;
        Query query = session.createQuery(ARTIST_WITH_NAME);
        query.setParameter("name", artistName);
        ArtistsEntity artist = (ArtistsEntity) query.uniqueResult();
        if (isNull(artist)) {
            artist = new ArtistsEntity();
            artist.setName(artistName);
            session.save(artist);
        }
        return artist;
    }
}
