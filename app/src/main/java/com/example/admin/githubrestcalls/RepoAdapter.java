package com.example.admin.githubrestcalls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.githubrestcalls.Repos.Repo;

import java.util.ArrayList;

/**
 * Created by Admin on 8/16/2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    ArrayList<Repo> repoList = new ArrayList<>();
    Context context;

    public RepoAdapter(ArrayList<Repo> repoList){
        this.repoList = repoList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(repoList.get(position).getName());
        holder.tvLanguage.setText(repoList.get(position).getLanguage().toString());
        holder.tvDescription.setText(repoList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if(repoList != null){
            return repoList.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription, tvName, tvLanguage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvLanguage = (TextView) itemView.findViewById(R.id.tvLanguage);
        }
    }
}
