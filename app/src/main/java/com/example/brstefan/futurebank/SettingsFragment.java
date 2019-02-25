package com.example.brstefan.futurebank;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class SettingsFragment extends Fragment  implements AdapterView.OnItemSelectedListener {

    private PreferinteViewModel preferinteViewModel;
    private Button add_button;
    private String categorie;
    private EditText mEditText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.Preferinta_1);

        Spinner spinner = view.findViewById(R.id.spinner_settings);
        final ArrayAdapter<CharSequence> adapter  = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),R.array.options,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        RecyclerView rw = view.findViewById(R.id.preferinte_rw);
        rw.setLayoutManager(new LinearLayoutManager(getContext()));
        rw.setHasFixedSize(true);


        final PreferinteAdapter adapter1 = new PreferinteAdapter();
        rw.setAdapter(adapter1);

        preferinteViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(PreferinteViewModel.class);
        preferinteViewModel.getAllPreferinte().observe(this, new Observer<List<Preferinte>>() {
            @Override
            public void onChanged(@Nullable List<Preferinte> preferintes) {
                adapter1.setPreferinte(preferintes);
            }
        });

        add_button = view.findViewById(R.id.add_pref);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //preferinteViewModel.insert(new Preferinte("teste","teste"));
                if(mEditText.getText().toString().trim().length() >0 && categorie != null ) preferinteViewModel.insert(new Preferinte(categorie,mEditText.getText().toString()));
                else Toast.makeText(getContext(),"Completati toate campurile!",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categorie = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
