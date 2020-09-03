package com.example.nasa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.appwidget.AppWidgetHost;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends YouTubeBaseActivity {

    Button btn;
    Calendar current;
    int month,date,year;
    TextView tv;
    TextView dets;
    ImageView img;
    TextView head;
    int i;
    ScrollView s;
    String foryoutube;
    Button abt;
    YouTubePlayerView video;
    WebView web;
    int pos1,pos2;
    SearchView searchView;
    Button play;
    YouTubePlayer.OnInitializedListener moninitialisedlistener;
    int x;
    ProgressDialog pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       s=findViewById(R.id.scroll);
        btn=findViewById(R.id.button3);
       tv=findViewById(R.id.textView2);
       head=findViewById(R.id.head);
       pg=new ProgressDialog(this);
       pg.setTitle("PLEASE WAIT");
       pg.setMessage("Wait till we fetch data");

       abt=findViewById(R.id.button5);
      // searchView=findViewById(R.id.search);
       web=findViewById(R.id.web);
       video=findViewById(R.id.videoview);
       play=findViewById(R.id.button4);
       img=findViewById(R.id.img);
       dets=findViewById(R.id.textView3);
       current=Calendar.getInstance();
       date=current.get(Calendar.DAY_OF_MONTH);
       month=current.get(Calendar.MONTH);
       year=current.get(Calendar.YEAR);
       month=month+1;

       abt.setVisibility(View.INVISIBLE);

       s.setVisibility(View.INVISIBLE);
       dets.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        web.setVisibility(View.INVISIBLE);
        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,abt.class);
                intent.putExtra("info",dets.getText().toString());
                startActivity(intent);
            }
        });

       tv.setText(String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(date));

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               final Calendar calendar=Calendar.getInstance();

               DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                       calendar.set(Calendar.YEAR,year);
                       calendar.set(Calendar.MONTH,month);
                       calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                       tv.setText(String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));

                       abt.setVisibility(View.VISIBLE);
                       if(current.get(Calendar.YEAR)<calendar.get(Calendar.YEAR) ||
                             (  current.get(Calendar.YEAR)==calendar.get(Calendar.YEAR)
                             && current.get(Calendar.MONTH)<calendar.get(Calendar.MONTH)
                             )||
                               (current.get(Calendar.YEAR)==calendar.get(Calendar.YEAR)
                                       && current.get(Calendar.MONTH)==calendar.get(Calendar.MONTH)
                                       && current.get(Calendar.DAY_OF_MONTH)<calendar.get(Calendar.DAY_OF_MONTH)
                                               ))

                       {
                           Toast.makeText(Main2Activity.this,"INVALID DATE",3000).show();
                       }
                       else {pg.show();
                       change();}


                       moninitialisedlistener=new YouTubePlayer.OnInitializedListener() {
                           @Override
                           public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                               youTubePlayer.loadVideo(foryoutube);
                           }

                           @Override
                           public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                           }
                       };


                       play.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               video.initialize(Youtubeconfig.getApikey(),moninitialisedlistener);
                           }
                       });



                   }
               };

               new DatePickerDialog(Main2Activity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


           }
       });


       /*moninitialisedlistener=new YouTubePlayer.OnInitializedListener() {
           @Override
           public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

               youTubePlayer.loadVideo("W4hTJybfU7s");
           }

           @Override
           public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

           }
       };


       play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               video.initialize(Youtubeconfig.getApikey(),moninitialisedlistener);
           }
       });*/

    }

    public void change()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String fordate;
        fordate=tv.getText().toString();

        placeholderapi placeholderapi=retrofit.create(com.example.nasa.placeholderapi.class);
        Call<List<Photo>> call=placeholderapi.getphotos("DEMO_KEY",fordate);
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                img.setImageResource(R.drawable.ic_autorenew_black_24dp);
                if(!response.isSuccessful())
                {
                    pg.dismiss();dets.setText("code"+response.code());
                }

                List<Photo> photos=response.body();
                x=0;

                dets.setText(" ");

                for(Photo photo:photos){

                    if(x==1) break;
                    String c=" ";
                    c=c+photo.getDate()+"\n"+photo.getExplanation()+"\n";

                    head.setText(photo.getTitle());
                    dets.append(c);

                    if(photo.getMedia_type().equals("image"))
                    {  img.setVisibility(View.VISIBLE);
                        web.setVisibility(View.INVISIBLE);
                        Picasso.with(Main2Activity.this).load(photo.getUrl()).into(img);
                        pg.dismiss();
                    }
                    else {
                        String id=photo.getUrl();

                        img.setVisibility(View.INVISIBLE);
                        web.setVisibility(View.VISIBLE);
                       /* pos1=id.indexOf('=');
                        pos2=id.indexOf('&');
                       if(!id.contains("&")) pos2=id.length();

                      //  Toast.makeText(Main2Activity.this,String.valueOf(pos1)+" "+String.valueOf(pos2),5000).show();
                       if(!id.contains("=")|| !id.contains("&")) {pos1=id.lastIndexOf('/');
                       pos2=id.indexOf('?');
                       if(!id.contains("&")) pos2=id.length();
                       }

                     //  Toast.makeText(Main2Activity.this,String.valueOf(pos1)+" "+String.valueOf(pos2),5000).show();
                       foryoutube=id.substring(pos1+1,pos2).trim();
                        //foryoutube="l36UkYtq6m0";

                       //  foryoutube="2WRgXvdasm0";
                        // foryoutube="W4hTJybfU7s";*/

                        web.getSettings().setJavaScriptEnabled(true);
                        web.getSettings().setPluginState(WebSettings.PluginState.ON);
                        web.loadUrl(id);
                        web.setWebChromeClient(new WebChromeClient());

                        pg.dismiss();

                    }


                    x=1;
                }

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                dets.setText(t.getMessage());
                pg.dismiss();
            }
        });
    }
}
