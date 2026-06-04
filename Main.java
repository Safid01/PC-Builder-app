import javax.swing.*;

public class Main {
   public Main() {
   }

   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      } catch (Exception var0) {
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception var1) {
            var1.printStackTrace();
         }
      }
      new PCBuilder();
   }
}