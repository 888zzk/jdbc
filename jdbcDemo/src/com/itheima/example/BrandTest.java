package com.itheima.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.itheima.pojo.Brand;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 品牌数据的增删改查
 */
public class BrandTest {

    /**
     * 查询所有
     * 1.SQL：select * from tb_brand;
     * 2.参数：不需要
     * 3.结果:List<Brand>
     */
    @Test
    public void testSelectAll() throws Exception {
        //1.获取Connection
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));

        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //5.获取数据库连接
        Connection conn = dataSource.getConnection();

        //2.定义SQL
        String sql = "select * from tb_brand;";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数

        //5.执行SQL
        ResultSet rs = pstmt.executeQuery();

        //6.处理结果    List<Brand>
        Brand brand = null;
        List<Brand> brands = new ArrayList<>();
        while (rs.next()){
            //获取数据
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");

            //封装Brand对象
            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            //装载集合
            brands.add(brand);
        }
        System.out.println(brands);

        //7.资源释放
        rs.close();
        pstmt.close();
        conn.close();
    }


    /**
     * 添加
     * 1.SQL：insert into tb_brand(brand_name,company_name,ordered,description,status) values(?,?,?,?,?);
     * 2.参数：需要，除了id之外的所有参数信息
     * 3.结果:Boolean
     */
    @Test
    public void testAdd() throws Exception {
        //接收页面提交的参数
        String brandName = "gpw2代";
        String companyName = "罗技";
        int ordered = 1;
        String description = "CSGO职业选手首选!";
        int status = 1;


        //1.获取Connection
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));

        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //5.获取数据库连接
        Connection conn = dataSource.getConnection();

        //2.定义SQL
        String sql = "insert into tb_brand(brand_name,company_name,ordered,description,status) values(?,?,?,?,?);";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setString(1,brandName);
        pstmt.setString(2,companyName);
        pstmt.setInt(3,ordered);
        pstmt.setString(4,description);
        pstmt.setInt(5,status);

        //5.执行SQL
        int count = pstmt.executeUpdate();   //影响的行数

        //6.处理结果
        System.out.println(count > 0);

        //7.资源释放
        pstmt.close();
        conn.close();
    }


    /**
     * 修改
     * 1.SQL：

     update tb_brand
        set brand_name = ?,
        company_name = ?,
        ordered = ?,
        description = ?,
        status = ?
     where id = ?

     * 2.参数：需要，所有数据
     * 3.结果:Boolean
     */
    @Test
    public void testUpdate() throws Exception {
        //接收页面提交的参数
        String brandName = "gpw2代";
        String companyName = "罗技公司";
        int ordered = 1000;
        String description = "职业战队首选首选!";
        int status = 1;
        int id = 4;


        //1.获取Connection
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));

        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //5.获取数据库连接
        Connection conn = dataSource.getConnection();

        //2.定义SQL
        String sql = "update tb_brand\n" +
                "        set brand_name = ?,\n" +
                "        company_name = ?,\n" +
                "        ordered = ?,\n" +
                "        description = ?,\n" +
                "        status = ?\n" +
                "     where id = ?";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setString(1,brandName);
        pstmt.setString(2,companyName);
        pstmt.setInt(3,ordered);
        pstmt.setString(4,description);
        pstmt.setInt(5,status);
        pstmt.setInt(6,id);

        //5.执行SQL
        int count = pstmt.executeUpdate();   //影响的行数

        //6.处理结果
        System.out.println(count > 0);

        //7.资源释放
        pstmt.close();
        conn.close();
    }


    /**
     * 删除
     * 1.SQL：delete from tb_brand where id = ?
     * 2.参数：需要，id
     * 3.结果:Boolean
     */
    @Test
    public void testDeleteById() throws Exception {
        //接收页面提交的参数
        int id = 4;


        //1.获取Connection
        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));

        //4.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //5.获取数据库连接
        Connection conn = dataSource.getConnection();

        //2.定义SQL
        String sql = "delete from tb_brand where id = ?";

        //3.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4.设置参数
        pstmt.setInt(1,id);

        //5.执行SQL
        int count = pstmt.executeUpdate();   //影响的行数

        //6.处理结果
        System.out.println(count > 0);

        //7.资源释放
        pstmt.close();
        conn.close();
    }
}
