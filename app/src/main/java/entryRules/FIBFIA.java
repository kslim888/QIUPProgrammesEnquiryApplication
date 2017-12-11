package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "FIB/FIA", description = "Entry rule to join Foundation in Business or Foundation in Arts")
public class FIBFIA
{
    static RuleAttribute fibfiaRuleAttribute;

    public FIBFIA() {
        fibfiaRuleAttribute = new RuleAttribute();
    }

    // when
    // dont check for programme name. because wan see all programme qualify or not
    @Condition
    public boolean allowToJoin(@Fact("Results Type") String resultsType, @Fact("Student's Subjects")String[] studentSubjects, @Fact("Student's Grades")String[] studentGrades)
    {
        for(int i = 0; i < studentSubjects.length; i++)
        {
            if(!Objects.equals(studentGrades[i], "D") && !Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "G"))
            {
                fibfiaRuleAttribute.incrementCountCredit(1);
            }
        }

        if(Objects.equals(resultsType, "SPM") || Objects.equals(resultsType, "O-Level"))
        {
            if(fibfiaRuleAttribute.getCountCredits() < 5)
            {
                return false;
            }
        }
        else if(Objects.equals(resultsType, "UEC"))
        {
            if(fibfiaRuleAttribute.getCountCredits() < 3)
            {
                return false;
            }
        }
        String abc = "" + fibfiaRuleAttribute.getCountCredits();
        Log.d("FIA number of creits", abc);

        return true;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        fibfiaRuleAttribute.setJoinProgramme(true);
        Log.d("FIBFIA joinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return fibfiaRuleAttribute.isJoinProgramme();
    }

}
