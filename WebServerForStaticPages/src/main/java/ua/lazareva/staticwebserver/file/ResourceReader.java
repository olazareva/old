package ua.lazareva.staticwebserver.file;

import java.io.*;

public class ResourceReader {
    private static String resourceReaderPath;
    private String resourcePath;

    public ResourceReader(String resourcePath) {
        this.resourcePath = resourcePath;
        resourceReaderPath = resourcePath;
    }

    public static String getResourcePath() {
        return resourceReaderPath;
    }

    public InputStream getResource(String url) {
        File path = new File(resourcePath + File.separator + url);
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println(path + " - NOT FOUND");
            throw new RuntimeException(e);
        }
    }
}
