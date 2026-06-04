import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SavedBuildsFrame extends JFrame {
    private PCBuilder pcBuilder;

    public SavedBuildsFrame(PCBuilder pcBuilder) {
        super("Saved Builds");
        this.pcBuilder = pcBuilder;
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("C:\\Users\\safid\\Downloads\\Learning-Backpack-1\\Java\\PCBuilder\\Images\\save.png").getImage()); // Set your icon path here
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ArrayList<String> builds = BuildIO.loadBuilds();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        if (builds.isEmpty()) {
            mainPanel.add(new JLabel("No saved builds."));
        } else {
            for (String build : builds) {
                JPanel buildPanel = new JPanel(new BorderLayout());
                buildPanel.setBackground(new Color(255, 255, 255, 200));
                buildPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 120, 215), 2),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));

                JTextArea buildText = new JTextArea(build);
                buildText.setEditable(false);
                buildText.setBackground(new Color(245, 245, 245));
                buildText.setFont(new Font("Baloo Da 2", Font.PLAIN, 13));
                buildPanel.add(buildText, BorderLayout.CENTER);

                JButton loadButton = new JButton("Load");
                loadButton.setBackground(new Color(0, 120, 215));
                loadButton.setForeground(Color.WHITE);
                loadButton.setFocusPainted(false);

                loadButton.addActionListener(e -> {
                    if (pcBuilder != null) {
                        pcBuilder.loadBuild(build);
                        dispose();
                    }
                });
                buildPanel.add(loadButton, BorderLayout.WEST);

                JButton deleteButton = new JButton("Delete");
                deleteButton.setBackground(new Color(220, 53, 69));
                deleteButton.setForeground(Color.WHITE);
                deleteButton.setFocusPainted(false);

                deleteButton.addActionListener(e -> deleteBuild(build));
                buildPanel.add(deleteButton, BorderLayout.EAST);

                mainPanel.add(Box.createVerticalStrut(10)); // spacing between builds
                mainPanel.add(buildPanel);
            }
        }
        mainPanel.setBackground(new Color(230, 240, 255));
        add(new JScrollPane(mainPanel));
        setVisible(true);
    }

    private void deleteBuild(String buildToDelete) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this build?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            BuildIO.deleteBuild(buildToDelete);
            dispose();
            new SavedBuildsFrame(pcBuilder);
        }
    }
}