package Color_yr.GiftCode;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;

public class Commder implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cdk")) {
            if (args.length == 0) {
                sender.sendMessage("§d[GiftCode]§c请输入激活码来激活");
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.isOp()) {
                    Config config = new Config();
                    config.Config_reload();
                    sender.sendMessage("§d[GiftCode]§e重载成功");
                } else {
                    sender.sendMessage("§d[GiftCode]§c你没有权限");
                }
                return true;
            } else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§d[GiftCode]§e帮助手册");
                sender.sendMessage("§d[GiftCode]§e使用/GiftCode [cdk] 来使用你的激活码");
                if (sender.isOp()) {
                    sender.sendMessage("§d[GiftCode]§e使用/GiftCode reload 来重读插件配置文件");
                } else {
                    sender.sendMessage("§d[GiftCode]§c你没有权限");
                }
                return true;
            } else if (args[0].equalsIgnoreCase("load")) {
                BukkitRunnable a = new BukkitRunnable() {
                    @Override
                    public void run() {
                        try {
                            sender.sendMessage("§d[GiftCode]§e正在读取cdk");
                            Config.is_read = true;
                            Config Config = new Config();
                            Config.read_cdk();
                            Config.is_read = false;
                            sender.sendMessage("§d[GiftCode]§e读取完毕！");
                        } catch (Exception e) {
                            sender.sendMessage("§d[GiftCode]§c读取发生错误");
                        }
                    }
                };
                a.runTaskAsynchronously(Config.GiftCode);
            }if (args.length == 1) {
                if (Config.cdk_list.containsKey(args[0])) {
                    Boolean a = Config.cdk_list.get(args[0]);
                    if (a == true) {
                        sender.sendMessage("§d[GiftCode]§c该激活码已被使用");
                        return true;
                    } else if (Config.is_read == true) {
                        sender.sendMessage("§d[GiftCode]§c激活系统维护中");
                        return true;
                    }
                    Config.cdk_list.put(args[0], true);
                    Config.config_data.set("giftCode", Config.cdk_list);
                    try {
                        Config.config_data.save(Config.FileName);
                        sender.sendMessage("§d[GiftCode]§e使用激活码" + args[0]);
                        Logs log = new Logs();
                        log.log_write("玩家" + sender.getName() + "使用激活码：" + args[0]);
                        BukkitRunnable b = new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (String b : Config.commder_list) {
                                    send_bukkit.message.clear();
                                    b = b.replace("%Player%", sender.getName());
                                    Bukkit.dispatchCommand(send_bukkit.sender, b);
                                    if (send_bukkit.message.size() != 0) {
                                        for (String a : send_bukkit.message) {
                                            sender.sendMessage(a);
                                        }
                                    }
                                }
                            }
                        };
                        b.runTask(Config.GiftCode);
                    } catch (Exception e) {
                        Config.log.warning(e.toString());
                    }
                }else
                {
                    sender.sendMessage("§d[GiftCode]§c无效的CDK");
                    return true;
                }
            }
            return true;
        } else {
            sender.sendMessage("§d[GiftCode]§c错误，请使用/GiftCode help 获取帮助");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> arguments = null;
        if (command.getName().equalsIgnoreCase("GiftCode") == true) {
            arguments = new ArrayList<>();
            if (sender.isOp()) {
                arguments.add("reload");
            }
        }
        return arguments;
    }
}