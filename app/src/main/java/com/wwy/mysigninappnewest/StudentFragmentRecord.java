package com.wwy.mysigninappnewest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentFragmentRecord extends Fragment {
    private Activity context;
    // 测试数据
    protected List<StudentRegistrationItem> getTestData() {
        List<StudentRegistrationItem> mRegistrationList;
        mRegistrationList = new ArrayList<StudentRegistrationItem>();
        StudentRegistrationItem item = new StudentRegistrationItem();
        item.setRegistrationUser("王总");
        item.setRegistrationName("董事会签到");
        item.setRegistrationStatus("已签到");
        mRegistrationList.add(item);
        return mRegistrationList;
    }
    @Nullable
    @Override
    // 生成签到记录tab页视图
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.student_fragment_record, container, false);
    }

    @Override
    // 当活动创建，渲染签到列表，与listview类似
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        // 测试签到列表
        RecyclerView recyclerView = (RecyclerView) context.findViewById(R.id.registration_list_student);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        StudentRegistrationAdapter adapter = new StudentRegistrationAdapter(getTestData());
        recyclerView.setAdapter(adapter);
    }
}
