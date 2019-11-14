package com.mywork.gitreposapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mywork.gitreposapp.R;
import com.mywork.gitreposapp.adapter.RepoListAdapter;
import com.mywork.gitreposapp.entity.Repos;
import com.mywork.gitreposapp.viewmodel.ReposViewModel;

import java.util.List;


public class ReposListFragment extends Fragment implements RepoListAdapter.ItemClickListener {
    private ReposViewModel mReposViewModel;

    private OnFragmentRepoListListener mListener;
    private Context mContext;

    public ReposListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ReposListFragment newInstance(String param1, String param2) {
        ReposListFragment fragment = new ReposListFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_repos_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final RepoListAdapter adapter = new RepoListAdapter(mContext);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mReposViewModel = ViewModelProviders.of(this).get(ReposViewModel.class);

        mReposViewModel.getAllRepos().observe(this, new Observer<List<Repos>>() {
            @Override
            public void onChanged(@Nullable final List<Repos> repos) {
                // Update the cached copy of the words in the adapter.
                adapter.setRepos(repos);
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        if (context instanceof OnFragmentRepoListListener) {
            mListener = (OnFragmentRepoListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(View view, Repos repos) {
        if (mListener != null) {
            mListener.OnRepoClick(repos);
        }
    }


    public interface OnFragmentRepoListListener {
        // TODO: Update argument type and name
        void OnRepoClick(Repos repos);
    }
}
