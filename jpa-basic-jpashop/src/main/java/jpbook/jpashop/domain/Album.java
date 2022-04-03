package jpbook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item {

    private String artist;
    private String getc;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGetc() {
        return getc;
    }

    public void setGetc(String getc) {
        this.getc = getc;
    }
}
