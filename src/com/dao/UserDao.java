package com.dao;

import com.entity.User;

import java.sql.*;

public class UserDao {
    private static  String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static  String url = "jdbc:mysql://localhost:3306/firstmonthproject?characterEncoding=utf8&useSSL=false&serverTimezone=CST";
    private static  String username = "root";
    private static  String password = "root";
    /**
     * 创建连接
     * @return 连接对象
     */
    public Connection getConnection (){
        try {
            //获取连接对象
            Class.forName(DRIVER_CLASS);
            //返回值
            return DriverManager.getConnection(url,username,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 关闭资源数据
     * @param c conn
     * @param p pre
     * @param r res
     */
    public void closeAll(Connection c, ResultSet r,PreparedStatement p){
        try{
            if(c != null)c.close();
            if(p != null)p.close();
            if(r != null)r.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证用户信息
     * @return
     */
    public int Verification(User user){
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;

        conn = getConnection();
        String sql = "SELECT * FROM USER WHERE username = ? AND PASSWORD = ?";

        try {
            pra = conn.prepareStatement(sql);
            pra.setString(1,user.getUserName());
            pra.setString(2,user.getPassword());
            rs = pra.executeQuery();

            if(rs != null){
                if (rs.next()){
                    return 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 注册用户信息
     * @return
     */
    public int register(User user){
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;

        conn = getConnection();

        String sql = "INSERT INTO USER(username,PASSWORD,sex,hobbys,phone,email,addrs) VALUES (?,?,?,?,?,?,?)";

        try {

            pra = conn.prepareStatement(sql);
            pra.setString(1,user.getUserName());
            pra.setString(2,user.getPassword());
            pra.setInt(3,user.getSex());
            pra.setString(4,user.getHobbys());
            pra.setString(5,user.getPhone());
            pra.setString(6,user.getEmail());
            pra.setString(7,user.getAddrs());

            int result = pra.executeUpdate();

            if(result > 0){
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
