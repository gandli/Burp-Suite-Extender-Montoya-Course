# Burp Suite 扩展开发学习 🚀

## 开发环境 🛠️

参考： [手动设置您的扩展开发环境](https://portswigger.net/burp/documentation/desktop/extend-burp/extensions/creating/set-up/manual-setup)

- IDE：推荐使用 IntelliJ IDEA 社区版 💡
  - macOS：`brew install intellij-idea-ce`
  - Windows：`scoop install extras/idea`
- JDK：推荐使用 Liberica JDK 21 Full ☕️
  - macOS：`brew install bell-sw/liberica/liberica-jdk21-full`
  - Windows：`scoop bucket add java && scoop install java/liberica21-full-jdk`

## 调试

### IDEA ce 调试

```bash
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address="*:5005" -jar "/Applications/Burp Suite Community Edition.app/Contents/Resources/app/burpsuite_community.jar"
```

![快捷调试](IDEA调试.png)

### vscode 调试

.vscode/launch.json

```json
{
    // 使用 IntelliSense 了解相关属性。 
    // 悬停以查看现有属性的描述。
    // 欲了解更多信息，请访问: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Attach to Burp on 5005",
            "request": "attach",
            "hostName": "127.0.0.1",
            "port": 5005,
            "preLaunchTask": "Start Burp JDWP 5005"
        }
    ]
}
```

.vscode/tasks.json

```json
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Start Burp JDWP 5005",
      "type": "process",
      "command": "java",
      "args": [
        "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=127.0.0.1:5005",
        "-jar",
        "/Applications/Burp Suite Community Edition.app/Contents/Resources/app/burpsuite_community.jar"
      ],
      "options": {
        "cwd": "${workspaceFolder}"
      },
      "problemMatcher": []
    }
  ]
}
```

## 参考资料 📚

- 🧩 [Montoya API（GitHub 仓库）](https://github.com/PortSwigger/burp-extensions-montoya-api)
- 🧪 [Montoya API 示例（GitHub 仓库）](https://github.com/PortSwigger/burp-extensions-montoya-api-examples)
- 📖 [Montoya API 文档（Javadoc）](https://portswigger.github.io/burp-extensions-montoya-api/javadoc/)
- 🌐 [httpbin.org](https://httpbin.org/)：用于请求测试
- 🎓 [Burp-Suite-Extender-Montoya-Course（GitHub 仓库）](https://github.com/federicodotta/Burp-Suite-Extender-Montoya-Course)
- 📝 [Extending Burp Suite for Fun and Profit — The Montoya Way（Part 1）](https://hnsecurity.it/blog/extending-burp-suite-for-fun-and-profit-the-montoya-way-part-1/)

## 仓库分支 🗂️

- Hello World：在 Burp 的多个位置打印输出 🗣️
- HttpHandler：演示在 Burp 工具中的请求处理与操作 🔄
