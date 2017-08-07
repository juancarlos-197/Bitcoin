package com.example.juanc.bitcoin.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juanc.bitcoin.R;
import com.example.juanc.bitcoin.adapters.PersonAdapter;
import com.example.juanc.bitcoin.databinding.ActivityPersonBinding;
import com.example.juanc.bitcoin.models.Person;
import com.example.juanc.bitcoin.utils.L;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

    public static String PERSON_TYPE = "PersonType";
    public static int PERSON_TYPE_SALE = 1;
    public static int PERSON_TYPE_BUY = 2;

    ActivityPersonBinding binding;
    int personType;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_person);

        Bundle bundle = getIntent().getExtras();
        personType = bundle.getInt(PERSON_TYPE);

        if(personType == PERSON_TYPE_SALE){
            setTitle("Personas que venden Bitcoin");
            loaData(PERSON_TYPE_SALE);
        }else{
            setTitle("Personas que compran Bitcoin");
            loaData(PERSON_TYPE_BUY);
        }
    }

    private void loaData(int type){
        List<Person> persons = new ArrayList<>();
        for (Person person : L.personStaticList){
            if(type == person.getState()){
                persons.add(person);
            }
        }

        adapter = new PersonAdapter(PersonActivity.this, persons);
        binding.personList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
