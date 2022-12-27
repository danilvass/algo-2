import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class aBSTTest {

    @Test
    void test_init() {
        assertEquals(1, new aBST(0).Tree.length);
        assertEquals(3, new aBST(1).Tree.length);
        assertEquals(7, new aBST(2).Tree.length);
        assertEquals(15, new aBST(3).Tree.length);
        assertEquals(31, new aBST(4).Tree.length);
    }

    @Test
    void test_find() {
        aBST sut = new aBST(3);
        sut.Tree = new Integer[] { 50, 25, 75, null, null, null, null };
        assertEquals(0, sut.FindKeyIndex(50));
        assertEquals(1, sut.FindKeyIndex(25));
        assertEquals(2, sut.FindKeyIndex(75));
        assertEquals(-3, sut.FindKeyIndex(20));
        assertEquals(-3, sut.FindKeyIndex(24));
        assertEquals(-4, sut.FindKeyIndex(26));

        sut.Tree = new Integer[] { 50, 25, 75, 20, 30, 60, 80 };
        assertEquals(0, sut.FindKeyIndex(50));
        assertEquals(1, sut.FindKeyIndex(25));
        assertEquals(2, sut.FindKeyIndex(75));
        assertEquals(3, sut.FindKeyIndex(20));
        assertEquals(4, sut.FindKeyIndex(30));
        assertEquals(5, sut.FindKeyIndex(60));
        assertEquals(6, sut.FindKeyIndex(80));
        assertNull(sut.FindKeyIndex(1));
        assertNull(sut.FindKeyIndex(2));
        assertNull(sut.FindKeyIndex(3));
        assertNull(sut.FindKeyIndex(4));
    }

    @Test
    void test_add() {
        aBST sut = new aBST(2);
        assertEquals(0, sut.AddKey(50));
        assertEquals(1, sut.AddKey(25));
        assertEquals(2, sut.AddKey(75));
        assertEquals(1, sut.AddKey(25));
        assertEquals(4, sut.AddKey(37));
        assertEquals(5, sut.AddKey(62));
        assertEquals(6, sut.AddKey(84));
        assertEquals(3, sut.AddKey(20));
        assertEquals(-1, sut.AddKey(51));
        assertEquals(-1, sut.AddKey(0));
        assertEquals(-1, sut.AddKey(1));
        assertEquals(-1, sut.AddKey(100));
    }

}