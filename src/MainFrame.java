
// Импортируются классы, используемые в приложении 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма 
public class MainFrame extends JFrame {

    // Размеры окна приложения в виде констант
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 320;

    // Текстовые поля для считывания значений переменных,
    // как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JTextField textFieldM1;
    private JTextField textFieldM2;
    private JTextField textFieldM3;
    //private JLabel label;
    private JPanel formula = new JPanel();
    // Текстовое поле для отображения результата,
    // как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup memoryButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemory = Box.createHorizontalBox();
    private int formulaId = 1;
    private int memoryId = 1;
    private Double mem1=0.0,mem2=0.0,mem3=0.0;
    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.log(Math.pow(1+x,2))+Math.cos(Math.PI*Math.pow(z,3)),Math.sin(y))
                +(Math.pow(Math.E,Math.pow(x,2))+Math.cos(Math.pow(Math.E,z))+Math.sqrt(1/y));
    }

    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.PI*Math.pow(x,3))+Math.pow(Math.log(1+y),2),1/4)
        *(Math.pow(Math.E,Math.pow(z,2))+Math.sqrt(1/x)+Math.cos(Math.pow(Math.E,y)));
    }
    BufferedImage myPicture;
    ImageIcon icon;
    JLabel label=new JLabel(icon);
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                MainFrame.this.formulaId = formulaId;


                    if(formulaId==1)
                        icon=new ImageIcon("C:\\Users\\Stas\\Desktop\\Java\\Lab2\\src\\formula1.png");
                    else
                        icon=new ImageIcon("C:\\Users\\Stas\\Desktop\\Java\\Lab2\\src\\formula2.png");
                //label=new JLabel(icon);
                label.setIcon(icon);
                formula.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addMemoryButton(String buttonName, final int memoryId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        memoryButtons.add(button);
        hboxMemory.add(button);
    }
    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));



        icon=new ImageIcon("C:\\Users\\Stas\\Desktop\\Java\\Lab2\\src\\formula1.png");
        label.setIcon(icon);
        add(label);
        formula = new JPanel(new BorderLayout());
        formula.add(label, BorderLayout.CENTER );
        Box hboxFormula = Box.createHorizontalBox();
        hboxFormula.add(Box.createHorizontalGlue());
        hboxFormula.add(formula);
        formula.updateUI();

        hboxMemory.add(Box.createHorizontalGlue());
        addMemoryButton("Переменная 1", 1);
        addMemoryButton("Переменная 2", 2);
        addMemoryButton("Переменная 3", 3);
        memoryButtons.setSelected(
                memoryButtons.getElements().nextElement().getModel(), true);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));

        // Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForM1 = new JLabel("M1:");
        textFieldM1 = new JTextField("0", 10);
        textFieldM1.setMaximumSize(textFieldM1.getPreferredSize());
        JLabel labelForM2 = new JLabel("M2:");
        textFieldM2 = new JTextField("0", 10);
        textFieldM2.setMaximumSize(textFieldM2.getPreferredSize());
        JLabel labelForM3 = new JLabel("M3:");
        textFieldM3 = new JTextField("0", 10);
        textFieldM3.setMaximumSize(textFieldM3.getPreferredSize());

        Box hboxMemoryValues = Box.createHorizontalBox();
        hboxMemoryValues.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxMemoryValues.add(Box.createHorizontalGlue());
        hboxMemoryValues.add(labelForM1);
        hboxMemoryValues.add(Box.createHorizontalStrut(10));
        hboxMemoryValues.add(textFieldM1);
        hboxMemoryValues.add(Box.createHorizontalStrut(10));
        hboxMemoryValues.add(labelForM2);
        hboxMemoryValues.add(Box.createHorizontalStrut(10));
        hboxMemoryValues.add(textFieldM2);
        hboxMemoryValues.add(Box.createHorizontalStrut(10));
        hboxMemoryValues.add(labelForM3);
        hboxMemoryValues.add(Box.createHorizontalStrut(10));
        hboxMemoryValues.add(textFieldM3);
        hboxMemoryValues.add(Box.createHorizontalGlue());

        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        //labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        // Создать область для кнопок
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {
                    if (memoryId==1)
                        mem1=0.0;
                    else if (memoryId==2)
                        mem2=0.0;
                    else
                        mem3=0.0;
                textFieldM1.setText(mem1.toString());
                textFieldM2.setText(mem2.toString());
                textFieldM3.setText(mem3.toString());
            }
        });
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                double result = Double.parseDouble(textFieldResult.getText()); ;
                if (memoryId==1)
                    mem1+=result;
                else if (memoryId==2)
                    mem2+=result;
                else
                    mem3+=result;
                textFieldM1.setText(mem1.toString());
                textFieldM2.setText(mem2.toString());
                textFieldM3.setText(mem3.toString());
            }
        });
        Box hboxMemoryButtons = Box.createHorizontalBox();
        hboxMemoryButtons.add(Box.createHorizontalGlue());
        hboxMemoryButtons.add(buttonMC);
        hboxMemoryButtons.add(Box.createHorizontalStrut(30));
        hboxMemoryButtons.add(buttonMPlus);
        hboxMemoryButtons.add(Box.createHorizontalGlue());
        hboxMemoryButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //formula.updateUI();
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldY.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");

            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxFormula);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxMemory);
        contentBox.add(hboxMemoryValues);
        contentBox.add(hboxMemoryButtons);

        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}