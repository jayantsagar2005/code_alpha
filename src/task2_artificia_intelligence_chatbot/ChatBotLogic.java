package task2_artificia_intelligence_chatbot;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.*;
import java.util.*;

public class ChatBotLogic {

    private List<Intent> intents;

    public ChatBotLogic(String filePath) {
        intents = loadIntents(filePath);
    }

    public String getResponse(String userMessage) {
        userMessage = userMessage.toLowerCase();

        for (Intent intent : intents) {
            for (String pattern : intent.patterns) {
                if (userMessage.contains(pattern.toLowerCase())) {
                    return getRandomResponse(intent.responses);
                }
            }
        }

        return getRandomResponse(getIntentByName("unknown").responses);
    }

    private String getRandomResponse(List<String> responses) {
        return responses.get(new Random().nextInt(responses.size()));
    }

    private Intent getIntentByName(String name) {
        return intents.stream()
                .filter(intent -> intent.intent.equals(name))
                .findFirst()
                .orElse(new Intent("unknown", new ArrayList<>(), Arrays.asList("Sorry, I didn’t understand that.")));
    }

    private List<Intent> loadIntents(String filePath) {
        List<Intent> result = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONArray patternArray = obj.getJSONArray("patterns");
                JSONArray responseArray = obj.getJSONArray("responses");

                List<String> patterns = new ArrayList<>();
                List<String> responses = new ArrayList<>();

                for (int j = 0; j < patternArray.length(); j++) {
                    patterns.add(patternArray.getString(j));
                }
                for (int j = 0; j < responseArray.length(); j++) {
                    responses.add(responseArray.getString(j));
                }

                result.add(new Intent(obj.getString("intent"), patterns, responses));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Nested Intent Class
    static class Intent {
        String intent;
        List<String> patterns;
        List<String> responses;

        public Intent(String intent, List<String> patterns, List<String> responses) {
            this.intent = intent;
            this.patterns = patterns;
            this.responses = responses;
        }
    }

}
