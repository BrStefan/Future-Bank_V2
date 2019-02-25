package com.example.brstefan.futurebank;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PreferinteRepository {
    private  PreferinteDao preferinteDao;
    private LiveData<List<Preferinte>> allPreferinte;

    public PreferinteRepository(Application application){
        PreferinteDatabase database = PreferinteDatabase.getInstance(application);
        preferinteDao = database.preferinteDao();
        allPreferinte = preferinteDao.getAllPreferences();
    }

    public void insert(Preferinte preferinte){
        new InsertNoteAsyncTask(preferinteDao).execute(preferinte);
    }

    public void update(Preferinte preferinte){
        new UpdateNoteAsyncTask(preferinteDao).execute(preferinte);
    }

    public void delete(Preferinte preferinte){
        new DeleteNoteAsyncTask(preferinteDao).execute(preferinte);
    }

    public LiveData<List<Preferinte>> getAllPreferinte(){
        return allPreferinte;
    }

    public void deleteAllPreferinte(){
        new deleteAllAsyncTask(preferinteDao).execute();
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Preferinte,Void,Void>{
        private PreferinteDao preferinteDao;

        private InsertNoteAsyncTask(PreferinteDao preferinteDao){
            this.preferinteDao = preferinteDao;
        }

        @Override
        protected Void doInBackground(Preferinte... preferintes) {
            preferinteDao.insert(preferintes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Preferinte,Void,Void>{
        private PreferinteDao preferinteDao;

        private UpdateNoteAsyncTask(PreferinteDao preferinteDao){
            this.preferinteDao = preferinteDao;
        }

        @Override
        protected Void doInBackground(Preferinte... preferintes) {
            preferinteDao.update(preferintes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Preferinte,Void,Void>{
        private PreferinteDao preferinteDao;

        private DeleteNoteAsyncTask(PreferinteDao preferinteDao){
            this.preferinteDao = preferinteDao;
        }

        @Override
        protected Void doInBackground(Preferinte... preferintes) {
            preferinteDao.delete(preferintes[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Preferinte,Void,Void>{
        private PreferinteDao preferinteDao;

        private deleteAllAsyncTask(PreferinteDao preferinteDao){
            this.preferinteDao = preferinteDao;
        }

        @Override
        protected Void doInBackground(Preferinte... preferintes) {
            preferinteDao.deleteAllPreferences();
            return null;
        }
    }
}
