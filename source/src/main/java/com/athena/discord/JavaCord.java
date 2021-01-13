package com.athena.discord;

import com.athena.GameSettings;
import com.athena.world.World;
import com.athena.world.entity.impl.player.Player;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;


/**
 * @author Patrity || https://www.rune-server.ee/members/patrity/
 */

public class JavaCord {

    private static String token = "NzQwOTM2MzY5NzM1NzI5MjM0.XywQxA.p1NeB0xzbWkBgdFFIQEQNc2WQbM";

    private static String serverName = "Athena";

    private static DiscordApi api = null;


    public static void init() {
        new DiscordApiBuilder().setToken(token).login().thenAccept(api -> {
            JavaCord.api = api;
            System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
            sendMessage("bot", serverName+" is now online!");
            api.addMessageCreateListener(event -> {

                if (event.getMessageContent().equalsIgnoreCase("::userInfo")) {

                    MessageAuthor author = event.getMessage().getAuthor();

                    EmbedBuilder embed = new EmbedBuilder()

                            .setTitle("User Info")

                            .addField("Display Name", author.getDisplayName(), true)

                            .addField("Name + Discriminator", author.getDiscriminatedName(), true)

                            .addField("User Id", author.getIdAsString(), true)

                            .setAuthor(author);

                    // Keep in mind that a message author can either be a webhook or a normal user

                    author.asUser().ifPresent(user -> {

                        embed.addField("Online Status", user.getStatus().getStatusString(), true);

                        embed.addField("Connected Clients", user.getCurrentClients().toString());

                        // The User#getActivity() method returns an Optional

                        embed.addField("Activity", user.getActivity().map(Activity::getName).orElse("none"), true);

                    });

                    // Keep in mind that messages can also be sent as private messages

                    event.getMessage().getServer()

                            .ifPresent(server -> embed.addField("Server Admin", author.isServerAdmin() ? "yes" : "no", true));

                    // Send the embed. It logs every exception, besides missing permissions (you are not allowed to send message in the channel)

                    event.getChannel().sendMessage(embed);



                }

                if (event.getMessageContent().equalsIgnoreCase("::players")) {
                    int online = (int)(World.getPlayers().size());
                    event.getChannel().sendMessage("Players currently online: "+online);
                }

                if (event.getMessageContent().equalsIgnoreCase("::online")) {
                    event.getChannel().sendMessage(":tada: "+serverName+" is Online! :tada:");
                }


                if (event.getMessageContent().startsWith("::movehome")) {
                    if (event.getMessageAuthor().isServerAdmin()) {
                        System.out.println("perms");
                        String target = event.getMessageContent().replace("::movehome ", "");
                        Player playerToMove = World.getPlayerByName(target);
                        if (playerToMove != null) {
                            playerToMove.moveTo(GameSettings.DEFAULT_POSITION.copy());
                            playerToMove.getPacketSender().sendMessage("You have been teleported home by " + event.getMessageAuthor().getDisplayName() + ".");
                            event.getChannel().sendMessage("Sucessfully moved "+playerToMove.getUsername()+" to home.");

                        } else {
                            event.getChannel().sendMessage("Player is not online!");
                        }
                    } else {
                        event.getChannel().sendMessage("You do not have permission to preform this command");
                    }
                }

                if (event.getMessageContent().startsWith("::kick")) {
                    if (event.getMessageAuthor().isServerAdmin()) {
                        String target = event.getMessageContent().replace("::kick ", "");
                        Player playerToKick = World.getPlayerByName(target);
                        if (playerToKick != null) {
                            if (playerToKick.getDueling().duelingStatus >= 5) {
                                event.getChannel().sendMessage("The player is in a trade, or duel. You cannot do this at this time.");
                                return;
                            }
                            World.getPlayers().remove(playerToKick);
                            event.getChannel().sendMessage("Successfully kicked: " + playerToKick.getUsername() + ".");
                            World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> An Admin on Discord has just kicked " + playerToKick.getUsername() + ".");

                        } else {
                            event.getChannel().sendMessage("Player is not online!");
                        }
                    } else {
                        event.getChannel().sendMessage("You do not have permission to preform this command");
                    }
                }

            });
        })
                // Log any exceptions that happened
                .exceptionally(ExceptionLogger.get());
    }

    public static void sendMessage(String channel, String msg) {
        try {
            new MessageBuilder()
                    .append(msg)
                    .send((TextChannel) api.getTextChannelsByNameIgnoreCase(channel).toArray()[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}