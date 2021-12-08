package sample.entity;

import com.google.common.collect.Multimap;
import sample.Model.subscriber.NameType;
import sample.Model.subscriber.Subscriber;
import sample.sqlHelper.SqlHelper;
import sample.sqlHelper.Storage;

import java.sql.DriverManager;

public class SqlEntity implements Storage {
    private final SqlHelper sqlHelper;

    public SqlEntity(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    private void addMapElements(Subscriber s) {
        sqlHelper.<Void>execute("INSERT INTO subscriber (full_name, phone) VALUES (?,?)", ps -> {
            ps.setString(1, s.getName().getFullName());
            ps.setInt(2, Integer.parseInt(String.valueOf(s.getPhone().getPhone())));
            ps.execute();
            return null;
        });
    }

    @Override
    public void save(Multimap<NameType, Subscriber> entity) {
        clear();
        entity.entries().forEach(entry -> {
            addMapElements(entry.getValue());
        });
        sqlHelper.execute("INSERT INTO subscriber (full_name, phone) VALUES (?,?)", ps -> {
            ps.setString(1, "Alina Mikhaleva");
            ps.setInt(2, 2405531);
            ps.execute();
            return null;
        });
    }

    //    @Override
//    public void add(Subscriber s) throws ExistStorageException {
//        sqlHelper.<Void>execute("INSERT INTO subscriber (full_name, phone) VALUES (?,?)", ps -> {
//            ps.setString(1, s.getName().getFullName());
//            ps.setString(2, String.valueOf(s.getPhone()));
//            ps.execute();
//            return null;
//        });
//    }
//
//    @Override
//    public void remove(Subscriber s) throws ExistStorageException {
//        sqlHelper.execute("DELETE FROM subscriber where full_name = ? ", ps -> {
//            ps.setString(1, s.getName().getFullName());
//            if (ps.executeUpdate() == 0) {
//                throw new NotExistStorageException(s.getName().getFullName());
//            }
//            return null;
//        });
//    }
//
    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM subscriber");
    }

//    @Override
//    public void edit(Subscriber s1, Subscriber s2) throws ExistStorageException {
//        remove(s1);
//        add(s2);
//    }
//
//    @Override
//    public <T extends AbstractType> Collection<Subscriber> find(T value) throws ExistStorageException {
//        return sqlHelper.execute("SELECT * FROM SUBSCRIBER s WHERE s.full_name = ? ", ps -> {
//            ps.setString(1, value);
//
//            ResultSet rs = ps.executeQuery();
//            if (!rs.next()) {
//                throw new NotExistStorageException(uuid);
//            }
//            return new Resume(uuid, rs.getString("full_name"));
//        });
//    }

//    public Collection<Subscriber> getAllSorted() throws ExistStorageException {
//        return sqlHelper.execute("SELECT * FROM SUBSCRIBER s ORDER BY full_name", ps -> {
//            ResultSet rs = ps.executeQuery();
//            List<Subscriber> resumes = new ArrayList<>();
//            while (rs.next()) {
//                resumes.add(new Subscriber(rs.getString("full_name"), rs.getString("phone")));
//            }
//            return resumes;
//        });
//    }
}