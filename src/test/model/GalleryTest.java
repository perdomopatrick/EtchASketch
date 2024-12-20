package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GalleryTest {

    private Gallery testGallery;

    @BeforeEach
    void runBefore() {
        Gallery.getInstance().clear();
        testGallery = Gallery.getInstance();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testGallery.getCurrCanvasIndex());
        try {
            testGallery.getCanvas(0);
            fail("Has canvas");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
    }

    @Test
    void testSingletonInstance() {
        Gallery firstInstance = Gallery.getInstance();
        Gallery secondInstance = Gallery.getInstance();

        assertSame(firstInstance, secondInstance);

        assertNotNull(firstInstance);
        assertNotNull(secondInstance);
    }

    @Test
    void testClear() {
        testGallery.newCanvas(3, 2);

        Gallery.getInstance().clear();

        try {
            testGallery.getCanvas(0);
            fail("Has canvas");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
    }

    @Test
    void testNewCanvas() {
        testGallery.newCanvas(3, 2);

        assertEquals(0, testGallery.getCurrCanvasIndex());
        assertNotNull(testGallery.getCurrCanvas());
        assertEquals(3, testGallery.getCurrCanvas().getWidth());
        assertEquals(2, testGallery.getCurrCanvas().getHeight());
    }

    @Test
    void testNewCanvasMultiple() {
        testGallery.newCanvas(2, 3);
        testGallery.newCanvas(3, 2);

        assertEquals(1, testGallery.getCurrCanvasIndex());

        assertNotNull(testGallery.getCanvas(0));
        assertEquals(2, testGallery.getCanvas(0).getWidth());
        assertEquals(3, testGallery.getCanvas(0).getHeight());

        assertNotNull(testGallery.getCurrCanvas());
        assertEquals(3, testGallery.getCurrCanvas().getWidth());
        assertEquals(2, testGallery.getCurrCanvas().getHeight());
    }

    @Test
    void testNextCanvas() {
        testGallery.newCanvas(2, 3);
        testGallery.nextCanvas();

        assertEquals(1, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testNextCanvasMultiple() {
        testGallery.newCanvas(2, 3);
        testGallery.newCanvas(2, 3);
        testGallery.nextCanvas();
        testGallery.nextCanvas();

        assertEquals(2, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testNextCanvasPassedSize() {
        testGallery.nextCanvas();

        assertEquals(0, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testPrevCanvas() {
        testGallery.prevCanvas();

        assertEquals(-1, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testPrevCanvasMultiple() {
        testGallery.newCanvas(2, 3);
        testGallery.newCanvas(2, 3);

        testGallery.prevCanvas();
        testGallery.prevCanvas();

        assertEquals(-1, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testPrevCanvasPassedZero() {
        testGallery.prevCanvas();
        testGallery.prevCanvas();

        assertEquals(-1, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testResetCurrCanvas() {
        testGallery.resetCurrCanvas();

        assertEquals(0, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testResetCurrCanvasPositive() {
        testGallery.nextCanvas();
        testGallery.resetCurrCanvas();

        assertEquals(0, testGallery.getCurrCanvasIndex());
    }

    @Test
    void testResetCurrCanvasNegative() {
        testGallery.prevCanvas();
        testGallery.resetCurrCanvas();

        assertEquals(0, testGallery.getCurrCanvasIndex());
    }
}
