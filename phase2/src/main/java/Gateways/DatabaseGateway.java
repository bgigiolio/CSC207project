package main.java.Gateways;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * <h1>DatabaseGateway</h1>
 * Abstract gateway class to provide a common interface and max code reuse
 * @version phase2
 * @author Konstantinos Papaspyridis
 */
public abstract class DatabaseGateway<T> {

    /**
     * Stores the path to the database file
     */
    private String dbPath;

    public DatabaseGateway(String dbPath){
        this.dbPath = dbPath;
    }

    public String getDbPath(){
        return this.dbPath;
    }

    public abstract void save(T obj);

    public abstract T read();

    /**
     * Utility method to clear file contents if file contains corrupt data
     * @param db what kind of database is accessed (e.g. events, messages, etc.)
     */
    protected void clearFileContentsUtil(String db) {
        try {
            PrintWriter writer = new PrintWriter(dbPath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unexpected error when accessing the " + db + " database file.");
            e.printStackTrace();
        }
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }
}
