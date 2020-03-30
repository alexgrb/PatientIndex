package ch.hevs.alexpira.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.ui.admin.SettingsActivity;
import ch.hevs.alexpira.ui.admin.StatisticsActivity;
import ch.hevs.alexpira.ui.bed.AddEditBedActivity;
import ch.hevs.alexpira.ui.bed.SearchBedActivity;
import ch.hevs.alexpira.ui.patient.AddEditPatientActivity;
import ch.hevs.alexpira.ui.patient.SearchPatientActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //method permetting to do an action depending on which item of the menu we have selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent=new Intent(this,SettingsActivity.class);
                startActivity(intent);
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //button settings
    public void btnSettings_onClick(View view) {
        Intent intent=new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    //bouton StatisticsActivity
    public void btnStatistics_onClick(View view) {
        Intent intent=new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    //bouton search bed
    public void btnSearchBed_onClick(View view) {
        Intent intent=new Intent(this, SearchBedActivity.class);
        startActivity(intent);
    }

    //bouton search patient
    public void btnSearchPatient_onClick(View view) {
        Intent intent=new Intent(this, SearchPatientActivity.class);
        startActivity(intent);
    }
    //btnAddPatient_onClick
    public void btnAddpatient_onClick(View view) {
        Intent intent=new Intent(this, AddEditPatientActivity.class);
        startActivity(intent);
    }

    //bouton display bed
    public void btnAddBed_onClick(View view) {
        Intent intent=new Intent(this, AddEditBedActivity.class);
        startActivity(intent);
    }



}
