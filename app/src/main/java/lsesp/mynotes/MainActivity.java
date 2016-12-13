package lsesp.mynotes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private List<Task> tasksList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private AlertDialog.Builder ad;
    public EditText task, pickDate, pickTime;
    public String chosenDate = "", chosenTime="";

    public Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this.getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        taskAdapter = new TaskAdapter(tasksList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(taskAdapter);

       // recyclerView.addOnItemTouchListener(new RecyclerItemClickListener);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        prepareTaskData();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                newTask();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void newTask(){

        GregorianCalendar calendar = new GregorianCalendar();
        int m = 1+calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        int y = calendar.get(Calendar.YEAR);
        int hh = calendar.get(Calendar.HOUR);
        int mm = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);
        int AM_PM = calendar.get(Calendar.AM_PM);
        final String Date = m+"-"+d+"-"+y;
        final String Time = "@"+hh+":"+mm+" "+AM_PM;

        ad = new AlertDialog.Builder(MainActivity.this);

        ad.setTitle("New Task");

        View dialog = getLayoutInflater().inflate(R.layout.newtask,null);
        ad.setView(dialog);
        final EditText task = (EditText)dialog.findViewById(R.id.task);
        //name = task.getText().toString();
        pickDate = (EditText)dialog.findViewById(R.id.pickDate);
        pickTime = (EditText)dialog.findViewById(R.id.timePick);


        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dialogFragment = new DatePickerFragment();

                dialogFragment.show(getFragmentManager(),"Date Picker");

            }
        });

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timeDialog = new TimePickerFragment();

                timeDialog.show(getFragmentManager(),"Time Picker");
            }
        });


        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String taskName = task.getText().toString();
                        Task newTask = new Task(taskName, chosenDate, chosenTime);
                        tasksList.add(newTask);
                        taskAdapter.notifyDataSetChanged();
                    }
                })
                .create()
                .show();

        // sendMsgToMain(nt);//send the textview to the main activity

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void prepareTaskData(){
        Task task = new Task("homework","Dec 23","@5:00pm");
        tasksList.add(task);

        task = new Task("test", "Jan 6", "@6:00pm");
        tasksList.add(task);

        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

        String status = "am";
        int hourFormat;

        //FORMAT HOUR
        if(hour>11){
            status = "pm";
            hourFormat = hour-12;
        }

        else if(hour==0){
            hourFormat=12;
        }
        else {
            hourFormat=hour;
        }

        if(minute<=9){
            pickTime.setText(hourFormat+":0"+minute+status);

            chosenTime = ("@"+hourFormat+":0"+minute+status);
        }

        else {

            pickTime.setText(hourFormat + ":" + minute+" "+status);

            chosenTime = ("@" + hourFormat + ":" + minute+status);
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month+=1;
        String monthName;

        switch (month){
            case 1: monthName ="Jan";
                break;
            case 2: monthName ="Feb";
                break;
            case 3: monthName = "March";
                break;
            case 4: monthName = "April";
                break;
            case 5: monthName = "May";
                break;
            case 6: monthName = "June";
                break;
            case 7: monthName = "July";
                break;
            case 8: monthName = "Aug";
                break;
            case 9: monthName = "Sept";
                break;
            case 10: monthName = "Oct";
                break;
            case 11: monthName = "Nov";
                break;
            case 12: monthName = "Dec";
                break;

            default: monthName = Integer.toString(month);
                break;

        }
        pickDate.setText(month+"-"+day+"-"+year);
        chosenDate = (monthName+" "+day);
    }
}
