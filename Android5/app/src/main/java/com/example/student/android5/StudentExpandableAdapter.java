package com.example.student.android5;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentExpandableAdapter extends BaseExpandableListAdapter {

    private int mGroupResourceId;
    private int mChildResourceId;
    private ArrayList<Group> mGroups;
    private LayoutInflater mInflater;

    public StudentExpandableAdapter(Context context, int groupResourceId, int childResourceId, ArrayList<Group> groups) {
        mGroupResourceId = groupResourceId;
        mChildResourceId = childResourceId;
        mGroups = groups;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Group group = mGroups.get(groupPosition);
        return group.Students != null ? group.Students.length : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Group group = mGroups.get(groupPosition);
        return group.Students != null ? group.Students[childPosition] : null;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mGroupResourceId, null);

        Group group = (Group) getGroup(groupPosition);
        ((TextView) convertView.findViewById(R.id.textViewGroupName)).setText(group.Name);

        View indicator = convertView.findViewById(R.id.indicator);
        if (getChildrenCount(groupPosition) == 0) {
            indicator.setBackgroundColor(Color.GRAY);
        } else if (isExpanded) {
            indicator.setBackgroundColor(Color.RED);
        } else {
            indicator.setBackgroundColor(Color.GREEN);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mChildResourceId, null);

        Student student = (Student) getChild(groupPosition, childPosition);

        ((TextView) convertView.findViewById(R.id.textViewFirstName)).setText(student.FirstName);
        ((TextView) convertView.findViewById(R.id.textViewLastName)).setText(student.LastName);
        ((TextView) convertView.findViewById(R.id.textViewAge)).setText(String.valueOf(student.Age));

        return convertView;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
