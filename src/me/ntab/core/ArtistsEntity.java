package me.ntab.core;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artists", schema = "mydb", catalog = "")
public class ArtistsEntity {
    private int artistId;
    private String name;
    private Set<AlbumsEntity> albums = new HashSet<>();
    private Set<SongsEntity> songs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    public Set<AlbumsEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<AlbumsEntity> albums) {
        this.albums = albums;
    }

    public void addAlbum(AlbumsEntity album) {
        album.setArtist(this);
        this.albums.add(album);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    public Set<SongsEntity> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongsEntity> songs) {
        this.songs = songs;
    }

    public void addSong(SongsEntity song) {
        song.setArtist(this);
        this.songs.add(song);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artistId")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistsEntity that = (ArtistsEntity) o;
        return artistId == that.artistId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, name);
    }
}
