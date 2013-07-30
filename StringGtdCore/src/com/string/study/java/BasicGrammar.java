package com.string.study.java;

import junit.framework.TestCase;

public class BasicGrammar extends TestCase {

	class TestClass {
		public int primitiveType;
		public byte[] referenceType = {'1','2','3'}; 
		public byte[] returnRefereceType(){
			return referenceType;
		}
	}

	/**
	 * 获取类名
	 */
	public void testClassName() {
		TestClass test = new TestClass();

		// com.string.study.java.BasicGrammar$TestValue
		String getName = test.getClass().getName();
		assertEquals("com.string.study.java.BasicGrammar$TestValue", getName);
		
		// com.string.study.java.BasicGrammar.TestValue
		String getCanonicalName = test.getClass().getCanonicalName();
		assertEquals("com.string.study.java.BasicGrammar.TestValue", getCanonicalName);
	}


	public void testPassParameter() {
		TestClass test = new TestClass();
		test.primitiveType = 1;
		changeParaInnerValue(test);
		assertEquals(test.primitiveType, 2);
	}

	private void changeParaInnerValue(TestClass test) {
		test.primitiveType = 2;
	}

	class ReturnValueTestContainer {
		public TestClass test = new TestClass();
		public TestClass getReturnValue() {
			test.primitiveType = 1;
			return test;
		}
		public void changeValue() {
//			test = new TestValue();
			test.primitiveType = 2;
		}
	}

	public void testReturnValue() {
		ReturnValueTestContainer container = new ReturnValueTestContainer();
		TestClass returnValue = container.getReturnValue();
		assertEquals(1, returnValue.primitiveType);
		container.changeValue();
		assertEquals(2, returnValue.primitiveType);
		
		
	}
	
	public void testReferenceValue(){
		TestClass test1 = new TestClass();
		byte[] returnValue1 = test1.returnRefereceType();
		test1.referenceType[0] = 'a';
		assertEquals('a', returnValue1[0]);
		
		TestClass test2 = new TestClass();
		byte[] returnValue2 = test2.returnRefereceType().clone();
		test2.referenceType[0] = 'a';
		assertEquals('1', returnValue2[0]);
	}

	
	
	class ParentClass {
	}

	class ChildClass extends ParentClass {
	}

	public void testIs() {
		ParentClass pc = new ParentClass();
		ChildClass cc1 = new ChildClass();
		ChildClass cc2 = new ChildClass();
		ParentClass fackParent = cc1;

		assertTrue(fackParent instanceof ParentClass);
		assertTrue(fackParent instanceof ChildClass);

		assertTrue(fackParent.getClass() != pc.getClass());
		System.out.println("fackParent's class is: " + fackParent.getClass());
		System.out.println("pc's class is: " + pc.getClass());
		System.out.println("cc2's class is: " + cc2.getClass());
	}



}
