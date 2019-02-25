package com.example.brstefan.futurebank;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PreferinteViewModel extends AndroidViewModel {
    private PreferinteRepository repository;
    private LiveData<List<Preferinte>> allPreferinte;


    public PreferinteViewModel(@NonNull Application application) {
        super(application);
        repository  = new PreferinteRepository(application);
        allPreferinte = repository.getAllPreferinte();
    }

    public void insert(Preferinte preferinte){
        repository.insert(preferinte);
    }

    public void update(Preferinte preferinte){
        repository.update(preferinte);
    }

    public void delete(Preferinte preferinte){
        repository.delete(preferinte);
    }

    public LiveData<List<Preferinte>> getAllPreferinte(){
        return allPreferinte;
    }

    public void deleteAll() {
        repository.deleteAllPreferinte();
    }
}
