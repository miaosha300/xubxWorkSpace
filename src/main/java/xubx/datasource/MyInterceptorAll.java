package xubx.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.datasource
 * 类名称:     Interceptor
 * 类描述:     sql拦截器
 * 创建人:     xubx
 * 创建时间:   2020/3/25 9:42
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})

@Slf4j
public class MyInterceptorAll implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，
        // 然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();
        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        BoundSql boundSql = statementHandler.getBoundSql();

        //获取到原始sql语句
        String sql = boundSql.getSql();
        String mSql = sql + " where msisdn = '01518066117904'";
        log.error("sql: {}", mSql);
        //TODO 修改位置

//        //注解逻辑判断  添加注解了才拦截
//        Class<?> classType = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));
//        String mName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, mappedStatement.getId().length());
//        for (Method method : classType.getDeclaredMethods()) {
//            if (method.isAnnotationPresent(InterceptAnnotation.class) && mName.equals(method.getName())) {
//                InterceptAnnotation interceptorAnnotation = method.getAnnotation(InterceptAnnotation.class);
//                if (interceptorAnnotation.flag()) {
//                    mSql = sql + " limit 2";
//                }
//            }
//        }

        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, mSql);
        return invocation.proceed();

    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
