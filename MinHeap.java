public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap with a given capacity
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Get the index of the parent node
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Get the index of the left child
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Get the index of the right child
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Heapify the tree (upward)
    private void heapifyUp(int index) {
        while (index > 0 && heap[parent(index)] > heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // Heapify the tree (downward)
    private void heapifyDown(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Insert a value into the heap
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("Heap is full, can't insert.");
            return;
        }
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    // Delete the minimum element (root of the heap)
    public int deleteMin() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return -1;
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    // Peek the minimum element (root of the heap)
    public int peek() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return -1;
        }
        return heap[0];
    }

    // Print the heap array
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Main function for testing
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        // Insert some values into the heap
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(30);
        minHeap.insert(15);

        System.out.println("Min Heap after inserts:");
        minHeap.printHeap();  // Expected: [5, 15, 10, 30, 20]

        // Peek the minimum value
        System.out.println("Min value (peek): " + minHeap.peek());  // Expected: 5

        // Delete the minimum value (root)
        System.out.println("Deleted Min: " + minHeap.deleteMin());  // Expected: 5
        minHeap.printHeap();  // Expected: [10, 15, 20, 30]

        // Delete the minimum value
        System.out.println("Deleted Min: " + minHeap.deleteMin());  // Expected: 10
        minHeap.printHeap();  // Expected: [15, 30, 20]
    }
}
