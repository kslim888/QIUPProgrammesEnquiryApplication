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
    // advanced math is additional maths
    private static RuleAttribute bcsRuleAttribute;
    private boolean advancedMathCredit, gotMathSubject, gotMathSubjectAndCredit;

    public BCS() {
        bcsRuleAttribute = new RuleAttribute();
        advancedMathCredit = false;
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check got add math or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    gotMathSubject = true;
                    break;
                }
            }

            if(gotMathSubject)
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
                            gotMathSubjectAndCredit = true;
                        }
                    }
                }
            }

            // if stpm got add math subject but not credit, or no add math subject at STPM
            if(!gotMathSubjectAndCredit)
            {
                // if the student din take add maths, straight return false
                if(Objects.equals(studentAddMathGrade, "None"))
                {
                    return false;
                }

                // if the student got take add maths, check is credit or not
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    // if maths no credit, straightaway return false
                    if(Objects.equals(studentAddMathGrade, "D") || Objects.equals(studentAddMathGrade, "E") || Objects.equals(studentAddMathGrade, "G"))
                    {
                        return false;
                    }
                }
                else // if is o-level
                {
                    // check maths got credit or not. if no credit return false
                    if(Objects.equals(studentAddMathGrade, "D7") || Objects.equals(studentAddMathGrade, "E8") || Objects.equals(studentAddMathGrade, "F9") || Objects.equals(studentAddMathGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            // for all students subject check got above at least C+ or not
            for(int i = 0; i < studentSubjects.length; i++)
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
            // for all students subject check got add mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    gotMathSubject = true;
                    break;
                }
            }

            if(gotMathSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "F"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                }
            }

            // if A-level got add math subject but not credit, or no add math subject at A-level
            if(!gotMathSubjectAndCredit)
            {
                // if the student din take add maths, straight return false
                if(Objects.equals(studentAddMathGrade, "None"))
                {
                    return false;
                }

                // check maths and english at spm or o-level
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    // if maths no credit, straightaway return false
                    if(Objects.equals(studentMathematicsGrade, "D") || Objects.equals(studentMathematicsGrade, "E") || Objects.equals(studentMathematicsGrade, "G"))
                    {
                        return false;
                    }
                }
                else // if is o-level
                {
                    // if math no credit return false
                    if(Objects.equals(studentMathematicsGrade, "D7") || Objects.equals(studentMathematicsGrade, "E8") || Objects.equals(studentMathematicsGrade, "F9") || Objects.equals(studentMathematicsGrade, "U"))
                    {
                        return false;
                    }
                }
            }

            // for all student subject, check got minimum grade D
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "F"))
                {
                    bcsRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // check got advanced maths and is fail or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8") || Objects.equals(studentGrades[i], "F9"))
                    {
                        return false;
                    }
                    else
                    {
                        advancedMathCredit = true;
                    }
                    break;
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bcsRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA, English Proficiency Test
            // FIXME Foundation / Matriculation, Diploma
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(bcsRuleAttribute.getCountUEC() >= 4)
        {
            if(advancedMathCredit)
            {
                return true;
            }
        }

        if(bcsRuleAttribute.getCountALevel() >= 2 || bcsRuleAttribute.getCountSTPM() >= 2)
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
        bcsRuleAttribute.setJoinProgramme(true);
        Log.d("BCSjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bcsRuleAttribute.isJoinProgramme();
    }
}
