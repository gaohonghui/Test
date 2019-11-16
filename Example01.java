package jdbcdemo;

import java.net.SocketOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Example01
{
    public static void main(String[] args) throws Exception
    {
        Statement stat=null;
        boolean rs=false;
        Connection conn=null;

        try
        {
            //注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //通过DriverManager方法获取数据路链接
            String url="jdbc:mysql://localhost:3306/db1";
            String username="root";
            String password="722819";
            conn=DriverManager.getConnection(url, username, password);
            //通过connection对象获取Statement对象
            stat=conn.createStatement();
            //建立一个表
            String sql0="CREATE TABLE user\n" +
                    "(\n" +
                    "\tID INT(11),\n" +
                    "\tCreateTime VARCHAR(255),\n" +
                    "\tPASSWORD VARCHAR(255),\n" +
                    "\tstate INT(11)\n" +
                    ");";

            //使用Statement对象执行sql语句建立user表
            rs=stat.execute(sql0);
           if (!rs)
            {
                System.out.println("建立user表失败，退出程序");
                return;
            }
           else
           {
               System.out.println("恭喜你成功建立user表；");
           }

            //使用Statement对象执行sql语句插入数据
            String sql1="insert into user values(1,'2019-11-11','123',1)";
            String sql2="insert into user values(2,'2019-11-12','456',0)";
            //执行sql语句
            int count=stat.executeUpdate(sql1);
            int count2=stat.executeUpdate(sql2);
            //打印执行结果
            int sum=count+count2;
            if (count>0)
            {
                System.out.println("你插入了 "+sum+"条记录");
            }
            else
            {
                System.out.println("插入数据失败！");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            /*
            if(rs!=null)
            {
                try
                {
                    rs.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }*/
            if(stat!=null)
            {
                try
                {
                    stat.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                try
                {
                    conn.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
}
