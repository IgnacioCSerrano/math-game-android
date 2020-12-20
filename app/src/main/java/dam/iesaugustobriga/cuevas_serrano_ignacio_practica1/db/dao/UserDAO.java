package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.entity.UserEntity;

@Dao
public interface UserDAO {
    @Insert
    void insert(UserEntity user);

    @Query("DELETE FROM user WHERE id = :idUser")
    void deleteById(int idUser);

    @Query("SELECT * FROM user ORDER BY fecha DESC")
    LiveData<List<UserEntity>> getAll();
}
