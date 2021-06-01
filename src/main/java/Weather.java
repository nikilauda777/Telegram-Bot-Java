import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;



public class Weather {

    public static String getWheater(String message, Model model) throws IOException {

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "%20&units=metric&appid=c8726a6fda5490381ede832e7e9b30dd");
        Scanner in = new Scanner((InputStream) url.getContent());

        String result = "";
        while (in.hasNext()) { // до тех пор пока что вводится
            result += in.nextLine(); // все записывается в результат
        }

        JSONObject object = new JSONObject(result); // получили название города
        model.setName(object.getString("name"));

        JSONObject main =  object.getJSONObject("main"); // вытаскиваем из main подобъект
        model.setTemp(main.getDouble("temp")); // получили температуру
        model.setHumidity(main.getDouble("humidity")); // получили влажность

        JSONObject wind = object.getJSONObject("wind");
        model.setWind(wind.getDouble("speed"));

        JSONArray getArray = object.getJSONArray("weather"); // достаем из массива иконку и тд
        for (int i = 0; i < getArray.length(); i++){
            JSONObject object1 = getArray.getJSONObject(i);
            model.setIcon((String) object1.get("icon"));
            model.setMain((String) object1.get("main"));

        }

        return "Your City: " + model.getName() + "\n"
                + "Temperature: " + model.getTemp() + "°С" + "\n"
                + "Humidity: " + model.getHumidity() + "%" + "\n"
                + "Wind: " + model.getWind() + "km/h" + "\n"
                + "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}
