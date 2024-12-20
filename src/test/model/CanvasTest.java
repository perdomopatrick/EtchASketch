package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CanvasTest {

    private Canvas testCanvas;
    private boolean[][] expectedArray;

    @BeforeEach
    void runBefore() {
        testCanvas = new Canvas(3, 2);
        expectedArray = new boolean[2][3];
    }

    @Test
    void testConstructor() {
        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(3, testCanvas.getWidth());
        assertEquals(2, testCanvas.getHeight());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testSecondConstructor() {
        testCanvas = new Canvas(expectedArray, 1, 2);
        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(3, testCanvas.getWidth());
        assertEquals(2, testCanvas.getHeight());
        assertEquals(1, testCanvas.getStylusXCoord());
        assertEquals(2, testCanvas.getStylusYCoord());
    }


    @Test
    void testDrawRight() {
        assertTrue(testCanvas.draw("right", 2));

        expectedArray = new boolean[][] {
                { false, true, true },
                { false, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(2, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawLeft() {
        testCanvas.setStylusXCoord(2);
        assertTrue(testCanvas.draw("left", 2));

        expectedArray = new boolean[][] {
                { true, true, false },
                { false, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawUp() {
        testCanvas.setStylusYCoord(1);
        assertTrue(testCanvas.draw("up", 1));

        expectedArray = new boolean[][] {
                { true, false, false },
                { false, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawDown() {
        assertTrue(testCanvas.draw("down", 1));

        expectedArray = new boolean[][] {
                { false, false, false },
                { true, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(1, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawRightZero() {
        assertTrue(testCanvas.draw("right", 0));

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawLeftZero() {
        assertTrue(testCanvas.draw("left", 0));

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawUpZero() {
        assertTrue(testCanvas.draw("up", 0));

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawDownZero() {
        assertTrue(testCanvas.draw("down", 0));

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawRightOutOfBounds() {
        assertFalse(testCanvas.draw("right", 3));

        expectedArray = new boolean[][] {
                { false, true, true },
                { false, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(2, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawLeftOutOfBounds() {
        assertFalse(testCanvas.draw("left", 3));

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawUpOutOfBounds() {
        assertFalse(testCanvas.draw("up", 3));

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawDownOutOfBounds() {
        assertFalse(testCanvas.draw("down", 2));

        expectedArray = new boolean[][] {
                { false, false, false },
                { true, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(1, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawInvaildDirection() {
        assertFalse(testCanvas.draw("apple", 2));

        expectedArray = new boolean[][] {
                { false, false, false },
                { false, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawMultiple() {
        assertTrue(testCanvas.draw("right", 2));
        assertTrue(testCanvas.draw("down", 1));
        assertFalse(testCanvas.draw("left", 3));
        assertTrue(testCanvas.draw("up", 1));

        expectedArray = new boolean[][] {
                { true, true, true },
                { true, true, true }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawOnColoured() {
        assertTrue(testCanvas.draw("right", 2));
        assertTrue(testCanvas.draw("left", 1));

        expectedArray = new boolean[][] {
                { false, true, true },
                { false, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(1, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testShake() {
        testCanvas.shake();

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testShakeColouredBoard() {
        testCanvas.draw("right", 2);
        testCanvas.draw("down", 1);

        testCanvas.shake();

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(2, testCanvas.getStylusXCoord());
        assertEquals(1, testCanvas.getStylusYCoord());
    }

    @Test
    void testShakeColouredBoardMultiple() {
        testCanvas.draw("right", 2);
        testCanvas.draw("down", 1);

        testCanvas.shake();
        testCanvas.shake();

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(2, testCanvas.getStylusXCoord());
        assertEquals(1, testCanvas.getStylusYCoord());
    }
}
