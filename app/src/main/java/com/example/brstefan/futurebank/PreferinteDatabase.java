package com.example.brstefan.futurebank;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = Preferinte.class, version  = 1)
public abstract class PreferinteDatabase extends RoomDatabase {

    private static PreferinteDatabase instance;

    public abstract PreferinteDao preferinteDao();

    public static  synchronized PreferinteDatabase getInstance(Context ctx){
        if(instance==null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(),
                    PreferinteDatabase.class,"preferinte_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private PreferinteDao preferinteDao;

        private PopulateDbAsyncTask(PreferinteDatabase db) {
            preferinteDao = db.preferinteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /*preferinteDao.insert(new Preferinte("Sport","Fotbal"));
            preferinteDao.insert(new Preferinte("Sport","Handbal"));
            preferinteDao.insert(new Preferinte("Jocuri","CoD"));
            preferinteDao.insert(new Preferinte("Calatorie","Spania"));*/
            return null;
        }
    }
}
