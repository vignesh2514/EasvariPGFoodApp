package com.v7ench.eswaripg;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vignesh2514 on 23/1/17.
 */


public class MovieA extends ArrayAdapter {


    private List<Members_list> movieModelList;
    private int resource;
    private Context context;
    private LayoutInflater inflater;
    MovieA(Context context, int resource, List<Members_list> objects) {
        super(context, resource, objects);
        movieModelList = objects;
        this.context =context;
        this.resource = resource;
        inflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getViewTypeCount() {

        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder  ;
        if(convertView == null){
            convertView = inflater.inflate(resource,null);
            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.roomno=(TextView) convertView.findViewById(R.id.roomno);
            holder.monum=(TextView) convertView.findViewById(R.id.monum);


            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Members_list members_list= movieModelList.get(position);
        holder.name.setText(members_list.getName());
        holder.roomno.setText(members_list.getRoomno());
        holder.monum.setText(members_list.getMonum());
//            Picasso.with(context).load(Members_list.getImages()).fit().error(R.drawable.load).fit().into(holder.ima);
        return convertView;

    }

    private class ViewHolder{

        private TextView name,roomno,monum,amt;


    }

}