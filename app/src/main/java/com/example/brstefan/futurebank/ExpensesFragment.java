package com.example.brstefan.futurebank;

import android.app.AlertDialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.events.Event;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Objects;


public class ExpensesFragment extends Fragment implements ExpenseAdapter.OnItemClickListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference expenseRef = db.collection("tranzactii");

    private ExpenseAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expenses,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(view);

    }

    private void setUpRecyclerView(View v){

        Query query = expenseRef.whereEqualTo("Proprietar",1).orderBy("Data");


        FirestoreRecyclerOptions<Expense> options = new FirestoreRecyclerOptions.Builder<Expense>()
                .setQuery(query,Expense.class)
                .build();

        adapter = new ExpenseAdapter(options);

        RecyclerView rw = v.findViewById(R.id.expenses_rw);
        rw.setLayoutManager(new LinearLayoutManager(getContext()));
        rw.setAdapter(adapter);
        adapter.setOnItemClickListener(new ExpenseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Expense expense = documentSnapshot.toObject(Expense.class);
                final AlertDialog.Builder builder;
                Context context;
                builder= new AlertDialog.Builder(getContext(),android.R.style.Theme_Material_Dialog_Alert);
                builder.setTitle("Detalii tranzactie");
                String mesaj;
                assert expense != null;
                mesaj = "Categorie: "+expense.getCategorie()+"\n"
                        +"Data: "+expense.getData()+"\n"
                        +"Moneda: "+expense.getMoneda()+"\n"
                        +"Suma: "+expense.getSuma();
                builder.setMessage(mesaj);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nothing on click
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

    }
}
