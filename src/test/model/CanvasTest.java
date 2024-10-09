package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CanvasTest {

    private Canvas testCanvas;

    @BeforeEach
    void runBefore() {
        testCanvas = new Canvas(3, 2);
    }

    @Test
    void testConstructor() {
        assertArrayEquals(new boolean[2][3], testCanvas.getBoard());
        assertEquals(3, testCanvas.getWidth());
        assertEquals(2, testCanvas.getHeight());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawRight() {
        testCanvas.draw("right", 2);

        boolean[][] expectedArray = {
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
        testCanvas.draw("left", 2);

        boolean[][] expectedArray = {
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
        testCanvas.draw("up", 1);

        boolean[][] expectedArray = {
                { true, false, false },
                { false, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testDrawDown() {
        testCanvas.draw("down", 1);

        boolean[][] expectedArray = {
                { false, false, false },
                { true, false, false }
        };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(1, testCanvas.getStylusYCoord());
    }

    @Test
    void testShake() {
        testCanvas.shake();

        assertArrayEquals(new boolean[2][3], testCanvas.getBoard());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

    @Test
    void testShakeColouredBoard() {
        testCanvas.draw("right", 2);
        testCanvas.draw("down", 1);

        testCanvas.shake();

        assertArrayEquals(new boolean[2][3], testCanvas.getBoard());
        assertEquals(2, testCanvas.getStylusXCoord());
        assertEquals(1, testCanvas.getStylusYCoord());
    }
}
