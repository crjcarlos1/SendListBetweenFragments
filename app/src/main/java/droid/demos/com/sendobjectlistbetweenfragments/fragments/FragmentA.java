package droid.demos.com.sendobjectlistbetweenfragments.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import droid.demos.com.sendobjectlistbetweenfragments.R;
import droid.demos.com.sendobjectlistbetweenfragments.models.Student;

public class FragmentA extends Fragment implements View.OnClickListener {

    public static final String TAG = FragmentA.class.getSimpleName();

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private Button btnFragmentA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        btnFragmentA = (Button) view.findViewById(R.id.btnFragmentA);
        btnFragmentA.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFragmentA:
                sendStudentsListToFragmentB();
                break;
        }
    }

    private void sendStudentsListToFragmentB() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();

        List<Student> studentsList = initStudentsList();

        Gson gson = new Gson();
        Type type = new TypeToken<List<Student>>() {}.getType();
        String s = gson.toJson(studentsList, type);

        Bundle bundle = new Bundle();
        bundle.putString("STUDENTS-LIST", s);

        FragmentB fragmentB = new FragmentB();
        fragmentB.setArguments(bundle);

        transaction.addToBackStack(FragmentB.TAG);
        transaction.replace(R.id.conteinerFragments, fragmentB, FragmentB.TAG).commit();
    }

    private List<Student> initStudentsList() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Student("name " + i, "lastName " + i, 20 + i, i * 100));
        }
        return list;
    }

}
