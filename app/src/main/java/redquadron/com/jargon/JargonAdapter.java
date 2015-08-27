package redquadron.com.jargon;

import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import bolts.Task;

public class JargonAdapter extends ArrayAdapter<Jargon> {
    private Context mContext;
    private List<Jargon> mTasks;

    public JargonAdapter(Context context, List<Jargon> objects) {
        super(context, R.layout.jargon_row, objects);
        this.mContext = context;
        this.mTasks = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.jargon_row, null);
        }

        Jargon task = mTasks.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.jargon_title);
        descriptionView.setText(task.getText());

        TextView textView = (TextView) convertView.findViewById(R.id.jargon_subtitle);
        textView.setText(task.getDescription().substring(0, 40) + "...");

        ImageView iconView = (ImageView) convertView.findViewById(R.id.jargon_icon);

        String stack = task.getStack();

        if(stack.equalsIgnoreCase("collaboration")){
            iconView.setImageResource(R.drawable.collaboration);
        }else if(stack.equalsIgnoreCase("connect")){
            iconView.setImageResource(R.drawable.connect);
        }else if(stack.equalsIgnoreCase("consume")){
            iconView.setImageResource(R.drawable.consume);
        }else if(stack.equalsIgnoreCase("control")){
            iconView.setImageResource(R.drawable.control);
        }else if(stack.equalsIgnoreCase("create")){
            iconView.setImageResource(R.drawable.create);
        }

        if(task.isCompleted()){
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }

}