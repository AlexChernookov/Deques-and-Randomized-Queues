import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by a178300 on 7/21/2017.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
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
        if (item == null)
            throw new NullPointerException();
        Node newFirst = first;
        first = new Node();
        first.prev = null;
        first.item = item;
        if (isEmpty()) {
            first.next = null;
            last = first;
        } else {
            first.next = newFirst;
            newFirst.prev = first;
        }
        size++;

    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node newLast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        if (isEmpty()) {
            last.prev = null;
            first = last;
        } else {
            newLast.next = last;
            last.prev = newLast;
        }
        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        // Should consider if there are just one node in the Linkedlist
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        //while (!StdIn.isEmpty()) {
        //String item = StdIn.readString();
        deque.addFirst("study");
        deque.addFirst("I");
        deque.addFirst("and");
        deque.addFirst("You");
//        deque.addLast("in");
//        deque.addLast("NYU");
//        deque.addLast("Poly");
//        deque.removeLast();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        StdOut.println("output:");
        for (String x : deque) {
            StdOut.print(x + ' ');
        }


    }
}