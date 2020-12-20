package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.dao.UserDAO;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    private static volatile UserRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) { // si es la primera vez que se invoca obtención de instancia base de datos
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
