package com.example.shaaditest.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shaaditest.R;
import com.example.shaaditest.db.DatabaseClient;
import com.example.shaaditest.db.Persond;
import com.example.shaaditest.model.Person;
import com.example.shaaditest.network.User;
import com.example.shaaditest.network.response.PersonResponse;
import com.example.shaaditest.ui.Shadicard;
import com.example.shaaditest.utils.Alerts;
import com.example.shaaditest.utils.CoreConstants;
import com.example.shaaditest.utils.Logger;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;


import java.util.ArrayList;
import java.util.List;

import static com.example.shaaditest.utils.CoreConstants.perosnDBList;

public class MainActivity extends AppCompatActivity {
PersonResponse response;
    private SwipePlaceHolderView mSwipeView;
    ArrayList<Person> personalList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init(){
        personalList = new ArrayList<>();

        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.shadi_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.shadi_swipe_out_msg_view));

        if (perosnDBList.size()>0){
            for (int i = 0; i < perosnDBList.size(); i++) {
                Persond pd = perosnDBList.get(i);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .personDao()
                        .insert(pd);
            }
        getResultsDB();
        }else {
            new GetPersonsAPICall().execute();
        }

        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });


    }



    protected class GetPersonsAPICall extends AsyncTask<String, Void, String> {
        ProgressDialog pd;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Loading...");
            pd.show();


        }

        @Override
        protected String doInBackground(String... urls) {
            // Do your request
                    getResults();

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd != null)
            {
                pd.dismiss();
            }

            if(response != null){
                    // materialsList = consumedMaterilaResponse.getMaterialList();
                    Logger.e("GET PERSONS DATA " + response);
                personalList = response.getPersonList();

                for(Person profile : personalList) {
                    mSwipeView.addView(new Shadicard(mContext, profile, mSwipeView));
                    Persond pd = new Persond();
                    pd.setLogin_username(profile.getLogin_username());
                    pd.setGender(profile.getGender());
                    pd.setTitle_name(profile.getTitle_name());
                    pd.setFirst_nmae(profile.getFirst_nmae());
                    pd.setLast_name(profile.getLast_name());
                    pd.setCity(profile.getCity());
                    pd.setState(profile.getState());
                    pd.setCountry(profile.getCountry());
                    pd.setEmail(profile.getEmail());
                    pd.setAge(profile.getAge());
                    pd.setPhone(profile.getPhone());
                    pd.setPic_large(profile.getPic_large());

                    //adding to database

                    perosnDBList.add(pd);
                }


            }else{
                Alerts.ShowMessageAlert(MainActivity.this, "", "Some Issue from Server End. Please try again later");
            }

        }
    }
    void getResults(){
        User user = new User();
        response = user.getPersons();
    }

    //get results from db

    private void getResultsDB() {
        class GetResults extends AsyncTask<Void, Void, List<Persond>> {

            @Override
            protected List<Persond> doInBackground(Void... voids) {
                List<Persond> personDList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .personDao()
                        .getAll();
                return personDList;
            }

            @Override
            protected void onPostExecute(List<Persond> tasks) {
                super.onPostExecute(tasks);
                for (int i = 0; i < tasks.size(); i++) {
                    Logger.e("GET PERSONS DATAbase " + tasks.get(i).getFirst_nmae());
                }
            }
        }

        GetResults gt = new GetResults();
        gt.execute();
    }
}