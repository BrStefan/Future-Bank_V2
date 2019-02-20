package com.example.brstefan.futurebank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private FirebaseFirestore db;
    private TextView mTextView_nume;
    private TextView mTextView_sold;
    private TextView mTextView_activitate;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
        initHome();

    }

    private void initVariables(View view)
    {
        db=FirebaseFirestore.getInstance();
        mTextView_nume = view.findViewById(R.id.nume_dreapta_hf);
        mTextView_sold = view.findViewById(R.id.sold_dreapta_hf);
        mTextView_activitate = view.findViewById(R.id.eveniment_dreapta_hf);
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
    }

    private void initHome()
    {
        db.collection("utilizatori")
                .whereEqualTo("UID", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                                {
                                    String nume = (String) document.get("Nume");
                                    String prenume = (String) document.get("Prenume");
                                    long cod = (long) document.get("Cod");
                                    String display = nume + " " + prenume;
                                    sold(display,cod);

                                }
                        }
                        else Toast.makeText(getContext(),"A aparut o eroare la preluarea datelor!",Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void sold(final String nume, long cod)
    {
        db.collection("carduri")
                .whereEqualTo("Proprietar",cod)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult()))
                            {
                                long sold = (long) document.get("Sold");
                                String sold_string = Long.toString(sold);
                                mTextView_nume.setText(nume);
                                mTextView_sold.setText(sold_string);
                            }
                        }
                        else Toast.makeText(getContext(),"A aparut o eroare la preluarea datelor!",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
