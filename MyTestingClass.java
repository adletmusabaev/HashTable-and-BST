import java.util.Random;

public class MyTestingClass {
    private int key;

    public MyTestingClass(int key) {
        this.key = key;
    }


    public int getKey() {
        return key;
    }

    @Override
    public int hashCode() {
        return key % 11; // very simple example for demonstration, should be improved for production use
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int randomKey = random.nextInt(1000);
            MyTestingClass testClass = new MyTestingClass(randomKey);
            table.put(testClass, i);
        }

        for (int i = 0; i < table.chainArray.length; i++) {
            int count = 0;
            MyHashTable<MyTestingClass, Integer>.HashNode<MyTestingClass, Integer> node = table.chainArray[i];
            while (node != null) {
                count++;
                node = node.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}
