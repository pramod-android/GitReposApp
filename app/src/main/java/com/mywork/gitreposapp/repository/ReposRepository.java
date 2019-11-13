package com.mywork.gitreposapp.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.mywork.gitreposapp.activity.MainActivity;
import com.mywork.gitreposapp.api.GitWebServices;
import com.mywork.gitreposapp.constant.Constants;
import com.mywork.gitreposapp.dao.RepoDao;
import com.mywork.gitreposapp.database.ReposRoomDatabase;
import com.mywork.gitreposapp.entity.Repos;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mywork.gitreposapp.constant.Constants.BASE_URL;

public class ReposRepository {

    private RepoDao mRepoDao;
    private LiveData<List<Repos>> mAllRepos;
    private GitWebServices webservice;

    public ReposRepository(Application application) {
        ReposRoomDatabase db = ReposRoomDatabase.getDatabase(application);
        mRepoDao = db.repoDao();
        mAllRepos = mRepoDao.getAllRepos();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Repos>> getAllRepos() {
        refreshData();
        return mAllRepos;
    }

    private void refreshData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        GitWebServices webServices = retrofit.create(GitWebServices.class);

        Call<List<Repos>> call = webServices.getRepositories();
        call.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {

                List<Repos> reposList = response.body();

                insertRepos(reposList);
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void insertRepos(List<Repos> repos) {
        new insertReposAsyncTask(mRepoDao).execute(repos);
    }


    private static class insertReposAsyncTask extends AsyncTask<List<Repos>, Void, Void> {

        private RepoDao mAsyncTaskDao;

        insertReposAsyncTask(RepoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<Repos>... params) {
            mAsyncTaskDao.insertRepos(params[0]);
            return null;
        }
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
