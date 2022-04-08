package com.imjustdoom.justanimations.command.subcommand.impl;

import com.imjustdoom.justanimations.JustAnimations;
import com.imjustdoom.justanimations.api.util.TranslationUtil;
import com.imjustdoom.justanimations.command.subcommand.SubCommand;
import com.imjustdoom.justanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DeleteCmd implements SubCommand {

    public String getName() {
        return "delete";
    }

    public String getDescription() {
        return "Deletes an animation";
    }

    public void execute(CommandSender sender, String[] args) {

        if(args.length == 1) {
            sender.sendMessage("Please specify an animation to delete");
            return;
        }
        if (JustAnimations.INSTANCE.getAnimations().get(args[1]) == null) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELETE_NOT_EXISTS));
            return;
        }
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELETE));
        if (JustAnimations.INSTANCE.getAnimations().get(args[1]).isRunning()) {
            JustAnimations.INSTANCE.getAnimations().get(args[1]).stop();
        }
        JustAnimations.INSTANCE.getAnimations().get(args[1]).getDataStore().deleteAnimation(args[1].toLowerCase());
        JustAnimations.INSTANCE.getAnimations().remove(args[1].toLowerCase());
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELETE_SUCCESS));
    }

    public String[] getPermission() {
        return new String[]{"justanimations.delete", "justanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }
}
