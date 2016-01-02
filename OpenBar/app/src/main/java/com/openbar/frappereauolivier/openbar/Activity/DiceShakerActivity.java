package com.openbar.frappereauolivier.openbar.Activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.openbar.frappereauolivier.openbar.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

import Evenement.OnActionBarMenuSelected;

public class DiceShakerActivity extends AppCompatActivity implements SensorEventListener, TextToSpeech.OnInitListener {
    Toolbar myToolbar;
    ImageView dice1;
    ImageView dice2;
    SensorManager mySensorManager;
    Sensor myAccelerometer;
    static int SHAKE_THRESHOLD = 8;
    static String FACE_ONE = "face_one";
    static String FACE_TWO = "face_two";
    static String FACE_THREE = "face_three";
    static String FACE_FOUR = "face_four";
    static String FACE_FIVE = "face_five";
    static String FACE_SIX = "face_six";
    static String FACE_DICE[] = {FACE_ONE,FACE_TWO,FACE_THREE,FACE_FOUR,FACE_FIVE,FACE_SIX};
    TextToSpeech myTts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_shaker);
        setSpecifyPresentation();
    }

    private void setSpecifyPresentation() {
        setToolBarView();
        setTheDice();
        setSensor();
    }

    private void setSensor() {
        this.mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        this.myAccelerometer = this.mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.myTts = new TextToSpeech(this,this);
    }

    private void setTheDice() {
        this.dice1 = (ImageView) findViewById(R.id.dice1);
        this.dice2 = (ImageView) findViewById(R.id.dice2);
    }

    private void setToolBarView() {
        this.myToolbar = (Toolbar) findViewById(R.id.my_toolbar_profil);
        setSupportActionBar(this.myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Shake dice");
        ab.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        this.mySensorManager.registerListener(this, this.myAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        //put your code here...
        this.mySensorManager.unregisterListener(this);
    }

    @Override
    public void onInit(int status) {
        //put your code here...
        if(status != TextToSpeech.ERROR) {
            this.myTts.setLanguage(getResources().getConfiguration().locale);
        }
    }

    @Override
    protected  void onDestroy() {
        //put your code here...
        if(this.myTts != null) {
            this.myTts.stop();
            this.myTts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dice_shaker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

       // return super.onOptionsItemSelected(item);
        OnActionBarMenuSelected tmpMnger = new OnActionBarMenuSelected(this,item);
        return tmpMnger.manageActionUsers();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float acceleration = (float) Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH ;

        if(acceleration > SHAKE_THRESHOLD) {
            generateRandomNumber(this.dice1);
            generateRandomNumber(this.dice2);
            Toast.makeText(this.getApplicationContext(),"Shaked",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Rien pour le moment
    }

    private void generateRandomNumber(ImageView display) {
        Random randomGenerator = new Random();
        int number = randomGenerator.nextInt(6)+1;
        updateDice(number,display);
        /*En mode débug, trop de bruit, à décommenter*/
       /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.myTts.speak("Shaked !",TextToSpeech.QUEUE_FLUSH,null,null);
        } else {
            this.myTts.speak("Shaked !",TextToSpeech.QUEUE_FLUSH,null);
        } */
    }

    private void updateDice(int nb, ImageView display) {

        int drawableID = getApplicationContext().getResources().getIdentifier(FACE_DICE[nb-1],"drawable",getPackageName());
        Context context = display.getContext();
        Picasso.with(context)
                .load(drawableID)
                .into(display);
    }
}
