package com.mywork.gitreposapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mywork.gitreposapp.entity.Repos;
import com.mywork.gitreposapp.repository.ReposRepository;

import java.util.List;

public class ReposViewModel extends AndroidViewModel {
    private ReposRepository mRepository;

    private LiveData<List<Repos>> mAllRepos;

    public ReposViewModel (Application application) {
        super(application);
        mRepository = new ReposRepository(application);
        mAllRepos = mRepository.getAllRepos();
    }
    public LiveData<Integer> getCount() { return mRepository.getCount(); }
    public LiveData<List<Repos>> getAllRepos() { return mAllRepos; }

    public void insert(Repos repos) {
        mRepository.insert(repos);
    }
}
