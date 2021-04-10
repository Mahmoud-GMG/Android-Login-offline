package yt.mahmoudgaming.login;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.graphics.drawable.GradientDrawable;
import android.graphics.Color;
import android.widget.TextView;
import java.security.acl.Group;
import android.view.Gravity;

public class LoginForm {

    public static native boolean Check(String str, String str2);
	 public static native String Login();
	public static native String Login2();

    public static void Start(final Context context) {
		
		
		System.loadLibrary("MahmoudAlaa");
        //Create shared preferences to remember user and pass
        final SharedPreferences sharedPreferences = context.getSharedPreferences("SavePref", 0);
        String string = sharedPreferences.getString("User", null);
        String string2 = sharedPreferences.getString("Pass", null);

        //Create LinearLayout
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		GradientDrawable gradientdrawable = new GradientDrawable();
		gradientdrawable.setCornerRadius(20); //Set corner
		gradientdrawable.setColor(Color.parseColor("#1C2A35")); //Set background color
		gradientdrawable.setStroke(2, Color.parseColor("#32cb00")); //Set 
		linearLayout.setBackground(gradientdrawable);

        //Create username edittext field
		TextView txt = new TextView(context);
		txt.setGravity(Gravity.CENTER);
		txt.setText(Login());
		txt.setTextColor(Color.WHITE);
		txt.setBackgroundColor(Color.TRANSPARENT);
		txt.setTextSize(20);
		
		TextView txt1 = new TextView(context);
		txt1.setGravity(Gravity.CENTER);
		txt1.setText(Login2());
		txt1.setTextColor(Color.GREEN);
		txt1.setBackgroundColor(Color.TRANSPARENT);
		txt1.setTextSize(10);
		
        EditText editTextUser = new EditText(context);
        if (string != null && !string.isEmpty()) {
            editTextUser.setText(string);
        }
        editTextUser.setHint("Username...");

        //Create password edittext field
        EditText editTextPass = new EditText(context);
        if (string2 != null && !string2.isEmpty()) {
            editTextPass.setText(string2);
        }
        editTextPass.setHint("Password...");
        editTextPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //Create checkbox
        CheckBox checkBox = new CheckBox(context);
        checkBox.setChecked(true);
        checkBox.setText("Remember me");

        //Create button
        Button button = new Button(context);
        button.setText("Login");

        //Create button
        Button button2 = new Button(context);
        button2.setText("Cancel");

        linearLayout.addView(txt);
		linearLayout.addView(txt1);
		linearLayout.addView(editTextUser);
        linearLayout.addView(editTextPass);
        linearLayout.addView(checkBox);
        linearLayout.addView(button);
        linearLayout.addView(button2);

        //Create alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setTitle("Login");
        builder.setCancelable(false);
        builder.setView(linearLayout);
        AlertDialog show = builder.show();

        final EditText editText3 = editTextUser;
        final EditText editText4 = editTextPass;
        final CheckBox checkBox2 = checkBox;
        final AlertDialog alertDialog = show;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String user = editText3.getText().toString().trim();
                String pass = editText4.getText().toString().trim();
                    /*if (user.isEmpty() || pass.isEmpty()) {
                        alertDialog.dismiss();
                    }*/
                boolean isChecked = checkBox2.isChecked();
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (isChecked) {
                    edit.putString("User", user);
                    edit.putString("Pass", pass);
                    edit.apply();
                } else {
                    edit.clear();
                    edit.apply();
                }
                //Check if user and pass match in native lib
                if (Check(user, pass)) {
                    alertDialog.dismiss();
                    //Here you can do something after login
                } else {
                    Toast.makeText(context, "Username or password is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Process.killProcess(Process.myPid());
            }
        });
    }
}
