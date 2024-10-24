package persistence;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import model.Canvas;
import model.Gallery;

import java.io.IOException;
public class JsonWriterTest {

    private Gallery testGallery;

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
            testGallery = new Gallery();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(testGallery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            testGallery = reader.read();

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
            testGallery = new Gallery();
            testGallery.addCanvas(new Canvas(1, 2));
            ;
            JsonWriter writer = new JsonWriter("./data/testWriterOneCanvas.json");
            writer.open();
            writer.write(testGallery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterOneCanvas.json");
            testGallery = reader.read();

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
            testGallery = new Gallery();
            testGallery.addCanvas(new Canvas(1, 2));
            ;
            JsonWriter writer = new JsonWriter("./data/testWriterMultipleCanvases.json");
            writer.open();
            writer.write(testGallery);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMultipleCanvases.json");
            testGallery = reader.read();

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

}
