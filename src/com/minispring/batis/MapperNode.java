package com.minispring.batis;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-18 12:33
 */
public class MapperNode {
    String namespace;
    String id;
    String parameterType;
    String resultType;
    String sql;
    String parameter;

    public String getNamespace() {
        return namespace;
    }
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getParameterType() {
        return parameterType;
    }
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
    public String getResultType() {
        return resultType;
    }
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }
    public String getParameter() {
        return parameter;
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    public String toString(){
        return this.namespace+"."+this.id+" : " +this.sql;
    }
}