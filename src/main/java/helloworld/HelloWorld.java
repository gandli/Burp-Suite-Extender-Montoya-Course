// 包说明：helloworld 为示例扩展所在的包名，可根据团队规范调整
package helloworld;

// Montoya API：Burp 的扩展开发接口集合
import burp.api.montoya.BurpExtension; // 扩展接口，Burp 加载时会调用实现类的生命周期方法
import burp.api.montoya.MontoyaApi;     // 扩展的主入口，提供对 Burp 各子系统（日志、HTTP、UI 等）的访问
import burp.api.montoya.logging.Logging; // 日志子系统接口，用于输出到控制台、错误流和 Alerts

/**
 * HelloWorld 扩展示例
 *
 * 主要功能：
 * - 通过 Montoya API 的 Logging 接口输出不同级别的日志
 * - 在 Burp 的 Alerts 面板中展示信息/调试/错误/致命级别的事件
 * - 演示扩展初始化与异常处理的基本流程
 *
 * 使用说明：
 * - 将编译生成的 JAR 通过 Burp 的 Extender → Extensions 加载
 * - Burp 在加载扩展时会调用 initialize(MontoyaApi api) 方法
 */
public class HelloWorld implements BurpExtension {

    /**
     * 扩展初始化入口：Burp 加载扩展后首先调用该方法。
     *
     * @param api Montoya 主接口，提供访问日志、HTTP、UI 等功能的能力
     */
    @Override
    public void initialize(MontoyaApi api) {
        // 设置扩展在 Burp 中显示的名称（Extender 列表可见）
        api.extension().setName("Hello world extension");

        // 获取日志接口，用于输出到不同通道（输出流、错误流、Alerts 面板）
        Logging logging = api.logging();

        // 输出到 Burp 的输出流（Extender → Output 标签可见），适合一般信息
        logging.logToOutput("Hello output.");

        // 输出到 Burp 的错误流（Extender → Errors 标签可见），适合警告/错误信息
        logging.logToError("Hello error.");

        // 在 Burp 的 Alerts 面板中展示不同级别的事件
        logging.raiseInfoEvent("Hello info event.");      // 信息级别：一般提示
        logging.raiseDebugEvent("Hello debug event.");    // 调试级别：开发/排查问题
        logging.raiseErrorEvent("Hello error event.");    // 错误级别：需要关注的问题
        logging.raiseCriticalEvent("Hello critical event."); // 致命级别：严重问题

        // 演示异常处理：抛出并捕获运行时异常，将堆栈与消息输出到错误流
        try {
            throw new RuntimeException("Hello exception.");
        } catch (RuntimeException e) {
            // 将异常记录到错误输出，第二个参数为异常对象，Burp 会显示堆栈信息，便于定位问题
            logging.logToError("Hello thrown exception.", e);
        }
    }
}