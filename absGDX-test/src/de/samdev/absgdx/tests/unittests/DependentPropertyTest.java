package de.samdev.absgdx.tests.unittests;

import org.junit.Test;

import de.samdev.absgdx.framework.util.dependentProperties.BooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.ConstantBooleanProperty;
import de.samdev.absgdx.framework.util.dependentProperties.FloatProperty;
import de.samdev.absgdx.framework.util.dependentProperties.IntegerProperty;
import de.samdev.absgdx.framework.util.dependentProperties.StringProperty;
import de.samdev.absgdx.tests.BaseUnitTest;


public class DependentPropertyTest extends BaseUnitTest {
	
	@Test
	public void testDependentProperty() {
		BooleanProperty p1 = new BooleanProperty("a", false, null);
		BooleanProperty p2 = new BooleanProperty("b", true, p1);
		
		assertEquals(true, p2.getBooleanValue());
		assertEquals(false, p2.isActive());
		assertEquals(false, p1.getBooleanValue());
		assertEquals(false, p1.isActive());
		
		p1.set(true);
		
		assertEquals(true, p2.getBooleanValue());
		assertEquals(true, p2.isActive());
		assertEquals(true, p1.getBooleanValue());
		assertEquals(true, p1.isActive());
		
		p2.set(false);
		
		assertEquals(false, p2.getBooleanValue());
		assertEquals(false, p2.isActive());
		assertEquals(true, p1.getBooleanValue());
		assertEquals(true, p1.isActive());
		
	}

    @Test
    public void testBooleanProperty() {
		BooleanProperty p1 = new BooleanProperty("a", false, null);
		String s1;
		
		p1.set(true);
		p1.deserialize(p1.serialize());
		assertEquals(true, p1.get());
		
		p1.set(true);
		s1 = p1.serialize();
		p1.set(false);
		p1.deserialize(s1);
		assertEquals(true, p1.get());
		
		p1.set(false);
		s1 = p1.serialize();
		p1.set(true);
		p1.deserialize(s1);
		assertEquals(false, p1.get());
		
		assertEquals(true, new BooleanProperty("x", true, null).get());
		assertEquals(true, new BooleanProperty("x", true, null).getBooleanValue());
		assertEquals(true, new BooleanProperty("x", true, null).isActive());
		
		assertEquals(false, new BooleanProperty("x", false, null).get());
		assertEquals(false, new BooleanProperty("x", false, null).getBooleanValue());
		assertEquals(false, new BooleanProperty("x", false, null).isActive());
    }
    
    @Test
    public void testFloatProperty() {
    	FloatProperty p1 = new FloatProperty("a", 0, null);
		String s1;
		
		p1.set(0.0f);
		p1.deserialize(p1.serialize());
		assertEquals(0.0f, p1.get());
		
		p1.set(10.0f);
		p1.deserialize(p1.serialize());
		assertEquals(10.0f, p1.get());
		
		p1.set(-10.0f);
		p1.deserialize(p1.serialize());
		assertEquals(-10.0f, p1.get());
		
		p1.set(0.10f);
		p1.deserialize(p1.serialize());
		assertEquals(0.10f, p1.get());
		
		p1.set(-0.10f);
		p1.deserialize(p1.serialize());
		assertEquals(-0.10f, p1.get());
		
		p1.set(0.01f);
		s1 = p1.serialize();
		p1.set(100);
		p1.deserialize(s1);
		assertEquals(0.01f, p1.get());
		
		p1.set(100);
		s1 = p1.serialize();
		p1.set(0);
		p1.deserialize(s1);
		assertEquals(100.0f, p1.get());
		
		assertEquals(1.0f, new FloatProperty("x", 1, null).get());
		assertEquals(true, new FloatProperty("x", 1, null).getBooleanValue());
		assertEquals(true, new FloatProperty("x", 1, null).isActive());
		
		assertEquals(0.0f, new FloatProperty("x", 0, null).get());
		assertEquals(true, new FloatProperty("x", 0, null).getBooleanValue());
		assertEquals(true, new FloatProperty("x", 0, null).isActive());
    }
    
    @Test
    public void testIntegerProperty() {
    	IntegerProperty p1 = new IntegerProperty("a", 0, null);
		String s1;
		
		p1.set(0);
		p1.deserialize(p1.serialize());
		assertEquals(0, p1.get());
		
		p1.set(10);
		p1.deserialize(p1.serialize());
		assertEquals(10, p1.get());
		
		p1.set(-10);
		p1.deserialize(p1.serialize());
		assertEquals(-10, p1.get());
		
		p1.set(1);
		s1 = p1.serialize();
		p1.set(-100);
		p1.deserialize(s1);
		assertEquals(1, p1.get());
		
		p1.set(0);
		s1 = p1.serialize();
		p1.set(999);
		p1.deserialize(s1);
		assertEquals(0, p1.get());
		
		assertEquals(1, new IntegerProperty("x", 1, null).get());
		assertEquals(true, new IntegerProperty("x", 1, null).getBooleanValue());
		assertEquals(true, new IntegerProperty("x", 1, null).isActive());
		
		assertEquals(0, new IntegerProperty("x", 0, null).get());
		assertEquals(true, new IntegerProperty("x", 0, null).getBooleanValue());
		assertEquals(true, new IntegerProperty("x", 0, null).isActive());
    }
    
    @Test
    public void testStringProperty() {
    	StringProperty p1 = new StringProperty("a", "?", null);
		String s1;
		
		p1.set("HeLL0");
		p1.deserialize(p1.serialize());
		assertEquals("HeLL0", p1.get());
		
		p1.set("");
		p1.deserialize(p1.serialize());
		assertEquals("", p1.get());
		
		p1.set(null);
		p1.deserialize(p1.serialize());
		assertEquals(null, p1.get());
		
		p1.set("A\"B");
		p1.deserialize(p1.serialize());
		assertEquals("A\"B", p1.get());
		
		p1.set("[[");
		p1.deserialize(p1.serialize());
		assertEquals("[[", p1.get());
		
		p1.set("'");
		p1.deserialize(p1.serialize());
		assertEquals("'", p1.get());
		
		p1.set("9");
		p1.deserialize(p1.serialize());
		assertEquals("9", p1.get());
		
		p1.set(";,");
		p1.deserialize(p1.serialize());
		assertEquals(";,", p1.get());
		
		p1.set("BLAH");
		s1 = p1.serialize();
		p1.set(null);
		p1.deserialize(s1);
		assertEquals("BLAH", p1.get());
		
		p1.set(null);
		s1 = p1.serialize();
		p1.set("BLAH");
		p1.deserialize(s1);
		assertEquals(null, p1.get());
		
		assertEquals("",    new StringProperty("x", "", null).get());
		assertEquals(true,  new StringProperty("x", "", null).getBooleanValue());
		assertEquals(true,  new StringProperty("x", "", null).isActive());
		
		
		assertEquals(null,  new StringProperty("x", null, null).get());
		assertEquals(false, new StringProperty("x", null, null).getBooleanValue());
		assertEquals(false, new StringProperty("x", null, null).isActive());
		
		assertEquals("A",   new StringProperty("x", "A", null).get());
		assertEquals(true,  new StringProperty("x", "A", null).getBooleanValue());
		assertEquals(true,  new StringProperty("x", "A", null).isActive());
    }
    
    @Test
    public void testConstantBooleanProperty() {
		ConstantBooleanProperty p1 = new ConstantBooleanProperty("a", false, null);
		ConstantBooleanProperty p2 = new ConstantBooleanProperty("a", true, null);
		
		assertEquals(false, p1.get());
		assertEquals(true, p2.get());
		
		assertEquals("false", p1.serialize());
		assertEquals("true", p2.serialize());
		
		assertEquals(true, new ConstantBooleanProperty("x", true, null).get());
		assertEquals(true, new ConstantBooleanProperty("x", true, null).getBooleanValue());
		assertEquals(true, new ConstantBooleanProperty("x", true, null).isActive());
		
		assertEquals(false, new ConstantBooleanProperty("x", false, null).get());
		assertEquals(false, new ConstantBooleanProperty("x", false, null).getBooleanValue());
		assertEquals(false, new ConstantBooleanProperty("x", false, null).isActive());
    }
}
