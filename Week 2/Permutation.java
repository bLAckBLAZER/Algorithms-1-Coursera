/* *****************************************************************************
 *  Name: Omkar Jadhav
 *  Date: 31/05/2020
 *  Description: Permutation program to provide random k elements from the text provided using a randomized queue.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randQueue.enqueue(item);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(randQueue.dequeue());
        }

        // for (String s : randQueue) {
        //     System.out.println(s);
        // }

    }
}
