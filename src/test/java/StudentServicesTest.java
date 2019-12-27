import com.alibaba.fastjson.JSONArray;import com.alibaba.fastjson.JSONObject;import com.alibaba.fastjson.parser.Feature;import com.exam.demain.Paper;import com.exam.demain.Question;import com.exam.demain.Student;import com.exam.services.LoginServices;import com.exam.services.ParperServices;import com.exam.util.ParperUtil;import org.junit.Test;import org.springframework.context.ApplicationContext;import org.springframework.context.support.ClassPathXmlApplicationContext;import java.util.ArrayList;import java.util.HashMap;import java.util.Iterator;import java.util.Map;public class StudentServicesTest {    @Test    public void checkLogin() {        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");        LoginServices services = (LoginServices) context.getBean("loginServices");        Student student = services.checkUser("111114", "111111");        System.out.println(student.toString());    }    @Test    public void testJSON(){        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");        ParperServices services = (ParperServices) context.getBean("parperServices");        Paper paper = services.selectPaper(1);        JSONObject object1 = paper.getContent().getJSONObject("content");        ArrayList<Question> questions = new ArrayList<>();        //System.out.println(object1);        // 解析json        JSONArray object2 = object1.getJSONArray("multiple");        System.out.println(object2);        for (int i = 0; i < object2.size(); i++) {            Question q =new Question();            JSONObject object = object2.getJSONObject(i);            // 考题id            q.setTitle(object.getString("id"));            //  解析答案"answer": {            //                    "a": "a",            //                    "b": "b",            //                }            JSONObject an = object.getJSONObject("answer");            ArrayList<String> answer = new ArrayList<>();            Iterator iter1 = an.entrySet().iterator();            while (iter1.hasNext()) {                Map.Entry entry = (Map.Entry) iter1.next();                answer.add(entry.getKey().toString());            }            q.setAnswer(answer);            // 解析选项 "choose": {            //                    "a": "a1",            //                    "a1": "a1",            //                    "a2": "a1",            //                    "a3": "a1"            //                }            JSONObject ch = object.getJSONObject("choose");            Map<String,String> choose =new HashMap<>();            Iterator iter2 = ch.entrySet().iterator();            while (iter2.hasNext()) {                Map.Entry entry = (Map.Entry) iter2.next();                choose.put(entry.getKey().toString(),entry.getValue().toString());                //System.out.println(entry.getKey().toString());                //System.out.println(entry.getValue().toString());            }            q.setChoosees(choose);            System.out.println(q);            questions.add(q);        }    }    @Test    public void testJSONObject(){        //JSONObject jsonInfo = new JSONObject();        //String key1 = "a";        //jsonInfo.put(key1, "aa");        //String key2 = "b";        //jsonInfo.put(key2, "bb");        //Iterator iter = jsonInfo.entrySet().iterator();        //while (iter.hasNext()) {        //    Map.Entry entry = (Map.Entry) iter.next();        //    System.out.println(entry.getKey().toString());        //    System.out.println(entry.getValue().toString());        //}    }}