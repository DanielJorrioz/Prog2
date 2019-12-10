package it.unimi.di.prog2.l18.digraph;

import java.util.AbstractQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A collection of utilities related to {@link Queues}s needed for graph visits.
 *
 * <p>
 * This class provides {@link Supplier}s and wrappers to {@link Queue}s to be used by the
 * {@link DiGraph#visit(Object, Consumer, java.util.function.Supplier)} method.
 */
public class Queues {

  private Queues() {}

  /**
   * A {@link Supplier} of LIFO {@link Queue}s.
   *
   * @param <T> the type of the graph nodes.
   * @return the supplier.
   */
  public static <T> Supplier<Queue<T>> LIFOSupplier() {
    return new Supplier<>() {
      @Override
      public Queue<T> get() {
        return Collections.asLifoQueue(new LinkedList<>());
      }
    };
  }

  /**
   * A {@link Supplier} of FIFO {@link Queue}s.
   *
   * @param <T> the type of the graph nodes.
   * @return the supplier.
   */
  public static <T> Supplier<Queue<T>> FIFOSupplier() {
    return new Supplier<>() {
      @Override
      public Queue<T> get() {
        return new LinkedList<>();
      }
    };
  }

  /**
   * Wraps a {@code Queue} making it a <em>once</em> queue.
   *
   * <p>
   * A <em>once</em> queue is a queue where every element can be added just once.
   *
   * @param <T> the type of the graph nodes.
   * @param queue the queue to be wrapped.
   * @return the wrapped queue.
   */
  public static <T> Queue<T> once(final Queue<T> queue) {
    return new AbstractQueue<>() {

      final Set<T> seen = new HashSet<>();

      @Override
      public boolean offer(T e) {
        if (seen.contains(e))
          return true;
        seen.add(e);
        return queue.offer(e);
      }

      @Override
      public T poll() {
        return queue.poll();
      }

      @Override
      public T peek() {
        return queue.peek();
      }

      @Override
      public Iterator<T> iterator() {
        return queue.iterator();
      }

      @Override
      public int size() {
        return queue.size();
      }
    };
  }
}