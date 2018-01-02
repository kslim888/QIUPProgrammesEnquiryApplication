package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Arrays;
import java.util.Objects;

@Rule(name = "DET", description = "Entry rule to join Diploma in Environmental Technology")
public class DET
{
    private static RuleAttribute detRuleAttribute;
    private int countPassScienceSubjects;
    private boolean isScienceStream;

    public DET() {
        detRuleAttribute = new RuleAttribute();
        countPassScienceSubjects = 0;
        isScienceStream = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        // first, check whether the student is science stream or not.
        // if the student is not taking general science, he is science stream
        if(!Arrays.asList(studentSubjects).contains("Science"))
        {
            isScienceStream = true;
        }

        //start validating condition
        if(Objects.equals(qualificationLevel, "SPM")) // if is STPM qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Science"))
                {
                    if(!Objects.equals(studentGrades[i], "G"))
                    {
                        countPassScienceSubjects++;
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    detRuleAttribute.incrementCountSPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is STPM qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Combined Science"))
                {
                    if(!Objects.equals(studentGrades[i], "U"))
                    {
                        countPassScienceSubjects++;
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D7")
                        && !Objects.equals(studentGrades[i], "E8")
                        && !Objects.equals(studentGrades[i], "F9")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    detRuleAttribute.incrementCountOLevel(1);
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
                    detRuleAttribute.incrementCountSTPM(1);
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
                    detRuleAttribute.incrementCountALevel(1);
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
                    detRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if (Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if (!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        countPassScienceSubjects++; // here count pass is for credit (at least B6)
                    }
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    detRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        if(isScienceStream)
        {
            if(detRuleAttribute.getCountSPM() >= 3
                    || detRuleAttribute.getCountOLevel() >= 3
                    || detRuleAttribute.getCountUEC() >= 3)
            {
                if(countPassScienceSubjects >= 2)
                {
                    return true;
                }
            }
        }
        else // is not science stream
        {
            if(detRuleAttribute.getCountSPM() >= 3
                    || detRuleAttribute.getCountOLevel() >= 3
                    || detRuleAttribute.getCountUEC() >= 3)
            {
                if(countPassScienceSubjects >= 1)
                {
                    return true;
                }
            }
            if(detRuleAttribute.getCountSTAM() >= 1
                    || detRuleAttribute.getCountSTPM() >= 1
                    || detRuleAttribute.getCountALevel() >= 1)
            {
                return true;
            }
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        detRuleAttribute.setJoinProgramme(true);
        Log.d("DiplEnvironmentalTech", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return detRuleAttribute.isJoinProgramme();
    }
}
