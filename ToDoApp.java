import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoApp {

    private JFrame frame;
    private JTextField taskField;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public ToDoApp() {
        createUI();
    }

    private void createUI() {
        frame = new JFrame("To-Do List App");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout(5,5));
        taskField = new JTextField();
        JButton addButton = new JButton("Add");

        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // List
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Bottom buttons
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Selected");
        JButton clearButton = new JButton("Clear All");

        bottomPanel.add(deleteButton);
        bottomPanel.add(clearButton);

        // Add to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add task button
        addButton.addActionListener(e -> addTask());

        // Enter key adds task
        taskField.addActionListener(e -> addTask());

        // Delete selected
        deleteButton.addActionListener(e -> deleteTask());

        // Clear all
        clearButton.addActionListener(e -> clearAll());

        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Enter a task first!");
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                frame, "Delete selected task?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                listModel.remove(index);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Select a task to delete.");
        }
    }

    private void clearAll() {
        int confirm = JOptionPane.showConfirmDialog(
            frame, "Clear all tasks?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            listModel.clear();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}