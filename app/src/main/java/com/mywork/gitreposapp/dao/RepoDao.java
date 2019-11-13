package com.mywork.gitreposapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mywork.gitreposapp.entity.Repos;

import java.util.List;

@Dao
public interface  RepoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Repos repos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRepos(List<Repos> repos);

    @Query("DELETE FROM repos_table")
    void deleteAll();

    @Query("SELECT * from repos_table ORDER BY name ASC")
    LiveData<List<Repos>> getAllRepos();
}
