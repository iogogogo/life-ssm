# spring mvc 整合 mybatis、spring jdbc



## 新建maven项目

idea新建web项目参考：[IntelliJ IDEA 2019 创建maven web项目](https://iogogogo.github.io/2020/02/26/idea-web-project/)

idea创建多模块项目：[Maven构建Spring-Boot多模块项目](https://iogogogo.github.io/2019/07/27/spring-boot-multi-module-project/)



## 项目结构

```verilog
➜  life-ssm git:(master) ✗ tree
.
├── README.md
├── pom.xml
├── ssm-annotation
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   └── resources
│       └── test
│           └── java
├── ssm-common
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── iogogogo
│       │   │           ├── base
│       │   │           │   ├── BaseController.java
│       │   │           │   ├── BaseMapper.java
│       │   │           │   └── BaseResult.java
│       │   │           ├── consts
│       │   │           │   └── DateTimePattern.java
│       │   │           ├── domain
│       │   │           │   └── ResponseWrapper.java
│       │   │           └── util
│       │   │               └── JsonParse.java
│       │   └── resources
│       └── test
│           └── java
├── ssm-dist
│   └── ssm.sql
└── ssm-xml
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── iogogogo
    │   │   │           └── ssm
    │   │   │               ├── MainApplication.java
    │   │   │               ├── controller
    │   │   │               │   └── IndexController.java
    │   │   │               ├── dao
    │   │   │               │   ├── AccountDao.java
    │   │   │               │   ├── UserDao.java
    │   │   │               │   └── impl
    │   │   │               │       ├── AccountDaoImpl.java
    │   │   │               │       └── UserDaoImpl.java
    │   │   │               ├── entity
    │   │   │               │   ├── AccountEntity.java
    │   │   │               │   └── UserEntity.java
    │   │   │               ├── mapper
    │   │   │               │   ├── AccountMapper.java
    │   │   │               │   └── UserMapper.java
    │   │   │               └── service
    │   │   │                   ├── AccountService.java
    │   │   │                   ├── UserService.java
    │   │   │                   └── impl
    │   │   │                       ├── AccountServiceImpl.java
    │   │   │                       └── UserServiceImpl.java
    │   │   ├── resources
    │   │   │   ├── application-context.xml
    │   │   │   ├── jdbc.properties
    │   │   │   ├── logback.xml
    │   │   │   ├── mapper
    │   │   │   │   ├── AccountMapper.xml
    │   │   │   │   └── UserMapper.xml
    │   │   │   ├── mybatis-config.xml
    │   │   │   └── spring
    │   │   │       ├── druid.xml
    │   │   │       ├── spring-dao.xml
    │   │   │       ├── spring-datasource.xml
    │   │   │       ├── spring-mvc.xml
    │   │   │       ├── spring-service.xml
    │   │   │       └── tx.xml
    │   │   └── webapp
    │   │       ├── WEB-INF
    │   │       │   └── web.xml
    │   │       ├── index.html
    │   │       └── jsp
    │   └── test
    │       └── java
    │           └── com
    │               └── iogogogo
    │                   └── ssm
    │                       └── ApplicationTests.java
    └── ssm-xml.iml

46 directories, 42 files
```





## 添加maven依赖

- parent

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.iogogogo</groupId>
    <artifactId>life-ssm</artifactId>
    <packaging>pom</packaging>
    <version>1.0.0</version>

    <modules>
        <module>ssm-xml</module>
        <module>ssm-annotation</module>
        <module>ssm-common</module>
    </modules>

    <properties>
        <java.version>1.8</java.version>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <spring.version>5.2.3.RELEASE</spring.version>
        <mysql.version>5.1.37</mysql.version>
        <servlet.version>4.0.1</servlet.version>
        <druid.version>1.1.16</druid.version>
        <hikaricp.version>3.3.1</hikaricp.version>
        <mybatis.version>3.5.4</mybatis.version>
        <mybatis-spring.version>2.0.3</mybatis-spring.version>
        <fileupload.version>1.4</fileupload.version>
        <jackson.version>2.10.1</jackson.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- spring 事务-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 声明式的事务管理 基于aspectj tx.xml -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- jsp 依赖-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>${servlet.version}</version>
            <scope>provided</scope>
        </dependency>
        <!-- 文件上传-->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>${fileupload.version}</version>
        </dependency>

        <!-- 对象序列化-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>maven-net-cn</id>
            <name>Maven China Mirror</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

</project>

```

- ssm-xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>life-ssm</artifactId>
        <groupId>com.iogogogo</groupId>
        <version>1.0.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>war</packaging>

    <artifactId>ssm-xml</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.iogogogo</groupId>
            <artifactId>ssm-common</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
            <version>${hikaricp.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${mybatis.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>${mybatis-spring.version}</version>
        </dependency>
    </dependencies>

</project>

```





## `web.xml`文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">


    <display-name>spring-mvc</display-name>

    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
        <welcome-file>index.html</welcome-file>
    </welcome-file-list>

    <!-- 指定Spring Bean的配置文件所在目录。默认配置在WEB-INF目录下 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:application-context.xml</param-value>
    </context-param>

    <!-- spring字符编码过滤器start-->
    <filter>
        <!--① Spring 编码过滤器 -->
        <filter-name>encodingFilter</filter-name>
        <filter-class>
            org.springframework.web.filter.CharacterEncodingFilter
        </filter-class>
        <!--② 编码方式  -->
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <!--③ 强制进行编码转换 -->
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <!-- ② 过滤器的匹配 URL -->
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <!-- spring字符编码过滤器end-->

    <!--configure the setting of springmvcDispatcherServlet and configure the mapping -->
    <!-- Spring MVC配置 -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-mvc.xml</param-value>
        </init-param>
        <!-- <load-on-startup>1</load-on-startup> -->
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

</web-app>

```






## `spring-context`配置

### spring-mvc

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- scan the package and the sub package -->
    <!-- 使spring扫描包下的所有类，让标注spring注解的类生效 -->
    <context:component-scan base-package="com.iogogogo.ssm.controller"/>

    <!-- don't handle the static resource -->
    <mvc:default-servlet-handler/>

    <!-- if you use annotation you must configure following setting -->
    <mvc:annotation-driven>
        <!-- 对象序列化配置-->
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter"/>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="maxUploadSize" value="10485760000"/>
        <property name="maxInMemorySize" value="40960"/>
    </bean>

    <!-- configure the InternalResourceViewResolver -->
    <!-- 对转向页面的路径解析。prefix：前缀， suffix：后缀 -->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>

```



### spring-datasource

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 加载外部的配置文件 -->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <bean id="hikariDataSource" class="com.zaxxer.hikari.HikariDataSource">
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="poolName" value="TransferHikariCP"/>
        <property name="connectionTestQuery" value="SELECT 1"/>
        <!-- 生效超时 -->
        <property name="validationTimeout" value="3000"/>
        <!-- 连接只读数据库时配置为true， 保证安全 -->
        <property name="readOnly" value="false"/>
        <!-- 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒 -->
        <property name="connectionTimeout" value="60000"/>
        <!-- 一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟 -->
        <property name="idleTimeout" value="60000"/>
        <!-- 一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，建议设置比数据库超时时长少30秒，参考MySQL
            wait_timeout参数（show variables like '%timeout%';） -->
        <property name="maxLifetime" value="60000"/>
        <!-- 连接池中允许的最大连接数。缺省值：10；推荐的公式：((core_count * 2) + effective_spindle_count) -->
        <property name="maximumPoolSize" value="10"/>
    </bean>

    <!-- 配置事务管理器 -->
    <bean id="txManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 注入数据库连接池 -->
        <property name="dataSource" ref="hikariDataSource"/>
    </bean>

    <!-- 配置基于注解的声明式事务 -->
    <tx:annotation-driven transaction-manager="txManager"/>

    <!-- spring-jdbc-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="hikariDataSource"/>
    </bean>

    <!-- spring mybatis-->
    <!-- 配置SqlSessionFactory对象 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 注入数据库连接池 -->
        <property name="dataSource" ref="hikariDataSource"/>
        <!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!-- 扫描entity包 使用别名 -->
        <property name="typeAliasesPackage" value="com.iogogogo.ssm.entity"/>
        <!-- 扫描sql配置文件:mapper需要的xml文件 -->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>
    <!-- Mybatis-Spring 会自动为我们注册Mapper对应的MapperFactoryBean对象 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 注入sqlSessionFactory -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!-- Mapper接口所在包名，Spring会自动查找其下的Mapper -->
        <property name="basePackage" value="com.iogogogo.ssm.mapper"/>
    </bean>

</beans>

```



### 事务配置



#### spring-transaction 基于aspectj的aop方式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd

       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- (事务管理)transaction manager, use JtaTransactionManager for global tx -->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="hikariDataSource"/>
    </bean>

    <!-- 定义事务增强处理 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!-- read-only="true"属性设置提高事务处理性能 -->
            <tx:method name="find*" read-only="true"/>
            <tx:method name="search*" read-only="true"/>
            <tx:method name="query*" read-only="true"/>
            <tx:method name="select*" read-only="true"/>
            <tx:method name="list*" read-only="true"/>
            <tx:method name="get*" read-only="true"/>

            <!-- propagation 事务传播机制 -->
            <tx:method name="add*" propagation="REQUIRED"/>
            <tx:method name="save*" propagation="REQUIRED"/>
            <tx:method name="insert*" propagation="REQUIRED"/>

            <tx:method name="del*" propagation="REQUIRED"/>
            <tx:method name="delete*" propagation="REQUIRED"/>
            <tx:method name="remove*" propagation="REQUIRED"/>

            <tx:method name="update*" propagation="REQUIRED"/>
            <tx:method name="modify*" propagation="REQUIRED"/>
            <tx:method name="merge*" propagation="REQUIRED"/>

            <tx:method name="do*" propagation="REQUIRED"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>


    <!-- 定义事务处理切面 -->
    <aop:config>
        <aop:pointcut expression="execution(* com.iogogogo.ssm.service.impl.*.*(..))" id="pointcut"/>
        <!-- 将事务增强与切入点组合 -->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut"/>
    </aop:config>

</beans>

```



#### 基于`@Transactional`注解方式

##### 配置事务注解扫描

```xml
<!-- 配置基于注解的声明式事务 -->
<tx:annotation-driven transaction-manager="txManager"/>
```

##### 需要事务处理加上注解

```java
@Override
@Transactional
public void transfer(String in, String out, float money) {
  log.info("in:{} out:{} money:{}", in, out, money);
  accountMapper.inMoney(in, money);
  // int i = 1 / 0;
  accountMapper.outMoney(out, money);
}
```

