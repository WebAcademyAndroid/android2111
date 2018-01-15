package com.example.student.android10;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BlankFragment1 extends Fragment {

    private static final String EXTRA_STUDENTS = "com.example.student.android10.extra.STUDENTS";

    private ArrayList<Student> mStudents;
    private ListView mListView;

    public static BlankFragment1 newInstance(ArrayList<Student> students) {
        BlankFragment1 fragment = new BlankFragment1();

        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_STUDENTS, students);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStudents = getArguments().getParcelableArrayList(EXTRA_STUDENTS);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ActionListener) {
            mListener = (ActionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_1, container, false);

        mListView = view.findViewById(R.id.listView);

        initList();

        return view;
    }

    private void initList() {
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mStudents);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    Student student = mStudents.get(position);
                    mListener.click(student);
                }
            }
        });
    }

    private ActionListener mListener;

    public interface ActionListener {
        void click(Student student);
    }
}
