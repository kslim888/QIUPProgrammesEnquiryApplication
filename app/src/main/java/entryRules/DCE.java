package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DCE", description = "Entry rule to join Diploma in Early Childhood Education")
public class DCE
{
    private static RuleAttribute dceRuleAttribute;

    public DCE() {
        dceRuleAttribute = new RuleAttribute();
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
                    dceRuleAttribute.incrementCountSPM(1);
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
                    dceRuleAttribute.incrementCountOLevel(1);
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
                    dceRuleAttribute.incrementCountSTPM(1);
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
                    dceRuleAttribute.incrementCountALevel(1);
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
                    dceRuleAttribute.incrementCountSTAM(1);
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
                    dceRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        if(dceRuleAttribute.getCountSPM() >= 3
                || dceRuleAttribute.getCountOLevel() >= 3
                || dceRuleAttribute.getCountUEC() >= 3
                || dceRuleAttribute.getCountSTAM() >= 1
                || dceRuleAttribute.getCountSTPM() >= 1
                || dceRuleAttribute.getCountALevel() >= 1)
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
        dceRuleAttribute.setJoinProgramme(true);
        Log.d("DiplEarlyChildhood", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return dceRuleAttribute.isJoinProgramme();
    }
}
