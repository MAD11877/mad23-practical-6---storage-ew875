package sg.edu.np.mad.madpractical;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    public static ArrayList<User> data;
    ListActivity activity;

    public UserAdapter(ArrayList<User> users) {
        this.data = users;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recyclerview,
                parent,
                false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        User userList = data.get(position);
        String Name = userList.getName();
        String Description = userList.getDescription();
        if (!Name.endsWith("7")) {
            holder.bigImage.setVisibility(View.GONE);
        }
        else {
            holder.bigImage.setVisibility(View.VISIBLE);
        }

        holder.text1.setText(Name);
        holder.text2.setText(Description);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
                builder.setTitle("Profile");
                builder.setMessage(Name);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent profile = new Intent(view.getContext(), MainActivity.class);
                        profile.putExtra("userId", userList.getId());
                        view.getContext().startActivity(profile);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}

public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2;
        ImageView image, bigImage;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            text1 = itemView.findViewById(R.id.nametextview);
            text2 = itemView.findViewById(R.id.descriptionTextView);
            image = itemView.findViewById(R.id.imageView4);
            bigImage = itemView.findViewById(R.id.imageView2);
        }
    }
}
