//package t03t04.javafilereader;
//
//import t03t04.javafilereader.wordchecker.ReservedWordChecker;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Arrays;
//
///**
// * @author Alexey Ushakov
// */
//public class JavaByteReader extends JavaReader {
//    private static final int[] BYTE_CODE_ARRAY = {10, 32, -1, 46, 59, 125, 123,
//            40, 41, 44};
//
//    public JavaByteReader(String filePath) {
//        super(new File(filePath).getAbsolutePath());
//    }
//
//    private boolean isCorrectCode(int code) {
//        for (int byteCode : BYTE_CODE_ARRAY) {
//            if (code == byteCode) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void checkReservedWord() throws IOException {
//
//        String checkWord;
//
//        try (FileInputStream input = new FileInputStream(getFilePath())) {
//            while (input.available() > 0) {
//                int symbolCode = input.read();
//                char[] line = new char[200];
//                int lineLength;
//
//                for (lineLength = 0; isCorrectCode(symbolCode); lineLength++) {
//                    line[lineLength] = (char) symbolCode;
//                    symbolCode = input.read();
//                }
//
//                if (lineLength > 1) {
//                    checkWord = String.valueOf(Arrays.copyOfRange(line, 0, lineLength));
//                    if (ReservedWordChecker.isReservedWord(checkWord)) {
//                        addReservedWord(checkWord);
//                    }
//                }
//            }
//        }
//    }
//
//    public void writeReport() throws IOException {
//        writeReport("JavaByteReaderReport.log");
//    }
//
//    public void writeReport(String reportPath) throws IOException {
//        try (FileOutputStream output = new FileOutputStream(reportPath)) {
//            output.write(("Words count " + getReservedWordsCount() + "\n\n").getBytes());
//
//            for (String word : getReservedWords()) {
//                output.write((word + "\n").getBytes());
//            }
//        }
//    }
//}
