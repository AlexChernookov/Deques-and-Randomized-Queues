import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by a178300 on 7/21/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item items[];

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    private void resize(int n) {
        Item newItems[] = (Item[]) new Object[n];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (size == items.length)
            resize(items.length * 2);
        items[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int random = StdRandom.uniform(size);
        Item item = items[random];
        if (random != size - 1)
            items[random] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size > 0 && size == items.length / 4)
            resize(items.length / 2);
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int random = StdRandom.uniform(size);
        Item item = items[random];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int index = 0;
        private int newLength = size;
        private Item newArray[] = (Item[]) new Object[size];

        private QueueIterator() {
            for (int i = 0; i < newLength; i++) {
                newArray[i] = items[i];
            }
        }

        public boolean hasNext() {
            return index <= newLength - 1;
        }

        public Item next() {
            if (newArray[index] == null)
                throw new NoSuchElementException();
            int random = StdRandom.uniform(newLength);
            Item item = newArray[random];
            if (random != newLength - 1)
                newArray[random] = newArray[newLength - 1];
            newLength--;
            newArray[newLength] = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
    }
}