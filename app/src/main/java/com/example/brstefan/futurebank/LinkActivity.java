package com.example.brstefan.futurebank;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.hash.Hashing;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

public class LinkActivity extends AppCompatActivity {

    private EditText mNume;
    private EditText mPrenume;
    private EditText mPin;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        init_fields();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
    }

    public void link_account(View view)
    {
        String name = mNume.getText().toString();
        String name2 = mPrenume.getText().toString();
        String pin = mPin.getText().toString();
        final String hash = Hashing.sha512().hashString(pin,StandardCharsets.UTF_8).toString().toUpperCase();

        db.collection("utilizatori")
                .whereEqualTo("Nume", name)
                .whereEqualTo("Prenume", name2)
                .whereEqualTo("Pin", hash)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            QuerySnapshot query = task.getResult();
                            assert query != null;
                            int count = query.size();
                            if(count == 0)Toast.makeText(LinkActivity.this,"Cont negasit!",Toast.LENGTH_LONG).show();
                            else Toast.makeText(LinkActivity.this,"Cont gasit!",Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(LinkActivity.this,"A aparut o eroare la verificarea contului!",Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void init_fields()
    {
        mNume = (EditText)findViewById(R.id.Nume);
        mPrenume = (EditText)findViewById(R.id.Prenume);
        mPin = (EditText)findViewById(R.id.Pin);

    }
}
