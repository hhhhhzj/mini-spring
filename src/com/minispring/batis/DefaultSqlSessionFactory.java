package com.minispring.batis;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.minispring.core.Autowired;
import com.minispring.jdbc.core.JdbcTemplate;
import com.minispring.batis.MapperNode;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-18 14:21
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String mapperLocations;

    private Map<String, MapperNode> mapperNodeMap = new HashMap<>();

    public DefaultSqlSessionFactory() {
    }

    public void init() {
        scanLocation(this.mapperLocations);
    }
    private void scanLocation(String location) {
        String sLocationPath = this.getClass().getClassLoader().getResource("").getPath()+location;
        File dir = new File(sLocationPath);
        for (File file : dir.listFiles()) {
            if(file.isDirectory()){
                scanLocation(location+"/"+file.getName());
            }else{
                buildMapperNodes(location+"/"+file.getName());
            }
        }
    }

    @Override
    public MapperNode getMapperNode(String name) {
        return this.mapperNodeMap.get(name);
    }

    @Override
    public SqlSession openSession() {
        SqlSession newSqlSession = new DefaultSqlSession();
        newSqlSession.setJdbcTemplate(jdbcTemplate);
        newSqlSession.setSqlSessionFactory(this);

        return newSqlSession;
    }

    private Map<String, MapperNode> buildMapperNodes(String filePath) {
        System.out.println(filePath);
        SAXReader saxReader=new SAXReader();
        URL xmlPath=this.getClass().getClassLoader().getResource(filePath);
        try {
            Document document = saxReader.read(xmlPath);
            Element rootElement=document.getRootElement();

            String namespace = rootElement.attributeValue("namespace");

            Iterator<Element> nodes = rootElement.elementIterator();;
            while (nodes.hasNext()) {
                Element node = nodes.next();
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();

                MapperNode selectnode = new MapperNode();
                selectnode.setNamespace(namespace);
                selectnode.setId(id);
                selectnode.setParameterType(parameterType);
                selectnode.setResultType(resultType);
                selectnode.setSql(sql);
                selectnode.setParameter("");

                this.mapperNodeMap.put(namespace + "." + id, selectnode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this.mapperNodeMap;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }
    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public Map<String, MapperNode> getMapperNodeMap() {
        return mapperNodeMap;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}