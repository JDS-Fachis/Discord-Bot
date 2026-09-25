package fachis.discordbot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Brain extends ListenerAdapter {

	final String commandPrefix = "!";
	
    public static void main(String[] args) {
        JDABuilder.createDefault(System.getenv("DISCORD_TOKEN"))
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

        if (text.equals(commandPrefix + "status")) {
            event.getChannel().sendMessage("Bot - Setup Phase").queue();
        }
        
        if (text.equals(commandPrefix + "franzosen")) {
            event.getChannel().sendMessage("Grrr").queue();
        }
    }
}