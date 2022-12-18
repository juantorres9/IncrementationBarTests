package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {


    int progress_value = 0;
    ProgressBar progress_bar;
    TextView progress_tv;
    Button progres_button_right;
    Button progres_button_left;
    Button button_go;
    Button button_go_dialog;
    Button button_permission1;
    public static int MAX_PROGRESS_BAR = 200;
    public static String TAG_MAIN = "MAIN_ACTIVITY JMJCH";

    AlertDialog alert;
    Activity currentAct;
    public static final int REQUEST_CODE=747;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentAct=this;

        setContentView(R.layout.activity_main);
        progress_bar = findViewById(R.id.progress_bar);
        progress_tv = findViewById(R.id.progress_tv);
        progres_button_left = findViewById(R.id.progress_button_left);
        progres_button_left.setText("BUTTON LEFT");
        progres_button_right = findViewById(R.id.progress_button_right);
        progres_button_right.setText("RIGHT");
        button_go = findViewById(R.id.progress_button_go);
        progress_bar.setMax(MAX_PROGRESS_BAR);

        button_go_dialog = findViewById(R.id.button_go_dialog);
        button_permission1 = findViewById(R.id.button_permission1);

        SensorClass sensor = new SensorClass(new OnLeftRightSensorListener() {
            @Override
            public void onLeftSensorDetected() {
                if (progress_value <= progress_bar.getMax()) {
                    progress_value += 1;
                    progress_bar.setProgress(progress_value);
                    progress_bar.invalidate();
                }
                try {
                    Thread.sleep(40);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }

            @Override
            public void onRightSensorDetected() {
                progress_value = 0;
                progress_bar.setProgress(progress_value);
                progress_bar.invalidate();
            }

            @Override
            public void onNothingDetected() {
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        progres_button_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG_MAIN, "pressed left right button");
                boolean result = false;
                if (progress_value <= 100) {
                    progress_value += 5;
                    progress_bar.setProgress(progress_value);
                    result = true;

                }
                Log.d(TAG_MAIN, "pressed left right button progress value=" + progress_value + "result=" + result);
                return result;
            }
        });

        progres_button_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean result = false;
                Log.d(TAG_MAIN, "pressed long right button");
                if (progress_value > 0) {
                    progress_value = 0;
                    progress_bar.setProgress(progress_value);

                }
                Log.d(TAG_MAIN, "pressed left right button progress value=" + progress_value + "result=" + result);

                return result;
            }
        });

        progres_button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main3ButtonPressed.class);
                startActivity(i);
            }
        });

        progres_button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main5Activity.class);
                startActivity(i);
            }
        });

        button_go_dialog.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity6.class);
                startActivity(i);
                return true;
            }
        });

        button_permission1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                /**CHECK ONLY IF THE PERMISSION IS ALREADY GRANTTED AND DO NOTHING**/
                askExplicitPermission();
                return true;
            }
        });
        button_permission1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**CHECK IF THE PERMISSION IS ALREADY GRANTTED AND ASK ONE TIME IF NOT GRANTTED**/
              //  askExplicitPermission2();
                /**CHECK IF THE PERMISSION IS ALREADY GRANTTED AND ASK X TIMES TIME IF NOT GRANTTED UNTIL USER DISABLES OR ACCEPT BOX**/
                 askExplicitPermission3();

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //CHECK RESPONSE
        switch (requestCode){
            case REQUEST_CODE:
                //VERIFY SI USER GRANTTED
                if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    String text_callback1 = "PERMISSION IS EXPLICITELY GRANTTED BY USER FOR SEND_SMS=" + Manifest.permission.SEND_SMS;
                    Toast.makeText(getApplicationContext(), text_callback1, Toast.LENGTH_LONG).show();
                    Log.d(TAG_MAIN, text_callback1);

                }else {
                    String text_callback1 = "PERMISSION IS NOT EXPLICITELY GRANT BY USER APP WILL NOT WORK AT 100% =" + Manifest.permission.SEND_SMS;
                    Toast.makeText(getApplicationContext(), text_callback1, Toast.LENGTH_LONG).show();
                    Log.d(TAG_MAIN, text_callback1);
                }
                return;
                default:return;
        }
    }

    public void askExplicitPermission() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            String text0 = "SECOND TIME,PERMISSION IS ALREADY GRANTTED SEND_SMS=" + Manifest.permission.SEND_SMS;
            Toast.makeText(getApplicationContext(), text0, Toast.LENGTH_LONG).show();
            Log.d(TAG_MAIN, text0);
        } else {
            String text1 = "PERMISSION IS NOT GRANTTED YET FOR SEND_SMS=" + Manifest.permission.SEND_SMS;
            Log.d(TAG_MAIN, text1);
            Toast.makeText(getApplicationContext(), text1, Toast.LENGTH_LONG).show();
        }
    }

    public void askExplicitPermission2() {
        final Activity act=currentAct;
        String []needed_permissions = new String[]{
                Manifest.permission.SEND_SMS
        };

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            String text0 = "SECOND TIME,PERMISSION IS ALREADY GRANTTED SEND_SMS=" + Manifest.permission.SEND_SMS;
            Toast.makeText(getApplicationContext(), text0, Toast.LENGTH_LONG).show();
            Log.d(TAG_MAIN, text0);
        } else if(shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){
            String text1 = "SECOND TIME PERMISSION IS NOT GRANTTED FOR SEND_SMS WE SHOW EXPLANATION=" + Manifest.permission.SEND_SMS;
            Log.d(TAG_MAIN, text1);
            Toast.makeText(getApplicationContext(), text1, Toast.LENGTH_LONG).show();
            showCustoAlert(act,"WE NEED SMS PERMISSION ONLY TO SEND VALID SECURE CODE TO YOUR WIFE","NO THANKS");
        }else {
            String text1 = "FIRST TIME PERMISSION IS ASKED FOR SEND_SMS DO YOU AUTHORIZE USE ??? " + Manifest.permission.SEND_SMS;
            Log.d(TAG_MAIN, text1);
            Toast.makeText(getApplicationContext(), text1, Toast.LENGTH_LONG).show();
            //Explicitely request permission
           requestPermissions(needed_permissions,REQUEST_CODE);
        }
    }
    public void askExplicitPermission3() {
        final Activity act=currentAct;

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            String text0 = "SECOND TIME,PERMISSION IS ALREADY GRANTTED SEND_SMS=" + Manifest.permission.SEND_SMS;
            Toast.makeText(getApplicationContext(), text0, Toast.LENGTH_LONG).show();
            Log.d(TAG_MAIN, text0);
        } else if(shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){
            String text1 = "SECOND TIME PERMISSION IS NOT GRANTTED FOR SEND_SMS WE SHOW EXPLANATION=" + Manifest.permission.SEND_SMS;
            Log.d(TAG_MAIN, text1);
            Toast.makeText(getApplicationContext(), text1, Toast.LENGTH_LONG).show();
            showCustoAlert2(act,"WE NEED SMS PERMISSION ONLY TO SEND VALID SECURE CODE TO YOUR WIFE","NO THANKS","YES REQUEST NOW");
        }else {
            String text1 = "FIRST TIME PERMISSION IS ASKED FOR SEND_SMS DO YOU AUTHORIZE USE ??? " + Manifest.permission.SEND_SMS;
            Log.d(TAG_MAIN, text1);
            Toast.makeText(getApplicationContext(), text1, Toast.LENGTH_LONG).show();
            //Explicitely request permission
            showPermissionRequest();
        }
    }

    public  void showCustoAlert(final Activity act,String title,String button_single_text){

        AlertDialog.Builder result= new AlertDialog.Builder(act);
        result.setTitle(title);
        result.setIcon(R.drawable.ic_danger);
        result.setNegativeButton(button_single_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(act.getApplicationContext(),"YOU CHOOSE NEGATIVE",Toast.LENGTH_LONG).show();
                // dialog.cancel();
            }
        });
        alert=result.create();
        alert.show();



    }
    public  void showCustoAlert2(final Activity act,String title,String button_no_text,String button_yes_text){

        AlertDialog.Builder result= new AlertDialog.Builder(act);

        result.setTitle(title);
        result.setIcon(R.drawable.ic_warning);
        result.setNegativeButton(button_no_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(act.getApplicationContext(),"YOU CHOOSE NEGATIVE",Toast.LENGTH_LONG).show();
                // dialog.cancel();
            }
        });
        result.setPositiveButton(button_yes_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(act.getApplicationContext(),"YOU CHOOSE YES _ASK REQUEST",Toast.LENGTH_LONG).show();
                showPermissionRequest();
            }
        });
        alert=result.create();
        alert.show();



    }

    public void showPermissionRequest(){
        String []needed_permissions = new String[]{
                Manifest.permission.SEND_SMS
        };
        requestPermissions(needed_permissions,REQUEST_CODE);

    }
}
