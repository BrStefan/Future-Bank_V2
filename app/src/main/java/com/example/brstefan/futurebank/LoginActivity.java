package com.example.brstefan.futurebank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener{


    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        pd = new ProgressDialog(LoginActivity.this);
    }

    @Override
    public void onStart() {
        super.onStart();
        user = mAuth.getCurrentUser();
        if(user!=null) IsLinkedAccount(user);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this,"A aparut o eroare la conectarea cu contul Google!",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            checkStatus();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                            Toast.makeText(LoginActivity.this,"A aparut o eroare la conectarea cu contul Google!",Toast.LENGTH_LONG).show();
                        }

                        // [START_EXCLUDE]
                       // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_in_button) {
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void checkStatus()
    {
        IsLinkedAccount(user);
    }

    private void IsLinkedAccount(FirebaseUser user)
    {
        pd.setMessage("Verificam starea contului");
        pd.show();
        db.collection("utilizatori")
                .whereEqualTo("UID", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        pd.dismiss();
                        if (task.isSuccessful())
                        {
                            QuerySnapshot query = task.getResult();
                            assert query != null;
                            int count = query.size();
                            if(count == 1)
                            {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                                {
                                    long status = (long) document.get("Statut");
                                    if (status == 1) goToApp();
                                }
                            }
                            else goToLink();
                        }
                        else Toast.makeText(LoginActivity.this,"A aparut o eroare la verificarea contului!",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void goToApp()
    {
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
    }

    private void goToLink()
    {
        Intent i = new Intent(LoginActivity.this,LinkActivity.class);
        startActivity(i);
    }
}
