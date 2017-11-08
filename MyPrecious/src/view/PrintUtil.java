package view;
import javax.swing.JOptionPane;

/**
 *
 * @author lhries
 */
public class PrintUtil {

    public static void mostrarMensagemErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.PLAIN_MESSAGE);
    }

    public static void mostrarMenssagemSucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, "SUCESSO", JOptionPane.PLAIN_MESSAGE);
    }
}

