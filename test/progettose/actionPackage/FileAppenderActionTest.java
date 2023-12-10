package progettose.actionPackage;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FileAppenderActionTest {

    private File tempFile;

    @Before
    public void setUp() throws IOException {
        // Creare un file temporaneo prima di ogni test
        tempFile = File.createTempFile("tempFile", ".txt");

        // Inizializzare JavaFX Toolkit (richiesto quando si utilizza Platform.runLater())
        new JFXPanel();
    }

    @After
    public void tearDown() {
        // Eliminare il file temporaneo dopo ogni test
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void execute_shouldAppendMessageToFile() {
        try {
            // Creare un'istanza di FileAppenderAction
            FileAppenderAction fileAppenderAction = new FileAppenderAction(tempFile.toPath(), "Hello, World!");

            // Creare un CompletableFuture per attendere la fine dell'esecuzione asincrona
            CompletableFuture<Void> future = new CompletableFuture<>();

            // Eseguire l'azione asincrona
            Platform.runLater(() -> {
                fileAppenderAction.execute();
                future.complete(null);
            });

            // Attendere che l'azione asincrona sia completata (con un timeout)
            future.get(5, TimeUnit.SECONDS);

            // Verificare che il messaggio sia stato aggiunto correttamente al file
            BufferedReader reader = new BufferedReader(new FileReader(tempFile));
            String line = reader.readLine();
            System.out.println(line);
            reader.close();

            assertEquals("Hello, World!", line);
        } catch (Exception e) {
            fail("Eccezione durante il test: " + e.getMessage());
        }
    }
}
