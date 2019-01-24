#### 四大核心对象

- StatementHandler：处理sql语句预编译，设置参数等相关工作；

- ParameterHandler：设置预编译参数用的

- ResultSetHandler：处理结果集

- TypeHandler：在整个过程中，进行数据库类型和javaBean类型的映射

  

#### 执行步骤

1. #####  获取sqlSessionFactory对象:

   解析文件的每一个信息保存在Configuration中，返回包含Configuration DefaultSqlSession；

   注意：【MappedStatement】：代表一个增删改查的详细信息

2. ##### 获取sqlSession对象

   返回一个DefaultSQlSession对象，包含Executor和Configuration;

   这一步会创建Executor对象；

3. ##### 获取接口的代理对象（MapperProxy）

   getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象

   代理对象里面包含了，DefaultSqlSession（Executor）

4. ##### 执行增删改查方法

   ##### 

#### 总结：

1. 根据配置文件（全局，sql映射）初始化出Configuration对象

   把配置文件的信息解析保存到Configureation对象中 ,返回包含Configureation的DefaultSqlSessionFactory

2. 创建一个DefaultSqlSession对象，

   他里面包含Configuration以及Executor（是用来增删改查的。根据全局配置文件中的defaultExecutorType创建出对应的Executor--可创建出 BatchExecutor/ReuseExecutor/SimpleExecutor等 ）

3. DefaultSqlSession.getMapper（）：拿到Mapper接口对应的MapperProxy；

   MapperProxy里面有（DefaultSqlSession）；

4. 执行增删改查方法：

   1. 调用DefaultSqlSession的增删改查（Executor）；

   2. 会创建一个StatementHandler对象。

      （同时也会创建出ParameterHandler和ResultSetHandler）

   3. 调用StatementHandler预编译参数以及设置参数值;

      使用ParameterHandler来给sql设置参数

   4. 调用StatementHandler的增删改查方法；

   5. ResultSetHandler封装结果

#### 注意：

​	四大对象每个创建的时候都有一个interceptorChain.pluginAll(parameterHandler);

