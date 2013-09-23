/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minitestes;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Rummenigge
 */
public class NewClass implements Serializable {

    private String nome;

    public NewClass() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public static void main(String[] asd) {
        JButton left = new JButton();
        JButton right = new JButton();
        
        Action al = new AbstractAction("<<") {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Esquerda");
            }
        };
        
        Action ar = new AbstractAction(">>") {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Direita");
            }
        };
        
        left.setAction(al);
        right.setAction(ar);
        
        left.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "pressed_left");
        left.getActionMap().put("pressed_left", al);
        
        right.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "pressed_right");
        right.getActionMap().put("pressed_right", ar);
        
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        
        Panel p = new Panel();
        p.add(left);
        p.add(right);
        
        f.setContentPane(p);
        
        f.setVisible(true);
    }
}
