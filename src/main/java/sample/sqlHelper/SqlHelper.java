package sample.sqlHelper;

import org.postgresql.util.PSQLException;
import sample.exeption.DataException;
import sample.exeption.storage.ExistStorageException;
import sample.exeption.storage.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private StorageException existException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> void execute(String sql, SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            executor.execute(ps);
        } catch (SQLException | DataException e) {
            assert e instanceof SQLException;
            throw existException((SQLException) e);
        }
    }
}