import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeaponsAPI // Get weapon info from API here
{
    private String baseURL;

    public WeaponsAPI()
    {
        baseURL = "https://impact.moe/api/weapons";
    }

    public Weapons getWeapon(String name)
    {
        // String weapon = makeAPICall(name);
        return parseWeapons(name);
    }

    public String makeAPICall(String name)
    {
        String url = baseURL + "/" + name.toLowerCase();
        try
        {
            URI myUri = URI.create(url);
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Weapons parseWeapons(String json)
    {
        Weapons weapon;
        JSONArray jsonArr = new JSONArray(makeAPICallWeapons());

        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String name = jsonObj.getString("name");
            if (name.toLowerCase().equals(json.toLowerCase()))
            {
                String type = jsonObj.getString("type");
                int rarity = jsonObj.getInt("rarity");
                int baseAtk = jsonObj.getInt("baseAtk");
                String subStatType = jsonObj.getString("subStatType");
                double subStat = jsonObj.getDouble("subStat");
                String description = jsonObj.getString("description");
                String abilityName = jsonObj.getString("abilityName");
                String abilityDescription = jsonObj.getString("abilityDescription");
                String location = jsonObj.getString("location");

                weapon = new Weapons(name, type, rarity, baseAtk, subStatType, subStat, description, abilityName, abilityDescription, location);
                return weapon;
            }
        }
        weapon = new Weapons("", "", 0, 0, "", 0.0, "", "", "", "");
        return weapon;
    }

    public ArrayList<String> weaponList()
    {
        ArrayList<String> allWeapons = new ArrayList<>();

        JSONArray jsonArr = new JSONArray(makeAPICallWeapons());

        for (int i = 0; i < 127; i++)
        {
            JSONObject weaponObj = jsonArr.getJSONObject(i);
            String name = weaponObj.getString("name");
            String returnName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            allWeapons.add(returnName);
        }
        return allWeapons;
    }

    public String makeAPICallWeapons()
    {
        String url = baseURL;
        try
        {
            URI myUri = URI.create(url);
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}