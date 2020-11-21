package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.*;

public class infoLabel extends Label {
    private final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";


    public infoLabel(String text) {
        setPrefWidth(853);
        setPrefHeight(480);
        setPadding(new Insets(40, 40, 40, 40));
        setText(text);
        setTextAlignment(TextAlignment.CENTER);
        setAlignment(Pos.CENTER);
        setWrapText(true);
        setLabelFont();
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    public void setTextFromFile(String url) {
        try {
            InputStream is = new FileInputStream(url);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();

            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = bufferedReader.readLine();
            }

            String context = stringBuilder.toString();

            setText(context);
            setWrapText(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
