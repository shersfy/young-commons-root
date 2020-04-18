package org.young.commons.connector.db;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.young.commons.exception.DatahubException;
import org.young.commons.meta.ColumnMeta;
import org.young.commons.meta.DBAccessType;
import org.young.commons.meta.DBMeta;
import org.young.commons.meta.TableMeta;
import org.young.commons.meta.TableType;
import org.young.commons.utils.AesUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * Hive数据库连接器
 */
public class KylinConnector extends DbConnectorInterface {

    private static String encoding;
    private static String baseURL;// = "http://10.32.15.84:7070/kylin/api";

    public static String login(String user, String passwd) throws Exception {
        String method = "POST";
        String para = "/user/authentication";
        byte[] key = (user + ":" + passwd).getBytes();
        encoding = Base64.encodeBase64String(key);
        return execute(para, method, null);
    }

    public static String listQueryableTables(String projectName) throws Exception {
        String method = "GET";
        String para = "/tables_and_columns?project=" + projectName;
        return execute(para, method, null);
    }

    /**
     * @param offset      required int Offset used by pagination
     * @param limit       required int Cubes per page.
     * @param cubeName    optional string Keyword for cube names. To find cubes whose name
     *                    contains this keyword.
     * @param projectName optional string Project name.
     * @return
     */
    public static String listCubes(int offset, int limit, String cubeName, String projectName) throws Exception {
        String method = "GET";
        String para = "/cubes?offset=" + offset + "&limit=" + limit + "&cubeName=" + cubeName + "&projectName="
                + projectName;
        return execute(para, method, null);
    }

    /**
     * @param cubeName Cube name.
     * @return
     */
    public static String getCubeDes(String cubeName) throws Exception {
        String method = "GET";
        String para = "/cube_desc/" + cubeName;
        return execute(para, method, null);
    }

    /**
     * @param cubeName
     * @return
     */
    public static String getCube(String cubeName) throws Exception {
        String method = "GET";
        String para = "/cubes/" + cubeName;
        return execute(para, method, null);
    }

    /**
     * @param modelName Data model name, by default it should be the same with cube name.
     * @return
     */
    public static String getDataModel(String modelName) throws Exception {
        String method = "GET";
        String para = "/model/" + modelName;
        return execute(para, method, null);
    }

    /**
     * @param cubeName cubeName Cube name.
     * @return
     */
    public static String enableCube(String cubeName) throws Exception {
        String method = "PUT";
        String para = "/cubes/" + cubeName + "/enable";
        return execute(para, method, null);
    }

    /**
     * @param cubeName Cube name.
     * @return
     */
    public static String disableCube(String cubeName) throws Exception {
        String method = "PUT";
        String para = "/cubes/" + cubeName + "/disable";
        return execute(para, method, null);

    }

    /**
     * @param cubeName Cube name.
     * @return
     */
    public static String purgeCube(String cubeName) throws Exception {
        String method = "PUT";
        String para = "/cubes/" + cubeName + "/purge";
        return execute(para, method, null);

    }

    /**
     * @param jobId Job id
     * @return
     */
    public static String resumeJob(String jobId) throws Exception {
        String method = "PUT";
        String para = "/jobs/" + jobId + "/resume";
        return execute(para, method, null);

    }

    /**
     * startTime - required long Start timestamp of data to build, e.g.
     * 1388563200000 for 2014-1-1 endTime - required long End timestamp of data to
     * build buildType - required string Supported build type: ‘BUILD’, ‘MERGE’,
     * ‘REFRESH’
     *
     * @param cubeName Cube name.
     * @return
     */
    public static String buildCube(String cubeName, String body) throws Exception {
        String method = "PUT";
        String para = "/cubes/" + cubeName + "/rebuild";
        return execute(para, method, body);
    }

    /**
     * @param jobId Job id.
     * @return
     */
    public static String discardJob(String jobId) throws Exception {
        String method = "PUT";
        String para = "/jobs/" + jobId + "/cancel";
        return execute(para, method, null);

    }

    /**
     * @param jobId Job id.
     * @return
     */
    public static String getJobStatus(String jobId) throws Exception {
        String method = "GET";
        String para = "/jobs/" + jobId;
        return execute(para, method, null);

    }

    /**
     * @param jobId  Job id.
     * @param stepId Step id; the step id is composed by jobId with step sequence id;
     *               for example, the jobId is “fb479e54-837f-49a2-b457-651fc50be110”,
     *               its 3rd step id is “fb479e54-837f-49a2-b457-651fc50be110-3”,
     * @return
     */
    public static String getJobStepOutput(String jobId, String stepId) throws Exception {
        String method = "GET";
        String para = "/" + jobId + "/steps/" + stepId + "/output";
        return execute(para, method, null);
    }

    /**
     * @param tableName table name to find.
     * @return
     */
    public static String getHiveTable(String tableName) throws Exception {
        String method = "GET";
        String para = "/tables/" + tableName;
        return execute(para, method, null);
    }

    /**
     * @param tableName table name to find.
     * @return
     */
    public static String getHiveTableInfo(String tableName) throws Exception {
        String method = "GET";
        String para = "/tables/" + tableName + "/exd-map";
        return execute(para, method, null);
    }

    /**
     * @param projectName will list all tables in the project.
     * @param extOptional boolean set true to get extend info of table.
     * @return
     */
    public static String getHiveTables(String projectName, boolean extOptional) throws Exception {
        String method = "GET";
        String para = "/tables?project=" + projectName + "&ext=" + extOptional;
        return execute(para, method, null);
    }

    /**
     * @param tables  table names you want to load from hive, separated with comma.
     * @param project the project which the tables will be loaded into.
     * @return
     */
    public static String loadHiveTables(String tables, String project) throws Exception {
        String method = "POST";
        String para = "/tables/" + tables + "/" + project;
        return execute(para, method, null);
    }

    /**
     * @param type   ‘METADATA’ or ‘CUBE’
     * @param name   Cache key, e.g the cube name.
     * @param action ‘create’, ‘update’ or ‘drop’
     * @return
     */
    public static String wipeCache(String type, String name, String action) throws Exception {
        String method = "POST";
        String para = "/cache/" + type + "/" + name + "/" + action;
        return execute(para, method, null);
    }

    public static String query(String body) throws Exception {
        String method = "POST";
        String para = "/query";
        return execute(para, method, body);
    }

    /**
     * 获取项目和所属表
     *
     * @param limit
     * @param offset
     * @return
     * @throws Exception
     */
    public String getProjects(Integer limit, Integer offset) throws Exception {
        String method = "GET";
        String para = "/projects?limit=" + limit + "&offset=" + offset;
        return execute(para, method, null);
    }

    private static String execute(String para, String method, String body) throws Exception {
        StringBuilder out = new StringBuilder();
        URL url = new URL(baseURL + para);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);
        //System.out.println("encoding:"+encoding);
        connection.setRequestProperty("Content-Type", "application/json");
        if (body != null) {
            byte[] outputInBytes = body.getBytes("UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write(outputInBytes);
            os.close();
        }
        InputStream content = (InputStream) connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(content));
        String line;
        while ((line = in.readLine()) != null) {
            out.append(line);
        }
        in.close();
        connection.disconnect();
        return out.toString();
    }


    @Override
    public Connection getConnection() throws DatahubException {
        DBMeta meta = this.getDbMeta();
        baseURL = meta.getUrl();
        try {
            if (baseURL != null) {
                if (baseURL.toUpperCase().startsWith("JDBC")) {
                    Class.forName("org.apache.kylin.jdbc.Driver");
                    DriverManager.getConnection(baseURL, meta.getUserName(), AesUtil.decryptHexStr(meta.getPassword(), AesUtil.AES_SEED));
                } else {
                    KylinConnector.login(meta.getUserName(), AesUtil.decryptHexStr(meta.getPassword(), AesUtil.AES_SEED));
                }
            }

        } catch (Exception e) {
            throw new DatahubException(e);
        }
        return null;
    }

    @Override
    public boolean checkAvailable() throws DatahubException {
        return true;
    }

    @Override
    public String loadDriverClass(DBAccessType dbAccessType) {
        return null;
    }


    @Override
    public List<DBMeta> getDatabases(Connection conn) throws DatahubException {
        ResultSet rs = null;
        List<DBMeta> dbs = new ArrayList<DBMeta>();
        try {
            String result = this.getProjects(10, 0);
            JSONArray jsonArray = JSON.parseArray(result);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = JSON.parseObject(obj.toString());
                String name = jsonObject.get("name").toString();
                DBMeta meta = new DBMeta();
                BeanUtils.copyProperties(meta, getDbMeta());
                meta.setName(name);
                meta.setCatalog(name);
                meta.setSchema(name);
                dbs.add(meta);
            }
        } catch (Exception e) {
            throw DatahubException.throwDatahubException("list all database error", e);
        } finally {
            close(rs);
        }
        return dbs;
    }

    public List<TableMeta> getTables(String catalog, String schema, TableType[] types, Connection conn) throws DatahubException {
        List<TableMeta> list = new ArrayList<TableMeta>();
        try {
            if (StringUtils.isBlank(schema)) {
                schema = null;
            }

            String result = listQueryableTables(schema);
            JSONArray jsonArray = JSON.parseArray(result);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = JSON.parseObject(obj.toString());
                String scheme = jsonObject.get("table_SCHEM").toString();
                String tableName = jsonObject.get("table_NAME").toString();
                TableMeta meta = new TableMeta();
                meta.setName(scheme + "." + tableName);
                meta.setSchema(schema);
                list.add(meta);
            }

            list.sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        } catch (Throwable ex) {
            String err = String.format("get table exception[catalog=%s, schema=%s]", catalog, schema);
            throw DatahubException.throwDatahubException(err, ex);
        }
        return list;
    }

    @Override
    public String showCreateTable(TableMeta tableMeta, Connection connection) throws DatahubException {
        return null;
    }

    @Override
    public String javaTypeToDbType(int i, ColumnMeta columnMeta) throws DatahubException {
        return null;
    }

    @Override
    public String queryByPage(String s, long l, long l1) throws DatahubException {
        return null;
    }

    @Override
    public String getCutPointSql(TableMeta tableMeta, ColumnMeta columnMeta, String s, long l, int i) {
        return null;
    }
}
