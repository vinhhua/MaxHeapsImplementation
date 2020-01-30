package com.VinhHua;

public class Heap {
    private int[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new int[capacity];
    }


    /**
     * Insert a new value into the heap whilst keeping the heap property.
     * @param value an int, easy to implement as practice.
     */
    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("The Heap is full");
        }
        heap[size] = value;
        fixHeapAbove(size);
        size++;
    }


    /**
     * Delete an index from the heap whilst maintaining the heap property.
     * @param index an index.
     * @return return the node, value that was removed.
     */
    public int delete(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The heap is empty");
        }
        int parent = getParent(index);
        int deletedValue = heap[index];

        heap[index] = heap[size - 1];

        // If the index is the root OR if the replacement value is less than the parent then we look below the heap.
        if (index == 0 || heap[index] < heap[parent]) {
            fixHeapBelow(index, size - 1);
        } else {
            fixHeapAbove(index); // Otherwise look at the heap above.
        }
        size--;
        return deletedValue;
    }


    /**
     * Sorts the heap.
     */
    public void sort() {
        int lastIndex = size - 1;
        for (int i = 0; i < lastIndex; i++) {
            // swap the first element in the heap(largest value) with the last element in the heap.
            int temp = heap[0];
            heap[0] = heap[lastIndex - i];
            heap[lastIndex - i] = temp;

            // heapify the heap after the swap.
            fixHeapBelow(0, lastIndex - i - 1);
        }
    }


    /**
     * Heapify.
     * @param index as an int.
     */
    private void fixHeapAbove(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        heap[index] = newValue;
    }


    /**
     * Heapify
     * @param index an int.
     * @param lastIndex also an int.
     */
    private void fixHeapBelow(int index, int lastIndex) {
        int childToSwap;

        while (index <= lastIndex) {
            int leftChild = getChild(index, true);
            int rightChild = getChild(index, false);
            if (leftChild <= lastIndex) {
                if (rightChild > lastIndex) {
                    childToSwap = leftChild;
                } else {
                    childToSwap = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
                }

                if (heap[index] < heap[childToSwap]) {
                    int temp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = temp;
                } else {
                    break;
                }
                index = childToSwap;
            } else {
                break;
            }
        }
    }


    /**
     * Prints the heap.
     */
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + ", ");
        }
    }


    /**
     * Checks if the heap is empty.
     *
     * @return true if the size of heap == 0.
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Take a look at the root.
     *
     * @return the root.
     */
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        return heap[0];
    }


    /**
     * Get the child of the given index.
     *
     * @param index as an int.
     * @param left  if true, right if false.
     * @return 2 * index + 1 for left and 2 * index + 2 for right.
     */
    public int getChild(int index, boolean left) {
        return 2 * index + (left ? 1 : 2);
    }


    /**
     * Checks if the heap is full.
     *
     * @return true when the size == the heap.length.
     */
    public boolean isFull() {
        return size == heap.length;
    }


    /**
     * Retrieves the parent of the node on the heap.
     *
     * @param index as an int.
     * @return the index of the parent.
     */
    public int getParent(int index) {
        return (index - 1) / 2;
    }
}
