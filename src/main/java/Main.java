public class Main {

    public static void main(String[] args) {
        try {
            while (true) {
                String reqest = Input.readReqest("Введите запрос: ");
            }
        } catch (InputClosedException e) {
            Input.outText("Поток ввода: " + e.getMessage());
        }
    }
}
