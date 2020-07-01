import java.io.Serializable;
import java.util.Date;

class Block implements Serializable {
    private static final long serialVersionUID = 2L;
    private final long id;
    private final String previousHash;
    private final int magicNumber;
    private final long timeStamp;
    private final String currentHash;

    Block(long id, String previousHash, int magicNumber) {
        this.id = id;
        this.magicNumber = magicNumber;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.currentHash = (this.previousHash == null ? "0" : StringUtil.applySha256(toString()));
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public long getId() {
        return id;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    @Override
    public String toString() {

        return id +
                previousHash +
                magicNumber +
                timeStamp;
    }
}
