package persistence;

import org.junit.jupiter.api.Test;

import model.Canvas;
import model.Gallery;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    private Gallery testGallery;
    private boolean[][] expectedArray;
    private Canvas testCanvas; 

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            testGallery = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
        assertNull(testGallery);
    }

    @Test
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            testGallery = reader.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        try {
            testGallery.getCanvas(0);
            fail("Has canvas");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
    }

    @Test
    void testReaderOneCanvas() {
        JsonReader reader = new JsonReader("./data/testReaderOneCanvas.json");
        try {
            testGallery = reader.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        testCanvas = testGallery.getCanvas(0);

        expectedArray = new boolean[][] {
                { false },
                { false }};

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(1, testCanvas.getWidth());
        assertEquals(2, testCanvas.getHeight());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());

    }

    @Test
    void testReaderMultipleCanvases() {
        JsonReader reader = new JsonReader("./data/testReaderMultipleCanvases.json");
        try {
            testGallery = reader.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        Canvas testCanvas = testGallery.getCanvas(0);

        expectedArray = new boolean[][] {
                { false },
                { false } };

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(1, testCanvas.getWidth());
        assertEquals(2, testCanvas.getHeight());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());

        testCanvas = testGallery.getCanvas(1);

        expectedArray = new boolean[][] {
                { false, false }};

        assertArrayEquals(expectedArray, testCanvas.getBoard());
        assertEquals(2, testCanvas.getWidth());
        assertEquals(1, testCanvas.getHeight());
        assertEquals(0, testCanvas.getStylusXCoord());
        assertEquals(0, testCanvas.getStylusYCoord());
    }

}
