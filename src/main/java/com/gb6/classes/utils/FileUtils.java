package com.gb6.classes.utils;

import com.google.gson.reflect.TypeToken;

import java.io.*;

import static com.gb6.classes.utils.Constants.GSON;
import static com.gb6.classes.utils.Constants.INSTANCE;

public final class FileUtils {
    public static <T> T readJSON(TypeToken<T> type, String file) {
        try {
            return GSON.fromJson(new FileReader(INSTANCE.getDataFolder().getAbsolutePath() + File.separator + file + ".json"), type.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveJSON(Object o, String file) {
        try (OutputStream os = new FileOutputStream(INSTANCE.getDataFolder().getAbsolutePath() + File.separator + file + ".json")) {
            os.write(GSON.toJson(o).getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createFile(String path) {
        File file = new File(INSTANCE.getDataFolder().getAbsolutePath() + File.separator + path);
        try {
            if (!file.exists()) {
                return file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
