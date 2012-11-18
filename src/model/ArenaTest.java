package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArenaTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void getMaximumLedInCubeTest()
	{
		Arena.xMaximum = 15;
		Arena.yMaximum = 15;
		Arena.zMaximum = 15;
		assertEquals( 4095, Arena.getMaximumLedInCube() );
	}

}
