package BBSmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
   private String bcode;
   private String pcode;
   private Date odate;
   private boolean paid;
   private int seat;

   public Booking() {
   }

   public Booking(String bcode, String pcode, Date odate, boolean paid, int seat) {
      this.bcode = bcode;
      this.pcode = pcode;
      this.odate = odate;
      this.paid = paid;
      this.seat = seat;
   }

   public String getBcode() {
      return this.bcode;
   }

   public void setBcode(String bcode) {
      this.bcode = bcode;
   }

   public String getPcode() {
      return this.pcode;
   }

   public void setPcode(String pcode) {
      this.pcode = pcode;
   }

   public Date getOdate() {
      return this.odate;
   }

   public void setOdate(Date odate) {
      this.odate = odate;
   }

   public boolean isPaid() {
      return this.paid;
   }

   public void setPaid(boolean paid) {
      this.paid = paid;
   }

   public int getSeat() {
      return this.seat;
   }

   public void setSeat(int seat) {
      this.seat = seat;
   }

   public String toDate(Date date) {

      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      return sdf.format(date);
   }

   public String toString() {

      return String.format("| %-10s | %-10s | %-15s | %-5s | %-5d |", this.bcode, this.pcode, toDate(this.odate),
            this.paid, this.seat);
   }
}