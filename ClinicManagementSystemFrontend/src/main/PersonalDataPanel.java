package main;

import javax.swing.*;
import java.awt.*;

public class PersonalDataPanel extends JPanel {

    private JPanel PersonalDataPanel;
    private JPanel toolbarPanel;
    private JPanel titlePanel;
    private JPanel parentPanel;
    private JPanel dataPanel;

    public PersonalDataPanel(){
        super();
        this.PersonalDataPanel = this;
        this.toolbarPanel = new ToolbarPanel();
        this.setLayout(new FlowLayout());
        this.toolbarPanel = new ToolbarPanel();
        this.createHeader();
        this.createData();
        this.createPanels();
    }

    // tworzy tytuł strony
    private void createHeader() {
        JLabel title = new JLabel("<html><div style='display: block; margin: 0 auto; text-align: center; margin: 40px 0px 0px 0px; font-size: 18px'>Dane konta</div></html>");
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1,1));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titlePanel.setBackground(new Color(255, 228, 188));
        titlePanel.add(title);
    }

    private void createData(){
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(4,2));
        dataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        dataPanel.setBackground(new Color(255, 228, 188));

        JLabel usernameKey = new JLabel("Username: ");
        usernameKey.setFont(new Font("Arial", Font.PLAIN,20));
        JLabel usernameValue = new JLabel(User.username);
        usernameValue.setFont(new Font("Arial", Font.PLAIN,20));

        JLabel emailKey = new JLabel("Email: ");
        emailKey.setFont(new Font("Arial", Font.PLAIN,20));
        JLabel emailValue = new JLabel("brak danych");
        emailValue.setFont(new Font("Arial", Font.PLAIN,20));

        JLabel peselKey = new JLabel("Pesel: ");
        peselKey.setFont(new Font("Arial", Font.PLAIN,20));
        JLabel peselValue = new JLabel("brak danych");
        peselValue.setFont(new Font("Arial", Font.PLAIN,20));

        JLabel dateKey = new JLabel("Data rejestracji: ");
        dateKey.setFont(new Font("Arial", Font.PLAIN,20));
        JLabel dateValue = new JLabel("brak danych");
        dateValue.setFont(new Font("Arial", Font.PLAIN,20));

        dataPanel.add(usernameKey);
        dataPanel.add(usernameValue);
        dataPanel.add(emailKey);
        dataPanel.add(emailValue);
        dataPanel.add(peselKey);
        dataPanel.add(peselValue);
        dataPanel.add(dateKey);
        dataPanel.add(dateValue);
    }

    // tworzy panel przycisków i parent panel
    private void createPanels() {
        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.setBackground(new Color(255, 228, 188));
        parentPanel.add(toolbarPanel, BorderLayout.NORTH);
        parentPanel.add(titlePanel, BorderLayout.CENTER);
        parentPanel.add(dataPanel, BorderLayout.SOUTH);
        add(parentPanel);
        setBackground(new Color(255, 228, 188));
    }
}
