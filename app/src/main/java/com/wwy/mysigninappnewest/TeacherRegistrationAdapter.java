package com.wwy.mysigninappnewest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// 实现recyclerview适配器
// 继承RecyclerView.Adapter
// 泛型为内部类ViewHolder
public class TeacherRegistrationAdapter extends RecyclerView.Adapter<TeacherRegistrationAdapter.ViewHolder> {
    List<TeacherRegistrationItem> mRegistrationList;
    // viewholder记录签到记录组成item的各个视图组件
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView registrationName;
        TextView registrationNum;
        TextView registrationCreator;
        // 参数view为签到记录的一个item
        public ViewHolder(View view) {
            super(view);
            // item由三个textview组成
            // 获取item的textview实例
            registrationName = (TextView) view.findViewById(R.id.registration_name);
            registrationCreator = (TextView) view.findViewById(R.id.registration_creator);
            registrationNum = (TextView) view.findViewById(R.id.registration_num);
        }
    }
    // 获取组成签到记录需要的数据源
    public TeacherRegistrationAdapter(List<TeacherRegistrationItem> registrationList) {
        mRegistrationList = registrationList;
    }

    @NonNull
    @Override
    // 创建viewholer
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_fragment_record_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    // 将viewholder与数据源每一项数据绑定
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
