package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
	    QueueNode<E> n = new QueueNode<>(e);
	    if (last == null) {
	        n.next = n; // Should refer to itself
        } else {
	        n.next = last.next; // Copy next ref. from last node.
            last.next = n; // Make last node point to new one.
        }

        // Set the new node as the last one and increase size.
        last = n;
	    size++;

		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null
	 * 			if this queue is empty
	 */
	public E peek() {
		return (last != null) ? last.next.element : null;
	}

	/**
	 * Retrieves and removes the head of this queue,
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty
	 */
	public E poll() {
	    E element = null;
	    if (last != null) {
	        element = last.next.element;

	        if (last.next == last) {
	            last = null;
            } else {
	            last.next = last.next.next;
            }

            size--;
        }

		return element;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {

	    private QueueNode<E> pos;

	    private QueueIterator() {
	        pos = (last != null) ? last.next : null;
        }

        @Override
        public boolean hasNext() {
            return (pos != null);
        }

        @Override
        public E next() {
	        if (hasNext()) {
	            E element = pos.element;
	            if (pos != last) {
	                pos = pos.next;
                } else {
	                pos = null;
                }
                return element;
            } else {
	            throw new NoSuchElementException();
            }
        }
    }

    /**
     * Appends the specified queue to this queue
     * post: all elements from the specified queue are appended
     * to this queue. The specified queue (q) is empty after the call.
     * @param q the queue to append
     * @throws IllegalArgumentException if this queue and q are identical
     */
    public void append(FifoQueue<E> q) {
        if (q != this) {
            if (q.last != null) {
                QueueNode<E> last_node = q.last;
                QueueNode<E> first_node = q.last.next;

                if (last != null) {
                    last_node.next = last.next;
                    last.next = first_node;
                }

                last = last_node; // Point to the new end of the list
                size += q.size; // Update size

                // Reset queue
                q.last = null;
                q.size = 0;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

}
