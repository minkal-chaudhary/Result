import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class RequestServlet extends HttpServlet
{
static float sum=0;
static int COP=0;
public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
res.setContentType("text/html");
PrintWriter out=res.getWriter();

String roll=req.getParameter("roll");


out.println("<html><body><br>");

try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","minkal");

Statement st=c.createStatement();



ResultSet rs=st.executeQuery("select * from RESULT where ROLL_NO="+roll);
if(rs.next())
{
out.println("<h4>Name : "+rs.getString(7)+"</h4><br>");

ResultSetMetaData rsmd=rs.getMetaData();
out.println("<table bgcolor='yellow' width=55% height=10% border=3>");
out.println("<tr>");
for(int i=1;i<rsmd.getColumnCount();i++)
out.println("<th >"+rsmd.getColumnName(i)+"</th>");

out.println("</tr>");
sum=0;
COP=0;

sum+=rs.getInt(6)+rs.getInt(2)+rs.getInt(3)+rs.getInt(4)+rs.getInt(5);
out.println("<tr>");
for(int i=2;i<=6;i++)
{
if(rs.getInt(i)<33)
COP++;
}
out.println("<td>"+rs.getString(1)+"</td>");
out.println("<td>"+rs.getString(2)+"</td>");
out.println("<td>"+rs.getString(3)+"</td>");
out.println("<td>"+rs.getString(4)+"</td>");
out.println("<td>"+rs.getString(5)+"</td>");
out.println("<td>"+rs.getString(6)+"</td>");
out.println("</tr>");

float m=sum;
sum=sum/500;

sum=sum*100;
out.println("</table>");
out.println("<h4>Total : "+m);
out.println("<p> Pecentage:"+sum+"% <br> COP:"+COP+"</p>");
}
else
out.println("<p> Invalid Roll No </p>");
out.println("</body></html>");
}catch(Exception e){out.println(e);}
}
}