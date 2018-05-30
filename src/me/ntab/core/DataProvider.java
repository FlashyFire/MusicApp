package me.ntab.core;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataProvider implements Serializable {
    private AlbumsEntity album = null;

    public DataProvider() {
    }

    public List<AlbumsEntity> getAlbumList() {
        Session session = null;
        List<AlbumsEntity> result = new ArrayList();
        try {
            session = HibernateUtils.getSession();
            Query query = session.createQuery("from AlbumsEntity o order by o.title");
            result = query.list();
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public AlbumsEntity getAlbum(int albumId) {
        Session session = null;
        try {
            session = HibernateUtils.getSession();
            Query query = session.createQuery("from AlbumsEntity o where o.albumId = :albumId");
            query.setParameter("albumId", albumId);
            return (AlbumsEntity)query.uniqueResult();
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void fetchAlbum(String albumId) {
        int id = Integer.parseInt(albumId);
        this.album = getAlbum(id);
    }

    public AlbumsEntity currentAlbum() {
        return this.album;
    }
}
