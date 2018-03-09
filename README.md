   jpf是为中欣的泛金融业务搭建的前段应该平台，工程基于maven构建,
前端使用springMVC, 组件bean管理使用spring, 数据库层框架使用mybatis.
工程大致结构说明:
jpf ----->父工程, 用于管理各个模块
    jpf-api -----> 业务接口, com.joiest.jpf.dto定义接口请求与返回对象,
           com.joiest.jpf.common.dto.JpfRequestDto是接口请求父类, 用于定义公用请求信息,
           com.joiest.jpf.common.dto.JpfResponseDto接口返回父类, 用于定义返回码等公共返回信息
    jpf-service -----> 业务实现
    jpf-dao -----> 数据访问层, mybatis-generator生成mappper在com.joiest.jpf.dao.repository.mapper.generate下,
                             自定义mappper在com.joiest.jpf.dao.repository.mapper.custom
    jpf-domain -----> 领域对象, 其中po为数据库访问对象
    jpf-common -----> 公共部分, 包括全局异常, 常量, 工具类,
                      com.joiest.jpf.common.constant下定义常量,
                      com.joiest.jpf.common.exception.JpfException是全局异常,
                      com.joiest.jpf.common.exception.JpfErrorInfo定义异常类型以及相关说明
                      com.joiest.jpf.common.util.JsonUtils是json转换工具,
                      com.joiest.jpf.common.util.OkHttpUtils是http通信工具,
                      com.joiest.jpf.common.util.DateUtils是日期工具,

    jpf-web -----> 对外提供http服务的web层次,
                      com.joiest.jpf.web.resolver.MyHandlerExceptionResolver 提供全局异常处理

    可以根据业务需要, 将 jpf-web 分出来, 比如针对管理的拆分为manage-web, 针对商户服务拆分有merch-web,
    类似的jpf-service也可以根据后续业务演进进行拆分, 前期可以先根据业务把package规划好.

    日志使用log4j2, 配置位于 jpf-web/src/main/resources/log4j2.xml.

sys_para_info.sql 是demo数据库表, 使用 mybatis-generator-utils生成相应的mapper, po, xml等,
参考mybatis-generator-utils中src/main/resources/jpf.xml配置.

