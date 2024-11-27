package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Canvas;
import model.Gallery;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    private Gallery testGallery;
    private boolean[][] expectedArray;
    private Canvas testCanvas; 

    @BeforeEach
    void setup() {
        Gallery.getInstance().clear();
        testGallery = Gallery.getInstance();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
        assertEquals(0, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            reader.read();
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
            reader.read();
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
            reader.read();
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
