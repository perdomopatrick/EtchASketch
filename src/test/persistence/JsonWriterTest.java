package persistence;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Canvas;
import model.Gallery;

import java.io.IOException;

public class JsonWriterTest {

    private Gallery testGallery;

    @BeforeEach
    void setup() {
        Gallery.getInstance().clear();
        testGallery = Gallery.getInstance();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmpty() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(testGallery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            reader.read();

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        try {
            testGallery.getCanvas(0);
            fail("Has canvas");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
    }

    @Test
    void testWriterOneCanvas() {
        try {
            testGallery.addCanvas(new Canvas(1, 2));

            JsonWriter writer = new JsonWriter("./data/testWriterOneCanvas.json");
            writer.open();
            writer.write(testGallery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterOneCanvas.json");
            reader.read();

            Canvas testCanvas = testGallery.getCanvas(0);
            boolean[][] expectedArray = new boolean[][] {
                    { false },
                    { false } };

            assertArrayEquals(expectedArray, testCanvas.getBoard());
            assertEquals(1, testCanvas.getWidth());
            assertEquals(2, testCanvas.getHeight());
            assertEquals(0, testCanvas.getStylusXCoord());
            assertEquals(0, testCanvas.getStylusYCoord());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultipleCanvas() {
        try {
            testGallery.addCanvas(new Canvas(1, 2));
            testGallery.addCanvas(new Canvas(2, 1));

            JsonWriter writer = new JsonWriter("./data/testWriterMultipleCanvases.json");
            writer.open();
            writer.write(testGallery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMultipleCanvases.json");
            reader.read();

            Canvas testCanvas = testGallery.getCanvas(0);
            Canvas testCanvas2 = testGallery.getCanvas(1);
            boolean[][] expectedArray = new boolean[][] {{ false }, { false } };
            boolean[][] expectedArray2 = new boolean[][] { { false, false } };

            assertArrayEquals(expectedArray, testCanvas.getBoard());
            assertEquals(1, testCanvas.getWidth());
            assertEquals(2, testCanvas.getHeight());
            assertEquals(0, testCanvas.getStylusXCoord());
            assertEquals(0, testCanvas.getStylusYCoord());

            assertArrayEquals(expectedArray2, testCanvas2.getBoard());
            assertEquals(2, testCanvas2.getWidth());
            assertEquals(1, testCanvas2.getHeight());
            assertEquals(0, testCanvas2.getStylusXCoord());
            assertEquals(0, testCanvas2.getStylusYCoord());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}