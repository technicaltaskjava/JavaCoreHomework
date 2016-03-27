package ua.valentin.htmlparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Line of parsed text.
 * Created by valentin.yakimenko on 21.03.16.
 */
public class Line {
    private int numbOfLine;
    private String data;
    private List<Integer> numbOfPics;
    private boolean isRefPrevPic;

    public Line(int numbOfLine, String data) {
        this.numbOfLine = numbOfLine;
        this.data = data;
        this.numbOfPics = new ArrayList<>();
    }

    public void addNumbOfPic(int numbOfPic) {
        this.numbOfPics.add(numbOfPic);
    }

    public String getData() {
        return data;
    }

    public List<Integer> getNumbOfPics() {
        return numbOfPics;
    }

    public int getNumbOfLine() {
        return numbOfLine;
    }

    public void setRefPrevPic() {
        isRefPrevPic = true;
    }

    @Override
    public String toString() {
        return numbOfLine + (isRefPrevPic ? " Ref prev pic" : "") + " : "  + numbOfPics.toString() + " : " + data;
    }
  }
