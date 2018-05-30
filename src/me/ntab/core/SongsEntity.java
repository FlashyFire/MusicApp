package me.ntab.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "songs", schema = "mydb", catalog = "")
public class SongsEntity {
    private int songId;
    private Integer artistId;
    private Integer albumId;
    private Integer trackNum;
    private String title;
    private Integer duration;
    private ArtistsEntity artist;
    private AlbumsEntity album;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "songId")
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Basic
    @Column(name = "artistId", insertable = false, updatable = false)
    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    @ManyToOne
    @JoinColumn(name = "artistId", referencedColumnName = "artistId")
    public ArtistsEntity getArtist() { return artist; }

    public void setArtist(ArtistsEntity artist) { this.artist = artist; }

    @Basic
    @Column(name = "albumId", insertable = false, updatable = false)
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @ManyToOne
    @JoinColumn(name = "albumId", referencedColumnName = "albumId")
    public AlbumsEntity getAlbum() { return album; }

    public void setAlbum(AlbumsEntity album) { this.album = album; }

    @Basic
    @Column(name = "trackNum")
    public Integer getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(Integer trackNum) {
        this.trackNum = trackNum;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String durationString() {
        return String.format("%1$d:%2$02d", duration / 60, duration % 60);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongsEntity that = (SongsEntity) o;
        return songId == that.songId &&
                Objects.equals(artistId, that.artistId) &&
                Objects.equals(albumId, that.albumId) &&
                Objects.equals(trackNum, that.trackNum) &&
                Objects.equals(title, that.title) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, artistId, albumId, trackNum, title, duration);
    }
}
