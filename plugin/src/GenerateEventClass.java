import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 2016/5/4.
 */
public class GenerateEventClass extends AnAction {

    private List<String> results = new ArrayList<>();

    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        results.clear();
        Project project = e.getData(PlatformDataKeys.PROJECT);
        String text = Messages.showMultilineInputDialog(project, "Input event text", "Input event text", null, Messages.getQuestionIcon(), null);
        if (text == null || text.length() == 0) {
            error(project, "empty");
            return;
        }
        //create
        int lineCount = 0;
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new StringReader(text));
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length != 3) {
                    error(project, "\'" + line + "\'" + " format not right in " + lineCount);
                }
                results.add("public static final String " + split[0].toUpperCase() + " = " + "\"" + split[0] + "\";" + "//" + split[1]);
                if (lineCount == 0) {
                    stringBuilder.append(results.get(lineCount));
                } else {
                    stringBuilder.append("\n").append(results.get(lineCount));
                }
                lineCount ++;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            error(project, "invalid input");
        }

        Messages.showMultilineInputDialog(project, "result", "generate code", stringBuilder.toString(), Messages.getInformationIcon(), null);
    }

    private void error(Project project, String message) {
        Messages.showErrorDialog(project, message, "do not kidding me");
    }
}
