import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int MAX_MSG_LENGTH = 100;

        //Данные чатов мессенджера
        String[] chatNames = {
                "Учебная группа",
                "Семья",
                "Команда проекта",
                "Техподдержка",
                "Спам-рассылка"
        };
        int[] unreadCounts = {3, 1, 0, 2, 5};
        boolean[] isOnline = {true, true, false, true, false};
        boolean[] isMuted = {false, false, false, false, true};
        boolean[] isBlocked = {false, false, false, false, true};

        //История сообщений
        String[][] chatMessages = new String[5][10];
        int[] messageCounts = {2, 2, 1, 1, 1};

        chatMessages[0][0] = "Кто сделал первую лабу по Java?";
        chatMessages[0][1] = "Сдаём в пятницу!";
        chatMessages[1][0] = "Привет, как дела в универе?";
        chatMessages[1][1] = "Не забудь купить хлеб";
        chatMessages[2][0] = "Репозиторий на GitHub создан";
        chatMessages[3][0] = "Ваш тикет #402 принят в работу";
        chatMessages[4][0] = "Выиграйте приз прямо сейчас!";

        //Статистика текущего пользователя
        int sentMessagesCount = 0;
        double[] sentTrafficKb = new double[20];
        boolean isRunning = true;

        System.out.println("==========================================");
        System.out.println("    Добро пожаловать в мессенджер Murk");
        System.out.println("==========================================");

        // Основной цикл меню (while)
        while (isRunning) {
            System.out.println("\n--- Главное меню ---");
            System.out.println("1. Показать все чаты");
            System.out.println("2. Показать непрочитанные чаты (без заглушённых)");
            System.out.println("3. Открыть чат и прочитать сообщения");
            System.out.println("4. Отправить сообщение в чат");
            System.out.println("5. Заблокировать / разблокировать чат");
            System.out.println("6. Поиск слова по всем сообщениям");
            System.out.println("7. Статистика профиля и исходящий трафик");
            System.out.println("0. Выйти из мессенджера");
            System.out.print("Выберите действие: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода: введите целое число от 0 до 7.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            //Оператор выбора switch
            switch (choice) {
                case 1:
                    System.out.println("\n--- Список всех чатов ---");
                    for (int i = 0; i < chatNames.length; i++) {
                        String onlineMark = isOnline[i] ? "Онлайн" : "Офлайн";
                        String muteMark = isMuted[i] ? " [Без звука]" : "";
                        String blockMark = isBlocked[i] ? " [ЗАБЛОКИРОВАН]" : "";
                        System.out.printf("%d. %-16s | %s | Непрочитано: %d%s%s%n",
                                (i + 1), chatNames[i], onlineMark, unreadCounts[i], muteMark, blockMark);
                    }
                    break;

                case 2:
                    //Цикл for + continue
                    System.out.println("\n--- Важные непрочитанные чаты ---");
                    boolean hasUnread = false;
                    for (int i = 0; i < chatNames.length; i++) {
                        if (unreadCounts[i] == 0 || isMuted[i]) {
                            continue;
                        }
                        System.out.printf("%d. %-16s | Непрочитано: %d%n",
                                (i + 1), chatNames[i], unreadCounts[i]);
                        hasUnread = true;
                    }
                    if (!hasUnread) {
                        System.out.println("Все важные сообщения прочитаны.");
                    }
                    break;

                case 3:
                    System.out.print("Введите номер чата (1-" + chatNames.length + "): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Ошибка: номер чата должен быть числом.");
                        scanner.nextLine();
                        break;
                    }
                    int readIdx = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (readIdx < 0 || readIdx >= chatNames.length) {
                        System.out.println("Ошибка: чата с таким номером не существует.");
                        break;
                    }

                    System.out.println("\n--- Чат: " + chatNames[readIdx] + " ---");
                    for (int m = 0; m < messageCounts[readIdx]; m++) {
                        System.out.println("  [" + (m + 1) + "] " + chatMessages[readIdx][m]);
                    }
                    if (unreadCounts[readIdx] > 0) {
                        unreadCounts[readIdx] = 0;
                        System.out.println("(Все сообщения отмечены как прочитанные)");
                    }
                    break;

                case 4:
                    System.out.print("Выберите номер чата для отправки (1-" + chatNames.length + "): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Ошибка: номер чата должен быть числом.");
                        scanner.nextLine();
                        break;
                    }
                    int sendIdx = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (sendIdx < 0 || sendIdx >= chatNames.length) {
                        System.out.println("Ошибка: чата с таким номером не существует.");
                        break;
                    }

                    if (isBlocked[sendIdx]) {
                        System.out.println("Ошибка: чат \"" + chatNames[sendIdx]
                                + "\" заблокирован. Отправка сообщений невозможна.");
                        break;
                    }

                    if (messageCounts[sendIdx] >= chatMessages[sendIdx].length) {
                        System.out.println("Ошибка: история этого чата переполнена (максимум 10 сообщений).");
                        break;
                    }

                    //Цикл do-while для валидации текста сообщения
                    String text;
                    boolean validText;
                    do {
                        System.out.print("Введите текст сообщения (от 1 до " + MAX_MSG_LENGTH + " символов): ");
                        text = scanner.nextLine().trim();
                        if (text.isEmpty()) {
                            System.out.println("Ошибка: сообщение не может быть пустым.");
                            validText = false;
                        } else if (text.length() > MAX_MSG_LENGTH) {
                            System.out.println("Ошибка: превышен лимит в " + MAX_MSG_LENGTH
                                    + " символов (введено: " + text.length() + ").");
                            validText = false;
                        } else {
                            validText = true;
                        }
                    } while (!validText);

                    //Отдельный блок кода { ... } для расчёта размера и типа сообщения
                {
                    int length = text.length();
                    double messageSizeKb = 0.50 + length * 0.01;
                    String sizeCategory;

                    //Лестница if-else-if
                    if (length <= 20) {
                        sizeCategory = "Короткое";
                    } else if (length <= 60) {
                        sizeCategory = "Среднее";
                    } else {
                        sizeCategory = "Длинное";
                    }

                    chatMessages[sendIdx][messageCounts[sendIdx]] = "Вы: " + text;
                    messageCounts[sendIdx]++;

                    if (sentMessagesCount < sentTrafficKb.length) {
                        sentTrafficKb[sentMessagesCount] = messageSizeKb;
                        sentMessagesCount++;
                    }

                    System.out.println("\nСообщение отправлено в чат \"" + chatNames[sendIdx] + "\"!");
                    System.out.printf("Тип: %s (%d симв.) | Расход трафика: %.2f КБ%n",
                            sizeCategory, length, messageSizeKb);
                }
                break;

                case 5:
                    System.out.print("Введите номер чата для блокировки/разблокировки (1-" + chatNames.length + "): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Ошибка: введите целое число.");
                        scanner.nextLine();
                        break;
                    }
                    int blockIdx = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (blockIdx < 0 || blockIdx >= chatNames.length) {
                        System.out.println("Ошибка: неверный номер чата.");
                        break;
                    }

                    //if-else переключение статуса блокировки
                    if (isBlocked[blockIdx]) {
                        isBlocked[blockIdx] = false;
                        System.out.println("Чат \"" + chatNames[blockIdx] + "\" разблокирован.");
                    } else {
                        isBlocked[blockIdx] = true;
                        System.out.println("Чат \"" + chatNames[blockIdx] + "\" заблокирован.");
                    }
                    break;

                case 6:
                    System.out.print("Введите слово для поиска по сообщениям: ");
                    String keyword = scanner.nextLine().trim().toLowerCase();
                    if (keyword.isEmpty()) {
                        System.out.println("Ошибка: поисковый запрос не может быть пустым.");
                        break;
                    }

                    //Вложенные циклы + break с меткой
                    boolean found = false;
                    searchLoop:
                    for (int c = 0; c < chatNames.length; c++) {
                        for (int m = 0; m < messageCounts[c]; m++) {
                            if (chatMessages[c][m].toLowerCase().contains(keyword)) {
                                System.out.println("Найдено первое совпадение в чате \"" + chatNames[c] + "\":");
                                System.out.println("  -> " + chatMessages[c][m]);
                                found = true;
                                break searchLoop;
                            }
                        }
                    }

                    if (!found) {
                        System.out.println("Сообщений со словом \"" + keyword + "\" не найдено.");
                    }
                    break;

                case 7:
                    //Цикл for-each для подсчёта суммарного исходящего трафика
                    double totalTrafficKb = 0.0;
                    for (double kb : sentTrafficKb) {
                        totalTrafficKb += kb;
                    }
                    String rank = getUserActivityRank(sentMessagesCount);
                    System.out.println("\n--- Статистика профиля ---");
                    System.out.println("Отправлено сообщений за сессию: " + sentMessagesCount);
                    System.out.printf("Исходящий трафик: %.2f КБ%n", totalTrafficKb);
                    System.out.println("Ранг активности: " + rank);
                    break;

                case 0:
                    System.out.println("Вы вышли из PolyChat. До встречи!");
                    isRunning = false;
                    scanner.close();
                    return;

                default:
                    System.out.println("Неизвестный пункт меню. Выберите число от 0 до 7.");
                    break;
            }
        }
    }

    //Вспомогательный метод с return и современным switch-выражением
    private static String getUserActivityRank(int sentCount) {
        return switch (sentCount) {
            case 0 -> "Наблюдатель";
            case 1, 2 -> "Собеседник";
            default -> "Активный участник";
        };
    }
}