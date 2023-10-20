package dev.ofilipesouza.tasksphere.utils;

import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    public static String getEmailFromSession(HttpSession session){
       return session.getAttribute("username").toString();
    }
    
}
