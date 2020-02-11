package cn.itcast.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("cookieServletTest1")
public class CookieServletTest1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //set response's data format
        response.setContentType("text/html;charset=utf-8");

        //1. get cookie
        Cookie[] cookies = request.getCookies();
        //2. search cookie
        if (cookies != null && cookies.length>0){
            for (Cookie cookie : cookies){
                //get cookie name
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    // this is not the first time to access
                    //get cookie value
                    String value = cookie.getValue();
                    response.getWriter().write("<h1>welcom back, last time you accessed: </h1>" + value );

                    //set new cookie value
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                    String str_date = simpleDateFormat.format(date);
                    cookie.setValue(str_date);
                    //set cookie lifespan
                    cookie.setMaxAge(60 * 60 *24 *30);

                    response.addCookie(cookie);
                    break;
                }
            }
        }

        if (cookies == null || cookies.length ==0){
            //set new cookie value
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy-HH:mm:ss");
            String str_date = simpleDateFormat.format(date);
            Cookie cookie = new Cookie("lastTime", str_date);
            cookie.setValue(str_date);
            //set cookie lifespan
            cookie.setMaxAge(60 * 60 *24 *30);

            response.addCookie(cookie);
            response.getWriter().write("Welcome! You are the first time to access");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
