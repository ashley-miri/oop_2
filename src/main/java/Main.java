public class Main {

    public static void main(String[] args) {
        try {
            while (true) {
                String reqest = ConsoleIO.readReqest("Введите запрос: ");
            }
        } catch (InputClosedException e) {
            ConsoleIO.outText("Поток ввода: " + e.getMessage());
        }
    }
}
