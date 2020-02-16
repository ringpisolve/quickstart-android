package com.google.firebase.example.fireeats.java;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.example.fireeats.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class StudentInfoFormActivityFragment extends Fragment {

    public StudentInfoFormActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_info_form, container, false);
    }
}
