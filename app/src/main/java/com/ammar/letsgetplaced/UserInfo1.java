package com.ammar.letsgetplaced;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfo1 extends AppCompatActivity {

    DatabaseReference reff;
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info1);

        reff=FirebaseDatabase.getInstance().getReference();
        t1=findViewById(R.id.tui1);
        t2=findViewById(R.id.tui2);
        t3=findViewById(R.id.tui3);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot datasnapshot)
    {
        for(DataSnapshot ds:datasnapshot.getChildren())
        {
            User unifo=new User();
            unifo.setEmail(ds.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getValue(User.class).getEmail());
            unifo.setCgpa(ds.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getValue(User.class).getCgpa());
            unifo.setSchno(ds.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).getValue(User.class).getSchno());
            t1.setText(unifo.cgpa);
            t2.setText(unifo.email);
            t3.setText(unifo.schno);
        }

    }
}
