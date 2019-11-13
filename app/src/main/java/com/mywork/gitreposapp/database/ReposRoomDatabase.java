package com.mywork.gitreposapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mywork.gitreposapp.dao.RepoDao;
import com.mywork.gitreposapp.entity.Repos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Repos.class}, version = 1, exportSchema = false)
public abstract class ReposRoomDatabase extends RoomDatabase {

    public abstract RepoDao repoDao();

    private static volatile ReposRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ReposRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReposRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReposRoomDatabase.class, "repos_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RepoDao mDao;

        PopulateDbAsync(ReposRoomDatabase db) {
            mDao = db.repoDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Repos repos = new Repos();
            mDao.insert(repos);
            repos = new Repos();
            mDao.insert(repos);
            return null;
        }
    }


}
