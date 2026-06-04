import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PCBuilder extends JFrame {

    private JLabel totalLabel;
    private Map<String, JComboBox<PCComponent>> componentComboBoxes = new LinkedHashMap<>();
    private Map<String, ArrayList<PCComponent>> componentOptions = new LinkedHashMap<>();

    public PCBuilder() {
        super("PC Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadComponents();

        BackgroundPanel mainPanel = new BackgroundPanel("C:\\Users\\safid\\Downloads\\Learning-Backpack-1\\Java\\PCBuilder\\Images\\bg.jpg");
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setOpaque(false);
        JLabel title = new JLabel("Build your own PC");
        title.setFont(new Font("Century Gothic", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        totalLabel = new JLabel("Total Amount: 0৳");
        totalLabel.setForeground(Color.MAGENTA);
        totalLabel.setFont(new Font("Baloo Da 2", Font.BOLD, 16));
        JPanel totalPanel = new JPanel();
        totalPanel.setOpaque(true);
        totalPanel.setBackground(new Color(255, 255, 255, 200));
        totalPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
        totalPanel.add(totalLabel);
        titlePanel.add(title);
        titlePanel.add(totalPanel);

        String[] coreComponents = {"Processor*", "Motherboard*", "CPU Cooler*", "RAM-1*", "RAM-2", "Graphics Card", "Storage*", "Power Supply*", "Case*"};
        String[] peripherals = {"Monitor", "Keyboard", "Mouse"};
        String[] accessories = {"Cooling Fan", "Cables"};

        JPanel coreComponentsPanel = createComponentSection("Core Components", coreComponents);
        coreComponentsPanel.setOpaque(false);
        JPanel peripheralsPanel = createComponentSection("Peripherals", peripherals);
        peripheralsPanel.setOpaque(false);
        JPanel accessoriesPanel = createComponentSection("Accessories", accessories);
        accessoriesPanel.setOpaque(false);

        // Scroll panes for each section
        JScrollPane coreScrollPane = new JScrollPane(coreComponentsPanel);
        coreScrollPane.setOpaque(false);
        coreScrollPane.getViewport().setOpaque(false);
        coreScrollPane.setBorder(BorderFactory.createTitledBorder("Core Components"));

        JScrollPane peripheralsScrollPane = new JScrollPane(peripheralsPanel);
        peripheralsScrollPane.setOpaque(false);
        peripheralsScrollPane.getViewport().setOpaque(false);
        peripheralsScrollPane.setBorder(BorderFactory.createTitledBorder("Peripherals"));

        JScrollPane accessoriesScrollPane = new JScrollPane(accessoriesPanel);
        accessoriesScrollPane.setOpaque(false);
        accessoriesScrollPane.getViewport().setOpaque(false);
        accessoriesScrollPane.setBorder(BorderFactory.createTitledBorder("Accessories"));

        // Tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Core Components", coreScrollPane);
        tabbedPane.addTab("Peripherals", peripheralsScrollPane);
        tabbedPane.addTab("Accessories", accessoriesScrollPane);
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setFont(new Font("Courier New", Font.BOLD, 13));
        tabbedPane.setForeground(Color.BLACK);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton savebuild = new JButton("Save Build");
        JButton loadBuild = new JButton("Load Build");
        JButton clearAll = new JButton("Clear All");

        customizeButton(savebuild);
        customizeButton(loadBuild);
        customizeButton(clearAll);

        savebuild.setBackground(new Color(0, 90, 30));
        loadBuild.setBackground(new Color(0, 102, 204));
        clearAll.setBackground(new Color(204, 0, 0));

        buttonPanel.add(savebuild);
        buttonPanel.add(loadBuild);
        buttonPanel.add(clearAll);

        savebuild.addActionListener(e -> savebuild());
        loadBuild.addActionListener(e -> loadBuild());
        clearAll.addActionListener(e -> clearAll());

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(610, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("C:\\Users\\safid\\Downloads\\Learning-Backpack-1\\Java\\PCBuilder\\Images\\pc.png").getImage());
        setVisible(true);
    }

    private JPanel createComponentSection(String title, String[] components) {
        JPanel grid = new JPanel();
        grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS));
        grid.setOpaque(false);

        for (String comp : components) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 5));
            row.setOpaque(false);

            String labelText = comp + ":";
            JLabel label = new JLabel(labelText);
            label.setPreferredSize(new Dimension(120, 30));
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Georgia", Font.PLAIN, 16));

            ArrayList<PCComponent> options = componentOptions.get(comp);
            JComboBox<PCComponent> comboBox = new JComboBox<>(options.toArray(new PCComponent[0]));
            comboBox.setPreferredSize(new Dimension(350, 30));
            comboBox.setBackground(Color.WHITE);
            comboBox.setForeground(Color.BLACK);
            comboBox.setFont(new Font("Baloo Da 2", Font.PLAIN, 12));
            componentComboBoxes.put(comp, comboBox);

            comboBox.addActionListener(e -> updateTotal());

            row.add(label);
            row.add(comboBox);
            grid.add(row);
        }
        return grid;
    }

    private void loadComponents() {
        componentOptions.put("Processor*", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("Intel i9-12900K", 55000),
            new CoreComponent("AMD Ryzen 9 5950X", 50000)
        )));
        componentOptions.put("Motherboard*", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("ASUS ROG Strix Z690", 25000),
            new CoreComponent("MSI MPG B550 Gaming Edge", 15000)
        )));
        componentOptions.put("CPU Cooler*", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("Noctua NH-D15", 8000),
            new CoreComponent("Corsair H100i", 10000)
        )));
        componentOptions.put("RAM-1*", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("Corsair Vengeance 16GB", 8000),
            new CoreComponent("G.Skill Ripjaws 32GB", 12000)
        )));
        componentOptions.put("RAM-2", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("Corsair Vengeance 16GB", 8000),
            new CoreComponent("G.Skill Ripjaws 32GB", 12000)
        )));
        componentOptions.put("Graphics Card", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("NVIDIA RTX 3080", 70000),
            new CoreComponent("AMD Radeon RX 6800 XT", 65000)
        )));
        componentOptions.put("Storage*", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("Samsung 970 EVO 1TB", 15000),
            new CoreComponent("Western Digital 2TB HDD", 5000)
        )));
        componentOptions.put("Power Supply*", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("Corsair RM750", 10000),
            new CoreComponent("EVGA SuperNOVA 850", 12000)
        )));
        componentOptions.put("Case*", new ArrayList<>(Arrays.asList(
            new CoreComponent("Not Selected", 0),
            new CoreComponent("NZXT H510", 7000),
            new CoreComponent("Cooler Master MasterBox Q300L", 5000)
        )));
        componentOptions.put("Monitor", new ArrayList<>(Arrays.asList(
            new Peripheral("Not Selected", 0),
            new Peripheral("Dell UltraSharp 27\"", 25000),
            new Peripheral("ASUS TUF Gaming 24\"", 18000)
        )));
        componentOptions.put("Keyboard", new ArrayList<>(Arrays.asList(
            new Peripheral("Not Selected", 0),
            new Peripheral("Logitech G Pro", 8000),
            new Peripheral("Razer BlackWidow", 10000)
        )));
        componentOptions.put("Mouse", new ArrayList<>(Arrays.asList(
            new Peripheral("Not Selected", 0),
            new Peripheral("Logitech G502", 5000),
            new Peripheral("Razer DeathAdder", 4000)
        )));
        componentOptions.put("Cooling Fan", new ArrayList<>(Arrays.asList(
            new Accessory("Not Selected", 0),
            new Accessory("Noctua NH-D15", 8000),
            new Accessory("Corsair LL120", 3000)
        )));
        componentOptions.put("Cables", new ArrayList<>(Arrays.asList(
            new Accessory("Not Selected", 0),
            new Accessory("SATA Cable Pack", 500),
            new Accessory("HDMI Cable", 1000)
        )));
    }

    private void updateTotal() {
        int total = 0;
        for (JComboBox<PCComponent> comboBox : componentComboBoxes.values()) {
            PCComponent selected = (PCComponent) comboBox.getSelectedItem();
            if (selected != null) total += selected.getPrice();
        }
        totalLabel.setText("Total Amount: " + total + "৳");
    }

    private void savebuild() {
        StringBuilder build = new StringBuilder();
        boolean allRequiredSelected = true;

        for (String key : componentComboBoxes.keySet()) {
            PCComponent selected = (PCComponent) componentComboBoxes.get(key).getSelectedItem();
            if (selected != null) {
                if (key.contains("*") && selected.getName().equals("Not Selected")) {
                    allRequiredSelected = false;
                    break;
                }
                build.append(key.replace("*", "")).append(": ").append(selected).append("\n");
            }
        }

        if (!allRequiredSelected) {
            JOptionPane.showMessageDialog(this, "Please select all required components marked with *", "Incomplete Build", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BuildIO.saveBuild(build.toString());
        JOptionPane.showMessageDialog(this, "Build saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadBuild() {
        new SavedBuildsFrame(this);
    }

    private void clearAll() {
        for (JComboBox<PCComponent> comboBox : componentComboBoxes.values()) {
            comboBox.setSelectedIndex(0);
        }
        updateTotal();
    }

    public void loadBuild(String build) {
        String[] lines = build.split("\n");
        for (String line : lines) {
            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                String category = parts[0];
                String componentName = parts[1].substring(0, parts[1].indexOf(" ("));
                JComboBox<PCComponent> comboBox = componentComboBoxes.get(category + "*");
                if (comboBox == null) comboBox = componentComboBoxes.get(category);
                if (comboBox != null) {
                    for (int i = 0; i < comboBox.getItemCount(); i++) {
                        PCComponent item = comboBox.getItemAt(i);
                        if (item.getName().equals(componentName)) {
                            comboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        }
        updateTotal();
    }

    private void customizeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Consolas", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(120, 40));
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                backgroundImage = null;
            }
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}