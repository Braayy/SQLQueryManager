package io.github.braayy.sqm.test;

import io.github.braayy.sqm.SQLQueryManager;
import io.github.braayy.sqm.exception.SQMException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.github.braayy.sqm.SQLQueryManager.query;
import static org.junit.jupiter.api.Assertions.*;

public final class SQLQueryManagerTest {

    @Test
    public void itShouldThrowSQMExceptionWhenResourceDirNotFound() {
        assertThrows(SQMException.class, () -> SQLQueryManager.load("queriesOfApp"));
    }

    @Test
    public void itShouldRetrieveTheCorrectQuery() throws Exception {
        SQLQueryManager.load("queries");

        String query = query("CreateUser");

        assertEquals(query, "CREATE TABLE IF NOT EXISTS User();");
    }

    @Test
    public void itShouldNotRetrieveTheCorrectQuery() throws Exception {
        SQLQueryManager.load("queries");

        assertThrows(IllegalArgumentException.class, () -> query("CreateRole"));
    }

}
