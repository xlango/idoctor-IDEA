# idoctor
基于SSM的医院综合管理系统</br>
1.使用PageHelper分页</br>
2.Redis</br>
  (1)Redis运行：在根目录下执行redis-server.exe redis.windows.conf</br>
  (2) 涉及问题：在数据库数据更新后如何更新Redis？----在更新数据时同时删除Redis</br>
  (3)参考：https://blog.csdn.net/xsyl08/article/details/78773760</br>
3.整合swagger-ui</br>
  (1)maven引入所需jar包:</br>
     <artifactId>springfox-swagger-ui</artifactId></br>
     <artifactId>springfox-swagger2</artifactId></br>
自定义配置文件：/idoctor/src/main/java/com/idoctor/utils/SwaggerConfig.java</br>
4.使用MD5加密工具</br>
5.Mybatis批量操作</br>
  (1)我的方法：前端想后端传递List<>参数时采用传Json String方式，使用JSONArray.fromObject(str)在后端转换为Json对象</br>
  (2)映射文件操作sql使用<foreach>迭代数组或者List<>集合  </br>
  (3)批量更新参考：https://www.cnblogs.com/Jason-Xiang/p/6558334.html  </br>
6.国际化i18n
  (1)Spring核心配置文件配置bea:messageSource、CookieLocaleResolver  </br>
  (2)按照需求创建中文和英文两个资源文件，将它们放在新建文件夹messages下。 </br>
  (3)引入Spring标签库：</br>
           jsp <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> </br>
           html h5国际化标签 或利用模版引擎加载 </br>
7.OAtho 2.0实现第三方登录 <br>
  (1)用户在服务商授权页完成授权，获得code <br>
  (2)第三方应用取得code后访问服务商应用，获得access token <br>
  (3)第三方应用以access token为凭证到服务商处获取资源 <br>
8.shiro认证和授权管理<br>
  (1)shiro核心：Subject、SecurityManager、Realm（类似一个dao）  <br>
  (2)依赖jar包：<br>
    ---shiro-spring  <br>
    ---shiro-web     <br>
    ---aspectjweaver（注解）  <br>
  (3)Realm   <br>
    ---使用IniReam，配置文件：xxx.ini配置相关认证授权信息  <br>
    ---JdbcRealm结合数据库认证授权  <br>
    ---自定义Realm   <br>
  (4)构建SecurityManager环境    <br>
  (5)整合到Spring，核心配置文件注入对象：   <br>
       <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">  <br>
       		<property name="realm" ref="realm"></property>    <br>
       </bean>   <br>
       <bean id="realm" class="com.idoctor.realm.MyRealm">   <br>
       		<property name="credentialsMatcher" ref="credentialsMatcher"/>   <br>
       </bean>   <br>
       <bean id="credentialsMatcher" class="org.apache.shiro.authc.credential.HashedCredentialsMatcher"> <br>
       		<property name="hashAlgorithmName" value="md5"></property>  <br>
       		<property name="hashIterations" value="1"></property>  <br>
       </bean>  <br>
  (6)Subjec提交认证请求  <br>
     ---认证方法登录login()、logout()  <br>
     ---角色  <br>
     ---授权  <br>
  (7)注解实现：  <br>
     ---@RequiresRoles("admin")  <br>
     ---@RequiresPermissions("user:add")    <br>
  (8)Shiro会话管理
     ---SessionManager、SessionDAO
     ---Redis实现Session共享
     ---Redis是吸纳Session共享存在的问题
  (9)缓存管理（缓存角色数据和授权的）
     ---CacheManager、Cache
     ---Redis实现CacheManager
  (10)shiro实现自动登录
     ---
     