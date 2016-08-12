package clipboard.anishhegde.com.clipboard;

/**
 * Created by anishhegde on 16/07/16.
 */
public class Clip {

    private long id;
    private String clip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClip() {
        return clip;
    }

    public void setClip(String comment) {
        this.clip = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return clip;
    }
}
