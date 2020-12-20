package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.R;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.entity.UserEntity;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.viewmodel.UserViewModel;

/**
 * A fragment representing a list of Items.
 */
public class UserFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyUserRecyclerViewAdapter adapterUser;
    private List<UserEntity> userList;
    private UserViewModel userViewModel;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UserFragment newInstance(int columnCount) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            userList = new ArrayList<>();

            adapterUser = new MyUserRecyclerViewAdapter(getActivity(), userList);
            recyclerView.setAdapter(adapterUser);

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(getActivity(), userEntities -> adapterUser.setNewUsers(userEntities));
    }
}