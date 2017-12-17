package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BBA", description = "Entry rule to join Bachelor of Business Administration")
public class BBA
{
    private static RuleAttribute bbaRuleAttribute;
    private int countAdvancedMathFail;
    public BBA() {
        bbaRuleAttribute = new RuleAttribute();
        countAdvancedMathFail = 0;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Results Type") String resultsType,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's English") String studentEnglishGrade)
    {
        if(Objects.equals(resultsType, "STPM"))
        {
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(Objects.equals(studentMathematicsGrade, "G") || Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                if(Objects.equals(studentMathematicsGrade, "D7") || Objects.equals(studentEnglishGrade, "E8")
                        || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }
            }

            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bbaRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(resultsType, "STAM"))
        {
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(Objects.equals(studentMathematicsGrade, "G") || Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                if(Objects.equals(studentMathematicsGrade, "D7") || Objects.equals(studentEnglishGrade, "E8")
                        || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }
            }

            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bbaRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(resultsType, "A-Level"))
        {
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(Objects.equals(studentMathematicsGrade, "G") || Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                if(Objects.equals(studentMathematicsGrade, "D7") || Objects.equals(studentEnglishGrade, "E8")
                        || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }
            }

            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentSubjects[i], "Mathematics") && !Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "F"))
                    {
                        bbaRuleAttribute.incrementCountALevel(1);
                    }
                }
            }
        }
        else if(Objects.equals(resultsType, "UEC"))
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Advanced Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "F9"))
                    {
                        countAdvancedMathFail++;
                    }
                }

                if(countAdvancedMathFail == 1)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(Objects.equals(studentGrades[i], "F9"))
                        {
                            return false;
                        }
                    }
                }

                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(Objects.equals(studentGrades[i], "F9"))
                    {
                        return false;
                    }
                }

                if(!Objects.equals(studentSubjects[i], "Mathematics") && !Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "B6") && !Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bbaRuleAttribute.incrementCountUEC(1);
                    }
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // minimum CGPA of 2.00 out of 4.00
        }

        if(bbaRuleAttribute.getCountALevel() >= 2 ||
                bbaRuleAttribute.getCountSTAM() >= 1 ||
                bbaRuleAttribute.getCountSTPM() >= 2 ||
                bbaRuleAttribute.getCountUEC() >= 5)
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
        bbaRuleAttribute.setJoinProgramme(true);
        Log.d("BBA joinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bbaRuleAttribute.isJoinProgramme();
    }
}
