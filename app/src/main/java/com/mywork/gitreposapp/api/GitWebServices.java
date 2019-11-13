package com.mywork.gitreposapp.api;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.mywork.gitreposapp.entity.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitWebServices {
    @GET("/users/pramod-android/repos")
    Call<List<Repos>> getRepositories();


}
