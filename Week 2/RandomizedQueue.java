/* *****************************************************************************
 *  Name: Omkar Jadhav
 *  Date: 31/05/2020
 *  Description: Randomized queue which store elements and pops in random order.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int top = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return top == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return top;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (top == queue.length) {
            resize(2 * queue.length);
        }
        queue[top++] = item;
    }

    private void resize(int capacity) {
        Item temp[] = (Item[]) new Object[capacity];
        for (int i = 0; i < top; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (top == 0) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniform(top);
        Item item = queue[i];
        queue[i] = queue[top - 1];
        queue[top - 1] = null;
        if (top == queue.length / 4) {
            resize(queue.length / 2);
        }
        top--;
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (top == 0) {
            throw new NoSuchElementException();
        }
        return queue[StdRandom.uniform(top)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new CustomIter();
    }

    private class CustomIter implements Iterator<Item> {
        private Item[] temp;
        private int tempTop;

        private CustomIter() {
            temp = (Item[]) new Object[top];
            for (int i = 0; i < top; i++) {
                temp[i] = queue[i];
            }
            tempTop = temp.length;
        }


        public boolean hasNext() {
            return tempTop != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int i = StdRandom.uniform(tempTop);
            Item item = temp[i];

            temp[i] = temp[tempTop - 1];
            temp[tempTop - 1] = null;
            tempTop--;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> deque = new RandomizedQueue<>();


        String text = "How";
        deque.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "are";
        deque.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "you";
        deque.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "I ";
        deque.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "hope";
        deque.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "this";
        deque.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");


        StdOut.println("dequeue(): " + deque.dequeue());


        StdOut.println("dequeue(): " + deque.dequeue());

        text = "works";
        deque.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        StdOut.println("Iterating deque randomly...");
        for (String item : deque) {
            StdOut.println("Iterate element: " + item);
        }

    }
}
