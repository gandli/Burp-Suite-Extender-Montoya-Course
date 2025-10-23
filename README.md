HTTP Handler Example Extension
============================

###### Demonstrates performing various actions on requests passing through any tool in Burp

 ---

The extension works as follows:
- It registers an HTTP handler
- For outgoing request messages:
    - If the request is a `POST` request:
        - The body of the request is logged to output
        - A comment is added to the request
    - A URL parameter is added to the request
- For incoming response messages:
    - If the corresponding request contained a `Content-Length` header, a `BLUE` highlight is added


## debug for vscode

.vscode/launch.json

```
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

```
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
