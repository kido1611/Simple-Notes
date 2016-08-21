package id.kido1611.simplenote.object;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ahmad on 21/08/2016.
 */
public class NoteItem extends RealmObject{
    @PrimaryKey
    private int id;
    private String title;
    private String note;
    private long date;
    private boolean archieve;

    public boolean isArchieve() {
        return archieve;
    }

    public void setArchieve(boolean archieve) {
        this.archieve = archieve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
