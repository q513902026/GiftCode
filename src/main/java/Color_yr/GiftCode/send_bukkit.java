package Color_yr.GiftCode;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class send_bukkit {
    public static List<String> message = new ArrayList<String>();
    public static String player;
    public static CommandSender sender = new CommandSender() {
        @Override
        public void sendMessage(String message) {
            send_bukkit.message.add(message);
        }

        @Override
        public void sendMessage(String[] messages) {
            for (String a : messages)
                send_bukkit.message.add(a);
        }

        @Override
        public Server getServer() {
            return Bukkit.getServer();
        }

        @Override
        public String getName() {
            return player;
        }

        @Override
        public boolean isPermissionSet(String name) {
            return true;
        }

        @Override
        public boolean isPermissionSet(Permission perm) {
            return true;
        }

        @Override
        public boolean hasPermission(String name) {
            return true;
        }

        @Override
        public boolean hasPermission(Permission perm) {
            return true;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
            return Bukkit.getConsoleSender().addAttachment(plugin, name, value);
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin) {
            return Bukkit.getConsoleSender().addAttachment(plugin);
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
            return Bukkit.getConsoleSender().addAttachment(plugin, name, value, ticks);
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
            return Bukkit.getConsoleSender().addAttachment(plugin, ticks);
        }

        @Override
        public void removeAttachment(PermissionAttachment attachment) {

        }

        @Override
        public void recalculatePermissions() {

        }

        @Override
        public Set<PermissionAttachmentInfo> getEffectivePermissions() {
            return Bukkit.getConsoleSender().getEffectivePermissions();
        }

        @Override
        public boolean isOp() {
            return true;
        }

        @Override
        public void setOp(boolean value) {

        }
    };
}
