package com.wwy.mysigninappnewest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentRegistrationAdapter extends RecyclerView.Adapter<StudentRegistrationAdapter.ViewHolder> {
    List<StudentRegistrationItem> mRegistrationList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView registrationName;
        TextView registrationUser;
        TextView registrationStatus;
        public ViewHolder(View view) {
            super(view);
            registrationName = (TextView) view.findViewById(R.id.registration_name_student);
            registrationUser = (TextView) view.findViewById(R.id.registration_user_student);
            registrationStatus = (TextView) view.findViewById(R.id.registration_status_student);
        }
    }
    public StudentRegistrationAdapter(List<StudentRegistrationItem> registrationList) {
        mRegistrationList  = registrationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_fragment_record_item, parent, false);
        StudentRegistrationAdapter.ViewHolder holder = new StudentRegistrationAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentRegistrationItem registrationItem = mRegistrationList.get(position);
        holder.registrationName.setText(registrationItem.getRegistrationName());
        holder.registrationUser.setText(registrationItem.getRegistrationUser());
        holder.registrationStatus.setText(registrationItem.getRegistrationStatus());
    }

    @Override
    public int getItemCount() {
        return mRegistrationList.size();
    }
}
