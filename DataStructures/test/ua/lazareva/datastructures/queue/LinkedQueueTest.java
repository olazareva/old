package ua.lazareva.datastructures.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedQueueTest extends AbstractQueueTest {
    protected LinkedQueue<Object> getQueue(){return new LinkedQueue<>(5);}
}