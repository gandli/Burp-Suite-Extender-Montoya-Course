# Burp Suite 扩展开发学习

## 开发环境

参考： [手动设置您的扩展开发环境](https://portswigger.net/burp/documentation/desktop/extend-burp/extensions/creating/set-up/manual-setup)

- IDE：推荐使用 IntelliJ IDEA 社区版
  - macOS：`brew install intellij-idea-ce`
  - Windows：`scoop install extras/idea`
- JDK：推荐使用 Liberica JDK 21 Full
  - macOS：`brew install bell-sw/liberica/liberica-jdk21-full`
  - Windows：`scoop bucket add java && scoop install java/liberica21-full-jdk`

## 参考资料

- [Montoya API 仓库](https://github.com/PortSwigger/burp-extensions-montoya-api)
- [Montoya API 示例](https://github.com/PortSwigger/burp-extensions-montoya-api-examples)
- [Montoya API 文档（Javadoc）](https://portswigger.github.io/burp-extensions-montoya-api/javadoc/)
- [httpbin.org](https://httpbin.org/)：用于请求测试
- [Burp-Suite-Extender-Montoya-Course（GitHub 仓库）](https://github.com/federicodotta/Burp-Suite-Extender-Montoya-Course)
- [Extending Burp Suite for Fun and Profit — The Montoya Way（Part 1）](https://hnsecurity.it/blog/extending-burp-suite-for-fun-and-profit-the-montoya-way-part-1/)