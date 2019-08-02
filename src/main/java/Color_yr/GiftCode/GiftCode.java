package Color_yr.GiftCode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginCommand;

public class GiftCode extends JavaPlugin {

    @Override
    public void onEnable() {
        Config.GiftCode = this;
        Config.log = Bukkit.getLogger();
        Config.log.info("§d[GiftCode]§e正在启动，感谢使用。");

        Config config = new Config();
        config.setConfig();
        config.Config_reload();

        getPluginCommand("cdk").setExecutor(new Commder());

        Config.log.info("§d[GiftCode]§e已启动-" + Config.Version);
    }

    @Override
    public void onDisable() {
        Config.log.info("§d[GiftCode]§e已停止，感谢使用");
    }
}
