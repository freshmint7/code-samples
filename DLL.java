package myLib.datastructures.Linear;

import myLib.datastructures.nodes.DNode;

/**
 * A doubly linked list (DLL) is a linked data structure that consists of a set
 * of sequentially linked nodes.
 * Each node contains two fields, called links, that are references to the
 * previous and to the next node in the sequence of nodes.
 * This DLL class contains methods for inserting, deleting, searching, and
 * sorting nodes in the DLL.
 */
public class DLL {
    DNode head;
    DNode tail;
    int size;

    /**
     * Returns the head node of the DLL.
     *
     * @return the head node of the DLL.
     */
    public DNode getHead() {
        return this.head;
    }

    /**
     * Returns the tail node of the DLL.
     *
     * @return the tail node of the DLL.
     */
    public DNode getTail() {
        return this.tail;
    }

    /**
     * Returns the number of nodes in the DLL.
     *
     * @return the size of the DLL.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Constructs an empty DLL.
     */
    public DLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Constructs a DLL with a single node.
     *
     * @param node the node to be added to the DLL.
     */
    public DLL(DNode node) {
        this.head = node;
        this.tail = node;
        this.size = 1;
    }

    /**
     * Inserts a node at the beginning of the DLL.
     *
     * @param node the node to be inserted at the beginning of the DLL.
     */
    public void InsertHead(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        size++;
    }

    /**
     * Inserts a node at the end of the DLL.
     *
     * @param node the node to be inserted at the end of the DLL.
     */
    public void InsertTail(DNode node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
        size++;
    }

    /**
     * Inserts a node at the specified position in the DLL.
     *
     * @param node     the node to be inserted at the specified position in the DLL.
     * @param position the position at which the node is to be inserted in the DLL.
     * @throws IndexOutOfBoundsException if the specified position is out of range
     *                                   (position < 0 || position > size).
     */
    public void Insert(DNode node, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException();
        }
        if (position == 0) {
            InsertHead(node);
        } else if (position == size) {
            InsertTail(node);
        } else {
            DNode current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            node.setPrevious(current);
            current.setNext(node);
            size++;
        }
    }

    /**
     * 
     * Inserts a node into the list in sorted order.
     * If the list is not sorted, it will be sorted before the insertion.
     * 
     * @param node the node to be inserted
     */
    public void SortedInsert(DNode node) {
        if (!isSorted()) {
            Sort();
        }
        if (head == null) { // if the list is empty
            head = node;
            tail = node;
        } else if (node.getData() <= head.getData()) { // if the node should be inserted at the beginning of the list
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        } else if (node.getData() >= tail.getData()) { // if the node should be inserted at the end of the list
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        } else { // if the node should be inserted somewhere in the middle of the list
            DNode current = head.getNext();
            DNode previous = head;
            while (current != null && node.getData() > current.getData()) {
                previous = current;
                current = current.getNext();
            }
            node.setNext(current);
            node.setPrevious(previous);
            current.setPrevious(node);
            previous.setNext(node);
        }
        // check if the list is sorted

    }

    /**
     * 
     * Checks if the list is sorted in non-descending order.
     * 
     * @return true if the list is sorted, false otherwise
     */
    public boolean isSorted() {
        if (head == null || head.getNext() == null) {
            return true;
        }
        DNode current = head;
        while (current.getNext() != null) {
            if (current.getData() > current.getNext().getData()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * 
     * Searches for a node in the list that contains the same data as the specified
     * node.
     * 
     * @param node the node to search for
     * @return the node in the list that matches the specified node, or null if not
     *         found
     */
    public DNode Search(DNode node) {
        DNode current = head;
        for (int i = 1; i < this.size; i++) {
            if (current.getData() == node.getData()) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * 
     * Removes the first node in the list.
     */
    public void DeleteHead() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
    }

    /**
     * 
     * Removes the last node in the list.
     */
    public void DeleteTail() {
        if (tail == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            DNode newTail = tail.getPrevious();
            newTail.setNext(null);
            head.setPrevious(newTail);
            tail = newTail;
        }
        size--;
    }

    /**
     * 
     * Removes a specified node from the list.
     * 
     * @param node the node to be removed
     */
    public void Delete(DNode node) {
        if (head == null) { // if the list is empty
            return;
        }
        if (head == node) { // if the node to delete is the head
            DeleteHead();
            return;
        }
        DNode current = head.getNext();
        DNode previous = current.getPrevious();
        while (current != null && current != node) {
            previous = current;
            current = current.getNext();
        }
        if (current == null) { // if the node to delete was not found
            return;
        }
        previous.setNext(current.getNext());
        current.getNext().setPrevious(previous);
        if (current == tail) { // if the node to delete is the tail
            tail.setPrevious(null);
            tail = previous;
        }
        size--;
    }

    /**
     * Sorts the doubly linked list in non-decreasing order using insertion sort.
     * If the list has size 0 or 1, it is already sorted and this method returns
     * immediately.
     */
    public void Sort() {
        if (size <= 1) {
            return; // already sorted/empty
        }

        DNode sortedHead = head; // start with the first node as the sorted head
        DNode unsorted = head.getNext();

        while (unsorted != null) { // iterate over the list
            DNode nodeToInsert = unsorted; // get next node to insert
            unsorted = unsorted.getNext(); 

            DNode current = sortedHead; // parse list to find sorted insertion point
            DNode prev = null;
            while (current != null && current.getData() < nodeToInsert.getData()) {
                prev = current;
                current = current.getNext();
            }

            nodeToInsert.getPrevious().setNext(nodeToInsert.getNext()); // remove nodeToInsert from previous spot
            if (nodeToInsert.getNext() != null) nodeToInsert.getNext().setPrevious(nodeToInsert.getPrevious());

            if (prev == null) { // insert to beginning of the list
                nodeToInsert.setNext(sortedHead);
                nodeToInsert.setPrevious(null);
                sortedHead = nodeToInsert;
            
            } else { // insert after prev
                nodeToInsert.setNext(prev.getNext());
                nodeToInsert.setPrevious(prev);
                prev.setNext(nodeToInsert);
            }
        }

        head = sortedHead; // update head and tail pointers
        DNode current = head;
        while (current.getNext() != null){ // iterate until we find the last node in the list
            current = current.getNext();
        }
        tail = current;
        tail.setNext(null);
    }

    /**
     * Removes all elements from the doubly linked list, setting the head and tail
     * pointers to null and size to 0.
     */
    public void Clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Prints the length of the doubly linked list, whether it is sorted or not, and
     * the contents of the list in order.
     */
    public void Print() {
        // Print list length
        System.out.println("List length: " + size);

        // Print sorted status
        if (isSorted()) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List is not sorted");
        }

        // Print list content
        System.out.print("List content: ");
        DNode current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
