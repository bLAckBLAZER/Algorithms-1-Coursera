/* *****************************************************************************
 *  Name: Omkar Jadhav
 *  Date: 31/05/2020
 *  Description: Double-ended queue which add elements from both left and right, and also can remove elements from both sides.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node start;
    private Node end;
    private int size;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        start = null;
        end = null;
        size = 0;

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the deque
    public int size() {
        return size;

    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();

        }
        if (size == 0) {
            start = new Node();
            start.item = item;
            start.next = null;
            start.prev = null;
            end = start;
        }
        else {
            Node temp = new Node();
            temp.item = item;
            temp.next = start;
            temp.prev = null;
            start.prev = temp;
            start = temp;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();

        }
        if (size == 0) {
            end = new Node();
            end.item = item;
            end.next = null;
            end.prev = null;
            start = end;
        }
        else {
            Node temp = new Node();
            temp.item = item;
            temp.next = null;
            temp.prev = end;
            end.next = temp;
            end = temp;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else if (size == 1) {
            Item returnItem = start.item;
            start = end = null;
            size--;
            return returnItem;


        }
        else {
            Item returnItem = start.item;
            start = start.next;
            start.prev = null;
            size--;
            return returnItem;
        }

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else if (size == 1) {
            Item returnItem = end.item;
            start = end = null;
            size--;
            return returnItem;


        }
        else {
            Item returnItem = end.item;
            end = end.prev;
            end.next = null;
            size--;
            return returnItem;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new CustomIter();
    }

    private class CustomIter implements Iterator<Item> {
        private Node current = start;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();


        String text = "there";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "you";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "are";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "How ";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "Hello";
        deque.addFirst(text);
        StdOut.println("addFirst() with: '" + text + "'");

        text = "!!!";
        deque.addLast(text);
        StdOut.println("addLast() with: '" + text + "'");


        StdOut.println("removeFirst(): " + deque.removeFirst());


        StdOut.println("removeLast(): " + deque.removeLast());

        text = "???";
        deque.addLast(text);
        StdOut.println("addLast() with: '" + text + "'");

        StdOut.println("Iterating deque...");
        for (String item : deque) {
            StdOut.println("Iterate element: " + item);
        }

    }
}
