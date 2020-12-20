package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.dao.UserDAO;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.entity.UserEntity;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<UserEntity>> allUsers;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        allUsers = userDAO.getAll();
    }

    public LiveData<List<UserEntity>> getAll() {  return allUsers; }

    public void insert(UserEntity user) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> userDAO.insert(user));
    }

    public void delete(UserEntity user) {
        UserRoomDatabase.databaseWriteExecutor.execute(() -> userDAO.deleteById(user.getId()));
    }
}
