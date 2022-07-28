package io.github.braayy.sqm;

import io.github.braayy.sqm.exception.SQMException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

final class SQLQueryLoader {

    static Map<String, String> loadQueries(String rootResourceQueriesDir) throws SQMException {
        try {
            ClassLoader classLoader = SQLQueryManager.class.getClassLoader();
            Enumeration<URL> resources = classLoader.getResources(rootResourceQueriesDir);
            if (!resources.hasMoreElements()) {
                throw new FileNotFoundException(rootResourceQueriesDir + " was not found in resources");
            }

            Map<String, String> queryMap = new HashMap<>();

            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                File file = new File(url.toURI());

                loadResourceFile(file, queryMap);
            }

            return queryMap;
        } catch (Exception exception) {
            throw new SQMException(exception);
        }
    }

    private static void loadResourceFile(File resourceFile, Map<String, String> queryMap) throws IOException {
        if (resourceFile.isDirectory()) {
            loadQueryFolder(resourceFile, queryMap);
        } else {
            loadQueryFile(resourceFile, queryMap);
        }
    }

    private static void loadQueryFolder(File queryFolder, Map<String, String> queryMap) throws IOException {
        File[] files = queryFolder.listFiles((file) -> file.isDirectory() || file.getName().endsWith(".sql"));
        if (files == null)
            return;

        for (File file : files) {
            if (file.isDirectory()) {
                loadQueryFolder(file, queryMap);
            } else {
                loadQueryFile(file, queryMap);
            }
        }
    }

    private static void loadQueryFile(File queryFile, Map<String, String> queryMap) throws IOException {
        byte[] bytes = Files.readAllBytes(queryFile.toPath());
        String name = getFilenameWithoutExtension(queryFile);
        String content = new String(bytes, 0, bytes.length);

        queryMap.put(name, content);
    }

    private static String getFilenameWithoutExtension(File file) {
        String filename = file.getName();

        return filename.substring(0, filename.lastIndexOf('.'));
    }

}
