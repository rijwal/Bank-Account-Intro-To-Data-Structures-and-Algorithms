import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent; //need this import to allow the dispatchEvent to work

public class WindowThree extends JFrame {

  private JPanel contentPane;
  protected JTextField textField; //protected means it is accessible outside the package but not by non-subclasses

  // Create the frame
  public WindowThree() {
    setTitle(""); //title of frame
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //just closes WindowThree
    setBounds(0, 0, 330, 330); //size of frame
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //size of border around frame
    contentPane.setLayout(null); //type of layout
    setContentPane(contentPane);
    
    JLabel lblEnterAmount = new JLabel("Enter Amount:");  //label number 1
    lblEnterAmount.setBounds(105, 50, 200, 50);
    contentPane.add(lblEnterAmount);
    
    textField = new JTextField();
    textField.setBounds(90, 100, 150, 50); //text field
    textField.setColumns(10); //sets columns in text field
    contentPane.add(textField);

    JButton btnDone = new JButton("Done");
    btnDone.addMouseListener(new MouseAdapter() { //listener that runs when mouse clicks on Press Me button
      public void mouseClicked(MouseEvent e) {
        done();
      }
    });
    btnDone.setBounds(115, 200, 100, 50);
    contentPane.add(btnDone);
  }
  
  public void done(){
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}