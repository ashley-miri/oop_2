import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ConsoleIO cio = new ConsoleIO();
        ApiWiki api = new ApiWiki();
        try {
            while (true) {
                String request = cio.readReqest("Введите запрос: ");
                String response = api.workApi(request);
            }
        } catch (InputClosedException e) {
            cio.outText("Поток ввода: " + e.getMessage());
            return;
        } catch (IOException e) {
            cio.outText(e.getMessage());
        } catch (ApiException e) {
            cio.outText(e.getMessage());
        }
    }
}
