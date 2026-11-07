import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class BrowserOpener {

    public boolean openBrowser(int id) {
        String url = Constants.CONST_URL_2 + id;
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(URI.create(url));
                    return true;
                }
            }
        } catch (IOException e) {}
        return false;
    }
}
