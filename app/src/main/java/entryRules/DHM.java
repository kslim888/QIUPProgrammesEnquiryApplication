package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DHM", description = "Entry rule to join Diploma in Hotel Management")
public class DHM
{
    private static RuleAttribute dhmRuleAttribute;

    public DHM() {
        dhmRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "SPM")) // if is STPM qualification
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    dhmRuleAttribute.incrementCountSPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is STPM qualification
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D7")
                        && !Objects.equals(studentGrades[i], "E8")
                        && !Objects.equals(studentGrades[i], "F9")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dhmRuleAttribute.incrementCountOLevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    dhmRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is STPM qualification
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dhmRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    dhmRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    dhmRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        if(dhmRuleAttribute.getCountSPM() >= 3
                || dhmRuleAttribute.getCountOLevel() >= 3
                || dhmRuleAttribute.getCountUEC() >= 3
                || dhmRuleAttribute.getCountSTAM() >= 1
                || dhmRuleAttribute.getCountSTPM() >= 1
                || dhmRuleAttribute.getCountALevel() >= 1)
        {
                return true;
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        dhmRuleAttribute.setJoinProgramme(true);
        Log.d("DiplomaHotelManagement", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return dhmRuleAttribute.isJoinProgramme();
    }
}
