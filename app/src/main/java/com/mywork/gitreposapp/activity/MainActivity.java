package com.mywork.gitreposapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mywork.gitreposapp.R;
import com.mywork.gitreposapp.adapter.RepoListAdapter;
import com.mywork.gitreposapp.entity.Repos;
import com.mywork.gitreposapp.viewmodel.ReposViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ReposViewModel mReposViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RepoListAdapter adapter = new RepoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mReposViewModel = ViewModelProviders.of(this).get(ReposViewModel.class);


//         for(int i=0;i<10;i++){
//             Repos repos=new Repos(i,"nodeid","name","fullname",true,"htmlurl","desc","url");
//
//             mReposViewModel.insert(repos);
//        }



        mReposViewModel.getAllRepos().observe(this, new Observer<List<Repos>>() {
            @Override
            public void onChanged(@Nullable final List<Repos> repos) {
                // Update the cached copy of the words in the adapter.
                adapter.setRepos(repos);
            }
        });
    }
}
