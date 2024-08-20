import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener; 
import java.awt.event.WindowEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import java.awt.Color;


public class WindowOne extends JFrame implements WindowListener {

  private JPanel contentPane;
  private JButton show;
  private WindowTwo two;
  private WindowThree three;
  JComboBox cb;

  BankAccount savings = new BankAccount ("savings");
  BankAccount cheque = new BankAccount("cheque");

  int qual = 40;


  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          WindowOne frame = new WindowOne();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public WindowOne() {
    setTitle("Main"); //title of frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default close function
    setBounds(0, 0, 330, 330); //size of frame
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //size of border around frame
    contentPane.setLayout(null); //type of layout
    setContentPane(contentPane);

    JButton btnWd = new JButton("Withdraw");
    btnWd.addMouseListener(new MouseAdapter() { //listener that runs when mouse clicks on Press Me button
      public void mouseClicked(MouseEvent e) {
        openWindowThree();
      }
    });
    btnWd.setBounds(30, 240, 110, 30);
    contentPane.add(btnWd);

    JButton btnDp = new JButton("Deposit");
    btnDp.addMouseListener(new MouseAdapter() { //listener that runs when mouse clicks on Press Me button
      public void mouseClicked(MouseEvent e) {
        openNewWindow();
      }
    });
    btnDp.setBounds(200, 240, 110, 30);
    contentPane.add(btnDp);

    JLabel lblAccount = new JLabel("Account");  
    lblAccount.setBounds(120, 20, 150, 21);
    contentPane.add(lblAccount);

    JLabel lblBalance = new JLabel("Current Balance:");  
    lblBalance.setBounds(30, 180, 150, 21);
    contentPane.add(lblBalance);

    JLabel lblActualBalance = new JLabel("Select an Account");  
    lblActualBalance.setBounds(180, 180, 150, 21);
    contentPane.add(lblActualBalance);

    String account[]={"Savings", "Chequing"};        
    cb = new JComboBox(account);    
    cb.setBounds(90,60,120,30);
    contentPane.add(cb);

    show = new JButton("Show Balance");
    show.setBounds(70, 130, 180, 20);
    contentPane.add(show);

    show.addActionListener(new ActionListener() {  
      public void actionPerformed(ActionEvent e) {       

        if (cb.getItemAt(cb.getSelectedIndex()).equals("Savings")) {
          lblActualBalance.setText(Integer.toString(savings.balance));
          if (savings.balance < 0) {
            lblActualBalance.setForeground(Color.RED);
          } else {
            lblActualBalance.setForeground(Color.BLACK);
          }
        }

        if (cb.getItemAt(cb.getSelectedIndex()).equals("Chequing")) {
          lblActualBalance.setText(Integer.toString(cheque.balance));
          if (cheque.balance < 0) {
            lblActualBalance.setForeground(Color.RED);
          } else {
            lblActualBalance.setForeground(Color.BLACK);
          }
        }
        
      }  
    });
 
  }


  public void openNewWindow(){
    two = new WindowTwo();
    two.addWindowListener(this); //allows window one to recieve window events from window two
    two.setVisible(true);
  }

  public void openWindowThree(){
    three = new WindowThree();
    three.addWindowListener(this); //allows window one to recieve window events from window two
    three.setVisible(true);
  }
  
  public void windowOpened(WindowEvent e) {

  }
  
  public void windowClosing(WindowEvent e) {

  }
  
  public void windowClosed(WindowEvent e) { //method that runs when window closed event gets sent

    if (e.getWindow() == three) {
      String withdrawAmount = three.textField.getText();
    
      if (cb.getItemAt(cb.getSelectedIndex()).equals("Savings")) {
        savings.withdraw(Integer.parseInt(withdrawAmount));
      }
    
      if (cb.getItemAt(cb.getSelectedIndex()).equals("Chequing")) {
        cheque.withdraw(Integer.parseInt(withdrawAmount));
      }
    } else if (e.getWindow() == two) {
        String depositAmount = two.textField.getText();
      
        if (cb.getItemAt(cb.getSelectedIndex()).equals("Savings")) {
          savings.deposit(Integer.parseInt(depositAmount));
        }
      
        if (cb.getItemAt(cb.getSelectedIndex()).equals("Chequing")) {
          cheque.deposit(Integer.parseInt(depositAmount));
       }
    }
  
  }
  
  
  public void windowIconified(WindowEvent e) {

  }
  
  public void windowDeiconified(WindowEvent e) {

  }
  
  public void windowActivated(WindowEvent e) {

  }
  
  public void windowDeactivated(WindowEvent e) {

  }
  
}
