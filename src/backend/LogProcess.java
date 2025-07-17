/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Nethula_Rankidu
 */
public class LogProcess {
    public final static Logger logger = Logger.getLogger(LogProcess.class.getName());
    public LogProcess(){
        try{
            FileHandler fh = new FileHandler("C:/Users/Nethula_Rankidu/Documents/GitHub/grocery_store/app.log", true);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            logger.addHandler(fh);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
