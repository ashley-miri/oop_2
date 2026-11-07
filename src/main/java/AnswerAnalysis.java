import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.List;

public class AnswerAnalysis {

    private final Gson gson = new Gson();

    private static class Query {

        private List<ParseResult> search;
    }

    private static class Response {

        private Query query;
    }

    public List<ParseResult> parse(String json) {
        Response response;
        try {
            response = gson.fromJson(json, Response.class);
        } catch (JsonSyntaxException e) {
            throw new ApiException("Сервер прислал некорректные данные.");
        }
        if (
            response == null ||
            response.query == null ||
            response.query.search == null
        ) {
            throw new ApiException("Некорректный ответ сервера.");
        }
        if (response.query.search == null) {
            throw new ApiException("По данному запросу ничего не найдено.");
        }
        return response.query.search;
    }
}
