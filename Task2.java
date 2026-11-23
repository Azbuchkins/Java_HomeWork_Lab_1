public class Task2 {
    public static void main(String[] args) {
        //Константа
        final int MAX_MESSAGE_LENGTH = 4_096;

        //Целочисленные типы и разные виды литералов
        byte pinnedChatsCount = 3;
        short serverPort = 0775;
        int protocolColorHex = 0x2A_9D_8F;
        int notificationFlags = 0b0000_1101;
        long userId = 79_001_234_567L;
        char mentionPrefix = '@';
        char statusIcon = '\u2713';

        //Вещественные типы и литералы
        float networkPingMs = 24.5f;
        double cacheSizeMb = 1_284.75;
        double cloudQuotaBytes = 2.5e9;

        //Логические типы и литералы
        boolean isOnline = true;
        boolean isEndToEndEncrypted = true;
        boolean isDoNotDisturb = false;

        //Ключевое слово var
        var appName = "Murk";
        var userHandle = "student_dev";
        var activeChatsCount = 4;

        //Одномерные массивы
        String[] chatTitles = {
                "Учебная группа",
                "Семья",
                "Команда проекта",
                "Техподдержка"
        };
        int[] unreadMessages = {3, 1, 0, 2};
        boolean[] isChatMuted = {false, false, false, true};

        //Двумерный массив
        int[][] threeDayActivity = {
                {12, 8, 15},
                {5, 7, 4}
        };

        //Вывод сводной информации
        System.out.println("Мессенджер \"" + appName + "\"");
        System.out.println("Профиль: " + mentionPrefix + userHandle + " (ID: " + userId + ")");
        System.out.println("Статус онлайн: " + isOnline + " " + statusIcon
                + " | Шифрование: " + isEndToEndEncrypted
                + " | Не беспокоить: " + isDoNotDisturb);
        System.out.println("Закреплённых чатов: " + pinnedChatsCount + " | Порт (oct 0775 -> dec): " + serverPort);
        System.out.println("Цвет темы (hex 0x2A9D8F -> dec): " + protocolColorHex);
        System.out.println("Маска уведомлений (bin 0b1101 -> dec): " + notificationFlags);
        System.out.println("Пинг: " + networkPingMs + " мс | Кэш: " + cacheSizeMb + " МБ");
        System.out.println("Квота облака: " + cloudQuotaBytes + " байт | Лимит сообщения: " + MAX_MESSAGE_LENGTH + " симв.");
        System.out.println("\nАктивных чатов: " + activeChatsCount);
        System.out.println("Чат [0]: \"" + chatTitles[0] + "\" | Непрочитано: " + unreadMessages[0]
                + " | Без звука: " + isChatMuted[0]);
        System.out.println("Сообщений в чате [0] за 3-й день: " + threeDayActivity[0][2]);
    }
}