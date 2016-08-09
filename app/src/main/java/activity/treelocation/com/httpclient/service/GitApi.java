package activity.treelocation.com.httpclient.service;



import activity.treelocation.com.httpclient.entities.GitModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by admin on 2016/8/9.
 */
public interface GitApi {

    @GET("/users/{user}")      // here is the other url part.best way is to start using /
    Observable<GitModel> getFeed(@Path("user") String user);
    // string user is for passing values from edittext for eg: user=basil2style,google
    // response is the response from the server which is now in the POJO
}