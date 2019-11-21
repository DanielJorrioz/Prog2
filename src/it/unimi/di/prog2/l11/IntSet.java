package it.unimi.di.prog2.l11;

import it.unimi.di.prog2.l07.EmptyException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * {@code IntSet}s are mutable, unbounded sets of integers.
 *
 * <p>
 * A typical IntSet is \( S = \{x_1, \ldots, x_n \} \).
 */
public class IntSet implements Iterable<Integer> {

  // Fields

  /** The {@link List} containing this set elements. */
  private final List<Integer> els;

  // Constructors

  /**
   * Initializes this set to be empty.
   *
   * <p>
   * Builds the set \( S = \varnothing \).
   */
  public IntSet() {
    els = new ArrayList<>();
  }

  /**
   * A *copy constructor*, provided to implement {@link #clone()}.
   *
   * @param other the {@code IntSet} to copy from.
   */
  private IntSet(IntSet other) {
    els = new ArrayList<Integer>(other.els);
  }

  // Methods

  /**
   * Looks for a given element in this set.
   *
   * @param x the element to look for.
   * @return the index where {@code x} appears in {@code els} if the element belongs to this set, or
   *         -1
   */
  private int getIndex(int x) {
    for (int i = 0; i < els.size(); i++)
      if (x == els.get(i))
        return i;
    return -1;
  }

  /**
   * Adds the given element to this set.
   *
   * <p>
   * This method modifies the object, that is: \( S' = S \cup \{ x \} \).
   *
   * @param x the element to be added.
   */
  public void insert(int x) {
    if (getIndex(x) < 0)
      els.add(x);
  }

  /**
   * Removes the given element from this set.
   *
   * <p>
   * This method modifies the object, that is: \( S' = S \setminus \{ x \} \).
   *
   * @param x the element to be removed.
   */
  public void remove(int x) {
    int i = getIndex(x);
    if (i < 0)
      return;
    int last = els.size() - 1;
    els.set(i, els.get(last));
    els.remove(last);
  }

  /**
   * Tells if the given element is in this set.
   *
   * <p>
   * Answers the question \( x\in S \).
   *
   * @param x the element to look for.
   * @return whether the given element belongs to this set, or not.
   */
  public boolean isIn(int x) {
    return getIndex(x) != -1;
  }

  /**
   * Returns the cardinality of this set.
   *
   * <p>
   * Responds with \( |S| \).
   *
   * @return the size of this set.
   */
  public int size() {
    return els.size();
  }

  /**
   * Returns an element chosen at random from this set.
   *
   * @return an arbitrary element from this set.
   * @throws EmptyException if this set is empty.
   */
  public int choose() throws EmptyException {
    if (els.size() == 0)
      throw new EmptyException("Can't choose from an empty set");
    return els.get(els.size() - 1);
  }

  @Override
  public String toString() {
    if (els.size() == 0)
      return "IntSet: {}";
    String s = "IntSet: {" + els.get(0);
    for (int i = 1; i < els.size(); i++)
      s = s + ", " + els.get(i);
    return s + "}";
  }

  @Override
  public IntSet clone() {
    return new IntSet(this);
  }

  // New stuff, related to lecture 11 (Iteration)

  /** Iterates over the element of the {@code IntSet}. */
  private class ElementsIterator implements Iterator<Integer> {

    private int current = 0;

    @Override
    public boolean hasNext() {
      return current < els.size();
    }

    @Override
    public Integer next() {
      try {
        return els.get(current++);
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
    }
  }

  /**
   * Returns an iterator on the elements of this {@code IntSet}.
   *
   * @return the iterator on set elements.
   */
  public Iterator<Integer> elements() {
    return new ElementsIterator();
  }

  @Override
  public Iterator<Integer> iterator() {
    return new ElementsIterator();
  }
}
