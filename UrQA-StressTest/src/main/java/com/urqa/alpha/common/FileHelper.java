package com.urqa.alpha.common;

import android.os.Environment;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author seunoh on 2014. 05. 06..
 */
public class FileHelper {

    private static final String FILE_NAME = "test_result.txt";

    private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/UrQA";

    private static volatile FileHelper sInstance;

    private File mFile;

    private FileHelper() {
    }

    public static FileHelper getInstance() {
        if (sInstance == null)
            sInstance = new FileHelper();
        return sInstance;
    }


    public static String absolutePath() {
        return new File(FILE_PATH + "/" + FILE_NAME).getAbsolutePath();
    }

    public void init() {

        if (existsDirectory(FILE_PATH)) {
            try {
                mFile = makeFile(FILE_PATH + "/" + FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean existsDirectory(String path) {
        File directory = new File(path);
        return directory.exists() || directory.mkdir();
    }

    private File makeFile(String path) throws IOException {
        File file = new File(path);

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("File create is fail");
            }
        }

        return file;
    }


    public void append(String data) throws IOException {
        FileWriter fw = new FileWriter(mFile, true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(data);
        writer.newLine();
        writer.flush();
        writer.close();
    }

    public List<String> reader() throws IOException {
        List<String> list = Lists.newArrayList();

        if (mFile != null) {
            BufferedReader in = new BufferedReader(new FileReader(mFile));

            String s;
            while ((s = in.readLine()) != null) {
                list.add(s);
            }
            in.close();
        } else {
            throw new IOException("file == null");
        }

        return list;
    }
}
