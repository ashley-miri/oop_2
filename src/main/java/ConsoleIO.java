import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    private final Scanner scan = new Scanner(System.in);

    public void outText(String text) {
        System.out.print("\n" + text);
    }

    public int readInt(String promt) {
        while (true) {
            outText(promt);
            try {
                if (!scan.hasNextLine()) {
                    throw new InputClosedException("Вы закрыли поток.");
                }
                return Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                outText("Введи значение принадлежащее множеству Z");
            }
        }
    }

    public String readReqest(String promt) {
        while (true) {
            outText(promt);
            if (!scan.hasNextLine()) {
                throw new InputClosedException("Вы закрыли поток.");
            }
            String str = scan.nextLine().trim();
            int len = str.length();
            if (len <= Constants.LEN_LIMIT && !str.isEmpty()) {
                return str;
            } else if (len > Constants.LEN_LIMIT) {
                outText("Строка превышает лимит " + Constants.LEN_LIMIT + ".");
            } else {
                outText("Строка пуста.");
            }
        }
    }

    public <T> T browse(List<T> list) {
        int page = 0;
        while (true) {
            for (int i = 1; i <= 5; i++) {
                if (5 * page + i - 1 > list.size() - 1) {
                    break;
                }
                outText(i + ") " + list.get(5 * page + i - 1));
            }
            outText("6)Предыдущая страница\n7)Следующая страница");
            int r = readInt("Ввод: ");
            switch (r) {
                case 1, 2, 3, 4, 5:
                    if (5 * page + r - 1 > list.size() - 1) {
                        outText("Некорректное значение, повторите ввод");
                        continue;
                    }
                    return list.get(5 * page + r - 1);
                case 6:
                    if (page == 0) {
                        outText(
                            "Это первая страница, предыдущей не существует"
                        );
                        break;
                    }
                    page--;
                    break;
                case 7:
                    if ((list.size() + 4) / 5 == page + 1) {
                        outText(
                            "Это последняя страница, следующей не существует"
                        );
                        break;
                    }
                    page++;
                    break;
                default:
                    outText("Некорректное значение, повторите ввод");
                    break;
            }
        }
    }
}
