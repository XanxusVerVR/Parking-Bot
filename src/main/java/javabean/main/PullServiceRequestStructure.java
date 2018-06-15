package javabean.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javabean.sub.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PullServiceRequestStructure {

    @SerializedName("Query")
    @Expose
    private Query query;

    @SerializedName("UserData")
    @Expose
    private UserData userData;

    @SerializedName("Result")
    @Expose
    private Result result;

    public PullServiceRequestStructure() {
    }

    public PullServiceRequestStructure(Query query, UserData userData, Result result) {
        super();
        this.query = query;
        this.userData = userData;
        this.result = result;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("query", query).append("userData", userData).append("result", result).toString();
    }

}
