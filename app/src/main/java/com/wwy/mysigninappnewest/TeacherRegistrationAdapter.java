package com.wwy.mysigninappnewest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherRegistrationAdapter extends RecyclerView.Adapter<TeacherRegistrationAdapter.ViewHolder> {
    List<TeacherRegistrationItem> mRegistrationList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView registrationName;
        TextView registrationNum;
        TextView registrationCreator;
        public ViewHolder(View view) {
            super(view);
            registrationName = (TextView) view.findViewById(R.id.registration_name);
            registrationCreator = (TextView) view.findViewById(R.id.registration_creator);
            registrationNum = (TextView) view.findViewById(R.id.registration_num);
        }
    }
    public TeacherRegistrationAdapter(List<TeacherRegistrationItem> registrationList) {
        mRegistrationList = registrationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_fragment_record_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeacherRegistrationItem registrationItem = mRegistrationList.get(position);
        holder.registrationName.setText(registrationItem.getRegistrationName());
        holder.registrationNum.setText(registrationItem.getRegistrationNum());
        holder.registrationCreator.setText(registrationItem.getRegistrationCreator());
    }

    @Override
    public int getItemCount() {
        return mRegistrationList.size();
    }
}
