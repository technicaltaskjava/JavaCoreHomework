package task3;

import messages.Message;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 19.03.2016
 */
public class HTMLParser {

    private StringBuilder sb = new StringBuilder(512);
    private Message m = new Message();
    private Pattern pattern = Pattern.compile("((<div>)(&nbsp)?((.)+)(Рис\\.)(.+)(<\\/div>))|((<div>)(&nbsp)?((.+))(а рисунк)(.)+(<\\/div>))",
                                              Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern pattern2 = Pattern.compile("(<div>)|(</div>)|(&nbsp;)|(<sub>)|(</sub>)|(<span>)|(</span>)",
            Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern pattern3  = Pattern.compile("[.?!]( )+[А-Я][а-я ]",
            Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern pattern4  = Pattern.compile(".+(Рис.).+|.+(а рисунк).+",
            Pattern.UNICODE_CHARACTER_CLASS);

    public String parseHTML(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            m.warn("File not found");
            throw new FileNotFoundException() ;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "cp1251"));) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(currentLine);
                while (matcher.find()) {
                    Matcher matcher2 = pattern2.matcher(matcher.group());
                    sb.append(matcher2.replaceAll(""));
                    sb.append("\n");
                }
           }
            Matcher matcher3 = pattern3.matcher(sb);
            while (matcher3.find()) {

                sb.setCharAt(matcher3.start() + 1,'\n' );
            }
            Matcher matcher4 = pattern4.matcher(sb);

            StringBuilder builder = new StringBuilder();
            while (matcher4.find()) {
                builder.append(matcher4.group());
                builder.append("\n");
            }

            return builder.toString();

        }catch (FileNotFoundException e){
            m.warn("File not found");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    return sb.toString();}
}
