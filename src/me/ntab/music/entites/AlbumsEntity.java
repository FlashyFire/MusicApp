package me.ntab.music.entites;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "albums", schema = "mydb", catalog = "")
public class AlbumsEntity {
    private int albumId;
    private Integer artistId;
    private String title;
    private Integer year;
    private String genre;
    private byte[] cover;
    private ArtistsEntity artist;
    private Set<SongsEntity> songs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album", fetch = FetchType.EAGER)
    public Set<SongsEntity> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongsEntity> songs) {
        this.songs = songs;
    }

    public void addSong(SongsEntity song) {
        song.setAlbum(this);
        this.songs.add(song);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "albumId")
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
    public ArtistsEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistsEntity artist) {
        this.artist = artist;
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
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "cover")
    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public List<SongsEntity> orderedSongs() {
        return songs.stream()
                .sorted((o1, o2) -> {
                    if (o1.getTrackNum() == null && o2.getTrackNum() != null) {
                        return 1;
                    }
                    if (o1.getTrackNum() != null && o2.getTrackNum() == null) {
                        return -1;
                    }
                    int result = 0;
                    if (o1.getTrackNum() != null) {
                        result = o1.getTrackNum().compareTo(o2.getTrackNum());
                    }
                    if (result == 0) {
                        result = o1.getTitle().compareTo(o2.getTitle());
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumsEntity that = (AlbumsEntity) o;
        return albumId == that.albumId &&
                Objects.equals(artistId, that.artistId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(year, that.year) &&
                Objects.equals(genre, that.genre) &&
                Arrays.equals(cover, that.cover);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(albumId, artistId, title, year, genre);
        result = 31 * result + Arrays.hashCode(cover);
        return result;
    }
}
