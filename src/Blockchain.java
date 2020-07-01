import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

class Blockchain implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int ONE = 1;
    private static final String FILENAME = "blockchain_data.txt";
    private final int noOfZeroes;
    private final LinkedList<Block> blockchain;

    Blockchain(int noOfZeroes) {
        blockchain = new LinkedList<>();
        this.noOfZeroes = noOfZeroes;
    }

    public void generateNewBlock() {
        Block block;
        if (blockchain.size() == 0) {
            block = new Block(0, null, 0);
            block = createBlock(block);
        } else {
            block = createBlock(blockchain.getLast());
        }
        blockchain.addLast(block);
        try {
            SerializationUtils.serialize(this, FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(block);
    }

    private Block createBlock(Block lastBlock) {
        String currentHash;
        int magicNumber;
        String previousHash = lastBlock.getCurrentHash();
        Block block;
        long id = lastBlock.getId() + ONE;
        Random random = new Random();
        do {
            magicNumber = random.nextInt();
            block = new Block(id, previousHash, magicNumber);
            currentHash = block.getCurrentHash();
        } while (StringUtil.countZeroes(currentHash) != noOfZeroes);
        return block;
    }

    public boolean validate(int noOfZeroes) {
        if (this.noOfZeroes != noOfZeroes) return false;
        if (blockchain.size() == 0) return true;
        String hash = "0";
        for (Block block : blockchain) {
            if (!block.getPreviousHash().equals(hash))
                return false;
            hash = StringUtil.applySha256(block.toString());
        }
        return true;
    }

    public void print(Block block) {
        System.out.println("\nBlock:");
        System.out.println("Id: " + block.getId());
        System.out.println("Timestamp: " + block.getTimeStamp());
        System.out.println("Magic number: " + block.getMagicNumber());
        System.out.println("Hash of the previous block:");
        System.out.println(block.getPreviousHash());
        System.out.println("Hash of the block:");
        System.out.println(block.getCurrentHash());
    }
}