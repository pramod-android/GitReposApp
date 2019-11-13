package com.mywork.gitreposapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;
@Entity(tableName = "repos_table")
public class Repos {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    @NonNull
    private int id;

    @ColumnInfo(name = "node_id")
    @SerializedName("node_id")
    @Expose
    private String nodeId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    @Expose
    private String fullName;

    @ColumnInfo(name = "private")
    @SerializedName("private")
    @Expose
    private boolean _private;

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    private String url;

    @Ignore
    public Repos() {
    }

    public Repos(int id, String nodeId, String name, String fullName, boolean _private, String htmlUrl, String description, String url) {
        this.id = id;
        this.nodeId = nodeId;
        this.name = name;
        this.fullName = fullName;
        this._private = _private;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.url = url;
    }
//setters


    public void setId(int id) {
        this.id = id;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void set_private(boolean _private) {
        this._private = _private;
    }


    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //getters

    public int getId() {
        return id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean is_private() {
        return _private;
    }



    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
