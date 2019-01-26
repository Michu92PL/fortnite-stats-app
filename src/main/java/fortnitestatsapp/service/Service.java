package fortnitestatsapp.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import fortnitestatsapp.model.UserData;
import org.json.JSONObject;

public class Service {

    private String URL = "https://api.fortnitetracker.com/v1/profile/";
    //private String platform;

    private String keyName = "TRN-Api-Key";
    private String keyValue = "fec99ff2-666e-4c57-a42d-72a296672893";
    private HttpResponse<JsonNode> response;


    public UserData getUserData(String platform, String name) {


        try {
            response = Unirest.get(URL + platform + "/" + name)
                    .header(keyName, keyValue)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = null;
        UserData userData = new UserData();

        try {
            jsonObject = new JSONObject(response.getBody().toString());

            userData.setAccountID(jsonObject.get("accountId").toString());
            userData.setEpicName(jsonObject.get("epicUserHandle").toString());

            userData.setTotalWins(jsonObject.getJSONArray("lifeTimeStats").getJSONObject(8).get("value").toString());
            userData.setTotalScore(jsonObject.getJSONArray("lifeTimeStats").getJSONObject(6).get("value").toString());
            userData.setTotalMatchesPlayed(jsonObject.getJSONArray("lifeTimeStats").getJSONObject(7).get("value").toString());
            userData.setTotalWinPercentage(jsonObject.getJSONArray("lifeTimeStats").getJSONObject(9).get("value").toString());
            userData.setTotalKills(jsonObject.getJSONArray("lifeTimeStats").getJSONObject(10).get("value").toString());
            userData.setTotalKD(jsonObject.getJSONArray("lifeTimeStats").getJSONObject(11).get("value").toString());

            userData.setSoloWins(jsonObject.getJSONObject("stats").getJSONObject("p2").getJSONObject("top1").get("value").toString());
            userData.setSoloKD(jsonObject.getJSONObject("stats").getJSONObject("p2").getJSONObject("kd").get("value").toString());
            userData.setSoloWinPercentage(jsonObject.getJSONObject("stats").getJSONObject("p2").getJSONObject("winRatio").get("value").toString());
            userData.setSoloGamesPlayed(jsonObject.getJSONObject("stats").getJSONObject("p2").getJSONObject("matches").get("value").toString());
            userData.setSoloKills(jsonObject.getJSONObject("stats").getJSONObject("p2").getJSONObject("kills").get("value").toString());

            userData.setDuoWins(jsonObject.getJSONObject("stats").getJSONObject("p10").getJSONObject("top1").get("value").toString());
            userData.setDuoKD(jsonObject.getJSONObject("stats").getJSONObject("p10").getJSONObject("kd").get("value").toString());
            userData.setDuoWinPercentage(jsonObject.getJSONObject("stats").getJSONObject("p10").getJSONObject("winRatio").get("value").toString());
            userData.setDuoGamesPlayed(jsonObject.getJSONObject("stats").getJSONObject("p10").getJSONObject("matches").get("value").toString());
            userData.setDuoKills(jsonObject.getJSONObject("stats").getJSONObject("p10").getJSONObject("kills").get("value").toString());

            userData.setTeamWins(jsonObject.getJSONObject("stats").getJSONObject("p9").getJSONObject("top1").get("value").toString());
            userData.setTeamKD(jsonObject.getJSONObject("stats").getJSONObject("p9").getJSONObject("kd").get("value").toString());
            userData.setTeamWinPercentage(jsonObject.getJSONObject("stats").getJSONObject("p9").getJSONObject("winRatio").get("value").toString());
            userData.setTeamGamesPlayed(jsonObject.getJSONObject("stats").getJSONObject("p9").getJSONObject("matches").get("value").toString());
            userData.setTeamKills(jsonObject.getJSONObject("stats").getJSONObject("p9").getJSONObject("kills").get("value").toString());

            if (jsonObject.getJSONObject("stats").has("curr_p2")) {
                userData.setCurrentSoloWins(jsonObject.getJSONObject("stats").getJSONObject("curr_p2").getJSONObject("top1").get("value").toString());
                userData.setCurrentSoloKD(jsonObject.getJSONObject("stats").getJSONObject("curr_p2").getJSONObject("kd").get("value").toString());
                userData.setCurrentSoloWinPercentage(jsonObject.getJSONObject("stats").getJSONObject("curr_p2").getJSONObject("winRatio").get("value").toString());
                userData.setCurrentSoloGamesPlayed(jsonObject.getJSONObject("stats").getJSONObject("curr_p2").getJSONObject("matches").get("value").toString());
                userData.setCurrentSoloKills(jsonObject.getJSONObject("stats").getJSONObject("curr_p2").getJSONObject("kills").get("value").toString());
            }
            if (jsonObject.getJSONObject("stats").has("curr_p10")) {
                userData.setCurrentDuoWins(jsonObject.getJSONObject("stats").getJSONObject("curr_p10").getJSONObject("top1").get("value").toString());
                userData.setCurrentDuoKD(jsonObject.getJSONObject("stats").getJSONObject("curr_p10").getJSONObject("kd").get("value").toString());
                userData.setCurrentDuoWinPercentage(jsonObject.getJSONObject("stats").getJSONObject("curr_p10").getJSONObject("winRatio").get("value").toString());
                userData.setCurrentDuoGamesPlayed(jsonObject.getJSONObject("stats").getJSONObject("curr_p10").getJSONObject("matches").get("value").toString());
                userData.setCurrentDuoKills(jsonObject.getJSONObject("stats").getJSONObject("curr_p10").getJSONObject("kills").get("value").toString());
            }
            if (jsonObject.getJSONObject("stats").has("curr_p9")) {
                userData.setCurrentTeamWins(jsonObject.getJSONObject("stats").getJSONObject("curr_p9").getJSONObject("top1").get("value").toString());
                userData.setCurrentTeamKD(jsonObject.getJSONObject("stats").getJSONObject("curr_p9").getJSONObject("kd").get("value").toString());
                userData.setCurrentTeamWinPercentage(jsonObject.getJSONObject("stats").getJSONObject("curr_p9").getJSONObject("winRatio").get("value").toString());
                userData.setCurrentTeamGamesPlayed(jsonObject.getJSONObject("stats").getJSONObject("curr_p9").getJSONObject("matches").get("value").toString());
                userData.setCurrentTeamKills(jsonObject.getJSONObject("stats").getJSONObject("curr_p9").getJSONObject("kills").get("value").toString());
            }
        } catch (Exception e) {
            return userData;
        }
        return userData;
    }
}
