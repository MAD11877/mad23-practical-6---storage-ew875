package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final String TITLE = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "Create!");
        TextView Name = (TextView) findViewById((R.id.name));
        TextView Description = (TextView) findViewById((R.id.description));

        Button followButton = (Button) findViewById(R.id.button2);

        Intent i2 = getIntent();
//        String username = i2.getStringExtra("Username");
//        String description = i2.getStringExtra("Description");
//        boolean followed = i2.getBooleanExtra("Followed", false);
//        List<User> userList = (List<User>) i2.getSerializableExtra("UserList");
        int place = i2.getIntExtra("index", -1);

        User targetuser = UserAdapter.data.get(place);

        Name.setText(targetuser.name);
        Description.setText(targetuser.description);
        if (targetuser.followed == true){
            followButton.setText("Unfollow");
        }
        else {
            followButton.setText("Follow");
        }

        dbhandler dbquerymachine = new dbhandler(this, null, null, 1);

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (targetuser.followed == true){
                    targetuser.followed = false;
                    dbquerymachine.updateUser(targetuser);
                    followButton.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                else {
                    targetuser.followed = true;
                    dbquerymachine.updateUser(targetuser);
                    followButton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}