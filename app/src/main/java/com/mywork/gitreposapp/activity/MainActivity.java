package com.mywork.gitreposapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.mywork.gitreposapp.R;
import com.mywork.gitreposapp.adapter.RepoListAdapter;
import com.mywork.gitreposapp.entity.Repos;
import com.mywork.gitreposapp.fragment.ReposListFragment;
import com.mywork.gitreposapp.viewmodel.ReposViewModel;

import java.util.List;

import static com.mywork.gitreposapp.constant.Constants.BASE_URL;

public class MainActivity extends AppCompatActivity implements ReposListFragment.OnFragmentRepoListListener {
    private ReposViewModel mReposViewModel;
    int count = 0;
    ReposListFragment reposListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mReposViewModel = ViewModelProviders.of(this).get(ReposViewModel.class);


//         for(int i=0;i<10;i++){
//             Repos repos=new Repos(i,"nodeid","name","fullname",true,"htmlurl","desc","url");
//
//             mReposViewModel.insert(repos);
//        }
        mReposViewModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                count = integer;

                if (count > 0) {
                    if (reposListFragment == null) {
                        reposListFragment = new ReposListFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout_container, reposListFragment);
                        // Commit the transaction
                        transaction.commit();
                    }
                }
            }
        });

    }


    @Override
    public void OnRepoClick(Repos repos) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(repos.getHtmlUrl()));
        startActivity(i);
    }
}
