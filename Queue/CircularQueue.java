package Queue;
import Interfaces.Queue;
public class CircularQueue implements Queue{
    int front, rear;
    Object[] queue;

    public CircularQueue(int initialSize){
        if(initialSize < 1){
            throw new IllegalArgumentException();
        }
        front = rear = 0;
        queue = new Object[initialSize + 1];
    }

    public CircularQueue(){
        this(10);
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public Object peek(){
        if(isEmpty()){
            return null;
        }
        return queue[(front + 1) % queue.length];
    }

    public void put(Object theElement) {
        // queue is full when next position after rear hits front
        if (front == (rear + 1) % queue.length) {
            Object[] newQueue = new Object[queue.length * 2];

            // calculate how many elements are from front+1 to end of array
            int secondSegmentLength = queue.length - front - 1;

            // copy elements from front+1 to end into new array starting at 0
            System.arraycopy(queue, front + 1, newQueue, 0, secondSegmentLength);

            // copy elements from 0 to rear right after the second segment
            System.arraycopy(queue, 0, newQueue, secondSegmentLength, rear + 1);

            // Front should simply be newQueue.length - 1 = 11 so it sits at the end, one anti-clockwise from index 0.
            front = newQueue.length - 1;

            // rear is now at end of all copied elements
            rear = secondSegmentLength + rear;

            // replace old array with new bigger array
            queue = newQueue;
        }

        // move rear one step clockwise, wrap around using modulo
        rear = (rear + 1) % queue.length;

        // insert new element at rear
        queue[rear] = theElement;
    }

    public Object remove(){
        if(isEmpty()){
            return null;
        }
        front = (front + 1 ) % queue.length;
        Object frontObject = queue[front];
        queue[front] = null;
        return frontObject;
    }
}
