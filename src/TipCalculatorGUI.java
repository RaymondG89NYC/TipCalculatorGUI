import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TipCalculatorGUI extends JFrame implements ActionListener, KeyListener{
    private JTextArea textAreaBill;
    private JTextArea textAreaTipPercent;
    private JTextArea textAreaPeople;
    private JTextField textAreaTipAmount;
    private JTextArea textAreaTotal;
    private JPanel MainPanel;
    private JButton tipButtonRightButton;
    private JButton tipButtonLeftButton;
    private JButton peopleButtonLeft;
    private JButton peopleButtonRight;
    private TipCalculator calculator;

    public static void main(String[] args) {
        TipCalculatorGUI gui = new TipCalculatorGUI();
        gui.setVisible(true);
    }

    public TipCalculatorGUI() {
        createUIComponents();
        double bill = Double.parseDouble(textAreaBill.getText());
        int tipPercent = Integer.parseInt(textAreaTipPercent.getText());
        int people = Integer.parseInt(textAreaPeople.getText());
        calculator = new TipCalculator(bill, tipPercent, people);
    }

    private void createUIComponents() {
        setContentPane(MainPanel);
        setTitle("Tip Calculator!");
        setSize(550, 300);
        setLocation(450, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textAreaBill.addKeyListener(this);
        textAreaPeople.addKeyListener(this);
        textAreaTipPercent.addKeyListener(this);
        tipButtonLeftButton.addActionListener(this);
        tipButtonRightButton.addActionListener(this);
        peopleButtonLeft.addActionListener(this);
        peopleButtonRight.addActionListener(this);
        setVisible(true);
    }
    private void calculateAll(){
        double bill = Double.parseDouble(textAreaBill.getText());
        calculator.setBill(bill);
        textAreaTipAmount.setText(""+calculator.tipPerPerson());
        textAreaTotal.setText(""+calculator.totalPerPerson());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String text = button.getText();
            int tipPercent = Integer.parseInt(textAreaTipPercent.getText());
            int people = Integer.parseInt(textAreaPeople.getText());
            if (text.equals("-->")) {
                tipPercent+=1;
                textAreaTipPercent.setText("" + tipPercent);
                calculator.setTipPercentage(tipPercent);
            } else if(text.equals("<--")){
                if(tipPercent >= 1) {
                    tipPercent-=1;
                    textAreaTipPercent.setText("" + tipPercent);
                    calculator.setTipPercentage(tipPercent);
                }
            }
            else if(text.equals("<---")){
                if(tipPercent >= 1) {
                    people-=1;
                    textAreaPeople.setText("" + people);
                    calculator.setNumberOfPeople(people);
                }

            }
            else if(text.equals("--->")){
                people+=1;
                textAreaPeople.setText("" + people);
                calculator.setNumberOfPeople(people);
            }
        }
        calculateAll();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // KeyListener interface requires this method be added, even if unimplemented
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // KeyListener interface requires this method be added, even if unimplemented
    }
}
