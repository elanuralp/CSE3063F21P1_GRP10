import org.json.simple.JSONObject;

import javax.json.JsonObject;
import javax.lang.model.type.NullType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        UniversitySystem universitySystem = new UniversitySystem();
        universitySystem.initializeUniversityCourseSelectionSystem();
    }
}