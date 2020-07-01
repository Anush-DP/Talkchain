import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static final String FILENAME = "blockchain_data.txt";

    public static void main(String[] args) {
        int n;
        Blockchain blockchain;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how many zeros the hash must start with: ");
        n = scanner.nextInt();
        File file = new File(FILENAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            blockchain = (Blockchain) SerializationUtils.deserialize(FILENAME);
            if (!blockchain.validate(n)) {
                blockchain = new Blockchain(n);
            }
        } catch (IOException | ClassNotFoundException e) {
            blockchain = new Blockchain(n);
        }
        for (int i = 0; i < 5; i++) {
            long timeStamp = new Date().getTime();
            blockchain.generateNewBlock();
            System.out.println("Block was generating for " + (float) (new Date().getTime() - timeStamp) / 1000 + " seconds");
        }
    }
}
