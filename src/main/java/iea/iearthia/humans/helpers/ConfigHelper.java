package iea.iearthia.humans.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ConfigHelper {

    private static final HumansConfig DEFAULT_CONFIG = new HumansConfig();

    public static HumansConfig getConfig(String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File configFile = new File(path);
        try {
            if (configFile.createNewFile()) {
                String json = gson.toJson(DEFAULT_CONFIG);
                BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
                writer.write(json);
                writer.close();
                return DEFAULT_CONFIG;
            } else {
                return gson.fromJson(readFileToString(path), HumansConfig.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DEFAULT_CONFIG;
        }
    }

    public static class HumansConfig {
        public HumansSkin[] skins = { getSkin("JadeIEA", "1e25868f-6372-492d-8319-3a4627f0cc18") };
    }
    public static class HumansSkin {
        public String username = "";
        public String uuid = "";
        public boolean useSlim = false;
    }

    private static HumansSkin getSkin(String username, String uuid) {
        HumansSkin skin = new HumansSkin();
        skin.username = username;
        skin.uuid = uuid;
        return skin;
    }



    private static String readFileToString(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }


}
