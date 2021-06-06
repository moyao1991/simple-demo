package com.moyao.demo.infra.rpc.db.config;

import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableNameInnerInterceptor extends DynamicTableNameInnerInterceptor implements InnerInterceptor {

    private final TableNameHandler tableNameHandler;

    protected String changeTable(String sql) {
        TableNameParser parser = new TableNameParser(sql);
        List<TableNameParser.SqlToken> names = new ArrayList<>();
        parser.accept(names::add);
        StringBuilder builder = new StringBuilder();
        int last = 0;
        for (TableNameParser.SqlToken name : names) {
            int start = name.getStart();
            if (start != last) {
                builder.append(sql, last, start);
                String value = name.getValue();
                TableNameHandler handler = getTableNameHandler(value);
                builder.append(handler.dynamicTableName(sql, value));
            }
            last = name.getEnd();
        }
        if (last != sql.length()) {
            builder.append(sql.substring(last));
        }
        return builder.toString();
    }

    private TableNameHandler getTableNameHandler(String tableName){
        if(getTableNameHandlerMap() == null ||
                getTableNameHandlerMap().get(tableName) == null){
            return tableNameHandler;
        }
       return getTableNameHandlerMap().get(tableName);

    }
}
