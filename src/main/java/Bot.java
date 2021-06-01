import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.vdurmont.emoji.EmojiParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    
    public static String weather = "Wheather" + EmojiParser.parseToUnicode(":partly_sunny:");
    public static String dollar = "Kurs" + EmojiParser.parseToUnicode(":money_with_wings:");
    public static String corona = "Corona " + EmojiParser.parseToUnicode(":mask:");

    @Override
    public void onUpdateReceived(Update update) {

        Model model = new Model();
        Message message = update.getMessage();
        if (message != null && message.hasText()) { // если сообщение не пустое и имеет текст

            switch (message.getText()) { 
                case "/help":
                    sendMsg(message, "Bot Features:" + "\n" +
                            "1. Tipp the figure and i will return you a binary code." + "\n" +
                            "2. With integreated Buttons und can learn a lot of different interesting things." + "\n" +
                            "3. For Wheather Predictions,write the name of your city.");
                    break;
                case "/setting":
                    sendMsg(message, "What you want to configurate here ? :)");
                    break;
                case "Films":   // Buttons Message
                    try {
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                default: // в другом случае
                    sendMsg(message, BotVajtersic.getPhrases(message.getText()));

                    try {
                        sendMsg(message, Weather.getWheater(message.getText(), model));
                    } catch (IOException e) {  
                    }

            }

        }else if(update.hasCallbackQuery()){ // если пользователь нажал на встройнную кнопку
            try {
                execute(new SendMessage().setText(
                        update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendMsg(Message message, String s) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString()); // получаем из сообщения чат айди
        sendMessage.setText(s); // текст который мы будем отправлять в метод
        setButtons(sendMessage); // подключили кнопки


        System.out.println(message.getChatId().toString());
        System.out.println(s);
        //sendMessage.setText(BotVajtersic.getPhrases(s)); // передали моему боту сообщение в класс
        // sendMessage.setReplyToMessageId(message.getMessageId());// на какое конкретное сообщение мы должны ответить
        try {
            sendMessage(sendMessage); // отправка сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);


        List<KeyboardRow> keyboard = new ArrayList<>();    // Создаем список строк клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow(); // Первая строчка клавиатуры
        keyboardFirstRow.add(new KeyboardButton(corona));// Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton(dollar));


        KeyboardRow keyboardSecondRow = new KeyboardRow();      // Вторая строчка клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Talks"));
        keyboardSecondRow.add(new KeyboardButton("Flip-Floper"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton(weather));

        KeyboardRow keyboardForthRow = new KeyboardRow();
        keyboardForthRow.add(new KeyboardButton("Films"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        keyboard.add(keyboardForthRow);

        replyKeyboardMarkup.setKeyboard(keyboard); // и устанваливаем этот список нашей клавиатуре
    }

    public static SendMessage sendInlineKeyBoardMessage(long chatId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("New");
        inlineKeyboardButton1.setCallbackData("Введите: Новинки");

        inlineKeyboardButton2.setText("Horrors");
        inlineKeyboardButton2.setCallbackData("Хочешь испугаться?: Ужасы");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Comedy").setCallbackData("Если охота посмеяться: Комедии"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Select genre ").setReplyMarkup(inlineKeyboardMarkup);
    }

    @Override
    public String getBotUsername() {
        return "*************";  // The name of your Bot 
    }


    @Override
    public String getBotToken() {
        return "*************************";  // your Token of Bot
    }


}