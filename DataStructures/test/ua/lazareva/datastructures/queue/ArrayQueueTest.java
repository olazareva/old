package ua.lazareva.datastructures.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayQueueTest extends AbstractQueueTest {
   protected ArrayQueue<Object> getQueue(){return new ArrayQueue<>(5);}
}