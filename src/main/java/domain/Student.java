package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    JsonObject jsonObject;
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        jsonObject = super.toJsonObject();
        ArrayList<Json> jExamObjs = new ArrayList<>();
        for(Tuple<String, Integer> exam: exams) {
            boolean passed = exam.value >= 3;
            JsonObject tmp = new JsonObject(new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed", new JsonBoolean(passed))
                    );
            jExamObjs.add(tmp);
        }
        JsonArray jExams = new JsonArray(jExamObjs);
        jsonObject.add(new JsonPair("exams", jExams));
    }

    public JsonObject toJsonObject() {
        return jsonObject;
    }
}
