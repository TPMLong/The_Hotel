/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author ACER
 */
@WebListener
public class MyServletListener implements ServletContextListener {

    private Map<String, String> list;
    private final Logger LOGGER = Logger.getLogger(MyServletListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileReader fr = null;
        ServletContext context = sce.getServletContext();
        try {
            String path = context.getRealPath("/WEB-INF/mapping.txt");
            fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String msg = "";
            while (br.ready()) {
                msg = br.readLine();
                if (!msg.isEmpty()) {
                    String tmp[] = msg.split("=");
                    if (list == null) {
                        list = new HashMap<>();
                    }
                    list.put(tmp[0], tmp[1]);
                }
            }
            br.close();
            context.setAttribute("LABEL_MAP", list);
        } catch (FileNotFoundException ex) {
            LOGGER.info("MyServletListener_FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            LOGGER.info("MyServletListener_IOException: " + ex.getMessage());
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                LOGGER.info("MyServletListener_IOException: " + ex.getMessage());
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
