package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BFI", description = "Entry rule to join Bachelor of Finance")
public class BFI
{
    private static RuleAttribute bfiRuleAttribute;
    private boolean advancedMathFail, gotAdvancedMath; // advanced maths = additional maths
    public BFI() {
        bfiRuleAttribute = new RuleAttribute();
        advancedMathFail = false;
        gotAdvancedMath = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's English") String studentEnglishGrade)
    {
        if (Objects.equals(qualificationLevel, "STPM")) // if qualification is STPM
        {
            // check maths and english
            if (Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                // mathematics
                if (Objects.equals(studentMathematicsGrade, "D") || Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G") )
                {
                    return false;
                }
                // english
                if(Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                // mathematics
                if (Objects.equals(studentMathematicsGrade, "D7")
                        || Objects.equals(studentMathematicsGrade, "E8")
                        || Objects.equals(studentMathematicsGrade, "F9")
                        || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }

                // english
                if (Objects.equals(studentEnglishGrade, "E8")
                        || Objects.equals(studentEnglishGrade, "F9")
                        || Objects.equals(studentEnglishGrade, "U"))
                {
                    return false;
                }
            }

            // minimum C+ only increment
            for (int i = 0; i < studentSubjects.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "C")
                        && !Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F")) {
                    bfiRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "STAM")) // if is STAM
        {
            // check maths and english
            if (Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                // mathematics
                if (Objects.equals(studentMathematicsGrade, "D") ||Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G") )
                {
                    return false;
                }
                // english
                if(Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                // mathematics
                if (Objects.equals(studentMathematicsGrade, "D7")
                        || Objects.equals(studentMathematicsGrade, "E8")
                        || Objects.equals(studentMathematicsGrade, "F9")
                        || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }

                //english
                if (Objects.equals(studentEnglishGrade, "E8")
                        || Objects.equals(studentEnglishGrade, "F9")
                        || Objects.equals(studentEnglishGrade, "U"))
                {
                    return false;
                }
            }

            // minimum Jayyid only increment
            for (int i = 0; i < studentSubjects.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bfiRuleAttribute.incrementCountSTAM(1);
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "A-Level")) // if is A-Level
        {
            // check math and english
            if (Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                // mathematics
                if (Objects.equals(studentMathematicsGrade, "D") || Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G") )
                {
                    return false;
                }
                //english
                if(Objects.equals(studentEnglishGrade, "G"))
                {
                    return false;
                }
            }
            else // is o-level
            {
                // mathematics
                if (Objects.equals(studentMathematicsGrade, "D7")
                        || Objects.equals(studentMathematicsGrade, "E8")
                        || Objects.equals(studentMathematicsGrade, "F9")
                        || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }

                //english
                if (Objects.equals(studentEnglishGrade, "E8")
                        || Objects.equals(studentEnglishGrade, "F9")
                        || Objects.equals(studentEnglishGrade, "U"))
                {
                    return false;
                }
            }

            //at least grade D only increment
            for (int i = 0; i < studentSubjects.length; i++)
            {
                if (!Objects.equals(studentGrades[i], "F"))
                {
                    bfiRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "UEC")) // if is UEC
        {
            for (int i = 0; i < studentSubjects.length; i++) // for all student subjects
            {
                // if got advanced maths, set true
                if (Objects.equals(studentSubjects[i], "Advanced Mathematics"))
                {
                    gotAdvancedMath = true; // if got, set true
                    if (Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
                    {
                        advancedMathFail = true; //  here fail means no credit
                    }
                }

                // if got advanced maths and is not credit, check maths got credit or not
                if(gotAdvancedMath)
                {
                    if(advancedMathFail)
                    {
                        // if maths also no credit, return false
                        if(Objects.equals(studentSubjects[i], "Mathematics"))
                        {
                            if(Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
                            {
                                return false;
                            }
                        }
                    }
                }
                else // if the person no advanced maths
                {
                    // if maths no credit, return false
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
                        {
                            return false;
                        }
                    }
                }

                // if english is fail, straightaway return false
                if (Objects.equals(studentSubjects[i], "English"))
                {
                    if (Objects.equals(studentGrades[i], "F9"))
                    {
                        return false;
                    }
                }

                // except maths and english, check other subject is at least grade B or not. if is then increment
                if (!Objects.equals(studentSubjects[i], "Mathematics") && !Objects.equals(studentSubjects[i], "English"))
                {
                    if (!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bfiRuleAttribute.incrementCountUEC(1);
                    }
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA of 2.00 out of 4.00
            // FIXME Foundation / Matriculation
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if (bfiRuleAttribute.getCountALevel() >= 2 ||
                bfiRuleAttribute.getCountSTAM() >= 1 ||
                bfiRuleAttribute.getCountSTPM() >= 2 ||
                bfiRuleAttribute.getCountUEC() >= 5)
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
        bfiRuleAttribute.setJoinProgramme(true);
        Log.d("BFIjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bfiRuleAttribute.isJoinProgramme();
    }
}
