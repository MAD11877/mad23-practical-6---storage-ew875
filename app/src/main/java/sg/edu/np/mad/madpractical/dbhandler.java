package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class dbhandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_USERNAME = "Name";
    public static final String COLUMN_USERDESCRIPTION = "Description";
    public static final String COLUMN_USERID = "ID";
    public static final String COLUMN_USERFOLLOWED = "Followed";

    public dbhandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE Users" +
                "(" +
                "Id" + " INTEGER" + "," +
                "Name" + " TEXT" + "," +
                "Description" + " TEXT" + "," +
                "Followed" + " BOOLEAN" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);

        for (int i = 0; i < 20; i++) {
            Random num1 = new Random();
            Integer numint1 = num1.nextInt(999999999);

            Random num2 = new Random();
            Integer numint2 = num2.nextInt(999999999);

            Random random = new Random();
            boolean bool = random.nextBoolean();

            User m = new User();
            m.name = "Name" + numint1;
            m.description = "Description " + numint2;
            m.followed = bool;
            m.id = i;

            ContentValues values = new ContentValues();
            values.put("Id", m.getId());
            values.put("Name", m.getName());
            values.put("Description", m.getDescription());
            values.put("Followed", m.isFollowed());
            SQLiteDatabase database = this.getWritableDatabase();

            db.insert("Users", null, values);
            db.close();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public void insertUser(User user){
        ContentValues userValues = new ContentValues();

        userValues.put(COLUMN_USERNAME, user.getName());
        userValues.put(COLUMN_USERDESCRIPTION, user.getDescription());
        userValues.put(COLUMN_USERID, user.getId());
        userValues.put(COLUMN_USERFOLLOWED, user.isFollowed());

        SQLiteDatabase db = this.getWritableDatabase();

        long val = db.insert(TABLE_USERS, null, userValues);
        db.close();
    }

    public ArrayList<User> getUser(){
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM Users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            User uinfo = new User();
            uinfo.setId(Integer.parseInt(cursor.getString(0)));
            uinfo.setName(cursor.getString(1));
            uinfo.setDescription(cursor.getString(2));
            uinfo.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            userList.add(uinfo);
        }
        cursor.close();
        db.close();
        return userList;
    }
    public void updateUser(User usertoupdate){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        if (usertoupdate.followed == false) {
            values.put("Followed", false);
            db.update("Users", values, "Id = ?", new String[]{"" + usertoupdate.id});
        }
        else {
            values.put("Followed", true);
            db.update("Users", values, "Id = ?", new String[]{"" + usertoupdate.id});
        }
    }
}


