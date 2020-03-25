package xubx.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.*;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.datasource
 * 类名称:     Interceptor
 * 类描述:     sql拦截器
 * 创建人:     xubx
 * 创建时间:   2020/3/25 9:42
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class})})
@Slf4j
public class MyInterceptor implements Interceptor {

    private HashMap columnMap = new HashMap();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        // 针对insert去除key-value值
        Map<String, String> insertKeyMap = new HashMap<>();
        Object returnValue;
        //获取sql语句
        String sql = showSql(configuration, boundSql, insertKeyMap);

        sql = sql.replaceAll("\'", "").replace("\"", "");
        List<String> value = new ArrayList<>();
        List<String> columns = getColumns(sql, value);
        for (int i = 0; i < columns.size(); i++) {
            columnMap.put(columns.get(i), value.get(i));
        }

        //执行结果
        returnValue = invocation.proceed();
        log.error(sql + "\r\n执行结果:" + returnValue);
        return returnValue;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }


    private String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof java.sql.Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return java.util.regex.Matcher.quoteReplacement(value);
    }

    public String showSql(Configuration configuration, BoundSql boundSql, Map<String, String> insertKeyMap) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
                String propertyName = parameterMappings.get(0).getProperty();
                insertKeyMap.put(propertyName, getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                        insertKeyMap.put(propertyName, obj.toString());
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                        insertKeyMap.put(propertyName, obj.toString());
                    }
                }
            }
        }
        return sql;
    }

    private List<String> getColumns(String s, List<String> value) {
        if (!s.toLowerCase().contains("where")) {
            return null;
        }
        int start = s.toLowerCase().indexOf("where");
        List<String> column = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                if (stringBuilder.length() != 0 && s.charAt(i) == '=') {
                    column.add(stringBuilder.toString());
                    addValue(s, i, value);
                    stringBuilder = new StringBuilder();
                    continue;
                }
                if (s.charAt(i - 1) == ' ' && s.charAt(i) != '=' && stringBuilder.length() != 0) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(s.charAt(i));
            }
        }
        return column;
    }

    private static int addValue(String s, int i, List<String> value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = i + 1; j < s.length(); j++) {
            if (s.charAt(j) != ' ') {
                stringBuilder.append(s.charAt(j));
            }
            if ((stringBuilder.length() > 0 && s.charAt(j) == ' ') || (stringBuilder.length() > 0
                    && j == s.length() - 1)) {
                value.add(stringBuilder.toString());
                return j;
            }
        }
        return i;
    }
}
