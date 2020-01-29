package com.VinhHua;

public class Main {

    public static void main(String[] args) {
	    Heap heap = new Heap(10);
	    heap.insert(80);
        heap.insert(75);
        heap.insert(70);
        heap.insert(65);
        heap.insert(60);
        heap.insert(55);
        heap.insert(50);
        heap.insert(45);

        heap.delete(0);
        System.out.println(heap.peek());

        heap.printHeap();
    }
}
