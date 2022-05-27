import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.Dimension;
import java.util.ArrayList;

// make fix scroll bar
// finish artifacts RAYMOND (make sure they don't duplicate)

public class Controller implements ActionListener
{
    private JFrame frame;
    private JTextArea infoLabel;
    private JTextField choiceEntryField;
    private CharactersAPI characterClient;
    private WeaponsAPI weaponClient;
    private ArtifactsAPI artifactClient;
    private JPanel infoPanel = new JPanel();

    public Controller()
    {
        frame = new JFrame("Genshin Impact Database");
        frame.setSize(50, 50);
        infoLabel = new JTextArea();
        infoLabel.setColumns(40);
        infoLabel.setEditable(false);
        infoLabel.setOpaque(false);
        infoPanel = new JPanel();
        choiceEntryField = new JTextField();
        characterClient = new CharactersAPI();
        weaponClient = new WeaponsAPI();
        artifactClient = new ArtifactsAPI();

        setUpGui();
    }

    public void setUpGui()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //--------------

        JLabel titleLabel = new JLabel("Impact.moe");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        JPanel logoWelcomePanel = new JPanel();
        logoWelcomePanel.add(titleLabel);

        JScrollPane scroll = new JScrollPane(infoLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //--------------

        infoLabel.setText("Select an option:\n1. Characters\n2. Weapons\n3. Artifacts\n");
        infoLabel.setWrapStyleWord(true);
        infoLabel.setLineWrap(true);
        infoLabel.setFont(new Font ("Helvetica", Font.BOLD, 15));


        infoPanel.add(scroll);


        //--------------
        JLabel choiceLabel = new JLabel("Enter your choice: ");
        choiceEntryField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");

        JPanel entryPanel = new JPanel();
        entryPanel.add(choiceLabel);
        entryPanel.add(choiceEntryField);
        entryPanel.add(submitButton);
        entryPanel.add(resetButton);

        //--------------

        frame.add(logoWelcomePanel, BorderLayout.NORTH);
        frame.add(infoPanel, BorderLayout.CENTER);
        frame.add(entryPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(this);
        resetButton.addActionListener(this);

        //--------------



        //--------------

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton) (e.getSource());
        String choice = choiceEntryField.getText();
        String text = button.getText();

        if (text.equals("Submit"))
        {
            if (choice.equals("1")) // Characters
            {
                String printedList = "";
                int number = 1;
                for (String names : characterClient.characterList())
                {
                    if (number % 3 == 0)
                    {
                        printedList += names + "\n";
                    }
                    else
                    {
                        printedList += names + "  |  ";
                    }
                    number++;
                }

                infoLabel.setText(printedList);
            }
            else if (choice.equals("2"))
            {
                String weaponsList = "";
                int number = 1;
                for (String names : weaponClient.weaponList())
                {
                    if (number % 3 == 0)
                    {
                        weaponsList += names + "\n";
                    }
                    else
                    {
                        weaponsList += names + " | ";
                    }
                    number++;
                }

                infoLabel.setText(weaponsList);
            }
            else if (choice.equals("3")) // Artifacts
            {
                String artifactsList = "";
                int number = 1;
                for (String names : artifactClient.artifactList())
                {
                    if (number % 4 == 0)
                    {
                        artifactsList += names + "\n";
                    }
                    else
                    {
                        artifactsList += names + " | ";
                    }
                    number++;
                }

                infoLabel.setText(artifactsList);
            }
            else
            {
                if (characterClient.characterList().contains(choice)) //add artifactsList and weaponsList when it gets implemented
                {
                    Characters character = characterClient.getCharacter(choice);
                    String str = "Name: " + character.getName() +
                            "\nBirthday: " + character.getBirthday() +
                            "\nRegion: " + character.getRegion() +
                            "\nDescription: " + character.getDescription() +
                            "\n" +
                            "\nRarity: " + character.getRarity() +
                            "\nWeapon: " + character.getWeapon() +
                            "\nElement: " + character.getElement() +
                            "\nConstellation: " + character.getConstellation();
                    infoLabel.setText(str);

                    //implement later
					/*
					try
					{
            URL imageURL = new URL(character.getImageURL());
            BufferedImage image = ImageIO.read(imageURL);
            JFrame frame = new JFrame(character.getName());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel characterImage = new JLabel(new ImageIcon(image));
            frame.add(characterImage);
            frame.pack();
            frame.setVisible(true);
	        }
					catch (IOException i)
					{
	          System.out.println(i.getMessage());
	        }
					*/
                }
                else if (weaponClient.weaponList().contains(choice))
                {
                    Weapons weapon = weaponClient.getWeapon(choice);
                    String str = "Name: " + weapon.getName() +
                            "\nType: " + weapon.getType() +
                            "\nRarity: " + weapon.getRarity() +
                            "\nBase ATK: " + weapon.getBaseAtk() +
                            "\nSubstat Type: " + weapon.getSubStatType() +
                            "\nSubstat: " + weapon.getSubStat() +
                            "\nDescription: " + weapon.getDescription() +
                            "\nAbility Name: " + weapon.getAbilityName() +
                            "\nAbility Description: " + weapon.getAbilityDescription() +
                            "\nLocation: " + weapon.getLocation();
                    infoLabel.setText(str);
                    //implement later
					/*
				try
					{
            URL imageURL = new URL(weapon.getImageURL());
            BufferedImage image = ImageIO.read(imageURL);
            JFrame frame = new JFrame(weapon.getName());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel weaponImage = new JLabel(new ImageIcon(image));
            frame.add(weaponImage);
            frame.pack();
            frame.setVisible(true);
	        }
					catch (IOException i)
					{
	          System.out.println(i.getMessage());
	        }
					*/
                }
                else if (artifactClient.artifactList().contains(choice)) // need to fix getArtifact
                {
                    Artifacts artifact = artifactClient.getArtifact(choice);
                    String str = "Name: " + artifact.getName() +
                            "\nMax Rarity: " + artifact.getMaxRarity() +
                            "\nTwo-piece Bonus: " + artifact.getTwoPiece() +
                            "\nFour-piece Bonus: " + artifact.getFourPiece();

                    infoLabel.setText(str);

                    ImageIcon image = new ImageIcon("src/Artifact Sets/" + choice + ".png");
                    Image imageData = image.getImage();
                    Image scaledImage = imageData.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
                    image = new ImageIcon(scaledImage);
                    JFrame frame = new JFrame(artifact.getName());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JLabel artifactImage = new JLabel(image);
                    frame.add(artifactImage);
                    frame.pack();
                    frame.setVisible(true);

                    //image (implement later)
					/*
					try
					{
            URL imageURL = new URL(artifact.getImageURL());
            BufferedImage image = ImageIO.read(imageURL);
            JFrame frame = new JFrame(artifact.getName());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel artifactImage = new JLabel(new ImageIcon(image));
            frame.add(artifactImage);
            frame.pack();
            frame.setVisible(true);
	        }
					catch (IOException i)
					{
	          System.out.println(i.getMessage());
	        }
					*/
                }
                else
                {
                    choiceEntryField.setText("That is not a valid option");
                }
            }
        }
        else if (text.equals("Reset"))
        {
            infoLabel.setText("Select an option:\n1. Characters\n2. Weapons\n3. Artifacts\n");
            choiceEntryField.setText("");
        }
    }
}