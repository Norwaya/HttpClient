package activity.treelocation.com.httpclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import activity.treelocation.com.httpclient.entities.GitModel;
import activity.treelocation.com.httpclient.entities.Version;
import activity.treelocation.com.httpclient.service.GitApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button click;
    TextView tv;
    EditText edit_user;
    ProgressBar pbar;
    String API = "https://api.github.com";  // BASE URL
//    String API = "http://msgm.applinzi.com/";  // BASE URL
    String TAG = getClass().getSimpleName().toString();


    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.tv);
        edit_user = (EditText) findViewById(R.id.username);
        pbar = (ProgressBar) findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE);
//        final Gson customGsonInstance = new GsonBuilder()
//                .registerTypeAdapter(GitModel.class,
//                        new MarvelResultsDeserializer<GitModel>())
//                .create();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edit_user.getText().toString();
                pbar.setVisibility(View.VISIBLE);
//                Log.d(TAG, "onClick: " + user);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                GitApi gitApi = retrofit.create(GitApi.class);
                Log.d(TAG, "onClick:1 "+new Date().getTime());

                Observable<GitModel> observable = gitApi.getFeed(user);
//                Observable<Version> observable = gitApi.getString();

//                call.enqueue(new Callback<GitModel>() {
//                    @Override
//                    public void onResponse(Call<GitModel> call, Response<GitModel> response) {
//                        Log.d(TAG, "onClick: " +response.body().getName());
//                    }
//
//                    @Override
//                    public void onFailure(Call<GitModel> call, Throwable t) {
//                        Log.d(TAG, "onFailure: "+t.getMessage().toString());
//                    }
//                });
                Log.d(TAG, "onClick:2 "+new Date().getTime());

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<GitModel>() {
                            @Override
                            public void call(GitModel gitModel) {
                                tv.setText("Github Name :" + gitModel.getName() +
                                "\nWebsite :"+gitModel.getBlog() +
                                "\nCompany Name :"+gitModel.getCompany());
                                Log.d(TAG, "call: "+gitModel.getLogin());
                                Log.d(TAG, "call: "+gitModel.toString());
                                pbar.setVisibility(View.INVISIBLE);
                                Log.d(TAG, "onClick:3 "+new Date().getTime());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.d(TAG, "call: "+throwable.getMessage().toString());
                            }
                        });
                // Retrofit section start from here...
                // create an adapter for retrofit with base url
//                RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();
//                // creating a service for adapter with our GET class
//                GitApi git = restAdapter.create(GitApi.class);
//                // Now ,we need to call for response
//                // Retrofit using gson for JSON-POJO conversion
//
//                git.getFeed(user,new Callback<GitModel>() {
//                    @Override
//                    public void success(GitModel gitmodel, Response response) {
//                        // we get json object from github server to our POJO or model class
//                        tv.setText("Github Name :" + gitmodel.getName() +
//                                "\nWebsite :"+gitmodel.getBlog() +
//                                "\nCompany Name :"+gitmodel.getCompany());
//
//                        pbar.setVisibility(View.INVISIBLE); // disable progressbar
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        tv.setText(error.getMessage());
//                        pbar.setVisibility(View.INVISIBLE); // disable progressbar
//                    }
//                });
            }
        });
    }

//    class MarvelResultsDeserializer<T> implements JsonDeserializer<GitModel> {
//        @Override
//        public GitModel deserialize(JsonElement je, Type typeOfT,
//                                   JsonDeserializationContext context) throws JsonParseException {
//            // 转换Json的数据, 获取内部有用的信息
//            //JsonElement results = je.getAsJsonObject();
//            return new Gson().fromJson(je, typeOfT);
//        }
//    }
}
