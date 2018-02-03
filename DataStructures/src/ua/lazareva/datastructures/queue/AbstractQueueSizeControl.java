package ua.lazareva.datastructures.queue;


public abstract class AbstractQueueSizeControl {
    private final static int MAX_QUEUE_SIZE = 100;
    int maxQueueSize ;
    int size;

    int getMaxQueueSize() {
        return maxQueueSize;
    }

    void setMaxQueueSize(int maxQueueSize) {

        if (maxQueueSize <= size) {
            throw new IllegalArgumentException("New queue size is less than current. " +
                    "Remove queue elements and try again");
        } else if (maxQueueSize > MAX_QUEUE_SIZE) {
            throw new IllegalArgumentException("Not allowed queue size = " + maxQueueSize +
                    ", greater than MAX_QUEUE_SIZE = " + MAX_QUEUE_SIZE);
        } else {
            this.maxQueueSize = maxQueueSize;
        }
    }

    public int getSize() {
        return size;
    }

    void checkAvailableCapacity() {
        if (size == maxQueueSize) {
            throw new RuntimeException("The Limit of queue size is exceeded already. " +
                    "Can not add element to the queue.");
        }
    }
}
