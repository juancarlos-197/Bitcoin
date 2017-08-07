package com.example.juanc.bitcoin.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.juanc.bitcoin.R;
import com.example.juanc.bitcoin.databinding.TemplatePersonItemBinding;
import com.example.juanc.bitcoin.models.City;
import com.example.juanc.bitcoin.models.Person;
import com.example.juanc.bitcoin.utils.L;

import java.util.List;

/**
 * Created by RicardoAndrés on 07/08/2017.
 */

public class PersonAdapter extends BaseAdapter {

    Context context;
    List<Person> personList;

    public PersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int i) {
        return personList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return personList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TemplatePersonItemBinding binding;
        if(view == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.template_person_item,
                    viewGroup, false);
            view = binding.getRoot();
        }else {
            binding = (TemplatePersonItemBinding) view.getTag();
        }
        binding.setPerson(personList.get(i));
        for (City city : L.cityStaticList){
            if(personList.get(i).getCity_id() == city.getId()){
                binding.personItemCity.setText(city.getName());
            }
        }
        view.setTag(binding);

        return view;
    }
}
