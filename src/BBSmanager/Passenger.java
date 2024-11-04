
package BBSmanager;

/**
 *
 * @author ASUS
 */
public class Passenger implements Comparable<Passenger> {
   public String pcode;
   public String name;
   public String phone;

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
      sb.append(String.format("| %-16s | %-20s | %-15s |", this.pcode, this.name, this.phone));
      return sb.toString();
   }

   @Override
   public int compareTo(Passenger other) {
      return this.pcode.compareTo(other.pcode);
   }
}
