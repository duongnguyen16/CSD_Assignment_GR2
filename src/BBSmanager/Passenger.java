/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBSmanager;

/**
 *
 * @author ASUS
 */
public class Passenger {
   private String pcode;
   private String name;
   private String phone;

   public Passenger() {
   }

   public Passenger(String pcode, String name, String phone) {
      this.pcode = pcode;
      this.name = name;
      this.phone = phone;
   }

   public String getPcode() {
      return this.pcode;
   }

   public void setPcode(String pcode) {
      this.pcode = pcode;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("| %-16s | %-20s | %-15s |\n", this.pcode, this.name, this.phone));
    return sb.toString();
}
}
