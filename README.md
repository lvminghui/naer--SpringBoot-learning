# 项目介绍

naer是一个基于Spring Boot2.X的后台权限管理系统，项目采用 SpringBoot+ Thymeleaf 开发，是本人的springboot练手项目，提供纯净的权限管理功能，可作为开发项目的脚手架，作为基础项目。
 
   

## 项目技术栈

### 后端技术栈

1. Spring Boot 2.1.4
2. Spring Security 5.1.5
3. Mybatis 3.5.1
4. MySQL 5.6
5. Logback 1.2.3
6. Druid 1.1.10
7. Swagger 2.9.2
8. Lombok 1.18.6
9. ...

### 前端技术栈

1. X-admin 2.2
2. Thymeleaf 3.0.11
3. Layui 2.5.3
4. ajax

### 项目效果图
![Image text](https://github.com/lvminghui/naer--SpringBoot-learning/blob/master/image/%E7%A4%BA%E4%BE%8B1.PNG)

### 分配权限页面效果
  ![Image text](https://github.com/lvminghui/naer--SpringBoot-learning/blob/master/image/%E7%A4%BA%E4%BE%8B2%20.PNG)

#### 内置功能
1. 用户管理：用户查询、添加用户、修改用户、用户角色设置、删除用户；
2. 角色管理：角色查询、添加角色、修改角色、角色菜单权限配置、删除角色；
3. 菜单管理：菜单查询、添加菜单、修改菜单、删除菜单；
4. 登录、登出：基于Spring Security的认证和授权；
5. Druid数据源监控功能；
6. Swagger接口文档功能；
7. 修改密码；
8. 代码自动生成：根据数据表以及自定义模板自动生成html、controller、service、serviceImpl、dao、mapper.xml文件；

## 快速部署

1. 克隆源代码并使用Intelij IDEA导入项目代码；
2. Intelij IDEA中安装Lombok插件
3. 将'/resources'目录下的'naer.sql'导入MySQL数据库；
4. 修改'/resources'目录下的'application.yml'文件中的数据源配置，改为你自己的MySQL环境:

```yml
url: jdbc:mysql://127.0.0.1:3306/umi-base?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=UTC
username: root
password: root
```

5. 启动项目，访问"http://localhost:8080",输入admin/admin即可登陆成功。



**有问题可以提出，我会解答**

#### 如有帮助，请随手点击右上角的star，非常感谢。



