package nl.hu.v2iac1.domain;

/**
 * Created by Nathan on 12/02/2015.
 */
public class User {
    int level;

    public User(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
