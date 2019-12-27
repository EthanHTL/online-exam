package com.exam.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.demain.Paper;
import com.exam.demain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParperUtil {

    public ArrayList<Question> allQuestions(Paper paper,String type){

        JSONObject object1 = paper.getContent().getJSONObject("content");
        ArrayList<Question> questions = new ArrayList<>();
        //System.out.println(object1);

        // 解析json
        JSONArray object2 = object1.getJSONArray(type);
        //System.out.println(object2);
        for (int i = 0; i < object2.size(); i++) {
            Question q =new Question();
            JSONObject object = object2.getJSONObject(i);
            // 考题id
            q.setTitle(object.getString("title"));

            //  解析答案"answer": {
            //                    "a": "a",
            //                    "b": "b",
            //                }
            JSONObject an = object.getJSONObject("answer");
            ArrayList<String> answer = new ArrayList<>();
            Iterator iter1 = an.entrySet().iterator();
            while (iter1.hasNext()) {
                Map.Entry entry = (Map.Entry) iter1.next();
                answer.add(entry.getKey().toString());
            }
            q.setAnswer(answer);

            // 解析选项 "choose": {
            //                    "a": "a1",
            //                    "a1": "a1",
            //                    "a2": "a1",
            //                    "a3": "a1"
            //                }
            JSONObject ch = object.getJSONObject("choose");
            Map<String,String> choose =new HashMap<>();
            Iterator iter2 = ch.entrySet().iterator();
            while (iter2.hasNext()) {
                Map.Entry entry = (Map.Entry) iter2.next();
                choose.put(entry.getKey().toString(),entry.getValue().toString());
                //System.out.println(entry.getKey().toString());
                //System.out.println(entry.getValue().toString());
            }
            q.setChoosees(choose);

            questions.add(q);
        }
        System.out.println(questions.toString());

        return questions;

    }


}