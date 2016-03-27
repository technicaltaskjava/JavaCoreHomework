package ua.valentin.htmlparser;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser realisation.
 * Created by valentin.yakimenko on 21.03.16.
 */
public class Parser {
    private Pattern picPattern = Pattern.compile("pic([0-9]{1,2})[.]jpg");
    private Pattern refPattern = Pattern.compile("[Р|р](ис[.]|исунке)[ ]?([0-9]{1,2})");
    private static final String FILE_FOR_PARSE = "Information handling_task_attachment.html";

    public void parse(List<Line> refLines, List<Line> picLines) {
        try {
            InputStream fin = getClass().getResourceAsStream(FILE_FOR_PARSE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fin, "cp1251"));
            int numberOfLine = 0;
            String str;
            while ((str = in.readLine()) != null) {
                Matcher m = picPattern.matcher(str);
                Matcher p = refPattern.matcher(str);
                numberOfLine++;
                if (m.find()) {
                    picLines.add(new Line(numberOfLine, str));
                } else if (p.find()) {
                    refLines.add(new Line(numberOfLine, str));
                }
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setState(List<Line> refLines, List<Line> picLines) {
        for (Line line : refLines) {
            Matcher m = refPattern.matcher(line.getData());
            while (m.find()) {
                line.addNumbOfPic(Integer.valueOf(m.group(2)));
            }
        }
        for (Line line : picLines) {
            Matcher m = picPattern.matcher(line.getData());
            if (m.find()) {
                line.addNumbOfPic(Integer.valueOf(m.group(1)));
            }
        }
        for (Line line : refLines) {
            for (int i = 0; i < line.getNumbOfPics().size(); i++) {
                if (line.getNumbOfLine() < getNumbOfPicLine(line.getNumbOfPics().get(i), picLines))
                    line.setRefPrevPic();
            }
        }
    }

    private int getNumbOfPicLine(int numbOfPic, List<Line> picLines) {
        for (Line line : picLines) {
            if (line.getNumbOfPics().get(0) == numbOfPic)
                return line.getNumbOfLine();
        }
        return 0;
    }
  }
