package droid.demos.com.sendobjectlistbetweenfragments.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import droid.demos.com.sendobjectlistbetweenfragments.R;
import droid.demos.com.sendobjectlistbetweenfragments.models.Student;

public class FragmentB extends Fragment {

    public static final String TAG = FragmentB.class.getSimpleName();
    private List<Student> studentsList = null;

    private TextView txvResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String string = bundle.getString("STUDENTS-LIST");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Student>>() {}.getType();
            studentsList = gson.fromJson(string, type);

            for (int i = 0; i < studentsList.size(); i++) {
                Log.e("STUDENT", studentsList.get(i).getName() + " " + studentsList.get(i).getLastName() + " " + studentsList.get(i).getAge());
            }

        } else {
            Toast.makeText(getContext(), "Ocurrio un error al obtener la lista de estudiantes", Toast.LENGTH_LONG).show();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        txvResult = (TextView) view.findViewById(R.id.txvResult);

        if (studentsList != null) {
            txvResult.setText("tamaño de la lista: " + studentsList.size() + "\n" +
                    "Studiante en la posición 10 de la lista: \n" +
                    "Nombre: " + studentsList.get(10).getName() + "\n" +
                    "Apellido: " + studentsList.get(10).getLastName() + "\n" +
                    "Edad: " + studentsList.get(10).getAge() + "\n" +
                    "Matrícula: " + studentsList.get(10).getId());
        }

        return view;
    }
}
