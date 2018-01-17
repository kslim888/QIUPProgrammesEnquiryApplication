package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BCS", description = "Entry rule to join Bachelor of Computer Sciences")
public class BCS
{
    // Advanced math is additional maths
    private static RuleAttribute bcsRuleAttribute;

    public BCS() { bcsRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check got add math or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    bcsRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If STPM got add maths, check it is credit or not
            if(bcsRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                    {
                        if(!Objects.equals(studentGrades[i], "C-")
                                && !Objects.equals(studentGrades[i], "D+")
                                && !Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            bcsRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // if STPM got add math subject but not credit, or no add math subject at STPM
            // Check SPM / O-Level got maths subject is credit or not
            if(bcsRuleAttribute.isGotMathSubjectAndCredit())
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && Objects.equals(studentAddMathGrade, "E")
                            && Objects.equals(studentAddMathGrade, "G"))
                    {
                        bcsRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is o-level
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bcsRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all students subject check got above at least C+ or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bcsRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all students subject check got further mathematics or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    bcsRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If A-Level got further maths, check it is credit or not
            if(bcsRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            bcsRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // if A-level got add math subject but not credit, or no add math subject at A-level
            // Check SPM / O-Level got maths subject is credit or not
            if(!bcsRuleAttribute.isGotMathSubjectAndCredit())
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bcsRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is o-level
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bcsRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all student subject, check got minimum grade C
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    bcsRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // Check got advanced maths and is minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bcsRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    break;
                }
            }

            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    bcsRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        if(bcsRuleAttribute.getCountALevel() >= 2
                || bcsRuleAttribute.getCountSTPM() >= 2 || bcsRuleAttribute.getCountUEC() >= 5)
        {
            if(bcsRuleAttribute.isGotMathSubjectAndCredit())
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
        bcsRuleAttribute.setJoinProgramme(true);
        Log.d("BCSjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bcsRuleAttribute.isJoinProgramme();
    }
}
