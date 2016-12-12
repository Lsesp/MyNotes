package lsesp.mynotes;

import lsesp.mynotes.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.View;



/**
 * Created by Luis on 12/9/2016.
 */

public class DatePickerFragment extends DialogFragment {

    private Activity mActivity;
    private DatePickerDialog.OnDateSetListener mListener;

    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);
        mActivity = activity;

        try {
            mListener = (DatePickerDialog.OnDateSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTimeSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity,mListener,year,
                (month+1),day);

        return  datePickerDialog;

    }

    /*@Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day){
        //View date =
        TextView date = (TextView) getActivity().findViewById(R.id.pickDate);

        //date.setText(month+"-"+day+"-"+year);


    }*/


}
