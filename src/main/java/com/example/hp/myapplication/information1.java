package com.example.hp.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2/14/2019.
 */

public class information1 extends Activity {
    TextView v;

   // ArrayList<DataModel> dataModels;
    ListView listView;
    List<DataModel2> dataModels2;
    ArrayList<DataModel2> dataModels3;
    ArrayList<DataModel2> dataModels4;
    TextView txtString;
    TextView tv;
    String[] a;
    //public String url = "http://127.0.0.1:8000";
    //  public String url = "http://127.0.0.1:8080";
    // public String url= "https://reqres.in/api/users/2";
    String fileName="output4.csv";
    List<String[]> rows = new ArrayList<>();
    public static final String url = "http://10.0.2.2:9000";
    // public static final String url = "http://127.0.0.1:9000";
    DbHelper2 mydb;
    List airPollution_List;
    private static CustomAdapter2 adapter;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        listView = (ListView) findViewById(R.id.list);
        //     dataModels=new ArrayList<DataModel>();
        dataModels2= new ArrayList<DataModel2>();
        //   dataModels3= new ArrayList<DataModel2>();
        //  dataModels4= new ArrayList<DataModel2>();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                str= null;
            } else {
                str= extras.getString("url");
            }
        } else {
            str= (String) savedInstanceState.getSerializable("url");
        }



        Button button = (Button) findViewById(R.id.server);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                HTTPAsyncTask a=new HTTPAsyncTask();
                a.execute(new String[]{str});
            }
        });
        mydb = new DbHelper2(this);
        for(DataModel2 d:dataModels2 )
            mydb.addDataModel(d);
        Button  button1 = (Button) findViewById(R.id.DB);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                dataModels3= (ArrayList<DataModel2>) mydb.getAll();
                adapter= new CustomAdapter2(dataModels3,getApplicationContext());
                //  dataModels4.add(new DataModel2("1","1","1"));
                // adapter= new CustomAdapter2(dataModels4,getApplicationContext());
                listView.setAdapter(adapter);
            }
        });


    }

    private String HttpGet(String myUrl) throws IOException {
        InputStream inputStream = null;
        String result = "";

        URL url = new URL(myUrl);

        // create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // make GET request to the given URL
        conn.connect();

        // receive response as inputStream
        inputStream = conn.getInputStream();
/////////////////
        BufferedReader reader=null;
        String csv_item;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((csv_item = reader.readLine()) != null) {
                //   String csv_item;

                String[] row = csv_item.split(",");
                //Date time pt co2 No2
                String date= row[0];
                String time= row[1];
                String No2= row[2];
                String co= row[3];
                String PT= row[4];
//No2,co2,pt,date
                dataModels2.add(new DataModel2(date,time,No2));
                mydb.addDataModel(new DataModel2(date,time,No2));
                //dataModels3.add(new DataModel2(date,time,No2));
                //mydb3.add(new DataModel(date,time,No2,co,PT));
                //  DataModelCollection.studentCollectionArr.add(new DataModelCollection(date,time,PT,co,No2));
                //  }
            } }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if(reader!=null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }


        //////////// //// //
        // convert inputstream to string
        if(inputStream != null)
            result = convertInputStreamToString(inputStream);
        else
            result = "Did not work!";

        return result;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return HttpGet(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //    List<DataModel> dataModels2=mydb3.getAllDataModel();
            //adapter= new CustomAdapter(dataModels2,getApplicationContext());
            //   listView.setAdapter(adapter);
            //     dataModels3= (ArrayList<DataModel2>) mydb.getAll();
            //adapter= new CustomAdapter2(dataModels3,getApplicationContext());
            /////
            adapter= new CustomAdapter2(dataModels2,getApplicationContext());
            listView.setAdapter(adapter);
            ////////////
//            Toast.makeText(information1.this,result,Toast.LENGTH_LONG).show();
            //for(DataModel2 d:dataModels2 )
            //    mydb.addDataModel(d);


            //for(DataModel2 d:dataModels2 )
            //  mydb.addDataModel(d);
            //tv.setText(result);
        }
    }
}
