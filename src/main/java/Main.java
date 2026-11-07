import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ConsoleIO cio = new ConsoleIO();
        ApiWiki api = new ApiWiki();
        AnswerAnalysis answ = new AnswerAnalysis();
        BrowserOpener bro = new BrowserOpener();
        while (true) {
            try {
                ParseResult result = null;
                String request = cio.readReqest("Введите запрос: ");
                String json = api.workApi(request);
                List<ParseResult> response = answ.parse(json);
                if (response.isEmpty()) {
                    cio.outText("Ничего не найдено.");
                    continue;
                } else if (response.size() == 1) {
                    result = response.get(0);
                } else {
                    result = cio.browse(response);
                }
                if (!bro.openBrowser(result.getPageid())) {
                    cio.outText(
                        "Не удалось открыть браузер, вот ссылка " +
                            Constants.CONST_URL_2 +
                            result.getPageid()
                    );
                }
            } catch (InputClosedException e) {
                cio.outText("Поток ввода: " + e.getMessage());
                return;
            } catch (IOException | ApiException e) {
                cio.outText("Ошибка поиска: " + e.getMessage());
            }
        }
    }
}
