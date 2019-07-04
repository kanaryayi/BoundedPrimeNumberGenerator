import Server.ServerStart;

import org.junit.Test;

import PrimeGenerator.PrimeFounder;

import static org.junit.Assert.*;

public class ServerTest {
	@Test
	public void test1() {
		ServerStart classUnderTest = new ServerStart();
		assertTrue("test1 should return 'true'", classUnderTest.ApplicationFunc());
	}
	@Test
	public void test2() {
		PrimeFounder classUnderTest = new PrimeFounder(0,10,"s");
		String res = classUnderTest.startPrimeFounder()[0];
		System.out.println(res.toString());
		assertTrue("test2 should return ''", res.toString().equals("[2, 3, 5, 7]"));
	}
	@Test
	public void test3() {
		PrimeFounder classUnderTest = new PrimeFounder(0,10,"b");
		String res = classUnderTest.startPrimeFounder()[0];
		System.out.println(res.toString());
		assertTrue("test3 should return ''", res.toString().equals("[2, 3, 5, 7]"));
	}
	@Test
	public void test4() {
		PrimeFounder classUnderTest = new PrimeFounder(0,10,"pb");
		String res = classUnderTest.startPrimeFounder()[0];
		System.out.println(res.toString());
		assertTrue("test4 should return ''", res.toString().equals("[2, 3, 5, 7]"));
	}
	@Test
	public void test5() {
		PrimeFounder classUnderTest = new PrimeFounder(0,10,"pb",10);
		String res = classUnderTest.startPrimeFounder()[0];
		System.out.println(res.toString());
		assertTrue("test5 should return ''", res.toString().equals("[2, 3, 5, 7]"));
	}
}
