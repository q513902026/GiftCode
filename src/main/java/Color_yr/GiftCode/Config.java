package Color_yr.GiftCode;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

public class Config {

    public static final String Version = "1.0.0";
    public static File FileName;
    public static FileConfiguration config_data;
    public static Logger log;

    public static Plugin GiftCode;

    public static boolean is_read = false;

    public static Map<String, Boolean> cdk_list = new HashMap<String, Boolean>();
    public static List<String> commder_list = new ArrayList<String>();

    public void loadconfig() {
        log.info("§d[GiftCode]§e当前插件版本为：" + Version
                + "，你的配置文件版本为：" + config_data.getString("Version"));

        commder_list = config_data.getStringList("commder");
        Map<String, Object> a = config_data.getConfigurationSection("giftCode").getValues(false);
        for(Map.Entry<String, Object> entry : a.entrySet()){
            cdk_list.put(entry.getKey(), (Boolean) entry.getValue());
        }
    }

    public void Config_reload() {
        try {
            config_data = YamlConfiguration.loadConfiguration(FileName);
            loadconfig();
        } catch (Exception arg0) {
            log.warning("§d[GiftCode]§c配置文件读取失败:" + arg0);
        }
    }

    public void setConfig() {
        FileName = new File(GiftCode.getDataFolder(), "config.yml");
        Logs.file = new File(GiftCode.getDataFolder(), "Logs.log");
        if (!GiftCode.getDataFolder().exists())
            GiftCode.getDataFolder().mkdir();
        if (!FileName.exists()) {
            try (InputStream in = GiftCode.getResource("config.yml")) {
                Files.copy(in, FileName.toPath());
            } catch (IOException e) {
                log.warning("§d[GiftCode]§c配置文件创建失败：" + e);
            }
        }
        try {
            if (!Logs.file.exists()) {
                Logs.file.createNewFile();
            }
        } catch (IOException e) {
            log.warning("§d[GiftCode]§c日志文件错误：" + e);
        }
    }

    public void read_cdk()
    {
        File FileName = new File(GiftCode.getDataFolder(), "cdk.txt");
        Logs logs = new Logs();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), Charset.defaultCharset()));
            String lineTxt;
            cdk_list.clear();
            while ((lineTxt = br.readLine()) != null) {
                String[] names = lineTxt.split(",");
                for (String name : names) {
                    if (!cdk_list.containsKey(name)) {
                        cdk_list.put(name, false);
                        logs.log_write("添加CDK：" + name);
                    }
                }
            }
            br.close();
            Date date = new Date();
            String year = String.format("%tF", date);
            String time = String.format("%tT", date);
            String write = "[" + year + "]" + "[" + time + "]";
            config_data.set("giftCode", cdk_list);
            config_data.set("gitUpdata", write);
            config_data.save(this.FileName);
        }catch (Exception e)
        {
            logs.log_write("读取发生错误" + e.getMessage());
        }
    }
}