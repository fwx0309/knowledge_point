# springmvc乱码解决

**springmvc的中文乱码问题主要有以下几种情形：**

##### **页面传值到后台：**

- 工程编码（最后一开始建立工程就设置整个工程的编码）

- 页面的几种编码属性的设置

- get提交方式乱码的处理

- post提交方式乱码的处理


##### 后台到数据库乱码：

- 数据库连接字符串指定编码格式

- 数据库编码属性（也是一开始设置数据库的时候就要设置好，不然要重新建数据库）

##### **1.页面的几种编码属性** 

```html
//jsp页面编码，jsp文件本身的编码
pageEncoding="UTF-8"

//web页面显示的编码，jsp页面输出流在浏览器中显示的编码
contentType="text/html; charset=UTF-8" 

//web界面的输入编码，就是输入框中输入的字体编码。
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
```

##### 2.get提交方式乱码处理（一般是由于tomcat引起的，所以需要设置tomcat的编码）

改tomcat中server.xml中的port=“8080”，加上一个 URIEncoding=”utf-8”

##### **3.post提交方式乱码处理（在web.xml中设置编码过滤器）** 

```xml
<!-- 解决工程编码过滤器 -->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

##### **4.数据库连接字符串指定编码** 

```properties
jdbc.url=jdbc:mysql://localhost:3306/student?characterEncoding=UTF-8
```