package com.mialab.healthbutler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.model.Text;
import com.bumptech.glide.Glide;
import com.mialab.healthbutler.R;
import com.mialab.healthbutler.domain.Branch;
import com.mialab.healthbutler.domain.Doctor;
import com.mialab.healthbutler.domain.TaskList.Task;
import com.mialab.healthbutler.utils.ListItemClickHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp on 2016/2/20.
 */
public class DoctorListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Doctor> doctors;
    public ListItemClickHelp callback;
    private LayoutInflater mInflater;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors,
                                      ListItemClickHelp callback) {
        this.context = context;
        this.doctors = doctors;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return doctors.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.list_doctor_item, null);
            holder = new ViewHolder();

            holder.tv_name = (TextView) convertView
                    .findViewById(R.id.tv_name);

            holder.tv_hospital = (TextView) convertView
                    .findViewById(R.id.tv_hospital);

            holder.tv_illness = (TextView) convertView
                    .findViewById(R.id.tv_illness);

            holder.tv_intro = (TextView) convertView
                    .findViewById(R.id.tv_intro);


            holder.iv_head = (ImageView) convertView
                    .findViewById(R.id.iv_head);

            holder.rl_detail= (RelativeLayout) convertView
                    .findViewById(R.id.rl_detail);

            holder.rl_personal_web= (RelativeLayout) convertView
                    .findViewById(R.id.rl_personal_web);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(doctors.get(position).getDoctor_name());
        holder.tv_hospital.setText(doctors.get(position).getHospital_name());
        holder.tv_illness.setText(doctors.get(position).getIllness_name());
        holder.tv_intro.setText(doctors.get(position).getDoctor_intro());

//?????头像与url如何对应
        Glide.with(context)
                .load(doctors.get(position).getHead_image())
                .placeholder(R.drawable.doctor)
                .error(R.drawable.doctor)
                .into(holder.iv_head);


        final View view = convertView;
        final int p = position;
        final int rl_detail = holder.rl_detail.getId();
        holder.rl_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(view, parent, p, rl_detail);
            }
        });
        final int rl_personal_web = holder.rl_personal_web.getId();
        holder.rl_personal_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(view, parent, p, rl_personal_web);
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView  tv_name;
        TextView  tv_hospital;
        TextView tv_illness;
        TextView tv_intro;
        ImageView  iv_head;
        RelativeLayout rl_detail;
        RelativeLayout rl_personal_web;

    }

}
