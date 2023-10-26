/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slot;

/**
 *
 * @author FPTSHOP
 */
public class SlotDB extends DBcontext<Slot> {
    public List<Slot> getSlot(){
        List<Slot> list = new ArrayList<>();
        String sql = "select * from Slot";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Slot s = new Slot();
                s.setSlot_id(rs.getString(1));
                s.setStarttime(rs.getTime(2));
                s.setEndtime(rs.getTime(3));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
