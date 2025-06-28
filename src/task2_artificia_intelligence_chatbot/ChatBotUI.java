package task2_artificia_intelligence_chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatBotUI extends JFrame implements ActionListener{

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private ChatBotLogic botLogic;

    public ChatBotUI() {
        setTitle("AI ChatBot - Happy Coder");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // Input Field & Button
        inputField = new JTextField();
        inputField.addActionListener(this);

        sendButton = new JButton("Send");
        sendButton.addActionListener(this);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        // Load Bot Logic
        botLogic = new ChatBotLogic("chatbot_data.json");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = inputField.getText().trim();
        if (!userText.isEmpty()) {
            chatArea.append("You: " + userText + "\n");
            String botReply = botLogic.getResponse(userText);
            chatArea.append("Bot: " + botReply + "\n");
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatBotUI::new);
    }

}
