package fachis.discordbot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Brain extends ListenerAdapter {

    public static void main(String[] args) {
        JDABuilder.createDefault(System.getenv("Fachis_Bot_Token"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.watching("you"))
                .addEventListeners(new Brain())
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String text = event.getMessage().getContentRaw();

        if (text.equals("!hallo")) {
            event.getChannel().sendMessage("Hallo!").queue();
        }

        if (text.equals("!dnd")) {
            event.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        }

        if (text.equals("!online")) {
            event.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
        }

        if (text.startsWith("!spielt ")) {
            String neuerText = text.substring(8);
            event.getJDA().getPresence().setActivity(Activity.playing(neuerText));
        }
    }
}