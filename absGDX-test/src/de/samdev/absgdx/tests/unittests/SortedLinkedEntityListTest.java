package de.samdev.absgdx.tests.unittests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import de.samdev.absgdx.framework.entities.Entity;
import de.samdev.absgdx.framework.math.SortedLinkedEntityList;
import de.samdev.absgdx.tests.BaseUnitTest;
import de.samdev.absgdx.tests.dummy.DummyEntity;

public class SortedLinkedEntityListTest extends BaseUnitTest {

	@Test
	public void testOrder() {
		DummyEntity.uidctr = 1;
		{
			SortedLinkedEntityList elist = new SortedLinkedEntityList();
			
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(1));
			elist.add(new DummyEntity(2));
			elist.add(new DummyEntity(3));
			elist.add(new DummyEntity(4));
			
			assertEquals("1-2-3-4-5", getUniqueIdent(elist));
			assertEquals(5, elist.size());
		}

		DummyEntity.uidctr = 1;
		{
			SortedLinkedEntityList elist = new SortedLinkedEntityList();
			
			elist.add(new DummyEntity(4));
			elist.add(new DummyEntity(3));
			elist.add(new DummyEntity(2));
			elist.add(new DummyEntity(1));
			elist.add(new DummyEntity(0));
			
			assertEquals("5-4-3-2-1", getUniqueIdent(elist));
			assertEquals(5, elist.size());
		}

		DummyEntity.uidctr = 1;
		{
			SortedLinkedEntityList elist = new SortedLinkedEntityList();
			
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(0));
			
			assertEquals("1-2-3-4-5", getUniqueIdent(elist));
			assertEquals(5, elist.size());
		}

		DummyEntity.uidctr = 1;
		{
			SortedLinkedEntityList elist = new SortedLinkedEntityList();
			
			assertEquals("", getUniqueIdent(elist));
			assertEquals(0, elist.size());
		}
		
		DummyEntity.uidctr = 1;
		{
			SortedLinkedEntityList elist = new SortedLinkedEntityList();
			
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(0));
			elist.add(new DummyEntity(1));
			elist.add(new DummyEntity(2));
			elist.add(new DummyEntity(3));
			elist.add(new DummyEntity(3));
			elist.add(new DummyEntity(3));
			elist.add(new DummyEntity(1));
			elist.add(new DummyEntity(1));
			elist.add(new DummyEntity(-1));
			elist.add(new DummyEntity(-1));
			elist.add(new DummyEntity(-1));
			
			assertEquals("11-12-13-1-2-3-4-9-10-5-6-7-8", getUniqueIdent(elist));
			assertEquals(13, elist.size());
		}
	}

	@SuppressWarnings("deprecation")
	@Test(expected=Exception.class)
	public void testInsert() {
		SortedLinkedEntityList elist = new SortedLinkedEntityList();
		
		elist.add(new DummyEntity(1));
		elist.add(new DummyEntity(2));
		elist.add(new DummyEntity(3));
		
		elist.add(1, new DummyEntity(0));
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected=Exception.class)
	public void testListIteratorInsert() {
		SortedLinkedEntityList elist = new SortedLinkedEntityList();
		
		elist.add(new DummyEntity(1));
		elist.add(new DummyEntity(2));
		elist.add(new DummyEntity(3));
		
		elist.listIterator().add(new DummyEntity(0));
	}
	
	private String getUniqueIdent(SortedLinkedEntityList slist) {
		StringBuilder sb = new StringBuilder();
		
		boolean first = true;
		
		
		for( Iterator<Entity> it = slist.descendingIterator(); it.hasNext();) {
			DummyEntity de = (DummyEntity) it.next();
			
			if (! first) sb.append("-");
			sb.append(de.UID);
			
			first = false;
		}
		
		return sb.toString();
	}
}
