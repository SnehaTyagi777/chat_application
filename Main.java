import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Chat Application");

        JLabel username  = new JLabel("Name");
        JTextField usertf = new JTextField();

        JLabel messagelb =  new JLabel("message");
        JTextField messtf = new JTextField();

        JButton submitbtn = new JButton("Submit");


        username.setBounds(10,30,100,30);
        usertf.setBounds(120,30,100,30);

        messagelb.setBounds(10,70,100,30);
        messtf.setBounds(120,70,100,30);
        submitbtn.setBounds(30,110,110,45);

        frame.add(username);
        frame.add(usertf);
        frame.add(messagelb);
        frame.add(messtf);
        frame.add(submitbtn);

        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(300, 300);
        frame.setVisible(true);

        submitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usertf.getText().toString().isEmpty() ||
                        messtf.getText().toString().isEmpty()) {
                    JOptionPane.showInputDialog(null, "Please enter the name and message");
                } else {
                    String url = "jdbc:mysql://localhost:3306/sanyadb";
                    String username = "root";
                    String password = "";
                    try {
                        Connection connection = DriverManager.getConnection(url, username, password);
                        String sql = " insert into chat"
                                + " values (null, ?, ?)";
                        PreparedStatement preparedStmt = connection.prepareStatement(sql);
                        preparedStmt.setString(1, usertf.getText().toString());
                        preparedStmt.setString(2, messtf.getText().toString());
                        preparedStmt.execute();

                        System.out.println("DB Connected");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex + "error");
                    }
                }
            }
        });
    }
}




