package com.mywork.gitreposapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mywork.gitreposapp.R;
import com.mywork.gitreposapp.entity.Repos;

import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder> {

    class RepoViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName,textViewFullName,textViewDescription,textViewUrl;

        private RepoViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewFullName = itemView.findViewById(R.id.textViewFullName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewUrl = itemView.findViewById(R.id.textViewUrl);

        }
    }

    private final LayoutInflater mInflater;
    private List<Repos> mRepos; // Cached copy of repos

    public RepoListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.repos_recyclear_item, parent, false);
        return new RepoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        if (mRepos != null) {
            Repos current = mRepos.get(position);
            holder.textViewName.setText(current.getName());
            holder.textViewFullName.setText(current.getFullName());
            holder.textViewDescription.setText(current.getDescription());
            holder.textViewUrl.setText(current.getUrl());

        } else {
            // Covers the case of data not being ready yet.
            holder.textViewName.setText("no data");
        }
    }

    public void setRepos(List<Repos> repos){
        mRepos = repos;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRepos != null)
            return mRepos.size();
        else return 0;
    }

}
