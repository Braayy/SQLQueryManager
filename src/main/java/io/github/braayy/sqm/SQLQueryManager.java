package io.github.braayy.sqm;

import io.github.braayy.sqm.exception.SQMException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SQLQueryManager {

    private static final Map<String, String> queryMap = new HashMap<>();

    public static void load(@NotNull String queriesFolder) throws SQMException {
        Map<String, String> loadedQueryMap = SQLQueryLoader.loadQueries(queriesFolder);
        queryMap.putAll(loadedQueryMap);
    }

    public static @NotNull String query(@NotNull String name) {
        Objects.requireNonNull(name, "Query name cannot be null");

        String query = queryMap.get(name);
        if (query == null) {
            throw new IllegalArgumentException("Query " + name + " was not found!");
        }

        return query;
    }

}
