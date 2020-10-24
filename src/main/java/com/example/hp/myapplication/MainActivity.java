package com.example.hp.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
  //  private static CustomAdapter adapter;
    private Socket socket;
    private static final int SERVERPORT = 5000;
    private static final String SERVER_IP = "127.0.0.1";
    private static final String ipadres = "192.168.183.1";
    private static final int portnumber = 60000;
    private static final String host = "localhost";
    final String adress = "192.168.1.3";
    final int Port = 60000;
    String fileName = "output4.csv";
    List<String[]> rows = new ArrayList<>();
    //  public static final String url ="http://localhost:8000";
    // public static final String url = "http://10.0.2.2:9000";
     public static final String url = "http://192.168.100.10:9000";
    //  public static final String url = "http://192.168.43.79:9000";
    //  public static final String url = "http://192.168.43.1:9000";
    Button button;
    Button button3;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.option2);
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.NO2);
        button2 = (Button) findViewById(R.id.CO);
        button3 = (Button) findViewById(R.id.C6H6);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {



                Intent i = new Intent(getApplicationContext(),information1.class);
                i.putExtra("url", url);
                startActivity(i);


            }
        });
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {



                Intent i = new Intent(getApplicationContext(), information2.class);
                i.putExtra("url", url);
                startActivity(i);


            }
        });

        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), information3.class);
                i.putExtra("url", url);
                startActivity(i);


            }
        });
    }



}
