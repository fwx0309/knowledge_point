# Spring

### Bean的作用范围

> 当通过spring容器创建一个Bean实例时，不仅可以完成Bean实例的实例化，还可以为Bean指定特定的作用域。Spring支持如下5种作用域： 

- singleton：单例模式，在整个Spring IOC容器中，使用singleton定义的Bean将只有一个实例
- prototype：原型模式，每次通过容器的getBean方法获取prototype定义的Bean时，都将产生一个新的Bean实例
- request：对于每次HTTP请求，使用request定义的Bean都将产生一个新实例，即每次HTTP请求将会产生不同的Bean实例。只有在Web应用中使用Spring时，该作用域才有效
- session：对于每次HTTP Session，使用session定义的Bean豆浆产生一个新实例。同样只有在Web应用中使用Spring时，该作用域才有效
- globalsession：每个全局的HTTP Session，使用session定义的Bean都将产生一个新实例。典型情况下，仅在使用portlet context的时候有效。同样只有在Web应用中使用Spring时，该作用域才有效

------



### 面试问题

> 曾经面试的时候有面试官问我spring的controller是单例还是多例，结果
> 我的回答当然是多例，要不然controller类中的非静态变量如何保证是线程安全的，这样想起似乎是对的，但是不知道（主要是我没看过spring的源码，不知道真正的内在意图）为什么spring的controller是单例的。

先看看spring的bean作用域有几种，分别有啥不同。

##### spring bean作用域有以下5个：

- singleton:单例模式，当spring创建applicationContext容器的时候，spring会欲初始化所有的该作用域实例，加上lazy-init就可以避免预处理；
- prototype：原型模式，每次通过getBean获取该bean就会新产生一个实例，创建后spring将不再对其管理；

====下面是在web项目下才用到的===

- request：搞web的大家都应该明白request的域了吧，就是每次请求都新产生一个实例，和prototype不同就是创建后，接下来的管理，spring依然在监听
- session:每次会话，同上
- global session:全局的web域，类似于servlet中的application

##### 为什么spring要默认是单例呢？原因有二：

1. 为了性能。
   - 这个不用废话了，单例不用每次都new，当然快了。
2. 不需要多例。
   - 不需要实例会让很多人迷惑，因为spring mvc官方也没明确说不可以多例。
   - 我这里说不需要的原因是看开发者怎么用了，<u>如果你给controller中定义很多的属性，那么单例肯定会出现竞争访问了</u>。
   -  因此，只要controller中不定义属性，那么单例完全是安全的。

> 从此可见，单例是不安全的，会导致属性重复使用。
>

最佳实践：

1. 不要在controller中定义成员变量。
2. 万一必须要定义一个非静态成员变量时候，则通过注解 <u>**@Scope("prototype")**</u>，将其设置为多例模式。