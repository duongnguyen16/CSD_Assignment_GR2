/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BBSmanager;

/**
 *
 * @author ASUS
 */
public class Bus implements Comparable<Bus> {
   private String bcode;
   private String bnum;
   private String dstation;
   private String astation;
   private double dtime;
   private int seat;
   private int booked;
   private double atime;

   public Bus() {
   }

   public Bus(String bcode, String bnum, String dstation, String astation, double dtime, int seat, int booked,
         double atime) {
      this.bcode = bcode;
      this.bnum = bnum;
      this.dstation = dstation;
      this.astation = astation;
      this.dtime = dtime;
      this.seat = seat;
      this.booked = booked;
      this.atime = atime;
   }

   public String getBcode() {
      return this.bcode;
   }

   public void setBcode(String bcode) {
      this.bcode = bcode;
   }

   public String getBnum() {
      return this.bnum;
   }

   public void setBnum(String bnum) {
      this.bnum = bnum;
   }

   public String getDstation() {
      return this.dstation;
   }

   public void setDstation(String dstation) {
      this.dstation = dstation;
   }

   public String getAstation() {
      return this.astation;
   }

   public void setAstation(String astation) {
      this.astation = astation;
   }

   public double getDtime() {
      return this.dtime;
   }

   public void setDtime(double dtime) {
      this.dtime = dtime;
   }

   public int getSeat() {
      return this.seat;
   }

   public void setSeat(int seat) {
      this.seat = seat;
   }

   public int getBooked() {
      return this.booked;
   }

   public void setBooked(int booked) {
      this.booked = booked;
   }

   public double getAtime() {
      return this.atime;
   }

   public void setAtime(double atime) {
      this.atime = atime;
   }

   @Override
   public String toString() {
      return String.format("| %-10s | %-15s | %-25s | %-25s | %-20.2f | %-20d | %-25d | %-20.2f |",
            bcode, bnum, dstation, astation, dtime, seat, booked, atime);
   }

   public String saveFile() {
      return bcode + ", " + bnum + ", " + dstation + ", " + astation + ", " + dtime + ", " + seat + ", " + booked + ", "
            + atime;
   }

   @Override
   public int compareTo(Bus o) {
      return this.bcode.compareTo(o.bcode);
   }

}
