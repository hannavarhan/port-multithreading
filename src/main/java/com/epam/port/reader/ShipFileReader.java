package com.epam.port.reader;

import com.epam.port.exception.PortException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShipFileReader {
    private final static Logger logger = LogManager.getLogger(ShipFileReader.class);

    public ArrayList<String> readFile(String filepath) throws PortException {
        ArrayList<String> result;
        URL resource = getClass().getClassLoader().getResource(filepath);
        String path;
        if (resource == null) {
            logger.error("Invalid path: {}", filepath);
            throw new PortException("Invalid path: " + filepath);
        }
        path = new File(resource.getFile()).getAbsolutePath();
        try {
            result = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
                    .collect(Collectors.toCollection(ArrayList::new));
            logger.info("result of readFile is {}", result);
        } catch (IOException e) {
            logger.error("IOException in read from file method with path {}: {}", filepath, e.getMessage());
            throw new PortException("IOException in read from file method with path " + filepath);
        }
        return result;
    }
}
