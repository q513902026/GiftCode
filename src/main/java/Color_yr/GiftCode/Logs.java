package Color_yr.GiftCode;

import java.io.*;
import java.util.Date;

public class Logs {
    public static File file;

    public void log_write(String text) {
        FileWriter fw;
        try {
            fw = new FileWriter(file, true);
            Date date = new Date();
            String year = String.format("%tF", date);
            String time = String.format("%tT", date);
            String write = "[" + year + "]" + "[" + time + "]" + text;
            PrintWriter pw = new PrintWriter(fw);
            pw.println(write);
            pw.flush();
            fw.flush();
            pw.close();
            fw.close();
        } catch (FileNotFoundException e) {
            Config.log.warning("§d[GiftCode]§c日志文件写入失败" + e);
        } catch (IOException e) {
            Config.log.warning("§d[GiftCode]§c日志文件写入失败" + e);
        }
    }
}
