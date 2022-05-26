import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class ArtifactsAPI // Get artifact info from API here
{
    private String baseURL;

    public ArtifactsAPI()
    {
        baseURL = "https://impact.moe/api/artifacts";
    }

    public Artifacts getArtifact(String artifact)
    {
        return parseArtifacts(artifact);
    }

    public String makeAPICall(String artifact)
    {
        String url = baseURL + "/" + artifact.toLowerCase();
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

    public Artifacts parseArtifacts(String json)
    {
        Artifacts artifactSet;
        JSONArray jsonArr = new JSONArray(makeAPICallAllArtifacts());

        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject artifact = jsonArr.getJSONObject(i);
            JSONObject set = artifact.getJSONObject("artifactSet");
            String name = set.getString("name");
            if (name.toLowerCase().equals(json.toLowerCase()))
            {
                int maxRarity = set.getInt("maxRarity");
                String twoPieceBonus = set.getString("twoPieceBonus");
                String fourPieceBonus = set.getString("fourPieceBonus");

                artifactSet = new Artifacts(name, maxRarity, twoPieceBonus, fourPieceBonus);
                return artifactSet;
            }
        }
        artifactSet = new Artifacts("", 0, "", "");
        return artifactSet;
    }

    public String makeAPICallAllArtifacts()
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

    public ArrayList<String> artifactList()
    {
        ArrayList<String> allArtifacts = new ArrayList<>();

        JSONArray jsonArr = new JSONArray(makeAPICallAllArtifacts());

        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject artifact = jsonArr.getJSONObject(i);
            JSONObject set = artifact.getJSONObject("artifactSet");

            String name = set.getString("name");
            String returnName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();


            allArtifacts.add(returnName);
        }
        for (int i = 0; i < allArtifacts.size(); i++)
        {
            for (int j = i + 1; j < allArtifacts.size(); j++)
            {
                if (allArtifacts.get(i).equals(allArtifacts.get(j)))
                {
                    allArtifacts.remove(j);
                    j--;
                }
            }
        }
        return allArtifacts;
    }
}