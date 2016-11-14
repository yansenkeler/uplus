package com.ejb.uplus.component.pickers;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.Feature;
import com.bigkoo.pickerview.OptionsPickerView;
import com.ejb.uplus.util.ResUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by John on 10/27/2016.
 */

public class CityPicker implements OptionsPickerView.OnOptionsSelectListener
{
    private static CityPicker cityPicker;
    private OptionsPickerView<String> pickerView;
    private OnCitySelectListener listener;
    private Context context;
    private ArrayList<String> data1 = new ArrayList<>();
    private ArrayList<ArrayList<String>> data2 = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> data3 = new ArrayList<>();

    public CityPicker(Context context)
    {
        this.context = context;
        init();
    }

    public static CityPicker getInstance(Context context)
    {
        if (cityPicker==null)
        {
            cityPicker = new CityPicker(context);
        }
        return cityPicker;
    }

    private void init()
    {
        pickerView = new OptionsPickerView<String>(context);
        String parseResult = ResUtil.parseJsonFile(context, "areas.json");
        parseJsonToList(parseResult);
        pickerView.setPicker(data1, data2, data3, true);
        pickerView.setCyclic(false, false, false);
        pickerView.setOnoptionsSelectListener(this);
    }

    /**
     * 把json字符串解析成ArrayList
     * @param str
     */
    private void parseJsonToList(String str)
    {
        LinkedHashMap map1 = JSON.parseObject(str, LinkedHashMap.class, Feature.OrderedField);
        Set entrySets1 = map1.entrySet();
        for (Object entrySet1 : entrySets1)
        {
            Map.Entry entry1 = (Map.Entry) entrySet1;
            data1.add((String) entry1.getKey());
            Map map2 = (Map) entry1.getValue();
            Set entrySets2 = map2.entrySet();
            ArrayList<String> data2Item = new ArrayList<>();
            ArrayList<ArrayList<String>> data3Item = new ArrayList<>();
            for (Object entrySet2 : entrySets2)
            {
                Map.Entry entry2 = (Map.Entry) entrySet2;
                data2Item.add((String) entry2.getKey());
                JSONArray jsonArray2 = (JSONArray) entry2.getValue();
                ArrayList<String> data3ItemItem = new ArrayList<>();
                for (int i = 0; i < jsonArray2.size(); i++)
                {
                    data3ItemItem.add(jsonArray2.getString(i));
                }
                data3Item.add(data3ItemItem);
            }
            data2.add(data2Item);
            data3.add(data3Item);
        }
    }

    public void show(int i1, int i2, int i3)
    {
        pickerView.setSelectOptions(i1, i2, i3);
        pickerView.show();
    }

    public void setListener(OnCitySelectListener listener)
    {
        this.listener = listener;
    }

    public boolean isShow(){
        return pickerView.isShowing();
    }

    public void dismiss(){
        pickerView.dismiss();
    }

    public void destroy(){
        cityPicker = null;
        pickerView = null;
        data1.clear();
        data2.clear();
        data3.clear();
    }

    @Override
    public void onOptionsSelect(int option1, int option2, int option3)
    {
        listener.onSelect(data1.get(option1), data2.get(option1).get(option2), data3.get(option1).get(option2).get(option3), option1, option2, option3);
    }

    public interface OnCitySelectListener{
        void onSelect(String t1, String t2, String t3, int i1, int i2, int i3);
    }
}
