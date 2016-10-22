package com.nxt.firebasecloudmessage.database;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nxt.firebasecloudmessage.R;
import com.nxt.firebasecloudmessage.models.Post;
import com.nxt.firebasecloudmessage.models.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NXT on 21/10/2016.
 */

public class ReadWriteDataActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextValue;
    Button buttonAdd;

    TextView textViewUserId;
    TextView textViewUserName;

    DatabaseReference database=FirebaseDatabase.getInstance().getReference();
    DatabaseReference postDatabase=database.child("posts");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write);


        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextValue = (EditText) findViewById(R.id.editTextValue);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        textViewUserId=(TextView)findViewById(R.id.textViewUserID);
        textViewUserName=(TextView)findViewById(R.id.textViewUserName);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();

                User user = new User(editTextName.getText().toString(), editTextValue.getText().toString());

                // database.child("users").child("123").setValue(user);
             //   String keys = database.child("users").getKey();
              //  String keys2 = database.child("users").push().getKey();

              //  Log.d("key1", keys);
              //  Log.d("key2", keys2);

                writeNewPost("1","2","3",editTextValue.getText().toString());


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("________","change");

                Post post=dataSnapshot.getValue(Post.class);

                textViewUserId.setText(post.uid!=null?post.uid:"null");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("database","add");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("database","change");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("database","remove");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("database","move");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        postDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("postDatabase","add");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("postDatabase","change");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("postDatabase","remove");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("postDatabase","move");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key =database.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        database.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Log.d("update","Xong_");

            }
        });


    }
}
