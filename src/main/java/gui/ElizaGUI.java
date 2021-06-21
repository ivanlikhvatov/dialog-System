package gui;

import org.apache.commons.codec.binary.StringUtils;
import org.jpl7.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ElizaGUI extends JFrame implements ActionListener {
    private List<String> messages;
    private JTextArea generalTextArea,
                        repliesInput;
    private Container container;

    private JPanel generalMessagePanel,
                    learnElizaPanel;

    private JButton getLearnElizaPanelButton,
                    sendMessageButton,
                    getGMPanelButton,
                    getCreateNewPredicatePanelButton,
                    getAddNewAnswerInPredicatePanelButton,
                    createNewPredicateButton,
                    changeOldPredicateButton;

    private JPanel inputPanel;
    private JTextField messageInput, keyWordInput;

    {
        container = this.getContentPane();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getGMPanelButton){
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(getGeneralMessagePanel(), BorderLayout.CENTER);
            container.revalidate();
        }

        if (e.getSource() == getLearnElizaPanelButton){
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(getLearningElizaPanel(), BorderLayout.CENTER);
            container.revalidate();
        }

        if (e.getSource() == getCreateNewPredicatePanelButton){
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(getCreateNewPredicatePanel(), BorderLayout.CENTER);
            container.revalidate();
        }

        if (e.getSource() == getAddNewAnswerInPredicatePanelButton){
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(getAddNewAnswerInPredicatePanel(), BorderLayout.CENTER);
            container.revalidate();
        }

        if (e.getSource() == getCreateNewPredicatePanelButton){
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(getCreateNewPredicatePanel(), BorderLayout.CENTER);
            container.revalidate();
        }

        if (e.getSource() == sendMessageButton){

            if (messageInput.getText() == null || "".equals(messageInput.getText())){
                return;
            }

            generalTextArea.append("[Вы]: " + messageInput.getText() + "\n");

            messages.add("[Вы]: " + messageInput.getText() + "\n");

            Variable X = new Variable("X");


            Query q4 =
                    new Query(
                            "eliza",
                            new Term[] {new Atom(messageInput.getText()), X}
                    );



            Map<String, Term> solutions = q4.oneSolution();
            generalTextArea.append("[Eliza]: " + solutions.values().toString().replaceAll("\\['", "").replaceAll("']", "") + "\n");

            messages.add("[Eliza]: " + solutions.values().toString().replaceAll("\\['", "").replaceAll("']", "")  + "\n");
            messageInput.setText("");
        }

        if (e.getSource() == createNewPredicateButton){

            if (keyWordInput.getText() == null || keyWordInput.getText().equals("")){
                return;
            }

            if (repliesInput.getText() == null || repliesInput.getText().equals("")){
                return;
            }


            String[] strings = repliesInput.getText().split("&");

            Term rowsTerm = Util.stringArrayToList(strings);

            Query q5 =
                    new Query(
                            "add_new_association",
                            new Term[] {new Atom(keyWordInput.getText()), rowsTerm}
                    );

            q5.allSolutions();

            keyWordInput.setText("");
            repliesInput.setText("");

            messages.add("[Eliza]: Прошу прощения, после обучения, я забыла реплики, которые уже использовала, извините, если буду повторяться\n");
        }

        if (e.getSource() == changeOldPredicateButton){
            if (keyWordInput.getText() == null || keyWordInput.getText().equals("")){
                return;
            }

            if (repliesInput.getText() == null || repliesInput.getText().equals("")){
                return;
            }


            String[] strings = repliesInput.getText().split("&");

            Term rowsTerm = Util.stringArrayToList(strings);

            Query q5 =
                    new Query(
                            "add_new_answers_for_exist_keyword",
                            new Term[] {new Atom(keyWordInput.getText()), rowsTerm}
                    );

            q5.allSolutions();

            keyWordInput.setText("");
            repliesInput.setText("");

            messages.add("[Eliza]: Прошу прощения, после обучения, я забыла реплики, которые уже использовала, извините, если буду повторяться\n");
        }
    }

    private ElizaGUI(){
        super("Чат с Eliza");
        this.setBounds(300,200,750,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        messages = new ArrayList<>();
        container.setLayout(new BorderLayout());
        container.add(getGeneralMessagePanel(), BorderLayout.CENTER);
    }

    private JPanel getCreateNewPredicatePanel(){
        JPanel personalPanel = new JPanel(new BorderLayout());
        JPanel springLayout = new JPanel(new SpringLayout());
        SpringLayout layout = new SpringLayout();
        springLayout.setLayout(layout);

        JLabel infoLabel = new JLabel("Реплики вводить через символ &");
        infoLabel.setFont(new Font("Meiryo", Font.PLAIN, 16));

        JLabel keyWordLabel = new JLabel("Ключевое слово: ");
        keyWordInput = new JTextField(20);
        keyWordInput.setBorder(BorderFactory.createEtchedBorder(1));

        JLabel repliesLabel = new JLabel("Реплики: ");
        repliesInput = new JTextArea(10, 42);
        repliesInput.setBorder(BorderFactory.createEtchedBorder(1));

        createNewPredicateButton = new JButton("Создать ассоциацию");
        createNewPredicateButton.addActionListener(this);


        personalPanel.add(getNavigationBar(), BorderLayout.NORTH);
        layout.putConstraint(SpringLayout.WEST , infoLabel, 260, SpringLayout.WEST , springLayout);
        layout.putConstraint(SpringLayout.NORTH, infoLabel, 15, SpringLayout.NORTH, springLayout);

        layout.putConstraint(SpringLayout.WEST , keyWordLabel, 10, SpringLayout.WEST , springLayout);
        layout.putConstraint(SpringLayout.NORTH, keyWordLabel, 56, SpringLayout.NORTH, springLayout);
        layout.putConstraint(SpringLayout.NORTH, keyWordInput, 53, SpringLayout.NORTH, springLayout);//25
        layout.putConstraint(SpringLayout.WEST , keyWordInput, 25, SpringLayout.EAST , keyWordLabel);

        layout.putConstraint(SpringLayout.WEST , repliesLabel, 10, SpringLayout.WEST , springLayout);
        layout.putConstraint(SpringLayout.NORTH, repliesLabel, 84, SpringLayout.NORTH, springLayout);
        layout.putConstraint(SpringLayout.NORTH, repliesInput, 81, SpringLayout.NORTH, springLayout);//53
        layout.putConstraint(SpringLayout.WEST , repliesInput, 78, SpringLayout.EAST , repliesLabel);

        layout.putConstraint(SpringLayout.NORTH, createNewPredicateButton, 250, SpringLayout.NORTH, springLayout);

        springLayout.add(createNewPredicateButton);
        springLayout.add(infoLabel);
        springLayout.add(repliesLabel);
        springLayout.add(repliesInput);
        springLayout.add(keyWordLabel);
        springLayout.add(keyWordInput);
        personalPanel.add(springLayout, BorderLayout.CENTER);

        return personalPanel;
    }

    private JPanel getAddNewAnswerInPredicatePanel(){
        JPanel personalPanel = new JPanel(new BorderLayout());
        JPanel springLayout = new JPanel(new SpringLayout());
        SpringLayout layout = new SpringLayout();
        springLayout.setLayout(layout);

        JLabel infoLabel = new JLabel("Реплики вводить через символ &");
        infoLabel.setFont(new Font("Meiryo", Font.PLAIN, 16));

        JLabel keyWordLabel = new JLabel("Ключевое слово: ");
        keyWordInput = new JTextField(20);
        keyWordInput.setBorder(BorderFactory.createEtchedBorder(1));

        JLabel repliesLabel = new JLabel("Реплики: ");
        repliesInput = new JTextArea(10, 42);
        repliesInput.setBorder(BorderFactory.createEtchedBorder(1));

        changeOldPredicateButton = new JButton("Добавить ответы к ключевому слову");
        changeOldPredicateButton.addActionListener(this);


        personalPanel.add(getNavigationBar(), BorderLayout.NORTH);
        layout.putConstraint(SpringLayout.WEST , infoLabel, 260, SpringLayout.WEST , springLayout);
        layout.putConstraint(SpringLayout.NORTH, infoLabel, 15, SpringLayout.NORTH, springLayout);

        layout.putConstraint(SpringLayout.WEST , keyWordLabel, 10, SpringLayout.WEST , springLayout);
        layout.putConstraint(SpringLayout.NORTH, keyWordLabel, 56, SpringLayout.NORTH, springLayout);
        layout.putConstraint(SpringLayout.NORTH, keyWordInput, 53, SpringLayout.NORTH, springLayout);//25
        layout.putConstraint(SpringLayout.WEST , keyWordInput, 25, SpringLayout.EAST , keyWordLabel);

        layout.putConstraint(SpringLayout.WEST , repliesLabel, 10, SpringLayout.WEST , springLayout);
        layout.putConstraint(SpringLayout.NORTH, repliesLabel, 84, SpringLayout.NORTH, springLayout);
        layout.putConstraint(SpringLayout.NORTH, repliesInput, 81, SpringLayout.NORTH, springLayout);//53
        layout.putConstraint(SpringLayout.WEST , repliesInput, 78, SpringLayout.EAST , repliesLabel);

        layout.putConstraint(SpringLayout.NORTH, changeOldPredicateButton, 250, SpringLayout.NORTH, springLayout);

        springLayout.add(changeOldPredicateButton);
        springLayout.add(infoLabel);
        springLayout.add(repliesLabel);
        springLayout.add(repliesInput);
        springLayout.add(keyWordLabel);
        springLayout.add(keyWordInput);
        personalPanel.add(springLayout, BorderLayout.CENTER);

        return personalPanel;
    }

    private JPanel getGeneralMessagePanel(){
        generalMessagePanel = new JPanel(new BorderLayout());

        sendMessageButton = new JButton("отправить");
        sendMessageButton.setMargin(new Insets(5, 10, 5, 10));
        sendMessageButton.addActionListener(this);

        generalMessagePanel.add(getNavigationBar(), BorderLayout.NORTH);
        generalMessagePanel.add(getInputPanel(), BorderLayout.SOUTH);
        inputPanel.add(sendMessageButton, BorderLayout.WEST);
        generalMessagePanel.add(getGeneralTextPanel(), BorderLayout.CENTER);

        return generalMessagePanel;
    }

    private JPanel getLearningElizaPanel(){
        JPanel gridPanel = new JPanel(new GridLayout(1, 2, 5, 0));

        JLabel label = new JLabel("Выберите действие");
        getCreateNewPredicatePanelButton = new JButton("Создать новую ассоциацию ");
        getCreateNewPredicatePanelButton.addActionListener(this);

        getAddNewAnswerInPredicatePanelButton = new JButton("Добавить отклики в существующую ассоциацию ");
        getAddNewAnswerInPredicatePanelButton.addActionListener(this);

        gridPanel.add(getCreateNewPredicatePanelButton);
        gridPanel.add(getAddNewAnswerInPredicatePanelButton);


        learnElizaPanel = new JPanel(new BorderLayout());


        JPanel panelWithChoiseButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 70));
        panelWithChoiseButton.add(label);
        panelWithChoiseButton.add(gridPanel);

        learnElizaPanel.add(getNavigationBar(),  BorderLayout.NORTH);
        learnElizaPanel.add(panelWithChoiseButton);



        return learnElizaPanel;
    }

    private JPanel getGeneralTextPanel(){
        String welcome = "[Eliza] : " + "Здравствуйте. Меня зовут ЭЛИЗА. Я бы хотела поговорить с вами тему, которая касается каждого из нас. " +
                "А именно, о том какие процессы влияют на формирование личности. Я считаю что на формирование личности влияет огромное количество факторов, начиная от генов родителей" +
                " и семьи и заканчивая окружением. А уж какой из этих факторов окажет преимущественное воздействие на личность, вопрос риторический и " +
                "однозначного ответа по моему мнению не имеет. А как вы считаете, какие факторы и процессы формируют человека как личность?\n";
        generalTextArea = new JTextArea(welcome, 16, 42);
        generalTextArea.setMargin(new Insets(10, 10, 10, 10));
        generalTextArea.setLineWrap(true);
        generalTextArea.setWrapStyleWord(true);
        generalTextArea.setEditable(false);

        for (int i = 0; i < messages.size(); i++){
            generalTextArea.append(messages.get(i));
        }

        JScrollPane scrollPane = new JScrollPane(generalTextArea);
        JPanel textPanel = new JPanel();
        textPanel.add(scrollPane);
        textPanel.setFont(new Font("Meiryo", Font.PLAIN, 14));
        return textPanel;
    }


    private JPanel getInputPanel(){
        inputPanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        inputPanel.add(messageInput, BorderLayout.CENTER);
        inputPanel.setBorder(new EmptyBorder(10, 8, 10, 8));
        return inputPanel;
    }


    private JPanel getNavigationBar(){
        getGMPanelButton = new JButton("диалог");
        getGMPanelButton.addActionListener(this);

        getLearnElizaPanelButton = new JButton("учить Eliza");
        getLearnElizaPanelButton.addActionListener(this);

        JPanel leftButtonPanel = new JPanel(new GridLayout(1, 2, 28, 0));
        leftButtonPanel.add(getGMPanelButton);
        JPanel flowLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowLeft.add(leftButtonPanel);
        flowLeft.setBorder(new EmptyBorder(5, 0, -5, 0));

        JPanel rightButtonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        rightButtonPanel.add(getLearnElizaPanelButton);
        JPanel flowRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flowRight.add(rightButtonPanel);
        flowRight.setBorder(new EmptyBorder(5, 0, -5, 0));

        JPanel flowLeftAndRight = new JPanel(new GridLayout(1, 2, 5, 0));
        flowLeftAndRight.add(flowLeft);
        flowLeftAndRight.add(flowRight);

        return flowLeftAndRight;
    }

    public static void main(String[] args){
        ElizaGUI app = new ElizaGUI();
        app.setVisible(true);

        Query q1 =
                new Query(
                        "consult",
                        new Term[] {new Atom("/Users/ivanlikhvatov/University/ThirdCourse/SecondSemestr/ErmakovFaiphel/projects/dialog-system/src/main/resources/prolog/test.pl")}
                );

        System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
    }
}
