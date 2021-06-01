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
    /**
     * добавление смайлов из библеотеки https://www.webfx.com/tools/emoji-cheat-sheet/
     */

    public static String weather = "Погода " + EmojiParser.parseToUnicode(":partly_sunny:");
    public static String dollar = "Курс " + EmojiParser.parseToUnicode(":money_with_wings:");
    public static String corona = "Корона " + EmojiParser.parseToUnicode(":mask:");

    @Override
    public void onUpdateReceived(Update update) { /** метод для приема сообщений */

       /* String message = update.getMessage().getText(); // получаем сообщение от пользователя
        sendMsg(update.getMessage().getChatId().toString(), message); //@param update Содержит сообщение от пользователя.*/// по сути это и было зеркало

        Model model = new Model();
        Message message = update.getMessage();
        if (message != null && message.hasText()) { // если сообщение не пустое и имеет текст

            switch (message.getText()) { // в случае получение сообщения
                case "/help":
                    sendMsg(message, "Полный функционал Бота:" + "\n" +
                            "1. Введите число и он отправит Вам бинарный код." + "\n" +
                            "2. С помощью встроенной клавиатуры, Вы также можете узнать много интересного." + "\n" +
                            "3. Для сводки погоды в любом городе, Вам нужно ввести город английскими буквами." + "\n" +
                            "По вопросам сотрудничества или предложений свяжитесь с нами по номеру: 911.");
                    break;
                case "/setting":
                    sendMsg(message, "что ты хочешь настроить?! Тут и так все норм работает");
                    break;
                case "Фильмы":   // Подключили клавиатуру привязанную к сообщению
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
                        // sendMsg(message,"Город не найден");
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
        keyboardSecondRow.add(new KeyboardButton("Цитата"));
        keyboardSecondRow.add(new KeyboardButton("Перевертыш"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton(weather));

        KeyboardRow keyboardForthRow = new KeyboardRow();
        keyboardForthRow.add(new KeyboardButton("Фильмы"));

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
        inlineKeyboardButton1.setText("Новинки");
        inlineKeyboardButton1.setCallbackData("Введите: Новинки");

        inlineKeyboardButton2.setText("Ужасы");
        inlineKeyboardButton2.setCallbackData("Хочешь испугаться?: Ужасы");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Комедии").setCallbackData("Если охота посмеяться: Комедии"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Выберите жанр:").setReplyMarkup(inlineKeyboardMarkup);
    }

//    public synchronized void sendMsg(String chatId, String s) { // передали сообщение и чат Id
//
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.enableMarkdown(true);
//        sendMessage.setChatId(chatId);
//
//        System.out.println("Пользователь:" + chatId);// чат айди пользователя
//        System.out.println("Его сообщение: " + s);
//
//        sendMessage.setText(BotVajtersic.getPhrases(s)); // передаем ему введенное сообщение
//        setButtons(sendMessage); // кнопки
//
////        sendMessage.setText(s + ",Ваше число" + Integer.toBinaryString(bin));*/
//        try {
//            sendMessage(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    /** возращает имя бота */
    public String getBotUsername() {
        return "@Vajerticbot";
    }


    @Override
    /**возращает токен бота */
    public String getBotToken() {
        return "970256247:AAFzyIIcdNoLjV99i5Vi5JHf6ae7xRZyKR4";
    }


}