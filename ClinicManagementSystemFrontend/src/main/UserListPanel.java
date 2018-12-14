package main;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;


public class UserListPanel extends JPanel {

    private JSONObject list;
    private JPanel UserListPanel;
    private JPanel titlePanel;							// panel na tytuł
    private JPanel parentPanel;							// panel na panele
    private JPanel toolbarPanel;
    private JPanel usersPanel;
    private JToolBar toolbar;

    private JButton logoutButton;

    public UserListPanel(){
        this.getAllUsers();
        this.UserListPanel = this;
        this.toolbarPanel = new ToolbarPanel();
        this.setLayout(new FlowLayout());
        this.createHeader();
        this.createPanels();
    }

    // pobranie z backendu listy wszystkich uzytkownikow
    private void getAllUsers(){
        String loginUrl = "http://localhost:8081/user";
        int responseCode = 0;
        JSONObject json = null;
        String authString = User.username + ":" + User.password;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        try{
            URL url = new URL(loginUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "Basic " + authStringEnc);
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
            InputStream in = new BufferedInputStream(con.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(result);
            System.out.println("Response code: " + responseCode);
            System.out.println("Response: " + json);
            in.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Błąd pobierania danych o użytkownikach");
        } finally{
            // responseCode jest 200 gdy dobre dane, inaczej Karol wysyła response 401 - unauthorized
            if(responseCode == 200){
                this.list = json;
                this.createUserList();
            }
        }
    }

    // tworzy tytuł strony
    private void createHeader() {
        JLabel title = new JLabel("<html><div style='text-align: center; margin: 40px 0px 0px 0px; font-size: 18px'>Lista uzytkownikow systemu</div></html>");
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1,1));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        titlePanel.setBackground(new Color(255, 228, 188));
        titlePanel.add(title);
    }

    // generuje liste uzytkownikow
    private void createUserList(){
        Object _embedded = this.list.get("_embedded");
        System.out.println("_embedded: " + _embedded);
        JSONParser parser = new JSONParser();
        try {

            JSONObject parsedJSONObject = (JSONObject) parser.parse(_embedded.toString());
            System.out.println("1." + parsedJSONObject.size());
            Object users = parsedJSONObject.get("user");
            JSONArray parsedUsers = (JSONArray) parser.parse(users.toString());
            System.out.println("2." + parsedUsers.size());


            this.usersPanel = new JPanel();
            this.usersPanel.setLayout(new GridLayout(parsedUsers.size(),1,10,10));
            this.usersPanel.setBackground(new Color(255, 228, 188));

            DefaultListModel<String> model = new DefaultListModel<>();
            JList lista = new JList(model);
            lista.setFixedCellHeight(50);
            lista.setFixedCellWidth(100);

            for(int i = 0; i < parsedUsers.size(); i++){
                JSONObject parsedUserObject = (JSONObject) parser.parse(parsedUsers.get(i).toString());
                Object userUsername = parsedUserObject.get("username");
                Object userPassword = parsedUserObject.get("password");
                model.addElement( userUsername.toString());


//                JLabel usernameLabel = new JLabel(userUsername.toString());
//                usernameLabel.setFont(new Font("Arial", Font.PLAIN,20));
//                this.usersPanel.add(usernameLabel);
            }
            this.usersPanel.add(lista);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // tworzy panel przycisków i pnarent pael
    private void createPanels() {
        parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.setBackground(new Color(255, 228, 188));
        parentPanel.add(toolbarPanel, BorderLayout.NORTH);
        parentPanel.add(titlePanel, BorderLayout.CENTER);
        parentPanel.add(this.usersPanel, BorderLayout.SOUTH);
        add(parentPanel);
        setBackground(new Color(255, 228, 188));
    }

}
