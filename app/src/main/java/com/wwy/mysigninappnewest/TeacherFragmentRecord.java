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

public class TeacherFragmentRecord extends Fragment {
    Activity context;
    // 测试数据
    protected List<TeacherRegistrationItem> getTestData() {
        List<TeacherRegistrationItem> mRegistrationList;
        mRegistrationList = new ArrayList<TeacherRegistrationItem>();
        TeacherRegistrationItem item = new TeacherRegistrationItem();
        item.setRegistrationCreator("王总");
        item.setRegistrationName("董事会签到");
        item.setRegistrationNum("已签到10人");
        mRegistrationList.add(item);
        return mRegistrationList;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.teacher_fragment_record, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        // 测试签到列表
        RecyclerView recyclerView = (RecyclerView) context.findViewById(R.id.registration_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        TeacherRegistrationAdapter adapter = new TeacherRegistrationAdapter(getTestData());
        recyclerView.setAdapter(adapter);
    }
}
