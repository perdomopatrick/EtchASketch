package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GalleryTest {

    private Gallery testGallery;

    @BeforeEach
    void runBefore() {
        testGallery = new Gallery();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testGallery.getCurrCanvasIndex());
        assertEquals(0, testGallery.getCollection().size());
    }

    @Test
    public void testSingletonInstance() {
        Gallery firstInstance = Gallery.getInstance();
        Gallery secondInstance = Gallery.getInstance();
        
        assertSame(firstInstance, secondInstance);
        
        assertNotNull(firstInstance);
        assertNotNull(secondInstance);
    }

    @Test
    public void testNewCanvas() {
        testGallery.newCanvas(3, 2);


        assertEquals(0, testGallery.getCurrCanvasIndex());
        assertNotNull(testGallery.getCurrCanvas());
        assertEquals(3, testGallery.getCurrCanvas().getWidth());
        assertEquals(2, testGallery.getCurrCanvas().getHeight());
    }

    @Test
    public void testNewCanvasMultiple() {
        testGallery.newCanvas(2, 3);
        testGallery.newCanvas(3,2);

        assertEquals(1, testGallery.getCurrCanvasIndex());
        
        assertNotNull(testGallery.getCanvas(0));
        assertEquals(2, testGallery.getCanvas(0).getWidth());
        assertEquals(3, testGallery.getCanvas(0).getHeight());

        assertNotNull(testGallery.getCurrCanvas());
        assertEquals(3, testGallery.getCurrCanvas().getWidth());
        assertEquals(2, testGallery.getCurrCanvas().getHeight());
    }

    @Test
    public void testNextCanvas() {
        testGallery.nextCanvas();

        assertEquals(1, testGallery.getCurrCanvasIndex());
    }

    @Test
    public void testPrevCanvas() {
        testGallery.prevCanvas();

        assertEquals(-1, testGallery.getCurrCanvasIndex());
    }

    @Test
    public void testResetCurrCanvas() {
        testGallery.resetCurrCanvas();

        assertEquals(0, testGallery.getCurrCanvasIndex());
    }

    @Test
    public void testResetCurrCanvasNotAtZero() {
        testGallery.nextCanvas();
        testGallery.nextCanvas();
        testGallery.resetCurrCanvas();

        assertEquals(0, testGallery.getCurrCanvasIndex());
    }
}
