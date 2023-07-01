package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    String title = "List Activity";
    //    private String RandomNumber(){
//        Random random = new Random();
//        int randomValue = random.nextInt(100000);
//        return Integer.toString(randomValue);
//    }
//    ArrayList<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbhandler db = new dbhandler(this, null, null, 1);
        ArrayList<User> userArrayList = db.getUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        for (int i = 0; i < 20; i++){
//            Random randomNo = new Random();
//
//            int userId = Math.abs(randomNo.nextInt()/1000);
//            String userName = "Name" + userId;
//            String userDescription = "Description " + Math.abs(randomNo.nextInt()/1000);
//            boolean userIsFollowing = randomNo.nextInt(2)==1;
//            User newUser = new User(userName, userDescription, userId, userIsFollowing);
//            Log.v(title, newUser.getName());
//            db.insertUser(newUser);
//        }
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        UserAdapter myAdapter = new UserAdapter(userArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }
}