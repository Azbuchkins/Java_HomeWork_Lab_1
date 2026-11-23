public class Task3 {
    public static void main(String[] args) {
        System.out.println("Лабораторная работа №1. Задание 3: Все операции Java\n");

        //1. Арифметические операции
        int textMessages = 45;
        int voiceMessages = 12;
        int totalMessages = textMessages + voiceMessages;
        int deletedMessages = 7;
        int remainingMessages = totalMessages - deletedMessages;
        int bytesPerMessage = 128;
        int totalBytes = remainingMessages * bytesPerMessage;
        int fullPackets = totalBytes / 1024;
        int tailBytes = totalBytes % 1024;
        int unreadDelta = -deletedMessages;
        int positivePackets = +fullPackets;

        System.out.println("1. Арифметика:");
        System.out.println("Всего сообщений: " + totalMessages + ", после удаления: " + remainingMessages);
        System.out.println("Трафик: " + totalBytes + " байт = " + fullPackets + " полных КБ и " + tailBytes + " байт");
        System.out.println("Унарные операции: " + unreadDelta + " и " + positivePackets);

        //2. Инкремент и декремент
        int unreadCount = 5;
        System.out.println("\n2. Инкремент и декремент (исходно unreadCount = " + unreadCount + "):");
        System.out.println("Постфиксный unreadCount++ -> " + (unreadCount++) + " (после: " + unreadCount + ")");
        System.out.println("Префиксный  ++unreadCount -> " + (++unreadCount));
        System.out.println("Постфиксный unreadCount-- -> " + (unreadCount--) + " (после: " + unreadCount + ")");
        System.out.println("Префиксный  --unreadCount -> " + (--unreadCount));

        //3. Составное арифметическое присваивание - =, +=, -=, *=, /=, %=
        int storageKb = 500;
        storageKb += 300;
        storageKb -= 100;
        storageKb *= 2;
        storageKb /= 4;
        storageKb %= 100;
        System.out.println("\n3. Арифметическое присваивание (итог storageKb): " + storageKb);

        //4. Операции сравнения - ==, !=, >, <, >=, <=
        int currentChars = 120;
        int maxChars = 100;
        System.out.println("\n4. Операции сравнения (currentChars = " + currentChars + ", maxChars = " + maxChars + "):");
        System.out.println("currentChars == maxChars: " + (currentChars == maxChars));
        System.out.println("currentChars != maxChars: " + (currentChars != maxChars));
        System.out.println("currentChars > maxChars:  " + (currentChars > maxChars));
        System.out.println("currentChars < maxChars:  " + (currentChars < maxChars));
        System.out.println("currentChars >= maxChars: " + (currentChars >= maxChars));
        System.out.println("currentChars <= maxChars: " + (currentChars <= maxChars));

        //5. Логические операции - &&, ||, !, &, |, ^
        boolean isOnline = true;
        boolean isBlocked = false;
        boolean isMuted = true;
        System.out.println("\n5. Логические операции:");
        System.out.println("Онлайн и не заблокирован (&&): " + (isOnline && !isBlocked));
        System.out.println("Заблокирован или без звука (||): " + (isBlocked || isMuted));
        System.out.println("Не без звука (!):              " + (!isMuted));
        System.out.println("Полное И (&):                  " + (isOnline & isBlocked));
        System.out.println("Полное ИЛИ (|):                " + (isOnline | isBlocked));
        System.out.println("Исключающее ИЛИ (^):           " + (isOnline ^ isMuted));

        //6. Поразрядные операции - &, |, ^, ~, <<, >>, >>>
        //Смысловое применение: битовая маска прав участника группового чата
        int permRead = 0b0001;
        int permWrite = 0b0010;
        int permMedia = 0b0100;
        int permAdmin = 0b1000;

        int userPermissions = permRead | permWrite;
        boolean canWrite = (userPermissions & permWrite) != 0;
        int toggledPerms = userPermissions ^ permMedia;
        int invertedPerms = ~userPermissions;

        //Операции битового сдвига
        int packetHeader = -32;
        int leftShift = permAdmin << 1;
        int rightShift = packetHeader >> 2;
        int unsignedRightShift = packetHeader >>> 2;

        //Составное поразрядное присваивание - &=, |=, ^=, <<=, >>=, >>>=
        int mask = 0b1010;
        mask |= 0b0101;
        mask &= 0b1100;
        mask ^= 0b0110;
        mask <<= 1;
        mask >>= 2;
        mask >>>= 1;

        System.out.println("\n6. Поразрядные операции:");
        System.out.println("Права (|): " + Integer.toBinaryString(userPermissions)
                + ", может писать (&): " + canWrite);
        System.out.println("После XOR (^): " + Integer.toBinaryString(toggledPerms)
                + ", инверсия (~): " + invertedPerms);
        System.out.println("Сдвиги: 8 << 1 = " + leftShift
                + ", -32 >> 2 = " + rightShift
                + ", -32 >>> 2 = " + unsignedRightShift);
        System.out.println("Итог поразрядного присваивания (&=, |=, ^=, <<=, >>=, >>>=): " + mask);

        //7. Тернарный оператор (?:), приоритет скобок (), приведение типов и instanceof
        String userStatus = isOnline ? "В сети" : "Был(а) недавно";
        double avgMsgPerDay = (double) (textMessages + voiceMessages) / 3;
        Object lastMessage = "Привет! Встречаемся на паре?";
        boolean isTextMessage = lastMessage instanceof String;

        System.out.println("\n7. Тернарный оператор, приведение типов и instanceof:");
        System.out.println("Статус (?:): " + userStatus);
        System.out.printf("Среднее сообщений в день: %.2f%n", avgMsgPerDay);
        System.out.println("lastMessage instanceof String: " + isTextMessage);
    }
}