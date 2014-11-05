package de.samdev.absgdx.framework.math;

import java.util.ListIterator;


/**
 * The ListIterator only allows Read access (throw UnsupportedException otherwise)
 *
 * @param <T>
 */
public class ReadOnlyListIterator<T> implements ListIterator<T> {

	private final ListIterator<T> iterator;
	
	/**
	 * Creates a new ReadOnlyListIterator based on a normal ListIterator
	 * 
	 * @param it The underlying "real" iterator
	 */
	public ReadOnlyListIterator(ListIterator<T> it) {
		super();
		
		iterator = it;
	}

	@Override
	@Deprecated
	public void add(T e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		return iterator.hasPrevious();
	}

	@Override
	public T next() {
		return iterator.next();
	}

	@Override
	public int nextIndex() {
		return iterator.nextIndex();
	}

	@Override
	public T previous() {
		return iterator.previous();
	}

	@Override
	public int previousIndex() {
		return iterator.previousIndex();
	}

	@Override
	@Deprecated
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public void set(T e) {
		throw new UnsupportedOperationException();
	}
}
