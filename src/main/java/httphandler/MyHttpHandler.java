package httphandler;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.Annotations;
import burp.api.montoya.core.HighlightColor;
import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;

import static burp.api.montoya.http.handler.RequestToBeSentAction.continueWith;
import static burp.api.montoya.http.handler.ResponseReceivedAction.continueWith;
import static burp.api.montoya.http.message.params.HttpParameter.urlParameter;

/**
 * MyHttpHandler 实现 Burp Montoya 的 HttpHandler 接口，用于在
 * 1) 请求发送前：读取/修改请求并更新注释；
 * 2) 响应接收后：根据条件更新注释（如设置高亮）。
 *
 * 示例逻辑：
 * - 若请求为 POST：记录请求体到控制台，并添加备注。
 * - 向请求 URL 追加参数 foo=bar。
 * - 若发起该响应的请求包含 Content-Length 头，则将响应高亮为蓝色。
 */
class MyHttpHandler implements HttpHandler {
    // 日志接口，用于向 Burp 控制台输出调试/信息日志
    private final Logging logging;

    /**
     * 通过 MontoyaApi 获取 Logging，用于输出日志。
     *
     * @param api Montoya 的主入口，提供日志、HTTP 等能力
     */
    public MyHttpHandler(MontoyaApi api) {
        this.logging = api.logging();
    }

    /**
     * 在请求即将被发送时触发的回调。
     * - 获取并可能更新 Annotations（注释与高亮）。
     * - 若是 POST 请求：记录请求体到输出，并添加备注说明。
     * - 通过 withAddedParameters 为 URL 增加示例参数。
     * 返回时将修改后的请求和注释交由 Burp 继续处理。
     *
     * @param requestToBeSent 即将发送的请求对象
     * @return 继续发送的动作，包含可能修改的请求与注释
     */
    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent requestToBeSent) {
        // 取出现有注释对象（包含备注与高亮设置）
        Annotations annotations = requestToBeSent.annotations();

        // 如果是 POST 请求：添加备注并把请求体输出到控制台，便于调试
        if (isPost(requestToBeSent)) {
            annotations = annotations.withNotes("请求为 POST"); // 给该请求增加备注说明
            logging.logToOutput("检测到 POST 请求，打印请求体：");       // 记录提示信息
            logging.logToOutput(requestToBeSent.bodyToString());       // 记录请求体内容
           
            logging.raiseInfoEvent("检测到 POST 请求，打印请求体：");       // 记录提示信息
            logging.raiseInfoEvent(requestToBeSent.bodyToString());       // 记录请求体内容
            
        }

        // 修改请求：为 URL 追加一个示例参数 foo=bar
        HttpRequest modifiedRequest = requestToBeSent.withAddedParameters(urlParameter("foo", "bar"));

        // 返回：携带修改后的请求与更新后的注释，让 Burp 继续发送
        return continueWith(modifiedRequest, annotations);
    }

    /**
     * 在响应被接收后触发的回调。
     * - 获取并可能更新 Annotations。
     * - 若发起该响应的请求包含 Content-Length 头，则将响应高亮为蓝色，便于识别。
     *
     * @param responseReceived 已接收的响应对象
     * @return 继续处理的动作，包含可能更新后的注释
     */
    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived responseReceived) {
        // 取出现有注释对象
        Annotations annotations = responseReceived.annotations();

        // 如果发起该响应的请求带有 Content-Length 头，则高亮该响应为蓝色
        if (responseHasContentLengthHeader(responseReceived)) {
            annotations = annotations.withHighlightColor(HighlightColor.BLUE);
        }

        // 返回继续处理，并保留或更新的注释
        return continueWith(responseReceived, annotations);
    }

    /**
     * 判断请求方法是否为 POST（忽略大小写）。
     */
    private static boolean isPost(HttpRequestToBeSent httpRequestToBeSent) {
        return httpRequestToBeSent.method().equalsIgnoreCase("POST");
    }

    /**
     * 判断与该响应对应的请求是否包含 Content-Length 头。
     * 通过遍历请求头集合，执行名称不区分大小写的匹配。
     */
    private static boolean responseHasContentLengthHeader(HttpResponseReceived httpResponseReceived) {
        return httpResponseReceived
                .initiatingRequest()
                .headers()
                .stream()
                .anyMatch(header -> header.name().equalsIgnoreCase("Content-Length"));
    }
}