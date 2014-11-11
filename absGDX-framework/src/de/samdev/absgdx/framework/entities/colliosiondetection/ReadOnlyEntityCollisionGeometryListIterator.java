package de.samdev.absgdx.framework.entities.colliosiondetection;

import java.util.List;
import java.util.ListIterator;

/**
 * This is an CollisionGeometry ListIterator
 * 
 * Beware that its read-only :: add / remove operation do not work
 */
public class ReadOnlyEntityCollisionGeometryListIterator implements ListIterator<CollisionGeometry>{

	private ListIterator<EntityCollisionGeometry> iterator;
	
	/**
	 * Creates a new ReadOnlyEntityCollisionGeometryListIterator based on a EntityCollisionGeometry-List
	 * 
	 * @param g the List this iterates iterates through
	 */
	public ReadOnlyEntityCollisionGeometryListIterator(List<EntityCollisionGeometry> g) {
		super();
		
		iterator = g.listIterator();
	}

	@Override
	@Deprecated
	public void add(CollisionGeometry arg0) {
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
	public CollisionGeometry next() {
		return iterator.next().geometry;
	}

	@Override
	public int nextIndex() {
		return iterator.nextIndex();
	}

	@Override
	public CollisionGeometry previous() {
		return iterator.previous().geometry;
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
	public void set(CollisionGeometry arg0) {
		throw new UnsupportedOperationException();
	}

}
