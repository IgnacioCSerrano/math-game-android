package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.R;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.entity.UserEntity;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.viewmodel.UserViewModel;

public class MyUserRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder> {

    private List<UserEntity> mValues;
    private final UserViewModel viewModel;

    public MyUserRecyclerViewAdapter(Context context, List<UserEntity> items) {
        mValues = items;
        viewModel = new ViewModelProvider((AppCompatActivity)context).get(UserViewModel.class);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.textViewNombre.setText(holder.mItem.getNombre());
        holder.textViewPuntos.setText(String.format(Locale.getDefault(), "%d", holder.mItem.getPuntuacion()));

        holder.mView.setOnClickListener(v -> {
            UserEntity u = holder.mItem;
            String text = String.format(Locale.getDefault(),
                "Nombre: %s, Puntuación: %d, Dificultad: %s, Fecha: %s, Hora: %s",
                u.getNombre(), u.getPuntuacion(), u.getDificultad(), u.getFecha(), u.getHora()
            );
            Snackbar.make(v, text, Snackbar.LENGTH_LONG).show();
        });

        holder.mView.setOnLongClickListener(v -> {
            viewModel.deleteUser(holder.mItem);
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNewUsers(List<UserEntity> newUsers) {
        this.mValues = newUsers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewNombre;
        public final TextView textViewPuntos;
        public UserEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombre = view.findViewById(R.id.tvNombre);
            textViewPuntos = view.findViewById(R.id.tvPuntos);
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombre.getText() + "'";
        }
    }
}