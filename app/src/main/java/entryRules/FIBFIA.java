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
    private static RuleAttribute fibfiaRuleAttribute;
    public FIBFIA() {
        fibfiaRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel, @Fact("Student's Subjects")String[] studentSubjects, @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "SPM"))
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D") && !Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "G"))
                    fibfiaRuleAttribute.incrementCountCredit(1);
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level"))
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D7") && !Objects.equals(studentGrades[i], "E8") && !Objects.equals(studentGrades[i], "F9") && !Objects.equals(studentGrades[i], "U"))
                    fibfiaRuleAttribute.incrementCountCredit(1);
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC"))// is UEC
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    fibfiaRuleAttribute.incrementCountCredit(1);
            }
        }

        if(!Objects.equals(qualificationLevel, "UEC")) // is SPM or O-Level
        {
            if(fibfiaRuleAttribute.getCountCredits() < 5)
                return false;
        }
        else // is UEC
        {
            if(fibfiaRuleAttribute.getCountCredits() < 3)
                return false;
        }

        Log.d("FIA number of creits", "" + fibfiaRuleAttribute.getCountCredits());
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
