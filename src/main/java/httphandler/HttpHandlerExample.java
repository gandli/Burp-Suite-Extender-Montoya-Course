package httphandler;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;

public class HttpHandlerExample implements BurpExtension{
    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("HTTP Handler Example");
        Logging logging = api.logging();
        logging.logToOutput("*** Montoya API tutorial - HttpHandlerExample loaded ***");

        //Register our http handler with Burp.
        api.http().registerHttpHandler(new MyHttpHandler(api));
    }
}
