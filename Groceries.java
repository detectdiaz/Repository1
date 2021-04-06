// GroceriesFinal.java
//Ricardo Diaz
//CIS 113 Final

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Groceries extends JFrame implements ActionListener {

      /*

      * (storing contents of the frame in a file, so

      * that it can be used the next time program launches.)

      */

      private static final long serialVersionUID = 1L;

      // UI components

      JTextField input;

      JList output;

      JButton addBtn, clrBtn, remBtn;

      // this will hold and display grocery items

      private DefaultListModel model = new DefaultListModel();

      // Will initialize GUI

      public Groceries() {

            // using border layout as main layout

            setLayout(new BorderLayout());

            // creating a panel for the top section, adding label and input text

            // field and add button

            JPanel top = new JPanel();

            top.add(new JLabel("Item:", JLabel.CENTER));

            input = new JTextField(15);

            top.add(input);

            addBtn = new JButton("Add");

            // adding action listener to the button

            addBtn.addActionListener(this);

            top.add(addBtn);

            // adding top panel to page start

            add(top, BorderLayout.PAGE_START);

            // creating a panel for the middle, adding label and JList objects into

            // it

            JPanel middle = new JPanel();

            middle.add(new JLabel("List:", JLabel.CENTER));

            output = new JList(model);

            output.setPreferredSize(new Dimension(200, 100));

            middle.add(new JScrollPane(output));

            add(middle, BorderLayout.CENTER);

            // creating a panel for the bottom, adding clear and remove buttons

            JPanel bottom = new JPanel();

            clrBtn = new JButton("Clear");

            clrBtn.addActionListener(this);

            remBtn = new JButton("Remove");

            remBtn.addActionListener(this);

            bottom.add(clrBtn);

            bottom.add(remBtn);

            add(bottom, BorderLayout.PAGE_END);

            // loading data from file, if exists

            load();

            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setTitle("Groceries");

            pack();

            setVisible(true);

      }

      public static void main(String[] args) {

            new Groceries();

      }

      // private helper method to save data to binary file after each operation

      private void save() {

            try {

                  // opening file named groceries.dat and storing contents of model

                  FileOutputStream fos = new FileOutputStream(new File(

                              "groceries.dat"));

                  ObjectOutputStream ous = new ObjectOutputStream(fos);

                  ous.writeObject(model);

                  ous.close();

                  fos.close();

            } catch (IOException e) {

            }

      }

      // private helper method to load data into the program

      private void load() {

            try {

                  // opening groceries.dat and loading model from it

                  FileInputStream fos = new FileInputStream(new File("groceries.dat"));

                  ObjectInputStream ous = new ObjectInputStream(fos);

                  model = (DefaultListModel) ous.readObject();

                  output.setModel(model);

                  ous.close();

                  fos.close();

            } catch (Exception e) {

            }

      }

      @Override

      public void actionPerformed(ActionEvent ev) {

            // button clicks

            if (ev.getSource().equals(addBtn)) {

                  // getting text, adding to model if it is not empty

                  String item = input.getText();

                  if (item.length() == 0) {

                        JOptionPane.showMessageDialog(this, "Item cannot be blank");

                  } else {

                        model.addElement(item);

                  }

            } else if (ev.getSource().equals(remBtn)) {

                  // getting selected items,removing one by one if not empty

                  Object objects[] = output.getSelectedValues();

                  if (objects == null || objects.length == 0) {

                        JOptionPane.showMessageDialog(this, "No item is selected!");

                  } else {

                        for (Object ob : objects) {

                              model.removeElement(ob);

                        }

                  }

            } else {

                  //clearing text boxes

                  model.clear();

                  input.setText("");

            }

            //saving to file.

            save();

      }

}