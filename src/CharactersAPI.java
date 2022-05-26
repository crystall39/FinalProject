import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class CharactersAPI // Get character info from API here
// finished class
{
    private String baseURL;

    public CharactersAPI()
    {
        baseURL = "https://impact.moe/api/characters";
    }

    public Characters getCharacter(String name)
    {
        return parseCharacters(name);
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

    public Characters parseCharacters(String json)
    {
        Characters character;
        JSONArray jsonArr = new JSONArray(makeAPICallCharacters());

        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String name = jsonObj.getString("name");
            if (name.toLowerCase().equals(json.toLowerCase()))
            {
                String birthday = jsonObj.getString("birthday");
                String region = jsonObj.getString("region");
                String description = jsonObj.getString("description");
                int rarity = jsonObj.getInt("rarity");
                String weapon = jsonObj.getString("weapon");
                String element = jsonObj.getString("element");
                String constellation = jsonObj.getString("constellation");

                character = new Characters(name, birthday, region, description, rarity, weapon, element, constellation);
                return character;
            }
        }
        character = new Characters("", "", "", "", 0, "", "", "");
        return character;
    }

    public ArrayList<String> characterList()
    {
        ArrayList<String> allCharacters = new ArrayList<>();

        JSONArray jsonArr = new JSONArray(makeAPICallCharacters());

        for (int i = 0; i < 54; i++)
        {
            JSONObject characterObj = jsonArr.getJSONObject(i);
            String name = characterObj.getString("name");
            String returnName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            allCharacters.add(returnName);
        }
        return allCharacters;
    }

    public String makeAPICallCharacters()
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