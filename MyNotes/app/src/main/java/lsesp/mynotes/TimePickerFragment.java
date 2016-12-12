package lsesp.mynotes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by Luis on 12/9/2016.
 */

public class TimePickerFragment extends DialogFragment {

    private Activity mActivity;
    private TimePickerDialog.OnTimeSetListener mListener;
    String time;

    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);
        mActivity = activity;

        try {
            mListener = (TimePickerDialog.OnTimeSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTimeSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        time = "";

        TimePickerDialog timePickerDialog = new TimePickerDialog(mActivity,mListener,hour,minute,false);

        return  timePickerDialog;

    }

    /*@Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute){

        //EditText editTime = (EditText)getActivity().findViewById(R.id.pickTime);
        //pickTime.setText
        pickTime =(hour+":"+minute);

    }*/

}
