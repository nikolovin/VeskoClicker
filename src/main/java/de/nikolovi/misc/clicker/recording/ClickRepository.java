package de.nikolovi.misc.clicker.recording;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClickRepository {

    private static final String FILE_NAME = "clicks.json";

    public void save(Clicks clicks) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(getFilePath().toFile(), clicks);
    }

    public Clicks load() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectReader reader = mapper.readerFor(Clicks.class);
        return reader.readValue(getFilePath().toFile());
    }

    private Path getFilePath() {
        return Paths.get(System.getProperty("user.home"), FILE_NAME);
    }
}
