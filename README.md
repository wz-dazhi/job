##### auto-job-admin #####
1. 导入db数据脚本文件
2. 修改auto-job-admin.properties文件中的数据库配置.
3. 打包部署运行

##### job-springboot #####
+ springboot编写job的Demo,可以根据Demo编写自己的job. 
+ 修改application.yml中的数据库配置
+ 修改job相关配置
++ job.executor.appname=job-springboot # 当前job的项目名称
++ job.executor.port=10020 # 当前job所占的端口
++ job.admin.addresses=http://127.0.0.1:9090/auto-job-admin # job管理平台访问地址
