# SQL Query Manager
A simple SQL File Manager for Java

This library aims to provide a simple way of loading and using SQL files when preparing statements in a JDBC Driver.
The SQL Files are stored inside the jar as resource files.

## Using

There are only two methods, `SQLQueryManager.load(String)` and `SQLQueryManager.query(String)`, the latter makes sense to use a static import: `import static io.github.braayy.sqm.SQLQueryManager.query;`

The `SQLQueryManager.load(String)` method should be called only once in the application lifetime and preferably at the start of it. The string parameter is the root folder of the queries in the jar's resources.

And finally, a long list of method right?, the `SQLQueryManager.query(String)` should be called everytime the application wants to do a database query.

Example:
```java
import static io.github.braayy.sqm.SQLQueryManager.query;

SQLQueryManager.load("queries");

try (
        Connection conn = /* HikariCP DataSource Here */.getConnection();
        PreparedStatment stmt = conn.prepareStatement(query("CreateUserTable"))
) {
    stmt.executeUpdate();
} catch (SQLException exception) {
    exception.printStackTrace();
}
```

## TODO:

- Named Parameters, if i find a reliable SQL string escape lib maybe it would work.  
