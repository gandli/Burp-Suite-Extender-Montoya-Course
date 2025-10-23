// 定义一个包名，通常按照你的项目或公司规范来命名，这里为 "helloworld"
package helloworld;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;

public class HelloWorld implements BurpExtension{

    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("Hello world extension");

    }
}