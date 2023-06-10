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
    User u = new User();
    dbhandler dbhandler = new dbhandler(this, null, null, 1);
    Intent intent = getIntent();

    int place = intent.getIntExtra("index", -1);

    User targetuser = UserAdapter.data.get(place);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "Create!");
        User myUser =  new User();
        myUser.setFollowed(false);
        TextView change = findViewById(R.id.helloworld);
        change.setText("MAD " + getRandomNumber());
        Button message = (Button) findViewById(R.id.button);
        message.setOnClickListener(view -> {
            Intent messager = new Intent (MainActivity.this, MessageGroup.class);
            startActivity(messager);}
                );

        Button myfollowbutton = findViewById(R.id.button2);
        myfollowbutton.setOnClickListener(view -> {
            boolean bool = myUser.isFollowed();
            bool = !bool;
            myUser.setFollowed(bool);
            Log.v(TITLE, "on Destroy....");
            if (myUser.isFollowed() == Boolean.FALSE)
            {
                dbhandler.updateUser(targetuser);
                myfollowbutton.setText("Follow");
                Toast.makeText(getApplicationContext(),"Followed",Toast.LENGTH_SHORT).show();
            }
            else
            {
                myfollowbutton.setText("Unfollow");
                Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
            }

            Log.v(TITLE, "on Destroy....");
        });
    }
    private int getRandomNumber(){
        Random random = new Random();
        int randomValue = random.nextInt(100000);
        return randomValue;
    }
}