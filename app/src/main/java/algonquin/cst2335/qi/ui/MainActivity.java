package algonquin.cst2335.qi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import algonquin.cst2335.qi.R;

/** This class takes user input anc check if it is complex enough for a password.
 * @author Zhenzheng Qi
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /** This holds the text at the centre of the screen.*/
    private TextView tv = null;
    /** This holds the edit text below the text view.*/
    private EditText et = null;
    /** This holds the button at the bottom of the screen.*/
    private Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editPassword);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(click -> {
            String psw = et.getText().toString();
            if (checkPasswordComplexity(psw)){
                tv.setText("Your password meets the requirements");
            }else{
                tv.setText("You shall not pass!");
            }
        });
    }

    /**
     * Check the password complexity, if it contains at least one upper case letter, one lower case
     * letter, one digit, and one special character.
     * @param psw the password that user has input
     * @return if the password is complex enough
     */
    boolean checkPasswordComplexity(String psw){
        boolean isComplex = false;

        if (checkUpperCase(psw) && checkLowerCase(psw) &&
                checkNumber(psw) && checkSpecialCharacter(psw)){
            isComplex = true;
        }

        return isComplex;
    }

    /**
     * Check if the password contains at least one upper case letter
     * @param psw the password that user has input
     * @return if the password contains at least one upper case letter
     */
    boolean checkUpperCase(String psw) {
        boolean found = false;
        for (char c : psw.toCharArray()) {
            if (Character.isUpperCase(c)) {
                found = true;
                break;
            }
        }
        if (!found) {
            CharSequence text = "Password is missing an upper case letter.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
        }
        return found;
    }

    /**
     * Check if the password contains at least one lower case letter
     * @param psw the password that user has input
     * @return if the password contains at least one lower case letter
     */
    boolean checkLowerCase(String psw) {
        boolean found = false;
        for (char c : psw.toCharArray()) {
            if (Character.isLowerCase(c)) {
                found = true;
                break;
            }
        }
        if (!found) {
            CharSequence text = "Password is missing a lower case letter.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
        }
        return found;
    }

    /**
     * Check if the password contains at least one digit
     * @param psw the password that user has input
     * @return if the password contains at least one digit
     */
    boolean checkNumber(String psw) {
        boolean found = false;
        for (char c : psw.toCharArray()) {
            if (Character.isDigit(c)) {
                found = true;
                break;
            }
        }
        if (!found) {
            CharSequence text = "Password is missing a number.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
        }
        return found;
    }

    /**
     * Check if the password contains at least one special character
     * @param psw the password that user has input
     * @return if the password contains at least one special character
     */
    boolean checkSpecialCharacter(String psw) {
        boolean found = false;
        String pattern = "[~!@#$%^&*()_+{}\\[\\]:;,.<>/?-]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(psw);
        if(m.find()) {
            found = true;
        } else {
            CharSequence text = "Password is missing a special character.";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(this, text, duration).show();
        }
        return found;
    }
}