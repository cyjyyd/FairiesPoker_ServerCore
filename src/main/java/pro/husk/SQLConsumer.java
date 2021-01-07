package pro.husk;
import java.sql.SQLException;
import pro.husk.mysql.MySQL;

@FunctionalInterface
public interface SQLConsumer<T> {
    void accept(T t) throws SQLException;
}
