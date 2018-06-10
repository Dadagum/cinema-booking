# CinemaBooking
uml第四次实验，电影预订系统。当作练手的小项目，实验要求中说明默认是登陆状态，也就是说没有登陆系统，为了方便也没有实现后台的管理系统，有很多细节也没有注意，单纯的练手项目。

## 概述
1. 前端：html+css+jqery+handlebars+bootstrap。由于我不懂前端，所以对于前端代码及其混乱，可读性差，所有的js代码和渲染的模板都直接在home.html中，这种做法实在不可取。handlebars是一个比较简单的渲染引擎，前端使用ajax调用后端提供的api，返回json，然后再进行渲染。至于ui方面用到bootstrap，为了美观直接在网上找了一个模板，然后经过适当的修改适应自身需求，外观还是看得过去的。
2. 后端：Spring+SpringMVC+Mysql。后端提供restful的api，并没有用到jsp作为视图，前后端分离了。数据库用的是Mysql，使用Spring封装过的JdbcTemplate进行简单的增删改查。后端返回的json格式为message，data，status。

## 基本功能描述
- 查看所有电影信息。
- 选择感兴趣的电影，选择该电影的时间段，选择座位号。
- 生成订单。
- 查看个人所有订单。

## 所需环境
- tomcat(我所用的版本为9.0.8)
- maven(我所用的版本为3.5.3)，项目用到许多依赖，如Spring和SpringMVC相关依赖，jackson(支持json)，数据库方面

## 项目结构
- bean包：存放java bean
- controller：控制器，提供api，里面注释比较详细，不细说了。
- service：业务逻辑层，定义了相关接口，impl为这些接口的具体实现，接口中注释也比较详细。
- dto：定义了返回的json的结构，另外还有一些根据实际需求返回给前端的数据格式。
- exception：异常定义，根据实际情况自定义异常，继承自RuntimeException类，这是因为Spring中事务回滚是在运行时异常抛出时。
- resources资源目录：spring文件夹定义了Spring中的配置文件,sql文件夹包含该实验的建表语句以及初始化语句，jdbc.properties是数据库的配置文件。
- webapp：许多静态资源文件(css,js,img...)，还有主页面home.html,以及WEB-INF下web.xml配置文件。
- pom.xml：maven配置文件。
