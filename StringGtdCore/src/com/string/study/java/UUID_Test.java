package com.string.study.java;

import java.util.UUID;

import junit.framework.TestCase;

public class UUID_Test extends TestCase {
	public void testUUID() {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
	}

}
