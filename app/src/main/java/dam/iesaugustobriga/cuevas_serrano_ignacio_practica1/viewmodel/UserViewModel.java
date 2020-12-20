package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.UserRepository;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.entity.UserEntity;

public class UserViewModel extends AndroidViewModel {
    private final LiveData<List<UserEntity>> allUsers;
    private final UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAll();
    }

    public LiveData<List<UserEntity>> getAllUsers() { return allUsers; }

    public void insertUser(UserEntity newUser) { userRepository.insert(newUser); }

    public void deleteUser(UserEntity deletedUser) { userRepository.delete(deletedUser); }
}
