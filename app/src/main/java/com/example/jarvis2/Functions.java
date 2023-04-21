package com.example.jarvis2;

import java.util.Calendar;

public class Functions {

        public static String wishMe(){
            String s ="";
            Calendar c = Calendar.getInstance();
            int time = c.get(Calendar.HOUR_OF_DAY);

            if (time < 12)
            {
                s = "Good Morning vaishnavi";
            }
            else if(time < 16)
            {
                s= "Good afternoon vaishnavi";
            }
            else if(time < 21)
            {
                s= "Good evening vaishnavi";
            }
            else if(time < 23)
            {
                s= "Good night vaishnavi";
            }
            else {
                s= "you should sleep now..";
            }
            return s;
        }
    }
