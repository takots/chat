import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        header(stringBuffer ,"Alex - 2023/11/29");
        readfile(stringBuffer);
        footer(stringBuffer);
        writefile(stringBuffer ,"20231129");
    }

    private static void header(StringBuffer stringBuffer ,String title){
        stringBuffer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\t<link rel=\"stylesheet\" href=\"../css/chat.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"header\">\n" +
                "   <h2>"+title+"</h2>\n" +
                "</div>");
    }

    private static void footer(StringBuffer stringBuffer){
        stringBuffer.append("</body>\n" +
                "</html>");

    }
    private static void writefile(StringBuffer stringBuffer ,String dateStr){
        // 設定要保存的檔案路徑
        String filePath = "D:\\workspace_java\\chat\\webapp\\html\\"+dateStr+".html";

        // 使用 Paths.get() 創建 Path 對象
        Path path = Paths.get(filePath);

        try {
            // 使用 Files.write() 將HTML內容寫入檔案
            Files.write(path, stringBuffer.toString().getBytes());

            System.out.println("HTML文件已成功保存到：" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void readfile(StringBuffer stringBuffer){
        // 設定要讀取的檔案路徑
        String line2 = "";
        String filePath = "D:\\workspace_java\\chat\\msg\\simple.txt";
//        System.out.println("filePath>"+filePath);
        // 使用 Paths.get() 創建 Path 對象
        Path path = Paths.get(filePath);
        try {
            // 使用 Files.readAllLines() 讀取檔案內容到 List<String>
            List<String> lines = Files.readAllLines(path);
            // 處理讀取到的內容
            for (String line : lines) {
                if(line.contains("Alex: ")) {
                    stringBuffer.append("<div ");
                    stringBuffer.append("title=\"");
                    stringBuffer.append(line.split("Alex: ")[1].split("@")[1]);
                    stringBuffer.append("\" ");
                    stringBuffer.append("class=\"message receive\">");
                    stringBuffer.append(line.split("Alex: ")[1].split("@")[0]);
                    stringBuffer.append("</div>");

                }else if(line.contains("Bob: ")) {
                    stringBuffer.append("<div ");
                    stringBuffer.append("title=\"");
                    stringBuffer.append(line.split("Bob: ")[1].split("@")[1]);
                    stringBuffer.append("\" ");
                    stringBuffer.append("class=\"message send\">");
                    stringBuffer.append(line.split("Bob: ")[1].split("@")[0]);
                    stringBuffer.append("</div>");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}