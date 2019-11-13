package com.mywork.gitreposapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mywork.gitreposapp.dao.RepoDao;
import com.mywork.gitreposapp.database.ReposRoomDatabase;
import com.mywork.gitreposapp.entity.Repos;

import java.util.List;

public class ReposRepository {

    private RepoDao mRepoDao;
    private LiveData<List<Repos>> mAllRepos;


    public ReposRepository(Application application) {
        ReposRoomDatabase db = ReposRoomDatabase.getDatabase(application);
        mRepoDao = db.repoDao();
        mAllRepos = mRepoDao.getAllRepos();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Repos>> getAllRepos() {
        return mAllRepos;
    }

    public void insert(Repos repos) {
        new insertAsyncTask(mRepoDao).execute(repos);
    }

    private static class insertAsyncTask extends AsyncTask<Repos, Void, Void> {

        private RepoDao mAsyncTaskDao;

        insertAsyncTask(RepoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Repos... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
