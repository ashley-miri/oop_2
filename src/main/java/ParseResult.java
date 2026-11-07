public class ParseResult {

    private int pageid;
    private String title;

    public int getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
