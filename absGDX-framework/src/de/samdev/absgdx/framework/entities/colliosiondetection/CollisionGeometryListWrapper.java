package de.samdev.absgdx.framework.entities.colliosiondetection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.CollisionGeometry;
import de.samdev.absgdx.framework.entities.colliosiondetection.geometries.EntityCollisionGeometry;

/**
 * This is an Wrapper around a List\<EntityCollisionGeometry\> to an List\<CollisionGeometry\>.
 * 
 * This is mostly for performance so you don't have to create a completely new list
 *
 */
public class CollisionGeometryListWrapper implements List<CollisionGeometry> {

	private final List<EntityCollisionGeometry> list;
	
	/**
	 * Creates a new CollisionGeometryListWrapper
	 * 
	 * @param innerlist the inner list that holds the data
	 */
	public CollisionGeometryListWrapper(List<EntityCollisionGeometry> innerlist) {
		super();
		
		this.list = innerlist;
	}

	@Override
	@Deprecated
	public boolean add(CollisionGeometry arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public void add(int arg0, CollisionGeometry arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public boolean addAll(Collection<? extends CollisionGeometry> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends CollisionGeometry> arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		for (EntityCollisionGeometry ecg : list) {
			if (ecg.geometry == o) return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (! contains(o)) return false;
		}
		return true;
	}

	@Override
	public CollisionGeometry get(int i) {
		return list.get(i).geometry;
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<CollisionGeometry> iterator() {
		return listIterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<CollisionGeometry> listIterator() {
		return new ReadOnlyEntityCollisionGeometryListIterator(list.listIterator());
	}

	@Override
	public ListIterator<CollisionGeometry> listIterator(int idx) {
		return new ReadOnlyEntityCollisionGeometryListIterator(list.listIterator(idx));
	}

	@Override
	@Deprecated
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public CollisionGeometry remove(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	@Deprecated
	public CollisionGeometry set(int arg0, CollisionGeometry arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<CollisionGeometry> subList(int i1, int i2) {
		List<CollisionGeometry> newlist = new ArrayList<CollisionGeometry>();
		for (EntityCollisionGeometry ecg : list) {
			newlist.add(ecg.geometry);
		}
		
		return newlist.subList(i1, i2);
	}

	@Override
	public Object[] toArray() {
		List<CollisionGeometry> newlist = new ArrayList<CollisionGeometry>();
		for (EntityCollisionGeometry ecg : list) {
			newlist.add(ecg.geometry);
		}
		
		return newlist.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arr) {
		List<CollisionGeometry> newlist = new ArrayList<CollisionGeometry>();
		for (EntityCollisionGeometry ecg : list) {
			newlist.add(ecg.geometry);
		}
		
		return newlist.toArray(arr);
	}

}
