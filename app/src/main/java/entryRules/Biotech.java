package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "Biotech", description = "Entry rule to join Bachelor of Science in Biotechnology")
public class Biotech
{
    // advanced math is additional maths
    private static RuleAttribute bioTechRuleAttribute;

    public Biotech() {
        bioTechRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        || !Objects.equals(studentGrades[i], "D+")
                        || !Objects.equals(studentGrades[i], "D")
                        || !Objects.equals(studentGrades[i], "F"))
                {
                    bioTechRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bioTechRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bioTechRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA, English Proficiency Test
            // FIXME Foundation / Matriculation, Diploma
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(bioTechRuleAttribute.getCountALevel() >= 2 || bioTechRuleAttribute.getCountSTPM() >= 2 || bioTechRuleAttribute.getCountUEC() >= 5)
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
        bioTechRuleAttribute.setJoinProgramme(true);
        Log.d("BioTechjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bioTechRuleAttribute.isJoinProgramme();
    }
}
