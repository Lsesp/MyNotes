package lsesp.mynotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luis on 12/9/2016.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder>{

    private List<Task> taskList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView description, dueDate, time;
        public CheckBox cb;

        public MyViewHolder(View view){
            super(view);
            description = (TextView) view.findViewById(R.id.description);
            dueDate = (TextView) view.findViewById(R.id.dueDate);
            time = (TextView) view.findViewById(R.id.time);
            cb = (CheckBox) view.findViewById(R.id.box);

        }

    }

    public TaskAdapter(List<Task> taskList){
        this.taskList = taskList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.recycled, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Task task = taskList.get(position);
        holder.description.setText(task.getDescription());
        holder.dueDate.setText(task.getDueDate());
        holder.time.setText(task.getTime());

    }

    @Override
    public int getItemCount(){
        return taskList.size();
    }



}
