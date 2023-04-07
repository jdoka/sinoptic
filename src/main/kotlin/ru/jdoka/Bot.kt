package ru.jdoka

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update


class Bot : TelegramLongPollingBot() {

    override fun getBotToken() = BOT_TOKEN

    override fun getBotUsername() = BOT_USERNAME

    private val storage = SearchStorage()

    override fun onUpdateReceived(update: Update?) {
        //Извлекаем из объекта сообщение пользователя
        val inMess: Message = update!!.message
        //Достаем из inMess id чата пользователя
        val chatId: String = inMess.getChatId().toString()
        //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
        val response: String = parseMessage(inMess.getText())
        //Создаем объект класса SendMessage - наш будущий ответ пользователю
        val outMess = SendMessage()

        //Добавляем в наше сообщение id чата а также наш ответ
        outMess.chatId = chatId
        outMess.text = response

        //Отправка в чат

        //Отправка в чат
        execute(outMess)
    }

    fun parseMessage(textMsg: String): String {
        val response: String

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        response = when (textMsg) {
            "/start" -> "Приветствую, бот знает много зверей. Жми хочуЗверя, чтобы получить случайную из них"
            "хочуЗверя" -> storage.getRandomSearch()
            else -> "Ну и все тогда"
        }
        return response
    }

    private companion object {
        private val BOT_USERNAME = "search125Bot"
        private val BOT_TOKEN = "5950854226:AAHjorVyXVNI1TEBubasHtV2MiZkGdupsEw"
    }
}