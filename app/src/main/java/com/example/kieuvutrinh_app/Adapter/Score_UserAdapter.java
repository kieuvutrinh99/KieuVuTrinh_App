package com.example.kieuvutrinh_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kieuvutrinh_app.R;
import com.example.kieuvutrinh_app.Score_User;

import org.w3c.dom.Text;

import java.util.List;

public class Score_UserAdapter extends RecyclerView.Adapter<Score_UserAdapter.Score_UserViewHolder> {
    private Context context;
    List<Score_User> score_userList;

    public Score_UserAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Score_User> score_users){
        this.score_userList = score_users;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Score_UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_highscore,parent,false);
        return new Score_UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Score_UserViewHolder holder, int position) {
        Score_User score_user = score_userList.get(position);
        holder.tv_username_user.setText(score_user.getUser_name());
        holder.tv_score_user.setText(String.valueOf(score_user.getScore()));
    }

    @Override
    public int getItemCount() {
        return score_userList.size();
    }

    public class Score_UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_username_user;
        private TextView tv_score_user;

        public Score_UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username_user = itemView.findViewById(R.id.tv_name_user);
            tv_score_user = itemView.findViewById(R.id.tv_score_user);
        }
    }
}
