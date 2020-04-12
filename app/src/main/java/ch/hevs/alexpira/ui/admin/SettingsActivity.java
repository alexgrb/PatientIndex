package ch.hevs.alexpira.ui.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import java.util.Locale;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.ui.bed.AddEditBedActivity;
import ch.hevs.alexpira.ui.bed.ListBedActivity;
import ch.hevs.alexpira.ui.home.HomeFragment;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        changeLanguage();
        setContentView(R.layout.settings_activity);
        Button button = findViewById(R.id.darktheme_btn);
//test cloud
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, SecondSettingsActivity.class);
                SettingsActivity.this.startActivity(intent);
            }
        });


//test cloud feature
    }

    public void changeLanguage() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = sharedPrefs.getString("pref_lang", "en-US");
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Resources resources = getBaseContext().getResources();
        Configuration config = resources.getConfiguration();
        config.locale = myLocale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

}


